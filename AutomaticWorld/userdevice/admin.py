from __future__ import unicode_literals
from django.contrib import admin
from django.contrib.auth.models import User
from django.forms import ModelForm
from django import forms
from .models import UserDevice
from devices.models import DeviceType


class UserDeviceForm(ModelForm):
    class Meta:
        model = UserDevice
        fields = ['user', 'device_type', 'status', 'code_name', 'attribute']

    user = forms.ModelChoiceField(required=False, queryset=User.objects.all())


class UserDeviceAdmin(admin.ModelAdmin):
    form = UserDeviceForm

    list_display = ('name', 'user', 'device_type', 'status', 'code_name', 'attribute')
    list_filter = ['user', 'code_name', 'device_type']
    search_fields = ['device_type', 'code_name', 'user']
    list_per_page = 30
    readonly_fields = ['attribute', 'code_name']

admin.site.register(UserDevice, UserDeviceAdmin)
