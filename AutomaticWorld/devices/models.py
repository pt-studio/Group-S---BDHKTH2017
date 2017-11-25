# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models


class DeviceType(models.Model):
    class Meta:
        verbose_name = 'Device Type'
        verbose_name_plural = 'Device Types'

    SMART_POT = 'sp'
    SMART_WATERING = 'sw'

    name = models.CharField(max_length=100)
    code_name = models.CharField(max_length=100, unique=True)
    description = models.CharField(max_length=200)

    def __str__(self):
        return '{} - {}'.format(self.code_name, self.name)
