# Generated by Django 3.2.3 on 2021-05-26 04:52

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Estudiante',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('nombre', models.CharField(max_length=100)),
                ('apellidos', models.CharField(max_length=100)),
                ('edad', models.IntegerField(default=0)),
                ('foraneo', models.BooleanField(default=False)),
                ('promedio', models.FloatField(default=9.8)),
            ],
        ),
        migrations.CreateModel(
            name='Carrera',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('tipo', models.IntegerField(blank=True, choices=[(1, 'Licenciatura'), (2, 'Ingenieria')], null=True)),
                ('nombre', models.CharField(max_length=100)),
                ('estudiante', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='myfirstapp.estudiante')),
            ],
        ),
    ]
