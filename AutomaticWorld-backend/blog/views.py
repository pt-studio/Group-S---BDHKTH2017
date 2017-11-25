# -*- coding: utf-8 -*-
from django.contrib.postgres.search import SearchVector
from rest_framework.viewsets import ModelViewSet
from rest_framework.permissions import IsAuthenticated
from rest_framework.authentication import TokenAuthentication
from .serializers import BlogSerializer
from .models import Blog


class BlogViewSet(ModelViewSet):
    """
    Blogs are tips for growing tree.
    """
    authentication_classes = (TokenAuthentication,)
    permission_classes = (IsAuthenticated, )
    serializer_class = BlogSerializer

    def get_queryset(self):
        query_params = self.request.query_params
        if 'search' in query_params.keys():
            queryset = (Blog.objects.annotate(search=SearchVector('title', 'content')).
                        filter(search=query_params['search']))
        else:
            queryset = Blog.objects.all()
        return queryset

