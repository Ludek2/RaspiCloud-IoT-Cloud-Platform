var PieChartWidget = Widget.extend({
	init: function (opts) {
		//console.log('DisplayTextWidget > init > opts', opts);
		this._super(opts);
	},

	appendBody: function () {
		return '<img src="http://www.bbc.co.uk/staticarchive/d00d6ca860bc06b66d5dd54f774144b2484623cc.gif">';
	},

	appendSettings: function () {
		return '<div class="dropdown"> '+
				   //'<div class="text-right">'+
					  '<button class="btn btn-default btn-xs dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true"> '+
					    'Dropdown '+
					    '<span class="caret"></span> '+
					  '</button> '+
					//'</div>' +
				  '<ul class="dropdown-menu" aria-labelledby="dropdownMenu1"> '+
				    '<li><a href="#" id="changeTitle&'+this.id+'">Change title</a></li> '+
				    '<li><a href="#">Another action</a></li> '+
				    '<li><a href="#">Something else here</a></li> '+
				    '<li role="separator" class="divider"></li> '+
				    '<li><a href="#">Separated link</a></li> '+
				  '</ul> '+
				'</div>';
	}
});