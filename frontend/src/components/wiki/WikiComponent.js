import React from 'react';
import WikiService from '../../services/WikiService';
import CardsComponent from '../card/CardsComponent';

class WikiComponent extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			persons: [],
		};
	}

	componentDidMount() {
		WikiService.getAll().then((response) => {
			this.setState({persons: response.data});
		});
	}

	render() {
		return (<div className="wiki-container">
			<CardsComponent persons={this.state.persons}/>
		</div>);
	}
}

export default WikiComponent;