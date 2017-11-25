# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models
from django.contrib.auth.models import User
from django.contrib.postgres.fields import JSONField
from devices.models import DeviceType


class UserDevice(models.Model):
    class Meta:
        verbose_name = 'Device in use'
        verbose_name_plural = 'Devices in use'

    STATUS = (
        ('sleep', 'sleep'),
        ('working', 'working'),
        ('off', 'off'),
        ('not-used', 'not used')
    )
    WORKING = 'working'
    SLEEP = 'sleep'
    OFF = 'off'
    NOT_USED = 'not-used'

    device_type = models.ForeignKey(DeviceType)
    user = models.ForeignKey(User, null=True)
    code_name = models.CharField(max_length=100, unique=True)
    status = models.CharField(max_length=10, choices=STATUS)
    attribute = JSONField(null=True)
    name = models.CharField(max_length=100, blank=True)

    def __str__(self):
        return self.code_name

    def save(self, *args, **kwargs):
        # if object is not in database
        if not self.pk:
            attribute = {}
            if self.device_type.code_name == DeviceType.SMART_POT:
                attribute = {
                    'humidity_value': 0,
                    'safe_value': 0
                }
            elif self.device_type.code_name == DeviceType.SMART_WATERING:
                attribute = {
                    'humidity_value': 0,
                    'safe_value': 0,
                    'motor': False  # False: motor is off, True: motor is on
                }
            self.attribute = attribute
            # save code_name
            user_device_list = UserDevice.objects.filter(device_type__code_name=self.device_type.code_name)
            if user_device_list:
                max_id = max(user_device.id for user_device in user_device_list)
            else:
                max_id = 1
            code_name = '{}-{}'.format(self.device_type.code_name,
                                       max_id + 1)
            self.code_name = code_name
        super(UserDevice, self).save(*args, **kwargs)

