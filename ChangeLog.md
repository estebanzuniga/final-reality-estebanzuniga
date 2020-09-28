ChangeLog
=========

Version 2.0
-----------

- 
- Se quitó equipWeapon(Weapon weapon) y getEquippedWeapon() de ICharacter
  y AbstractCharacter, debido a que Enemy no puede equiparse armas y se
  implementaron en PlayerCharacter
- Se agregaron los test faltante del código base

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