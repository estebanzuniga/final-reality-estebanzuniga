ChangeLog
=========

Version 5.0
-----------

- Se aplicó el patrón de diseño "Observer" para notificar al controlador cuando
  ocurren algunos eventos.

- Se creó el controlador.

- Se actualizaron los test.

Version 4.0
-----------

- Se incluyeron las condiciones de que solo algunos personajes
  pueden equipar ciertas armas.

- Se actualizaron los test, manteniendo el 100% de Coverage.

Version 3.0
-----------

- Se completaron los test con 100% de Coverage.

- Se crearon métodos attakedBy(...), los cuales son llamados por el método attack
  de cada clase.

- Se eliminaron las clases CharacterClass y WeaponType.

- En el paquete Weapon se eliminó la clase Weapon y se creó una clase para cada
  tipo de arma, es decir, Axe, Bow, Knife, Staff, Sword.

- Se crearon clases para Engineer, Knight y Thief, además, una clase abstracta
  AbstractMage, de la cual heredan WhiteMage y BlackMage.

- Se añadió la interfaz IPlayerCharacter, la cual implementa la clase
  abstractPlayerCharacter (ex PlayerCharacter).

Version 2.0
-----------

- Se arreglaron los test de acuerdo a los cambios realizado al código base.

- Se creó la interfaz IWeapon, la cual define ciertos métodos que luego implementa
  abstractWeapon. Esto nos ayudará próximamente a crear los diferentes tipos de armas.
  
- Se quitó equipWeapon de ICharacter y AbstractCharacter, debido a que
  Enemy no puede equiparse armas y se implementó en PlayerCharacter.
  
- Se agregaron los test faltante del código base.
  (No sirvió debido a los cambios realizado posteriormente)

Version 1.0
-----------
- (RC.1) Implemented missing tests
- (B.5) Updated License
- (B.4) Implementation and testing of Enemy class (ensured 100% branch coverage)
- (B.3) Created .gitignore
- (B.2) Implementation of most base elements of the model
- (B.1) Created project

A note on the version naming
----------------------------
- B.n: Version ``x.y`` _beta x_, alternative to ``x.y-b.n``.
  For example: ``v1.0-b.3``.
- RC.n: Release candidate x, alternative to ``x.y-rc.n``.
  For example: ``v1.0-rc.2``.