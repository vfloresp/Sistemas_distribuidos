from django.urls import path
from . import views

app_name = "pension"

urlpatterns = [
    path("", views.index, name="index"),
    path("simulacion", views.simulacion, name="simulacion"),
    path("listado", views.listado, name="listado"),
]
