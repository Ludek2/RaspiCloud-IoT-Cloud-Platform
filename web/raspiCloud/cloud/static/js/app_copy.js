/*
first row is rownull
*/

var widgetId = -1;
var widgets = [];
var rows = [];
var lastRowWidgetSizeSum = 0;
//var newWidgetDefaultSize = 4;
var pieChartWidgetDefaultSize =6;
var textWidgetDefaultSize =6;
var controlWidgetDefaultSize =6;

function renderWidgets(){
	var sumTemp = 0;
	var lastWidgetRendered = -1;
	
	//render widgets considering their size (distributes widgets into rows wit 12 columns)
	for(var i=0;i<widgets.length;i++){
		sumTemp += widgets[i].size;
		if(sumTemp>12){
			lastRowWidgetSizeSum=sumTemp;
			sumTemp=widgets[i].size;
			var widgetsIntoRow = [];
			for (var i2 = lastWidgetRendered+1; i2<i; i2++){
				widgetsIntoRow.push(widgets[i2]);
				lastWidgetRendered = i2;
			}
			var newRowId;
			if(rows.length==null)newRowId=0;
			else newRowId=rows.length;
			var row = new Row({
				widgets: widgetsIntoRow,
				id: newRowId
			});
			rows.push(row);
			row.render();
		} 
	}

	//the rest of the widgets that make the last row and their sum of the size is smaller than 12
	if (lastWidgetRendered != widgets.length-1){
		lastRowWidgetSizeSum=0;
		var widgetsIntoRow = [];
		for (var i = lastWidgetRendered+1; i<widgets.length; i++){
			lastRowWidgetSizeSum+=widgets[i].size;
			widgetsIntoRow.push(widgets[i]);
		}

		var newRowId;
		if(rows.length==null)newRowId=0;
		else newRowId=rows.length;
		var row = new Row({
			widgets: widgetsIntoRow,
			id: newRowId
		});
		rows.push(row);
		row.render();
	}
	
}

//for testing only
function createDefaultWidget(type){
	switch(type){
		case "chart":
			widgetSize = pieChartWidgetDefaultSize;
			var newWidget = new ChartWidget({
				id: ++widgetId,
				title: 'Chart: '+widgetId,
				size: widgetSize
			});
			google.setOnLoadCallback(newWidget.loadEditor);
			break;
		case "pieChart":
			//$(".widget-place").append("pieChart");
			var newWidget = new PieChartWidget({
				id: ++widgetId,
				title: 'Pie Chart: '+widgetId,
				size: pieChartWidgetDefaultSize
			});
			break;
		case  "text":
			//$(".widget-place").append("TEXT");
			var newWidget = new DisplayTextWidget({
				id: ++widgetId,
				title: 'Text Widget: '+widgetId,
				text: 'This is the text contents!!!! :-)',
				size: textWidgetDefaultSize
            });
			break;
		case  "control":
			widgetSize = controlWidgetDefaultSize;
			var newWidget = new ControlWidget({
				id: ++widgetId,
				title: 'Text Widget: '+widgetId,
				size: widgetSize
            });
			break;
	}
	widgets[widgetId]=newWidget;
}


//createDefaultWidget("chart");
//createDefaultWidget("pieChart");
createDefaultWidget("text");
//createDefaultWidget("control");


renderWidgets();


function addWidget(type){
	//create object and add to widgets array
	var widgetSize;
	switch(type){
		case "chart":
			widgetSize = pieChartWidgetDefaultSize;
			var newWidget = new ChartWidget({
				id: ++widgetId,
				title: 'Chart: '+widgetId,
				size: widgetSize
			});
			break;
		case "pieChart":
			widgetSize = pieChartWidgetDefaultSize;
			var newWidget = new PieChartWidget({
				id: ++widgetId,
				title: 'Pie Chart: '+widgetId,
				size: widgetSize
			});
			break;
		case  "text":
			widgetSize = textWidgetDefaultSize;
			var newWidget = new DisplayTextWidget({
				id: ++widgetId,
				title: 'Text Widget: '+widgetId,
				text: 'This is the text contents!!!! :-)',
				size: widgetSize
            });
			break;
		case  "control":
			widgetSize = controlWidgetDefaultSize;
			var newWidget = new ControlWidget({
				id: ++widgetId,
				title: 'Text Widget: '+widgetId,
				size: widgetSize
            });
			break;
	}
	widgets[widgetId]=newWidget;

	//show on the page
	var lastWidgetId= widgetId;
    var lastRowId= rows.length-1;	  
    //console.log("before "+rows[lastRowId].getWidgetsSizeSum());
    if (12-rows[lastRowId].getWidgetsSizeSum() >= widgetSize){
    	rows[lastRowId].widgets.push(widgets[lastWidgetId]);
    	rows[lastRowId].renderAgain();  
    }
    else{
    	var row = new Row({
			widgets: [widgets[lastWidgetId]],
			id: rows.length
		});
		rows.push(row);
		lastRowId= rows.length-1;
		rows[lastRowId].render();	
    }
	
    //console.log("after "+rows[lastRowId].getWidgetsSizeSum());

    if(type=="chart"){
    	newWidget.loadEditor();
    }
	
}

document.getElementById("newChartWidget").addEventListener("click", function(){
    	addWidget("chart");
});

document.getElementById("newTextWidget").addEventListener("click", function(){
    	addWidget("text");
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

function changeWidgetTitle(form){
	var newTitle = form.title.value;
	var sourceWidgetId = form.widgetId.value;
    widgets[sourceWidgetId].title=newTitle;
    renderUpdatedWidget(sourceWidgetId);
    if(widgets[sourceWidgetId].type == "chart")
    	widgets[sourceWidgetId].drawChart();
}



document.addEventListener('click', function (e) {
  var target = e.target;
  if (target.tagName && target.tagName.toLowerCase() == "a") {
  	if(target.id.includes("changeTitle")){
  		var changeTitleTemplate = _.template($('#changeTitleTemplate').html());
  		var sourceWidgetId = findSourceWidgetId(target.id);
  		var title = document.getElementById("widget"+sourceWidgetId+"Title");
  		var titleStr= widgets[sourceWidgetId].title;
  		
  		var changeTitleHTML = changeTitleTemplate({
  			titleStr:  titleStr,
  			sourceWidgetId: sourceWidgetId
  		});
		title.innerHTML = changeTitleHTML;
  	}
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

       