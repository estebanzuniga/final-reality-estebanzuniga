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

### Arquitectura del proyecto

Para la realización del proyecto se aplicó el patrón de arquitectura "modelo-vista-controlador"

# Modelo

### Estructura

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

### Equipamiento

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

### Combate

El combate consiste en el ataque de un personaje a otro. Para poder realizar este ataque se creó
el método ``attack``. A este método se le hace override en la clase ``Enemy`` y en ``AbstractPlayerCharacter``,
ya que los enemigos y personajes del jugador atacan de distinta forma. 

Un ataque solo se puede realizar si el personaje atacado está vivo en el momento del ataque,
para esto se creó el método ``isAlive()``

Si el personaje que ataca es del jugador, el método attack genera el daño del atacante como el daño del
arma del atacante, menos la defensa del atacado, es decir, por medio de la siguiente fórmula:

``daño = dañoArmaAtacante - defensaAtacado``

Y si el personaje que ataca es un enemigo, el daño se calcula como:

``daño = dañoEnemigo - defensaAtacado``

Luego, se actualiza la vida de personaje atacado, obteniéndola y cambiándola por medio de métodos
getters y setters propios de la clase del personaje atacado, como:

``nuevaVida = vida - daño``

---

# Controlador

Siguiendo la arquitectura de este proyecto, corresponde crear un controlador que una todo lo creado
en el modelo. Este controlador fue creado e implementado en la clase ``GameController``. 
El fin de la creación de un controlador es para que la vista solo interactúe con este y no
directamente con el modelo, lo que provocaría un debilitamiento en la seguridad del proyecto.

La clase ``GameController`` consta de muchos métodos que simulan una tarea dentro del proyecto, como
lo sería un turno, un ataque, un equipamiento, etc. Estos métodos son llamados por la vista y así
crear un flujo definido. Pero algunos métodos se podrían llamar en partes del proyecto que no
corresponde, para solucionar esto se implementó el patrón de diseño State.

Con la implementación de los estados, se crearon cuatro clases, y cada una representa a una fase
dentro del proyecto. Estas fases son:

- Fase inicial: En esta fase se escogen los personajes del usuario, se crean los enemigos y
se completa el inventario.

- Fase de combate: El nombre lo dice, corresponde a la fase donde se desarrolla el combate. Esta
fase permite equipar armas y atacar.

- Fase de fin de turno: Durante esta fase, que sucede cuando un personaje termina su turno,
se extrae al personaje de la cola de turnos y se vuelva a introducir a esta.

- Fase final: Ocurre cuando alguien gana el combate. En esta fase se puede volver a un estado de
inicio, para volver a combatir.

---

# Vista

Se creó una GUI, que representa la vista del proyecto, dentro de nuestra estructura de MVC.
La GUI consta de distintos elementos que ayudan a lograr el objetivo del juego, que es establecer
un combate entre dos bandos.

Se procederá a explicar como se desarrolla el juego y el uso de esta GUI.

El juego comienza con una pantalla de inicio, donde el usuario puede escoger tres personajes para
usarlos en el combate. Para cada personaje hay un botón que crea dicho personaje con valores
de vida y defensa aleatorios. Además, durante este proceso se crean los tres enemigos, con valores
aleatorios de vida, defensa y ataque. También se completa el inventario, el cual consta de cinco
armas: Hacha, arco, cuchillo, bastón y espada. El daño y peso de cada arma es aleatorio. Cuando
el usuario ya escogió sus tres personajes, se cambia de escena.

Cada estadística de un personaje o arma está en un rango establecido de aleatoriedad.

La siguiente escena es la del combate. En la parte superior se encentran los tres enemigos a los
cuales el usuario se enfrenta, representados por botones. Estos enemigos tienen etiquetas que
representan los valores de vida, defensa y ataque.

En la parte inferior de la escena se encuentran los personajes que escogió el usuario con unas
etiquetas con la vida y defensa de cada personaje. Debajo de los personajes está el inventario,
donde cada arma está representada por botones. Cada arma tiene su respectivo nombre y daño.

En la parte de el medio de la escena, hay dos paneles. El del lado izquierdo corresponde a 
distintas etiquetas, las cuales están en constante actualización y representan las estadísticas
del personaje que está de turno.

En el lado derecho del medio de la escena se tienen tres elementos importantes. El primero es un
botón que se debe presionar para simular el ataque del enemigo. Este ataque se hace de manera
aleatoria a cualquier personaje del usuario. El segundo elemento importante son dos etiquetas que
muestra cuantos enemigos y cuantos personajes del usuario están vivos. Esta etiqueta se actualiza
cada vez que algún personaje muere.

El tercer elemento importante es una etiqueta que permite saber qué está sucediendo en el juego.
Esta etiqueta está en constante actualización, ya que el usuario debe saber qué personaje le está
de turno. Además indica cuando el usuario debe escoger un arma, y también avisa cuando no se puede
equipar un arma.

El botón de cada enemigo se desactivan cuando este muere, así no se puede atacar a un enemigo que
no está en combate. Por su parte, en cada turno de un personaje del usuario, un botón de arma se
desactiva si el personaje actual no puede equipar dicha arma.

El combate se desarrolla hasta que algún bando gana.

Si el usuario ganó, aparece una escena de felicitaciones, en caso contrario, aparece una escena
informando al usuario que perdió.

En ambas escenas hay un botón para jugar de nuevo. Si el usuario lo presiona, se vuelva a la escena
inicial y el usuario puede comenzar otro combate. Si el usuario no quiere jugar de nuevo, solo debe
cerrar la aplicación.

This is Final Fantasy, may the best win...