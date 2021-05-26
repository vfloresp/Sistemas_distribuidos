from django.db import models

# Create your models here


class Pensionado(models.Model):
    nombre = models.CharField(max_length=100)
    edad_actual = models.IntegerField(default=0)
    edad_retiro = models.IntegerField(default=0)
    saldo_acumulado = models.FloatField(default=0.0)
    ahorro_mensual = models.FloatField(default=0.0)
    genero = models.BooleanField(default=True)

    def pension_mensual(self):
        return (
            (self.edad_actual + self.edad_retiro) * self.ahorro_mensual
        ) / self.saldo_acumulado
