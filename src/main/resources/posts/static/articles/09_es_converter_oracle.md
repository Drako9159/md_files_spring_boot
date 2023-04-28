---
id:09
category:education
tag:java
filename:09_es_converter_oracle
language:es
color:pink
title:Convertidor Challenge
image:/post05.webp
description:About chanllenge by ALura
createdAt:03-03-2023
readTime:15 min lectura
author:Antonio Jaramillo Jaramillo
---
# Convertidor Challenge
![logo oracle + alura](https://backendblog.fly.dev/api/v2/images/articles/post05.webp)

Uno de los retos en del bootcamp de Alura es crear un convertidor de moneda, esto para aquellos quienes optaron por la ruta de aprendizaje backend, en este post se explica como se realizó el convertidor.

## ¿Qué es el convertidor?

El convertidor es una aplicación hecha en java que permite convertir una cantidad de dinero de una moneda a otra, sería util el uso de una API para obtener el valor de la moneda, pero en este caso se utilizó un archivo de texto plano que contiene el valor de cada moneda en relación al valor del dólar.

Adicionalmente, opté por añadir la opción de convertir temperaturas, esto para que el convertidor sea más completo. Es posible convertir de Celsius a Fahrenheit y viceversa.

## ¿Cómo se realizó el convertidor?

El convertidor fue realizado en java, utilizando la librería Swing para la interfaz gráfica, el código completo se puede consultar en el [repositorio](https://github.com/Drako9159/Convertidor) en GitHub, además se añadió un archivo [.jar](https://github.com/Drako9159/Convertidor/releases/tag/1.0) para su ejecución.

### ¿Cómo funciona?

![image-gif](https://backendblog.fly.dev/api/v2/images/articles/post05-01.gif)

### ¿Cómo se realizó?

> Se aplicó un loook & feel de la librería [FlatLaf](https://www.formdev.com/flatlaf/), para darle un aspecto más moderno a la aplicación.

Aplicar un look & feel a la aplicación es muy sencillo, solo se debe añadir la librería al proyecto y añadir el siguiente código en el método main:

![image-complete](https://backendblog.fly.dev/api/v2/images/articles/post05-02.webp)

La clase Primary es la que crea el frame principal de la aplicación, en ella se añaden los componentes de la interfaz gráfica, como los panels, los campos de texto, el menu, y los actions o listeners:

![image-complete](https://backendblog.fly.dev/api/v2/images/articles/post05-03.webp)

El menu funciona como una navegación entre los 3 diferentes paneles que tiene la aplicación, cada panel se activa y desactiva con el método setVisible, el cual recibe un booleano, true para activar y false para desactivar:

![image-complete](https://backendblog.fly.dev/api/v2/images/articles/post05-04.webp)

Se crearon 3 clases para cada panel, estas clases heredan de JPanel, y se añaden al frame principal únicamente para llevar los mensajes de error, la clase para la conversión de moneda queda de la siguiente manera:

![image-gif](https://backendblog.fly.dev/api/v2/images/articles/post05-05.gif)

Después la clase para la conversión de temperatura queda de la siguiente manera:

![image-gif](https://backendblog.fly.dev/api/v2/images/articles/post05-06.gif)

El manejo de las validaciones se realiza con una clase Toast, esto para dar información al usuario sobre el estado de la aplicación, si se realizó la conversión o si hubo algún error, la clase recibe dos parametros, el mensaje que tiene que mostrar y el color del mensaje, el color se puede elegir entre 2 opciones, rojo y verde. Adicionalmente el mensaje debe desaparecer en 4 segundos, esto se realiza con un timer:

![image-gif](https://backendblog.fly.dev/api/v2/images/articles/post05-07.gif)

### Opinión personal

> Este es un reto para aquellos que tienen conocimientos básicos de java, pero que no han tenido la 
> oportunidad de aplicarlos en un proyecto real, es una buena oportunidad para practicar y aprender más 
> sobre la librería Swing.

Si quieres participar en los retos de Alura, puedes consultarlos en [alura](https://cursos.alura.com.br).
O espera a las siguientes ediciones del bootcamp.
