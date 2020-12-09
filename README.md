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

###Arquitectura del proyecto

Para la realización del proyecto se aplicó el patrón de arquitectura "modelo-vista-controlador"

#Modelo

###Estructura

Para lograr el objetivo del proyecto se deben crear los personajes, estos se dividen en los
controlados por el jugador y los controlados por el computador.

Para crear estos personajes se construyó la interfaz ``ICharacter``, la cual implementa la clase
``AbstractCharacter``, esta clase implementa los métodos declarados en la interfaz.

Para crear los enemigos se construyó la clase ``Enemy``. Esta clase hereda de ``AbstractCharacter``.

Luego, para especializar a los personajes controlados por el jugador, se creó la interfaz
``IPlayerCharacter``. Se creó la clase abstracta ``AbstractPlayerCharacter`` que da el paso para crear
las distintas clases para los distintos tipos de personajes manejables por el jugador, estas
clases son: ``Engineer``, ``Knight`` y ``Thief``, además, una clase abstracta ``AbstractMage``,
de la cual heredan ``WhiteMage`` y ``BlackMage``.

Para las armas, se creó una interfaz llamada ``IWeapon``, la cual contiene algunos métodos que
implementarán sus clases hijas, las que corresponden a una clase por arma. Las clases son:
``Axe``, ``Bow``, ``Knife``, ``Staff`` y ``Sword``.

---

###Equipamiento

Para el equipamiento de armas se utilizó double dispatch, ya que los personajes pueden equiparse
solo algunas armas, para esto se creó el método ``equip``, que llama a un método ``equippedBy...``,
siendo (...) el nombre de una clase de personaje controlado por el jugador, es decir un ``ICharacter``.
Estos métodos se declaran en la interfaz ``IWeapon`` y se definen en la clase abstracta ``AbstractWeapon``.
Ocupan el método ``setEquippedWeapon`` para cambiar la variable que contiene el arma equipada de un personaje.

Cuando un personaje no puede equiparse cierta arma, se hace un ``@Override`` del método
correspondiente. Por ejemplo:

Un ingeniero no puede equiparse un cuchillo, por lo tanto, se sobrescribe el método ``equippedByEngineer``
en la clase ``Knife``, para que no haga nada, es decir, no equipe un cuchillo.

---

###Combate

El combate consiste en el ataque de un personaje a otro. Para poder realizar este ataque se crearon
distintos métodos de la forma ``attackedBy...``, siendo (...) el nombre de una clase de cualquier personaje.
Estos métodos son llamados por el método attack de su respectiva clase. Por ejemplo en la clase
``Engineer`` existe el método attack y este a su vez llama al método ``attackedByEngineer``.

Un ataque solo se puede realizar si el personaje atacado está vivo en el momento del ataque,
para esto se creó el método ``isAlive()``

Los métodos ``attackedBy...`` generan el daño del atacante como el daño del arma del atacante, menos
la defensa del atacado, es decir, por medio de la siguiente fórmula:

``daño = dañoArmaAtacante - defensaAtacado``

Luego, se actualiza la vida de personaje, obteniéndola y cambiándola por medio de métodos
getters y setters propios de la clase del personaje atacado, como:

``nuevaVida = vida - daño``

---

#Controlador

Siguiendo la arquitectura de este proyecto, corresponde crear un controlador que una todo lo creado
en el modelo. Este controlador fue creado e implementado en la clase ``GameController``.

La clase ``GameController`` consta de muchos métodos que simulan una tarea dentro del juego, como
lo sería un turno, un ataque, un equipamiento, etc.

El fin de la creación de un controlador es para que la vista solo interactúe con este y no
directamente con el modelo, lo que provocaría un debilitamiento en la seguridad del proyecto.

---

Por implementar:

- Magias