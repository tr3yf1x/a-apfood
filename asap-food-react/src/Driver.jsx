import React, { Component } from 'react';
import Order from './Order.jsx';
import OrderForm from './OrderForm.jsx';

class Driver extends Component {
	render() {
		return (

			<div class="DriverDetails">
			<div class="DriverLocation">{this.props.driver.location}</div>
			<div class="DriverNumOfOrders">{Object.keys(this.props.driver.orders).length}</div>
			<div class="DriverName">{this.props.driver.name}</div>
			<div class="DriverDeadline">{this.props.driver.deadline}</div>
			<OrderForm driver={this.props.driver} />
			</div>
		);
	}
}

export default Driver;
