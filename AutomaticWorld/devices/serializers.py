from rest_framework import serializers
from .models import DeviceType


class DeviceTypeSerializer(serializers.ModelSerializer):
    class Meta:
        model = DeviceType
        fields = ('name', 'code_name', 'description')

