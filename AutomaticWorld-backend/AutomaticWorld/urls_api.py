from django.conf.urls import url, include

default = 'v1'

urlpatterns = [
    url(r'^v1/devices/', include('devices.urls', namespace='devices-' + default)),
    url(r'^v1/user_devices/', include('userdevice.urls', namespace='userdevice-' + default)),
    url(r'^v1/blogs/', include('blog.urls', namespace='blog-' + default)),
    url(r'^v1/notifications/', include('notifications.urls', namespace='notification-' + default)),
    url(r'^v1/auth/', include('rest_auth.urls')),
]
