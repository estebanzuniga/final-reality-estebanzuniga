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

Estructura
-------

Para lograr el objetivo del proyecto se deben crear los personajes, estos se dividen en los
controlados por el jugador y los controlados por el computador.

Para crear estos personajes se construyó la interfaz ICharacter, la cual implementa la clase
AbstractCharacter, esta clase implementa los métodos declarados en la interfaz.

Para crear los enemigos se construyó la clase Enemy. Esta clase hereda de AbstractCharacter.

Luego, para especializar a los personajes controlados por el jugador, se creó la interfaz
IPlayerCharacter. Se creó la clase abstracta AbstractPlayerCharacter que da el paso para crear
las distintas clases para los distintos tipos de personajes manejables por el jugador, estas
clases son: Engineer, Knight y Thief, además, una clase abstracta AbstractMage,
de la cual heredan WhiteMage y BlackMage.

Para las armas, se creó una interfaz llamada IWeapon, la cual contiene algunos métodos que
implementarán sus clases hijas, las que corresponden a una clase por arma. Las clases son:
Axe, Bow, Knife, Staff y Sword.

---

Combate
-------

El combate consiste en el ataque de un personaje a otro. Para poder realizar este ataque se crearon
distintos métodos de la forma attackedByNNN, siendo el nombre de una clase de cualquier personaje.
Estos métodos son llamados por el método attack de su respectiva clase. Por ejemplo en la clase
Engineer existe el método attack y este a su vez llama al método attackedByEngineer.

Los métodos attackedByNNN generan el daño del atacante como el daño del arma del atacante, menos
la defensa del atacado, es decir, por medio de la siguiente fórmula:

daño = dañoArmaAtacante - defensaAtacado

Luego, se actualiza la vida de personaje, obteniéndola y cambiándola por medio de métodos
getters y setters propios de la clase del personaje atacado, como:

nuevaVida = vida - daño

---

Por el momento los valores de vida, daño y defensa son estándar para personajes y armas, esto
en un futuro, dependiendo de las exigencias del proyecto, puede variar.

Los valores, por ahora, son iguales para todos los personajes y armas, esto cambiará.

Por implementar:

- Magias