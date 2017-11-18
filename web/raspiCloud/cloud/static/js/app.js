/*
first row is rownull
*/
var widgetId = 0;
var widgets = [];

//renderWidgets();

//addWidget("chartFromMemory");

function addWidget(type){
	//create object and add to widgets array
	var widgetSize; 
	var widgetHtml;
	var widgetHeight=200;
	var widgetWidth=300;
	switch(type){
		case "chart":
			numberOfWidgets++;
			widgetSize = 10;
			var newWidget = new ChartWidget({
				id: numberOfWidgets,
				title: 'Chart: '+widgetId,
				height: widgetHeight,
				width: widgetWidth
			});
			widgetHtml = newWidget.render();
			$(".widget-place").append(widgetHtml);
			break;
		case  "control":
			widgetSize = 10;
			var newWidget = new ControlWidget({
				id: ++widgetId,
				title: 'Text Widget: '+widgetId,
				size: widgetSize
            });
			break;
		case "chartFromMemory":
			numberOfWidgets++;
			var properties=JSON.parse(jsonString);
			var newWidget = new ChartWidget({
				id: properties.id,
				title: 'Chart: '+properties.id,
				width: properties.width,
				height: properties.height,
				positionTop: properties.positionTop,
				positionLeft: properties.positionLeft,
				type:properties.type,
				updInterval:properties.updInterval,
				wrapper:properties.wrapper
			});
			widgetHtml = newWidget.render();
			console.log("retrieved pt: "+properties.positionTop+" pl: "+properties.positionLeft+" h: "+ properties.height +" w: "+ properties.width);
			console.log(widgetHtml);
			$(".widget-place").append(widgetHtml);
			google.charts.setOnLoadCallback(function(){
				newWidget.firstDrawChart(1);
			});
			break;
	}
	widgets[widgetId]=newWidget;

    if(type=="chart"){
    	newWidget.loadEditor();
    }
	
}

function loadExistingWidgets(){
	var jsonData = $.ajax({
                      url: "get_json_chart1",
                      dataType: "json",
                      async: false
                      }).responseText;
}

document.getElementById("newChartWidget").addEventListener("click", function(){
    	addWidget("chart");
});

document.getElementById("newControlWidget").addEventListener("click", function(){
    	addWidget("control");
});

function findSourceWidgetId(inputString){
	var parsedString = inputString.split("&");
  	var sourceWidgetId = parsedString[1];
  	return sourceWidgetId;
}

function renderUpdatedWidget(updateWidgetId){
	var html = widgets[updateWidgetId].render();
    var widgetPanel = document.getElementById('widget'+updateWidgetId+'Panel');
    widgetPanel.parentNode.innerHTML = html;
}

document.addEventListener('click', function (e) {
  var target = e.target;
  if (target.tagName && target.tagName.toLowerCase() == "a") {
  	if(target.id.includes("scale_up")){
  		var sourceWidgetId = findSourceWidgetId(target.id);
  		widgets[sourceWidgetId].changeSize(40);
  		widgets[sourceWidgetId].drawChart();		
  	}
  	if(target.id.includes("scale_down")){
  		var sourceWidgetId = findSourceWidgetId(target.id);
  		widgets[sourceWidgetId].changeSize(-40);
  		widgets[sourceWidgetId].drawChart();		
  	}
  }
});

       