var rowTemplate = _.template($('#rowTemplate').html());

var Row = Class.extend({

	init: function (opts) {
		opts = opts || {};
		this.widgets = opts.widgets || null;
		this.id = opts.id;
	},


/*
	insideRowHTML: function(){
		var html ="";
		for (var i=0; i< this.widgets.length; i++){
        	html += '<div class="col-md-' + this.widgets[i].size +'">';
        	html += this.widgets[i].render();
        	html += '</div>';
        }
		return html;
	},

	formHtml: function(){
		var html = "";
        html += '<div class="row" id="row'+this.id+'">';
        html += this.insideRowHTML();
		html += '</div>';
	    return html;
	},
*/

	formHtml: function(){
		console.log(this.id);
		var widgetHtml = [];
		var widgetSize = [];
		for (var i=0; i< this.widgets.length; i++){
        	widgetHtml[i] = this.widgets[i].render(); 
        	widgetSize[i] = this.widgets[i].size;
        }
		return rowTemplate({
			rowId: this.id,
			numberOfWidgets: this.widgets.length,
			widgetSize: widgetSize,
			widgetHtml: widgetHtml
		});
	},

	render: function(){
		var html = this.formHtml() ;
		$(".widget-place").append(html);
	},

	renderAgain: function () {
		var html = this.formHtml();
		document.getElementById("row"+this.id).outerHTML = html;
	},
/*
	render: function () {
		var html = this.formHtml() ;
		$(".widget-place").append(html);
	},

	renderAgain: function () {
		var html = this.insideRowHTML() ;
		document.getElementById("row"+this.id).innerHTML = html;
	},
*/


	getWidgetsSizeSum: function(){
		var sum=0;
		for(var i=0; i < this.widgets.length; i++){
			sum+=this.widgets[i].size;
		}

		return sum;
	}
});