from django.http import HttpResponse
from django.shortcuts import render
import json
import gviz_api
import random
from django.views.decorators.csrf import csrf_exempt
from .models import ChartWidget

def index(request):
	all_chartWidgets = ChartWidget.objects.all()
	context = {'all_chartWidgets':all_chartWidgets}
	for ch in all_chartWidgets:
		print (ch.jsonFile + "/n/n/n/n/n/n/n/n")
	return render(request, 'index.html', context)

def detail(request, album_id):
	return HttpResponse("album id:" + str(album_id))


def get_json(request):
	  # Creating the data
	  description = {"name": ("string", "Name"),
	                 "salary": ("number", "Salary"),
	                 "full_time": ("boolean", "Full Time Employee")}
	  data = [{"name": "Mike", "salary": (10000, "$10,000"), "full_time": True},
	          {"name": "Jim", "salary": (800, "$800"), "full_time": False},
	          {"name": "Alice", "salary": (12500, "$12,500"), "full_time": True},
	          {"name": "Bob", "salary": (7000, "$7,000"), "full_time": True}]

	  # Loading it into gviz_api.DataTable
	  data_table = gviz_api.DataTable(description)
	  data_table.LoadData(data)

	  # Creating a JavaScript code string
	  #jscode = data_table.ToJSCode("jscode_data",
	                               #columns_order=("name", "salary", "full_time"),
	                               #order_by="salary")
	  # Creating a JSon string
	  json = data_table.ToJSon(columns_order=("name", "salary", "full_time"),
	                           order_by="salary")

	  return HttpResponse(json, content_type='application/json')

def get_json2(request):
	  # Creating the data
	  description = {"name": ("string", "Name"),
	                 "salary": ("number", "Salary"),
	                 "full_time": ("boolean", "Full Time Employee")}
	  data = [{"name": "Mike", "salary": (100, "$10,0"), "full_time": True},
	          {"name": "Jim", "salary": (8, "$8"), "full_time": False},
	          {"name": "Alice", "salary": (125, "$12,5"), "full_time": True},
	          {"name": "Bob", "salary": (70, "$7,000"), "full_time": True}]

	  # Loading it into gviz_api.DataTable
	  data_table = gviz_api.DataTable(description)
	  data_table.LoadData(data)

	  # Creating a JavaScript code string
	  #jscode = data_table.ToJSCode("jscode_data",
	                               #columns_order=("name", "salary", "full_time"),
	                               #order_by="salary")
	  # Creating a JSon string
	  json = data_table.ToJSon(columns_order=("name", "salary", "full_time"),
	                           order_by="salary")

	  return HttpResponse(json, content_type='application/json')

def get_json_gauge(request):
	  # Creating the data
	  description = {"label": ("string", "Label"),
	  				 "value": ("number", "Value")}
	  data = [{"label":"Memory", "value": random.randint(0, 100)},
	  		  {"label":"CPU", "value": random.randint(0, 100)},
	  		  {"label":"Speed", "value": random.randint(0, 100)},
	  		  {"label":"Voltage", "value": random.randint(0, 100)}]

	  # Loading it into gviz_api.DataTable
	  data_table = gviz_api.DataTable(description)
	  data_table.LoadData(data)

	  # Creating a JavaScript code string
	  #jscode = data_table.ToJSCode("jscode_data",
	                               #columns_order=("name", "salary", "full_time"),
	                               #order_by="salary")
	  # Creating a JSon string
	  json = data_table.ToJSon()

	  return HttpResponse(json, content_type='application/json')



def static_var():
    static_var.counter += 1
    static_var.data.append({"year":static_var.counter, "value": random.randint(0, 100)})
    static_var.data.pop(0)
    
static_var.counter = 2011
static_var.data =[{"year":2001, "value": 0},
	  		  	  {"year":2002, "value": random.randint(0, 100)},
	  		      {"year":2003, "value": 10},
	  		      {"year":2004, "value": random.randint(0, 100)},
	  		      {"year":2005, "value": random.randint(0, 100)},
	  		      {"year":2006, "value": random.randint(0, 100)},
	  		      {"year":2007, "value": random.randint(0, 100)},
	  		      {"year":2008, "value": random.randint(0, 100)},
	  		      {"year":2009, "value": random.randint(0, 100)},
	  		      {"year":2010, "value": random.randint(0, 100)},
	  		      {"year":2011, "value": random.randint(0, 100)},]

def get_json_lineChart(request):
	  static_var()
	  data=static_var.data
	  # Creating the data
	  description = {"year": ("number", "Year"),
	  				 "value": ("number", "Value")}
	  # Loading it into gviz_api.DataTable
	  data_table = gviz_api.DataTable(description)
	  data_table.LoadData(data)

	  # Creating a JavaScript code string
	  #jscode = data_table.ToJSCode("jscode_data",
	                               #columns_order=("name", "salary", "full_time"),
	                               #order_by="salary")
	  # Creating a JSon string
	  json = data_table.ToJSon(columns_order=("year", "value"))

	  return HttpResponse(json, content_type='application/json')


def json_saved():
	json_saved.data
	

@csrf_exempt
def save_json(request):
    if request.is_ajax():
        if request.method == 'POST':
    		try:
        		chart=ChartWidget.objects.get(pk=request.POST['id'])
        		#print ("chartWidget reused")
    		except ChartWidget.DoesNotExist:
        		chart=ChartWidget.objects.create(pk=request.POST['id'])
        		chart.pk=request.POST['id']
    			#print ("chartWidget created")
    		chart.jsonFile=request.POST['jsonData'] 	
    		if str(chart.pk) != request.POST['id']: 
    			print ("primary key is not equal to widgetID !!!!!!")
    			chart.delete()
    		else:
    			chart.save()		
    		#print ("pk= " +str(chart.pk)+  " widgetId= " +request.POST['id'])
    return HttpResponse("OK")


def get_saved_json(request):
	return HttpResponse(json_saved.data, content_type='application/json')