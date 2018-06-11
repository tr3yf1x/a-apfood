import React, { Component } from 'react';
import './App.css';
import Driver from './Driver.jsx'
import DriverForm from './DriverForm.jsx'
import OrderForm from './OrderForm.jsx'
const API = 'http://localhost:12345/driver'
//const DEFAULT_QUERY = 'driver'

class App extends Component {
	constructor(props)
	{
		super(props);
		this.state = {
			drivers: [],
		};
	}
	componentDidMount() {
		fetch(API )
			.then(response => response.json())
			.then(data => this.setState({ drivers : data}) );
	}
	render() {
		const {drivers} = this.state;

		return (
<div>
			{drivers.map(driver =>
				<Driver driver={driver}/>
			)}
			<DriverForm />
			<OrderForm />
			</div>
		);
	}
}

export default App;
