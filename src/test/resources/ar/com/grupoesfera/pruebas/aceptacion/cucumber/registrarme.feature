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

