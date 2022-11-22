# Two-Dice-Gam# TwoDiceGame-JwtSecurity-Mongo

API con interfaz visual de un juego de dados que consiste en sacar un 7 para ganar. En el juego tenemos un registro de usuarios, partidas y ranquings. Todo está encritado y con Json Web Tokens.

## Instalación

El proyecto puede ser descargado en un archivo .zip y hacerlo funcionar en tu IDE.

***Importante!:*** tengo instalado y uso lombok en mi IDE. Si no tienes lombok tendrás que crear setters, getters y constructores de las clases.
  
La API funciona con SQL, tienes el script de la base de datos en este repositorio (game.sql).

## Endpoints

Entra en:

http://localhost:9001

Y tendrás que iniciar sesión o registrarte. Todo la interdaz es visual e intuitiva así que puedes ir investigando los usuarios simplemente usando la API.
Si quieres saber exactamente los endopoinds y como funcionan al detalle ves a la clase DadosController y RegistroController (hábitualmente llamadas AuthController y Controller).

Te dejo 3 gif con ejemplos de lo que hace:

![1](https://user-images.githubusercontent.com/107991714/203300997-4702d0d2-dc48-4d5c-8c7c-06b384456501.gif)

![2](https://user-images.githubusercontent.com/107991714/203301011-9dceae6f-e770-4699-a697-93e71e37621e.gif)

![3](https://user-images.githubusercontent.com/107991714/203301035-03cb0c5f-9dd8-4938-b49a-60c51d31dc10.gif)



## API Design

La estructura es la siguiente:


