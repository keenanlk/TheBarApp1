from rest_framework import serializers

from django.contrib.auth.models import User
from api.models import Bar, Review

#Define API

class BarSerializer(serializers.ModelSerializer):
    class Meta:
        model = Bar
        fields = ('id', 'BarName')

class ReviewSerializer(serializers.ModelSerializer):
    Bar = serializers.SlugRelatedField(
        many=False,
        read_only=False,
        slug_field = 'BarName',
        queryset=Bar.objects.all())
    class Meta:
        model = Review
        fields = ('id', 'Bar', 'Drinks', 'Food', 'Atmosphere', 'Comment')

