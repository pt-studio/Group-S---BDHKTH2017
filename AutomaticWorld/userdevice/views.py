# -*- coding: utf-8 -*-
from __future__ import unicode_literals
from rest_framework.viewsets import ModelViewSet
from rest_framework.views import APIView
from rest_framework.permissions import IsAuthenticated
from rest_framework.authentication import TokenAuthentication
from rest_framework.response import Response
from rest_framework.authtoken.models import Token
from .serializers import UserDeviceSerializer
from .models import UserDevice


class UserDeviceViewSet(ModelViewSet):
    authentication_classes = (TokenAuthentication,)
    permission_classes = (IsAuthenticated, )
    serializer_class = UserDeviceSerializer

    def get_queryset(self):
        queryset = UserDevice.objects.filter(user=self.request.user)
        return queryset


class GetUserToken(APIView):

    @staticmethod
    def get(self):
        codename = self.request.GET('codename')
        try:
            user = UserDevice.objects.get(codename=codename)
        except UserDevice.DoesNotExist:
            return Response("User with the codename does not exist", 404)

        token = Token.objects.get_or_create(user=user)

        return Response(token, 200)

