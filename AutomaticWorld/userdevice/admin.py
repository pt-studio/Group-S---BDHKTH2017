from __future__ import unicode_literals
from django.contrib import admin
from .models import UserDevice


class UserDeviceAdmin(admin.ModelAdmin):
    list_display = ('user', 'device_type', 'status', 'code_name', 'attribute')
    list_filter = ['user', 'code_name', 'device_type']
    search_fields = ['device_type', 'code_name', 'user']
    list_per_page = 30
    readonly_fields = ['attribute', 'code_name']

admin.site.register(UserDevice, UserDeviceAdmin)
