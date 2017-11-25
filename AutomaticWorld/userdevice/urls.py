from django.conf.urls import url
from .views import (UserDeviceViewSet,
                    GetUserToken,
                    UpdateDeviceInfoFromESP,
                    AddTree,
                    UpdateDeviceMobileView)

user_device_list = UserDeviceViewSet.as_view({
    'get': 'list',
    'post': 'create',
})

user_device_detail = UserDeviceViewSet.as_view({
    'get': 'retrieve',
    'put': 'partial_update',
})


urlpatterns = [
    url(r'^$', user_device_list, name='user-device-list'),
    url(r'^(?P<pk>[0-9]+)/$', user_device_detail, name='user-device-detail'),
    url(r'^token/', GetUserToken.as_view(), name='get-user-token'),
    url(r'^update/$', UpdateDeviceInfoFromESP.as_view(), name='update-device-info'),
    url(r'^mobile_update/(?P<pk>[0-9]+)/$', UpdateDeviceMobileView.as_view(), name='update-device-info'),
    url(r'^add_tree/$', AddTree.as_view(), name='add-tree-mobile')
]
