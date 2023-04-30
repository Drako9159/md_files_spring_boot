---
id:07
category:education
tag:javascript
filename:07_en_challenge_oracle
language:en
color:green
title:First Alura Challenge
image:/post02.webp
description:Sobre el challenge de Alura
createdAt:27-12-2022
readTime:15 min read
author:Antonio Jaramillo Jaramillo
---
# First Alura Challenge
![logo oracle + alura](https://backendblog.fly.dev/api/v2/images/articles/post02.webp)

In December 2022, the second edition of the bootcamp of [Alura](https://app.aluracursos.com), offered jointly with the company [Oracle](www.oracle.com), this course aims to train developers using web development tools and allows participants to decide their level of training, as well as the possibility to choose the area in which they intend to work, Backend or Frontend.

The frontend training takes you through the basics of React, the most popular framework for web development, and teaches you how to create modern, scalable web applications. In the backend course, you will learn how to create web applications with Java, the most popular language for backend development.

## What is the challenge?
The challenge is a challenge that is proposed to the participants of the bootcamp to create a web application, with this it is sought that the participants put into practice the knowledge acquired during the course, the for this edition has as first challenge the creation of an encryptor.

## What is an encryptor?
A cipher is a web application that allows you to encrypt and decrypt messages, using an encryption algorithm, all the details of this first challenge can be found at [Challenge One](https://www.aluracursos.com/challenges/challenge-one-logica/sprint01-construye-un-encriptador-texto-con-javascript).

## How is the challenge carried out?
The challenge lasts 4 weeks and consists of creating an application to encrypt texts, the idea is to be able to exchange secret messages with other people who know the secret of the encryption used.

The encryption "keys" offered by the challenge are as follows:

> The letter "e" is converted to "enter"
> The letter "i" is converted to "imes"
> The letter "a" is converted to "ai"
> The letter "o" is converted to "ober"
> The letter "u" is converted to "ufat"


### Requirements:

- You must work only with lowercase letters
- No letters with accents or special characters should be used.
- It must be possible to convert a word for the encrypted version and also return an encrypted word for its original version.

For example:
> "gato" => "gaitober"
> gaitober" => "gato"

The page must have fields for
insertion of the text to be encrypted or decrypted, and the user must be able to choose between the two options.
The result must be displayed on the screen.

Extras:
> A button that copies encrypted/decrypted text for the transfer section, i.e. it has the same functionality as ctrl+C or the "copy" option in the application menu.

#### How was it completed?
This challenge was very easy to complete, it applies the main topics taught in the first weeks of the course, such as the use of functions, conditionals, cycles, arrays and objects, as well as the use of the browser console for testing.

In javascript apply the following function to perform the encryption:

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
It replaces the letters with the words found in the values object, if the letter is not found in the object, the same letter is returned.

The following function was used to perform the decryption:


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
This function is in charge of looking for the words found in the exp object and replaces them with the letters found in the values object, if the word is not found in the object the same word is returned.

The browser api offers us some copy options easy to implement, for this we use the following function:

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

It is a good practice to give feedback to the user when an action is performed, for this purpose the following function was used:

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
Finally, validations were performed with regular expressions, using the following function:

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

With all this and following a design of the offered page, I obtained this result, very easy to show in the gihub pages: [Encrypter](https://drako9159.github.io/encryptorByDrako/)