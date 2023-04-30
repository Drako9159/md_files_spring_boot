---
id:08
category:education
tag:javascript
filename:08_es_challenge_oracle
language:es
color:green
title:Primer Challenge de Alura
image:/post02.webp
description:About chanllenge by ALura
createdAt:27-12-2022
readTime:15 min lectura
author:Antonio Jaramillo Jaramillo
---
# Primer Challenge de Alura
![logo oracle + alura](https://backendblog.fly.dev/api/v2/images/articles/post02.webp)

En diciembre del 2022 comenzó la segunda edición el bootcamp de [Alura](https://app.aluracursos.com), que es ofrecido en conjunto con la empresa [Oracle](www.oracle.com), este curso pretende formar desarolladores utilizando herramientas de desarrollo web y permite que los participantes decidan su nivel de formación así como la posibilidad de elegir el área en la que pretenden desempeñarse, Backend o Frontend.

La formación frontend te lleva de la mano a través de los fundamentos de React, el framework más popular para el desarrollo web, y te enseña a crear aplicaciones web modernas y escalables. En el curso de backend, aprenderás a crear aplicaciones web con Java, el lenguaje más popular para el desarrollo backend.

## ¿Qué es el challenge?
El challenge es un reto que se propone a los participantes del bootcamp para crear una aplicación web, con ello se busca que los participantes pongan en práctica los conocimientos adquiridos durante el curso, el para esta edición se tiene como primero challenge la creación de un encriptador.

## ¿Qué es un encriptador?
Un encriptador es una aplicación web que permite encriptar y desencriptar mensajes, para ello se utiliza un algoritmo de encriptación, todos los detalles de este primer challenge se pueden consultar en [Challenge One](https://www.aluracursos.com/challenges/challenge-one-logica/sprint01-construye-un-encriptador-texto-con-javascript).

## ¿Cómo se realiza el challenge?
El challenge tiene una duración de 4 semanas, consiste en crear una aplicación para encriptar textos, la idea es poder intercambiar mensajes secretos con otras personas que sepan el secreto de la encriptación utilizada.

Las "llaves" de encriptación of ofrecidas por el challenge son las siguientes:

> La letra "e" es convertida para "enter"
> La letra "i" es convertida para "imes"
> La letra "a" es convertida para "ai"
> La letra "o" es convertida para "ober"
> La letra "u" es convertida para "ufat"


### Requisitos:

- Debe funcionar solo con letras minúsculas
- No deben ser utilizados letras con acentos ni caracteres especiales
- Debe ser posible convertir una palabra para la versión encriptada también devolver una palabra encriptada para su versión original.

Por ejemplo:
>"gato" => "gaitober"
> gaitober" => "gato"

La página debe tener campos para
inserción del texto que será encriptado o desencriptado, y el usuario debe poder escoger entre as dos opciones.
El resultado debe ser mostrado en la pantalla.

Extras:
> Un botón que copie el texto encriptado/desencriptado para la > sección de transferencia, o sea que tenga la misma funcionalidad del ctrl+C o de la opción "copiar" del menú de las aplicaciones.

#### ¿Cómo se completó?
Este reto resultó muy sencillo de realizar, aplica los principales temas impartidos en las primeras semanas del curso, como lo son el uso de funciones, condicionales, ciclos, arreglos y objetos, además de la utilización de la consola del navegador para realizar pruebas.

En javascript aplique la siguiente función para realizar la encriptación:

```javascript
function encrypt(letter) {
  const values = {
    e: "enter",
    i: "imes",
    a: "ai",
    o: "ober",
    u: "ufat",
  };
  if (values[letter]) return values[letter];
  return letter;
}
```
Esta se encarga de reemplazar las letras por las palabras que se encuentran en el objeto values, si la letra no se encuentra en el objeto se retorna la misma letra.

Para realizar la desencriptación se utilizó la siguiente función:



```javascript
function decrypt(letter) {
  let check = "";
  const exp = [
    { e: /enter/g },
    { i: /imes/g },
    { a: /ai/g },
    { o: /ober/g },
    { u: /ufat/g },
  ];
  exp.forEach((e) => {
    letter = letter.replace(Object.values(e)[0], Object.keys(e)[0]);
    check = letter;
  });
  return check;
}
```
Esta función se encarga de buscar las palabras que se encuentran en el objeto exp y las reemplaza por las letras que se encuentran en el objeto values, si la palabra no se encuentra en el objeto se retorna la misma palabra.

El api del navegador nos ofrece algunas opciones de copiado sencillas de implementar, para ello se utilizó la siguiente función:

```javascript
copyText.addEventListener("click", async (e) => {
  if (displayEncrypt.value) {
    displayEncrypt.focus();
    displayEncrypt.select();
    toast("Copiado", "green");
    await navigator.clipboard.writeText(displayEncrypt.value);
  } else {
    toast("Nada que copiar", "gray");
  }
});
```

Es una buena practica dar feedback al usuario cuando se realiza una acción, para ello se utilizó la siguiente función:

```javascript
function toast(message, color) {
  toastAlert.style.opacity = "1";
  toastAlert.classList.add("activeAnimation");
  let pushed = `<p class="toastAlert" style="background-color: ${color};">${message}</p>`;
  toastAlert.innerHTML = pushed;
  setTimeout(() => {
    toastAlert.style.opacity = "0";
    toastAlert.classList.remove("activeAnimation");
  }, 3000);
}
```
Por último, las validaciones se realizaron con expresiones regulares, para ello se utilizó la siguiente función:

```javascript
function validations(check) {
  if (check.match(/[0-9]/g)) {
    return "No admine números";
  } else if (check.match(/[A-Z]/g)) {
    return "Solo letras minúsculas";
  } else if (!check.match(/^[a-z\s]+$/g)) {
    return "No debe tener acentos";
  }
  return "passed";
}
```

Con todo esto y siguiendo un diseño de la página ofrecida, obtuve este resultado, muy sencillo desplegado en gihub pages: [Encrypter](https://drako9159.github.io/encryptorByDrako/)