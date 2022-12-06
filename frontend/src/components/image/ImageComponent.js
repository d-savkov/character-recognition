import React from 'react';

class ImageComponent extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			className: this.props.className, img: this.props.img, alt: this.props.alt,
		};
	}

	render() {
		return (<div>
			<img className={this.state.className}
					 src={`data:image/jpeg;base64,${this.state.img}`}
					 alt={this.state.alt}/>
		</div>);
	}
}

export default ImageComponent;