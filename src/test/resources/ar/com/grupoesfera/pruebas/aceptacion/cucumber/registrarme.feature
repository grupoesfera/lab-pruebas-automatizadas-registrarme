# language: en

Feature: Registrarme y darme de alta en el sitio

  Scenario: Si el usuario no existe en el sitio, el mismo se da de alta y se redirige al login
    Given que ingreso el usuario maria@maria.com con clave 1234
    When intento registrarme
    Then el usuario se crea y me redirige a la vista login

  Scenario: Si el usuario ya existe en el sitio, el mismo NO se da de alta y vuelve a la vista de registro
    Given que ya existe el usuario pedro@pedro.com con clave 1234
    When intento registrarme
    Then el usuario NO se crea y me redirige a la vista registrarme y muestra el mensaje 'El usuario ya existe'

  Scenario: Si el formato de usuario es incorrecto NO se da de alta y vuelve a la vista de registro
    Given que ingreso el usuario maria con clave 1234
    When intento registrarme
    Then el usuario NO se crea y me redirige a la vista registrarme y muestra el mensaje 'El formato del usuario no es una direccion de email válida'