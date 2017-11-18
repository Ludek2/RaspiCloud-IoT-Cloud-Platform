from django.conf.urls import url

from . import views

urlpatterns = [
	#/music/
    url(r'^$', views.index, name='index'),

    #/music/703/
    url(r'^(?P<album_id>[0-9]+)/$', views.detail, name='detail'),

    #music/get_json/
    url(r'^get_json/$', views.get_json, name='get_json'),

    #music/get_json2/
    url(r'^get_json2/$', views.get_json2, name='get_json2'),

    #music/get_json_gague/
    url(r'^get_json_gauge/$', views.get_json_gauge, name='get_json_gauge'),

    #music/get_json_lineChart/
    url(r'^get_json_lineChart/$', views.get_json_lineChart, name='get_json_lineChart'),

    #music/save_json/
    url(r'^save_json/$', views.save_json, name='save_json'),

    #music/get_saved_json/
    url(r'^get_saved_json/$', views.get_saved_json, name='get_saved_json'),
]