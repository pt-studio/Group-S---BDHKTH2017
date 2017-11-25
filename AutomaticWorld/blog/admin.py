from django.contrib import admin
from .models import Blog
# Register your models here.


class BlogAdmin(admin.ModelAdmin):
    list_display = ('title', 'image', 'content')
    list_filter = ['title', ]
    search_fields = ['title', ]
    list_per_page = 30

admin.site.register(Blog, BlogAdmin)
