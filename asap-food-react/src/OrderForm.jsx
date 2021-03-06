import React, { Component } from 'react';
import Button from "react-uwp/Button";
import TextBox from "react-uwp/TextBox";
import Icon from 'react-uwp/Icon';

import AutoSuggestBox from 'react-uwp/AutoSuggestBox';
const API = 'http://localhost:12345/order'

class OrderForm extends Component {


	getDefaultDriverName()
	{
		try
		{
			return this.props.driver.name;
		}
		catch(e)
		{
			return "";
		}
	}

	getSuggestMeal()
	{

	}
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
			<div>
			<Icon>🥨</Icon>
			<form onSubmit={this.handleSubmit}>
			<TextBox placeholder="Driver" name="driver"  defaultValue={this.getDefaultDriverName() }/>
			<TextBox placeholder="Ihr Name" name="customer" />
			<AutoSuggestBox placeholder="Ihr Gericht"  name="meal"/>
<br/>
			<AutoSuggestBox placeholder="mit Scharf." name="misc" />
<br/>
			<Button>
			Bestellen
			</Button>
			</form>
			</div>
		);
	}
}

export default OrderForm;
