from rest_framework import serializers
from .models import UserDevice
from devices.models import DeviceType


class UserDeviceSerializer(serializers.ModelSerializer):
    class Meta:
        model = UserDevice
        fields = ('id', 'device_type', 'user', 'code_name', 'status', 'attribute')




