var Widget = Class.extend({

	title: null,

	init: function (opts) {
		opts = opts || {};
		this.id = opts.id;
		this.title = opts.title || null;
		this.size = opts.size || null;

	},
	render: function () {
		var html = "";

		html += '<div class="panel panel-primary" id=widget'+this.id+'Panel>';
		html += '  <div class="panel-heading"><h3 class="panel-title" id="widget'+this.id+'Title">'+this.title+'</h3>';
		html += this.appendSettings();
		html += '</div>';
		html += '  <div class="panel-body">';

		html += this.appendBody();

		html += '  </div>';
		html += '</div>';
	
		return html;
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