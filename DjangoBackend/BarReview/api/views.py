from django.shortcuts import render
from rest_framework import routers, serializers, viewsets
from BarReview.serializers import BarSerializer, ReviewSerializer
from api.models import Bar, Review
from django_filters import rest_framework as filters

# Create your views here.


#Class to specifically define filter



class ReviewFilter(filters.FilterSet):
    #This can be changed by 
    Bar = filters.CharFilter(field_name='Bar__BarName')
    BarID = filters.CharFilter(field_name='Bar__id')
    class Meta:
        model = Review
        fields = ['Bar']
        
#Define how it gets viewed
class BarViewSet(viewsets.ModelViewSet):
    queryset = Bar.objects.all()
    serializer_class = BarSerializer
    filter_backends = [filters.DjangoFilterBackend]
    filterset_fields = ['id','BarName']
    
    
class ReviewViewSet(viewsets.ModelViewSet):
    Bar = BarSerializer
    queryset = Review.objects.all()
    serializer_class = ReviewSerializer
    filter_backends = [filters.DjangoFilterBackend]
    filter_class = ReviewFilter
