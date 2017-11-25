from django.db import models


class Blog(models.Model):
    class Meta:
        verbose_name = 'Blog'
        verbose_name_plural = 'Blogs'

    title = models.CharField(max_length=100)
    image = models.ImageField(upload_to='images')
    content = models.TextField()

    def __str__(self):
        return self.title
