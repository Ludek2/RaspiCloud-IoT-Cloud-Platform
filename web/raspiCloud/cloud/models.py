from __future__ import unicode_literals

from django.db import models

# Create your models here.

class ChartWidget(models.Model):
	jsonFile = models.TextField()
	chartWidgetId= models.PositiveSmallIntegerField(null=True)

