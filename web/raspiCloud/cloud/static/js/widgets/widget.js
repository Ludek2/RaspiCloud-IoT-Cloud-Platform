var widgetTemplate =_.template($('#widgetTemplate').html());

var Widget = Class.extend({

	title: null,

	init: function (opts) {
		opts = opts || {};
		this.id = opts.id;
		this.title = opts.title || null;
		this.width = opts.width || null;
		this.height = opts.height || null;
		this.positionTop = opts.positionTop || null;
		this.positionLeft = opts.positionLeft || null;
	},
	render: function () {
		
		return widgetTemplate({
			widgetId: this.id,
			widgetTitle: this.title,
			widgetSettings: this.appendSettings(), 
			widgetBody: this.appendBody(),
			widgetWidth: this.width,
			widgetHeight: this.height,
			widgetTop: this.positionTop,
			widgetLeft: this.positionLeft
		});
	},

	appendBody: function () {
		console.warn('append body method has not been implemented in sub class');
	},

	appendSettings: function(){
		console.warn('append settings method has not been implemented in sub class');
	},

	getTitle: function () {
		return this.title;
	},
	setTitle: function (title) {
		this.title = title;
	}
});