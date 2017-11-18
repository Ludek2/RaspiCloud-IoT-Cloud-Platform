var textWidgetSettingsTemplate = _.template($('#textWidgetSettingsTemplate').html());
var textWidgetBodyTemplate = _.template($('#textWidgetBodyTemplate').html());

var DisplayTextWidget = Widget.extend({
	init: function (opts) {
		//console.log('DisplayTextWidget > init > opts', opts);
		this._super(opts);
		this.text = opts.text || null;
	},

	appendBody: function () {
		var columnNames = ['Date','Time','Event'];
		var data = [['10.11','12:33','Device powered up'],
					['12.12','10:09','Device Fault'],
					['12.12','10.10','Device reboot']];
		return textWidgetBodyTemplate({
			columnNames: columnNames,
			data: data
		})
	},

	appendSettings: function () {
		return textWidgetSettingsTemplate({
			widgetId: this.id
		});
	}
});	

