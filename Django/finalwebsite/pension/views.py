from django.shortcuts import render
from django.http import HttpResponse
from .models import Pensionado
from django.template import loader

# Create your views here.
def index(request):
    template = loader.get_template("pension/index.html")
    contexto = {}
    return HttpResponse(template.render(contexto, request))


def simulacion(request):
    nombre = request.POST.get("nombre")
    edad_actual = request.POST.get("edad_actual")
    edad_retiro = request.POST.get("edad_retiro")
    saldo_acumulado = request.POST.get("saldo_acumulado")
    ahorro_mensual = request.POST.get("ahorro_mensual")
    genero = request.POST.get("genero")

    pensionado = Pensionado(
        nombre=nombre,
        edad_actual=edad_actual,
        edad_retiro=edad_retiro,
        saldo_acumulado=saldo_acumulado,
        ahorro_mensual=ahorro_mensual,
        genero=genero,
    )
    pensionado.save()
    return HttpResponse(
        "%s tendrás una pensión de %s pesos"
        % pensionado.nombre
        % pensionado.pension_mensual
    )


def listado(request):
    Pensionado.objects.all()
    template = loader.get_template("pension/listadod.html")
