from __future__ import unicode_literals
from django.contrib import admin
from .models import DeviceType


class DeviceTypeAdmin(admin.ModelAdmin):
    list_display = ('name', 'code_name', 'description')
    list_filter = ['name', 'code_name']
    search_fields = ['name', 'code_name']
    list_per_page = 30

admin.site.register(DeviceType, DeviceTypeAdmin)
