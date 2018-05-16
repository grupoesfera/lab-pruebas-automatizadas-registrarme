package ar.com.grupoesfera.pruebas;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-application-context.xml"})
public abstract class SpringTest {

    @Inject
    private SessionFactory sessionFactory;

    protected Session session() {
        return sessionFactory.getCurrentSession();
    }

}
