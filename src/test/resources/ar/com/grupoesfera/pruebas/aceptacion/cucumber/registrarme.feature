# language: en

Feature: Registrarme y darme de alta en el sitio

  Scenario: Si el usuario no existe en el sitio, el mismo se da de alta y se redirige al login
    Given que no existe el usuario maria@maria.com
    When ingreso a nuevo-usuario
      And ingreso el usuario maria@maria.com
      And ingreso la clave 1234
      And intento registrarme
    Then me redirige a la vista login
      And el usuario maria@maria.com con clave 1234 se crea


  Scenario: Si el usuario ya existe en el sitio, el mismo NO se da de alta y vuelve a la vista de registro
    Given que ya existe el usuario pedro@pedro.com con clave 1234
    When ingreso a nuevo-usuario
      And ingreso el usuario pedro@pedro.com
      And ingreso la clave 1234
      And intento registrarme
    Then el usuario NO se crea
      And me redirige a la vista registrarme
      And muestra el mensaje 'El usuario ya existe'

  Scenario: Si el formato de usuario es incorrecto NO se da de alta y vuelve a la vista de registro
    Given
    When ingreso a nuevo-usuario
      And ingreso el usuario pedro.com
      And ingreso la clave 1234
      And intento registrarme
    Then el usuario NO se crea
      And me redirige a la vista registrarme
      And muestra el mensaje 'El formato del usuario no es una direccion de email válida'


# ejercicio: agregar el escenario que valida formato de la clave a partir que existe un bug, que primero codeen
# la prueba y de rojo, luego agreguen el comportamiento y deberia pasar la prueba