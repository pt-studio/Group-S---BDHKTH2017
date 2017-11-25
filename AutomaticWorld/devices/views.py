# -*- coding: utf-8 -*-
from __future__ import unicode_literals
from rest_framework.viewsets import ModelViewSet
from rest_framework.permissions import IsAuthenticated
from rest_framework.authentication import TokenAuthentication
from .serializers import DeviceTypeSerializer
from .models import DeviceType


class DeviceTypeViewSet(ModelViewSet):
    # authentication_classes = (TokenAuthentication,)
    # permission_classes = (IsAuthenticated, )
    serializer_class = DeviceTypeSerializer

    def get_queryset(self):
        queryset = DeviceType.objects.all()
        return queryset

