from django.shortcuts import render, get_object_or_404
from django.http import HttpResponse, Http404
from .models import Estudiante, Carrera
from django.template import loader

# Create your views here.
def index(request):
    estudiantes = Estudiante.objects.order_by("nombre")
    # template = loader.get_template("myfirstapp/index.html")
    contexto = {"estudiantes": estudiantes}
    return render(request, "myfirstapp/index.html", contexto)
    # return HttpResponse(template.render(contexto, request))


def detalles(request, estudiante_id):
    estudiante = get_object_or_404(Estudiante, pk=estudiante_id)
    return render(request, "myfirstapp/detalles.html", {"estudiante": estudiante})


def borra_estudiante(request, estudiante_id):
    estudiante = Estudiante.objects.get(pk=estudiante_id)
    estudiante.delete()
    return HttpResponse("Borramos al estudiante %s" % estudiante_id)


def modifica_estudiante(request, estudiante_id, promedio):
    estudiante = Estudiante.objects.get(pk=estudiante_id)
    estudiante.promedio = float(promedio)
    estudiante.save()
    return HttpResponse("Actualizamos al estudiante %s" % estudiante_id)


def carreras(request, estudiante_id):
    return HttpResponse("Carreras del estudiante %s" % estudiante_id)


def agrega_carrera(request, estudiante_id, tipo, nombre):
    estudiante = Estudiante.objects.get(pk=estudiante_id)
    estudiante.carrera_set.create(tipo=int(tipo), nombre=nombre)
    return HttpResponse("Carrera agregada al estudiante %s" % estudiante_id)


def agrega_estudiante(request, nombre, apellidos, edad, foraneo, promedio):
    foraneo = foraneo.upper() == "TRUE"
    estudiante = Estudiante(
        nombre=nombre,
        apellidos=apellidos,
        edad=int(edad),
        foraneo=foraneo,
        promedio=float(promedio),
    )
    estudiante.save()
    return HttpResponse("El estudiante %s fue creado" % estudiante.id)


def agrega_estudiante_post(request):
    nombre = request.POST.get("nombre")
    apellidos = request.POST.get("apellidos")
    edad = request.POST.get("edad")
    promedio = request.POST.get("promedio")

    if request.POST.get("foraneo") == None:
        foraneo = False
    else:
        foraneo = request.POST.get("foraneo")

    estudiante = Estudiante(
        nombre=nombre,
        apellidos=apellidos,
        edad=int(edad),
        foraneo=foraneo,
        promedio=float(promedio),
    )
    estudiante.save()
    return HttpResponse("El estudiante %s fue creado" % estudiante.id)
