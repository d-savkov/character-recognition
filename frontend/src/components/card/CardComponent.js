import React from 'react';
import ImageComponent from '../image/ImageComponent';

class CardComponent extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			person: this.props.person,
		};
	}

	render() {
		return (<div className="card">
			<article>
				<ImageComponent className="card-img"
												img={this.state.person.base64Image}
												alt={this.state.person.name}/>
				<h3 className="card-name">
					{this.state.person.name}
				</h3>
				<p className="card-played-by">
					{this.state.person.playedBy}
				</p>
			</article>
		</div>);
	}
}

export default CardComponent;