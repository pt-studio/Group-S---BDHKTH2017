# -*- coding: utf-8 -*-
# Generated by Django 1.11.4 on 2017-11-09 17:58
from __future__ import unicode_literals

from django.conf import settings
from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('userdevice', '0005_auto_20171109_1750'),
    ]

    operations = [
        migrations.AlterField(
            model_name='userdevice',
            name='user',
            field=models.ForeignKey(null=True, on_delete=django.db.models.deletion.CASCADE, to=settings.AUTH_USER_MODEL),
        ),
    ]
