package ar.com.grupoesfera.pruebas.aceptacion.cucumber;

import cucumber.runtime.java.picocontainer.PicoFactory;

/**
 * Extension of the standard PicoContainer ObjectFactory
 * which will register the proper AutomatioApi implementation
 * based on a system property.
 */
public class FabricaDeAdaptadoresDeTest extends PicoFactory {

    public FabricaDeAdaptadoresDeTest(){
        addClass(AdaptadorParaRegistrarmeWeb.class);
    }
}