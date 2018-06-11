import React, { Component } from 'react';

const API = 'http://localhost:12345/order'

class OrderForm extends Component {
	handleSubmit(event)
	{
		event.preventDefault();
		const data = new FormData(event.target);

		data.set('driver', data.get('driver'));
		data.set('customer', data.get('customer'));
		data.set('meal', data.get('meal'));
		data.set('misc', data.get('misc'));

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
			Driver:
			<input type="text" name="driver" />
			</label>
			<label>
			Customer:
			<input type="text" name="customer" />
			</label>
			<label>
			Meal:
			<input type="text" name="meal" />
			</label>
			<label>
			Misc:
			<input type="text" name="misc" />
			</label>
			<input type="submit" value="Submit" />
			</form>
		);
	}
}

export default OrderForm;
