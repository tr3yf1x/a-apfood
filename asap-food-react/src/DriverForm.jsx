import React, { Component } from 'react';
import './App.css';

import Button from "react-uwp/Button";
import TextBox from "react-uwp/TextBox";
import Icon from 'react-uwp/Icon';

const API = 'http://localhost:12345/driver'

class DriverForm extends Component {
	componentDidMount() {
		fetch(API )
			.then(response => response.json())
			.then(data => this.setState({ drivers : data}) );
	}

	handleSubmit(event)
	{
		event.preventDefault();
		const data = new FormData(event.target);

		const name = data.get('driverName');
		const location = data.get('location');
		const deadline = data.get('deadline');

		data.set('driver', name);
		data.set('location', location);
		data.set('deadline', deadline);

		fetch(API,
			{
				method: 'POST',
				body: data,
			}
		);
	}
	render() {
		return (
<div>
		<Icon>🏎️</Icon>
		<form onSubmit={this.handleSubmit}>
		<TextBox placeholder="Name" name="driverName" />
		<TextBox placeholder="Lokation" name="location" />
		<TextBox placeholder="Deadline" name="deadline" />
		<Button>
		Fahrer werden!
		</Button>
		</form>
			</div>
		);
	}
}

export default DriverForm;
