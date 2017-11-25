from rest_framework import serializers
from .models import UserDevice
from django.contrib.auth.models import User


class UserDeviceSerializer(serializers.ModelSerializer):
    class Meta:
        model = UserDevice
        fields = ('id', 'device_type', 'user', 'code_name', 'status', 'attribute', 'name')


class MobileUserDeviceSerializer(UserDeviceSerializer):
    username = serializers.CharField(max_length=100, write_only=True)

    class Meta(UserDeviceSerializer.Meta):
        fields = UserDeviceSerializer.Meta.fields + ('username',)

    def update(self, instance, validated_data):
        instance.name = validated_data['name']
        try:
            user = User.objects.get(username=validated_data['username'])
        except User.DoesNotExist:
            raise serializers.ValidationError({'username': 'Username does not exist'})
        instance.user = user
        instance.attribute['safe_value'] = validated_data['attribute']['safe_value']
        instance.save()
        return instance
