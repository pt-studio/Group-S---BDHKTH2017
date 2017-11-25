from django.conf.urls import url
from .views import Us

blog_list = BlogViewSet.as_view({
    'get': 'list',
    'post': 'create',
})

blog_detail = BlogViewSet.as_view({
    'get': 'retrieve',
    'put': 'partial_update',
})

urlpatterns = [
    url(r'^$', blog_list, name='blog-list'),
    url(r'^(?P<pk>[0-9]+)/$', blog_detail, name='blog-detail'),
]
