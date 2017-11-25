# -*- coding: utf-8 -*-
from __future__ import unicode_literals
from rest_framework.viewsets import ModelViewSet
from rest_framework.permissions import IsAuthenticated
from rest_framework.authentication import TokenAuthentication
from .serializers import NotificationSerializer
from .models import Notification


class NotificationViewSet(ModelViewSet):
    authentication_classes = (TokenAuthentication,)
    permission_classes = (IsAuthenticated, )
    serializer_class = NotificationSerializer

    def get_queryset(self):
        queryset = Notification.objects.filter(user_device__user=self.request.user)
        return queryset
