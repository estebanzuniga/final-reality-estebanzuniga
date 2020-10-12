Final Reality
=============

![http://creativecommons.org/licenses/by/4.0/](https://i.creativecommons.org/l/by/4.0/88x31.png)

This work is licensed under a 
[Creative Commons Attribution 4.0 International License](http://creativecommons.org/licenses/by/4.0/)

Context
-------

This project's goal is to create a (simplified) clone of _Final Fantasy_'s combat, a game developed
by [_Square Enix_](https://www.square-enix.com)
Broadly speaking for the combat the player has a group of characters to control and a group of 
enemies controlled by the computer.

---

Para lograr el objetivo del proyecto se deben crear los personajes, estos se dividen en los
controlados por el jugador y los controlados por el computador.

Para crear estos personajes se construyó la interfaz ICharacter, la cual implementa la clase
AbstractCharacter, esta clase implementa los métodos declarados en la interfaz.

Luego, se creó la clase PlayerCharacter que da el paso para crear las distintas clases para los
distintos tipos de personajes manipulables por el jugador. Para crear los enemigos se construyó
la clase Enemy. Estas dos clases heredan de AbstractCharacter.

----------  Fin tarea 1  ----------

Por implementar:

Distintos tipos de PlayerCharacter y Weapon
