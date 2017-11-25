from django.conf.urls import url, include

default = 'v1'

urlpatterns = [
    url(r'^v1/devices/', include('devices.urls', namespace='devices-' + default)),
    url(r'^v1/user_device/', include('userdevice.urls', namespace='userdevice' + default)),
]
