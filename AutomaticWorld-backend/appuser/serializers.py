from django.contrib.auth.models import User, Group
from rest_framework import serializers
from rest_auth.serializers import LoginSerializer, UserDetailsSerializer, PasswordResetSerializer


class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ('username', 'email', 'first_name', 'last_name')


class VisdLoginSerializer(LoginSerializer):

    class Meta(UserDetailsSerializer.Meta):
        fields = UserDetailsSerializer.Meta.fields + ('remember_me',)

    def validate_remember_me(self, remember_me):
        if not remember_me:
            self.context['request'].session.set_expiry(0)
        return remember_me


