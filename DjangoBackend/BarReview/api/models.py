from django.db import models


def generate_id():
    return str(uuid.uuid4()).split("-")[-1] #generate unique ticket id

# Create your models here.
class Bar (models.Model):
    
    BarName = models.TextField()
    
    #this is if we want random ids for bars
    
    #BarID = models.CharField(max_length=255)
    #def save(self, *args, **kwargs):
        #if len(self.BarID.strip(" "))==0:
            #self.BarID = generate_id()
        #super(Bar, self).save(*args, **kwargs)    
    
class Review (models.Model):
    
    Bar = models.ForeignKey(Bar, on_delete=models.CASCADE)
    Drinks = models.IntegerField()
    Food = models.IntegerField()
    Atmosphere = models.IntegerField()
    Comment = models.TextField(default="")
    
    #this is if we want random ids for reviews
    
    #ReviewID = models.CharField(max_length=255) 
    #def save(self, *args, **kwargs):
        #if len(self.ReviewID.strip(" "))==0:
            #self.ReviewID = generate_id()
        #super(Review, self).save(*args, **kwargs)       