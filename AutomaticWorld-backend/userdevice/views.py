# -*- coding: utf-8 -*-
from __future__ import unicode_literals
from rest_framework.viewsets import ModelViewSet
from rest_framework import views, generics
from rest_framework.permissions import IsAuthenticated
from rest_framework.authentication import TokenAuthentication
from rest_framework.response import Response
from rest_framework.authtoken.models import Token
from .serializers import UserDeviceSerializer, MobileUserDeviceSerializer, HistorySerializer
from .models import UserDevice, History


class UserDeviceViewSet(ModelViewSet):
    authentication_classes = (TokenAuthentication,)
    permission_classes = (IsAuthenticated, )
    serializer_class = UserDeviceSerializer

    def get_queryset(self):
        queryset = UserDevice.objects.filter(user=self.request.user)
        return queryset


class UpdateDeviceMobileView(generics.UpdateAPIView):
    authentication_classes = (TokenAuthentication,)
    permission_classes = (IsAuthenticated,)
    serializer_class = MobileUserDeviceSerializer

    def get_queryset(self):
        queryset = UserDevice.objects.filter(user=self.request.user)
        return queryset

    def put(self, request, *args, **kwargs):
        return self.partial_update(request, *args, **kwargs)


# For mobile dev
class AddTree(views.APIView):
    authentication_classes = (TokenAuthentication,)
    permission_classes = (IsAuthenticated, )

    def post(self, request):
        data = request.data
        if ('name' not in data.keys()
            or 'device_type' not in data.keys()
            or 'safe_value' not in data.keys()
            ):
            return Response({"message": "Invalid input"}, 400)
        attribute = {}
        user_devices = UserDevice.objects.filter(user=self.request.user,
                                                 status=UserDevice.NOT_USED,
                                                 device_type=data['device_type'])
        if user_devices:
            # Get first userdevice that's not used
            user_device = user_devices[0]
            name = data['name']
            safe_value = data['safe_value']
            attribute['safe_value'] = safe_value
            attribute['humidity_value'] = 0
            update_data = {
                "name": name,
                "status": UserDevice.WORKING,
                "attribute": attribute
            }
            user_device_serializer = UserDeviceSerializer(instance=user_device,
                                                          data=update_data,
                                                          partial=True)
        else:
            return Response({"message": "Run out of device"}, 400)
        if user_device_serializer.is_valid():
            user_device_serializer.save()
            return Response({"message": "Update device successfully"}, 200)
        else:
            return Response(user_device_serializer.errors, 400)


class UpdateDeviceInfoFromESP(views.APIView):
    authentication_classes = (TokenAuthentication,)
    permission_classes = (IsAuthenticated,)

    def post(self, request):
        data = request.data
        attribute = {}
        try:
            user_device = UserDevice.objects.get(code_name=data['code_name'])
            attribute['safe_value'] = user_device.attribute['safe_value']
            attribute['humidity_value'] = data['humidity_value']
            update_data = {
                "status": data['status'],
                "attribute": attribute
            }
            user_device_serializer = UserDeviceSerializer(instance=user_device,
                                                          data=update_data,
                                                          partial=True)
        except UserDevice.DoesNotExist:
            return Response({"message": "User with the codename does not exist"}, 404)
        if user_device_serializer.is_valid():
            user_device_serializer.save()
            return Response({"message": "Update from ESP successfully"}, 200)
        else:
            return Response(user_device_serializer.errors, 400)


class GetUserToken(views.APIView):

    def get(self, request):
        if "codename" in request.GET.keys():
            codename = request.GET['codename']
            try:
                user_device = UserDevice.objects.select_related('user').get(code_name=codename)
            except UserDevice.DoesNotExist:
                return Response({"message": "User with the codename does not exist"}, 404)
            token = Token.objects.get(user=user_device.user)
            return Response(token.key, 200)
        return Response({"message": "Miss codename in query"}, 400)


class HistoryViewSet(ModelViewSet):
    authentication_classes = (TokenAuthentication,)
    permission_classes = (IsAuthenticated,)
    serializer_class = HistorySerializer

    def get_queryset(self):
        query_params = self.request.query_params
        queryset = History.objects.filter(user_device__user=self.request.user)
        if query_params.keys():
            if 'user_device_id' in query_params.keys():
                queryset = History.objects.filter(user_device__id=query_params['user_device_id'])
            if 'count' in query_params.keys():
                count = int(query_params['count'])
                queryset = list(reversed(queryset))[:count]
                queryset = list(reversed(queryset))
        return queryset





