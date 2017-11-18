var DisplayTextWidget = Widget.extend({
	init: function (opts) {
		//console.log('DisplayTextWidget > init > opts', opts);
		this._super(opts);
		this.text = opts.text || null;
	},
	appendBody: function () {
		return '<p>' + this.text + '</p>';
	},

	appendSettings: function () {
		return '<div class="dropdown"> '+
				  '<button class="btn btn-default btn-xs dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true"> '+
				    'Dropdown '+
				    '<span class="caret"></span> '+
				  '</button> '+
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

