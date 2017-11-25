from django.contrib import admin
from .models import Notification
# Register your models here.


class NotificationAdmin(admin.ModelAdmin):
    list_display = ('user_device', 'content')
    search_fields = ['content', 'user_device']
    list_per_page = 30

admin.site.register(Notification, NotificationAdmin)
