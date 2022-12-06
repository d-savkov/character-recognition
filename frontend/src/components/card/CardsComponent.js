import React from 'react';
import {Link} from 'react-router-dom';
import CardComponent from './CardComponent';

class CardsComponent extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			persons: [],
		};
	}

	render() {
		return (<div className="cards">
			{this.props.persons
					.sort((a, b) => a.id > b.id ? 1 : -1)
					.map(person => (<Link style={{textDecoration: 'none'}} to={`/${person.id}`}>
						<CardComponent person={person}/>
					</Link>))}
		</div>);
	}
}

export default CardsComponent;