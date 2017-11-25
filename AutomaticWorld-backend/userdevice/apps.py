# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.apps import AppConfig


class UserdeviceConfig(AppConfig):
    name = 'userdevice'

    def ready(self):
        import AutomaticWorld.userdevice.signals
