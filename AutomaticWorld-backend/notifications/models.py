from django.db import models
from userdevice.models import UserDevice


class Notification(models.Model):
    class Meta:
        verbose_name = 'Notification'
        verbose_name_plural = 'Notifications'

    user_device = models.ForeignKey(UserDevice, related_name='notification')
    content = models.TextField()

    def __str__(self):
        return self.user_device.name
