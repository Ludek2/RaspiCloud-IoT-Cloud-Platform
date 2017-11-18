var pieChartWidgetSettingsTemplate = _.template($('#pieChartWidgetSettingsTemplate').html());
var pieChartWidgetBodyTemplate = _.template($('#pieChartWidgetBodyTemplate').html());

var PieChartWidget = Widget.extend({
	init: function (opts) {
		//console.log('DisplayTextWidget > init > opts', opts);
		this._super(opts);
	},

	appendBody: function () {
		return pieChartWidgetBodyTemplate({
			pictureURL: 'http://www.bbc.co.uk/staticarchive/d00d6ca860bc06b66d5dd54f774144b2484623cc.gif'
		});
	},

	appendSettings: function () {
		return pieChartWidgetSettingsTemplate({
			widgetId: this.id
		});
	}
});