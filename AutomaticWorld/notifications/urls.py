from django.conf.urls import url
from .views import NotificationViewSet

notification_list = NotificationViewSet.as_view({
    'get': 'list',
    'post': 'create',
})

notification_detail = NotificationViewSet.as_view({
    'get': 'retrieve',
    'put': 'partial_update',
})


urlpatterns = [
    url(r'^$', notification_list, name='notification-list'),
    url(r'^(?P<pk>[0-9]+)/$', notification_detail, name='notification-detail'),
]
