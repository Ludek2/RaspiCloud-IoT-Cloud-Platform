var chartWidgetSettingsTemplate = _.template($('#chartWidgetSettingsTemplate').html());
var chartWidgetBodyTemplate = _.template($('#chartWidgetBodyTemplate').html());
//var chartEditor = null;

function getChartWrapper(){
	return chartEditor.getChartWrapper();
}

var ChartWidget = Widget.extend({
	
	init: function (opts) {
		this._super(opts);
		this.chartEditor = null;
		this.type="chart";
		this.printed=false;
		//contains json data for the chart
		this.chartData=null;
		//data update interval
		this.updInterval=1000;
		this.wrapper=opts.wrapper || null;
	},

	setChartWrapper: function(chartWrapper) {
		this.chartWrapper = chartWrapper;
	},

	setPosition: function(top,left){
		this.positionTop=top;
      	this.positionLeft=left;
      	console.log("position top:" +this.positionTop+ " position left:" +this.positionLeft)
      	//send to ajax
	},
	appendBody: function () {
		return chartWidgetBodyTemplate({
			widgetId: this.id
		});
	},

	appendSettings: function () {
		return chartWidgetSettingsTemplate({
			widgetId: this.id
		});
	},
	
	changeSize: function (width,height){
		console.log("changeSize");
		this.width=width;
		this.heigth=height;
		this.wrapper.setOption('width',this.width);
		this.wrapper.setOption('height',this.heigth);
	},

  getJSONData: function(){
    return $.ajax({
                      url: "get_json_lineChart",
                      dataType: "json",
                      async: false
                      }).responseText;
  },

	drawChart: function () {
		console.log("draw");
		var jsonData = this.getJSONData();
    this.wrapper.setDataTable(jsonData);
		this.wrapper.draw(document.getElementById("chart"+this.id));
	},

	drawChartFromJSON: function(){
		var parsedWrapper= JSON.parse(this.wrapper);
    console.log(parsedWrapper);
		this.wrapper = new google.visualization.ChartWrapper({
	    	chartType: parsedWrapper.chartType,
	    	dataTable: parsedWrapper.dataTable,
	    	options: parsedWrapper.options,
	    	containerId: parsedWrapper.containerId
  		});
	},

	loadEditor: function () {
	  divName = "chart"+this.id;
      // Create the chart to edit.
      wrapper = new google.visualization.ChartWrapper({
         'chartType':'LineChart',
         'dataSourceUrl':'http://spreadsheets.google.com/tq?key=pCQbetd-CptGXxxQIG7VFIQ&pub=1',
         'query':'SELECT A,D WHERE D > 100 ORDER BY D',
         'options': { title:'Population Density (people/km^2)', legend:'none'}
      });

      this.chartEditor = new google.visualization.ChartEditor();
      _.bindAll(this, 'firstDrawChart');
      google.visualization.events.addListener(this.chartEditor, 'ok', function(){
        this.firstDrawChart(0);}.bind(this));
      this.chartEditor.openDialog(wrapper, {});
    },


    // On "OK" save the chart to a <div> on the page.
    firstDrawChart: function (fromJson){
      console.log("firstDrawChart");
      //var self = this;
      if(fromJson) this.drawChartFromJSON();
      else {
        this.wrapper=this.chartEditor.getChartWrapper();
        this.wrapper.setOption('width',this.width);
        this.wrapper.setOption('height',this.height);
      }
      var jsonData = this.getJSONData();
      this.wrapper.setDataTable(jsonData);
      //this.wrapper.setOption('height',00);
      this.wrapper.draw(document.getElementById("chart"+this.id));
      this.printed=true;
      this.refresh();
      
      //makes the div that contains the chart draggable
      $( "#widget"+this.id+"Panel" ).draggable({
      	stop: function(e,ui){
      		this.setPosition(ui.position.top,ui.position.left);
          console.log("after drag h: "+ this.height + " w: " +this.width+ " pt: "+this.positionTop+ " pl: "+this.positionLeft);
          this.saveProperties();
      	}.bind(this)

      });
      
      $( "#widget"+this.id+"Panel" ).resizable({
      	stop: function(e, ui) {
      		this.changeSize(ui.size.width,ui.size.height);
      		this.drawChart();
          console.log("after resize h: "+ this.height + " w: " +this.width+ " pt: "+this.positionTop+ " pl: "+this.positionLeft);

          this.saveProperties();
      	}.bind(this)
      });
      this.saveProperties(); 
    },

    refresh: function(){
    	var self = this;    	
    	if(this.updInterval!=0) {
    		//setInterval(this.drawChart, this.updInterval);
    		setInterval(function () {
    			self.drawChart();
    		}, this.updInterval);
    		
    	}
    },

    toJson: function(){
    	var values ={
    		id:this.id,
    		width:this.width,
  			height:this.height,
  			positionTop:this.positionTop,
  			positionLeft:this.positionLeft,

  			type:this.type,
  			updInterval:this.updInterval,
  			wrapper:this.wrapper
		  };
      console.log("saved h: "+ this.height + " w: " +this.width+ " pt: "+this.positionTop+ " pl: "+this.positionLeft);
    	return JSON.stringify(values);
    },

    saveProperties: function(){
    	jsonData=this.toJson();
      jQuery.ajax({
				url : "save_json/",
				type: 'POST',
				dataType : "json",
				data: {
          jsonData: jsonData,
          id: this.id
        },
				//success:function(data) { alert(); },
				//error: function() {alert("problem during saving to the Cloud"); }
		  });

      //console.log(JSON.parse(JSON.parse(jsonData).wrapper));
    }
});

