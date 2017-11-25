from django.conf.urls import url
from .views import DeviceTypeViewSet

device_type_list = DeviceTypeViewSet.as_view({
    'get': 'list',
})

device_type_detail = DeviceTypeViewSet.as_view({
    'get': 'retrieve',
})


urlpatterns = [
    url(r'^$', device_type_list, name='device-list'),
    url(r'^(?P<pk>[0-9]+)/$', device_type_detail, name='device-detail'),
]
