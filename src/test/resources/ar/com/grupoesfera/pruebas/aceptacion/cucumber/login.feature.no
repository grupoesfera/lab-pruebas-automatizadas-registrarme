Feature: Login de un sitio web

  Scenario: Login erroneo por clave incorrecta
    Given existe el usuario admin@sitio.com con clave 123
    When intento loguearme con usuario admin@sitio.com y clave 321
    Then se retorna el mensaje 'usuario o clave incorrecta'

  Scenario: Login exitoso
    Given existe el usuario seba con clave 123
    When intento loguearme con usuario seba y clave 123
    Then se retorna el mensaje 'login exitoso'
