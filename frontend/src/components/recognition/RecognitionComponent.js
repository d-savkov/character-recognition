import React from 'react';
import RecognitionService from '../../services/RecognitionService';
import CardComponent from '../card/CardComponent';
import {Link} from 'react-router-dom';

class RecognitionComponent extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			selectedFile: null, image: null, personsDto: [],
		};
	}

	onFileChange = event => {
		this.setState({personsDto: [], selectedFile: event.target.files[0]});
		if (event.target.files && event.target.files[0]) {
			let reader = new FileReader();
			reader.onload = (e) => {
				this.setState({image: e.target.result});
			};
			reader.readAsDataURL(event.target.files[0]);
		}
	};

	onFind = () => {
		this.setState({personsDto: []});
		RecognitionService.findMostSimilar(this.formData()).then((response) => {
			console.log(response.data);
			this.setState({personsDto: response.data});
		});
	};

	onShowAll = () => {
		this.setState({personsDto: []});
		RecognitionService.getAll(this.formData()).then((response) => {
			console.log(response.data);
			this.setState({personsDto: response.data});
		});
	};

	onRemove = () => {
		this.setState({image: null, selectedFile: null, personDto: []});
	};

	formData = () => {
		const formData = new FormData();
		formData.append('file', this.state.selectedFile);
		return formData;
	};

	fileData = () => {
		return (<div className="recognition-image-container">
			{this.state.image ? <img src={this.state.image} alt={this.state.image.name}/> : <div/>}
		</div>);
	};

	noImageComponent = () => {
		return (<div className="image-input">
			<div className="form-group">
				<label className="label">
					<i className="bi bi-paperclip"/>
					<span className="title">Загрузить фото</span>
					<input type="file" accept="image/jpg, image/jpeg" onChange={this.onFileChange}/>
				</label>
			</div>
		</div>);
	};

	yesImageComponent = () => {
		return (<div>
			{this.fileData()}
			<div className="recognition-button-bar">
				<button type="button" className="btn btn-outline-success btn-lg" onClick={this.onFind}>
					<label>Find</label>
					<i className="bi bi-search"/>
				</button>
				<button type="button" className="btn btn-outline-success btn-lg" onClick={this.onShowAll}>
					<label>Show all</label>
					<i className="bi bi-collection"/>
				</button>
				<button type="button" className="btn btn-outline-danger btn-lg" onClick={this.onRemove}>
					<label>Remove</label>
					<i className="bi bi-trash3"/>
				</button>
			</div>
			<div className="recognition-container">
				<div className="cards">
					{this.state.personsDto
							.sort((a, b) => a.similarity < b.similarity ? 1 : -1)
							.map(p => (<div>
								<Link style={{textDecoration: 'none'}} to={`/${p.person.id}`}>
									<CardComponent person={p.person}/>
								</Link>
								<p>{p.similarity}%</p>
							</div>))}
				</div>
			</div>
		</div>);
	};

	render() {
		return (<div>
			{this.state.image ? this.yesImageComponent() : this.noImageComponent()}
		</div>);
	}
}

export default RecognitionComponent;