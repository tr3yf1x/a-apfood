var React = require('react');

module.exports = Reacte.createClass(
	{
		render: function() {
			return (
				<div className="Drivers">
				<p className="Driver-Name">{this.props.text}</p>
				</div>
			)
		}

	}
);
