import React, { Component } from 'react';



class Order extends Component {
	render() {
		return (
		
			<div class="OrderDetails">
			<div class="OrderName">{this.order.meal.name}</div>
			<div class="OrderCustomer">{this.order.customer.name}</div>
			</div>

		);
	}
}

export default Order;
