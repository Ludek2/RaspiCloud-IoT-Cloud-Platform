var controlWidgetSettingsTemplate = _.template($('#controlWidgetSettingsTemplate').html());
var controlWidgetBodyTemplate = _.template($('#controlWidgetBodyTemplate').html());

var ControlWidget = Widget.extend({
	init: function (opts) {
		//console.log('DisplayTextWidget > init > opts', opts);
		this._super(opts);
		this.text = opts.text || null;
	},

	appendBody: function () {
		return controlWidgetBodyTemplate({
			
		})
	},

	appendSettings: function () {
		return controlWidgetSettingsTemplate({
			widgetId: this.id
		});
	}
});	

