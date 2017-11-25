from django.conf.urls import url
from .views import UserDeviceViewSet, GetUserToken

user_device_list = UserDeviceViewSet.as_view({
    'get': 'list',
})

user_device_detail = UserDeviceViewSet.as_view({
    'get': 'retrieve',
    'put': 'partial_update',
    'post': 'partial_update',
})


urlpatterns = [
    url(r'^$', user_device_list, name='user-device-list'),
    url(r'^(?P<pk>[0-9]+)/$', user_device_detail, name='user-device-detail'),
    url(r'^token/', GetUserToken, name='get-user-token'),
]
