from django.db import models

# Create your models here.


class Estudiante(models.Model):
    nombre = models.CharField(max_length=100)
    apellidos = models.CharField(max_length=100)
    edad = models.IntegerField(default=0)
    foraneo = models.BooleanField(default=False)
    promedio = models.FloatField(default=9.8)

    def __str__(self):
        return self.nombre + " " + self.apellidos

    def aspira_a_beca(self):
        return self.promedio >= 9


class Carrera(models.Model):
    # Tipo 1 : Licenciatura, Tipo 2: Ingenieria
    LICENCIATURA = 1
    INGENIERIA = 2
    OPCIONES_TIPO = ((LICENCIATURA, "Licenciatura"), (INGENIERIA, "Ingenieria"))
    estudiante = models.ForeignKey(Estudiante, on_delete=models.CASCADE)
    tipo = models.IntegerField(choices=OPCIONES_TIPO, null=True, blank=True)
    nombre = models.CharField(max_length=100)

    def __str__(self):
        return str(self.tipo) + " " + str(self.nombre)

    def get_tipo(self):
        return Carrera.OPCIONES_TIPO[self.tipo - 1][1]
