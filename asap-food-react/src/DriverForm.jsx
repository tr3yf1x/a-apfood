import React, { Component } from 'react';
import './App.css';

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
			<form onSubmit={this.handleSubmit}>
			<label>
			Name:
			<input type="text" name="driverName" />
			</label>
			<label>
			Lokation:
			<input type="text" name="location" />
			</label>
			<label>
			Deadline:
			<input type="text" name="deadline" />
			</label>
			<input type="submit" value="Submit" />
			</form>
		);
	}
}

export default DriverForm;
