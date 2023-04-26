package facades;

import dtos.PersonDTO;
import entities.Car;
import entities.Person;
import org.codehaus.groovy.transform.SourceURIASTTransformation;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonFacadeTest {
    private static EntityManagerFactory emf;
    private static PersonFacade facade;
    private static CarFacade carFacade;

    Person p1, p2, p3;
    Car c1, c2;

    public PersonFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = PersonFacade.getPersonFacade(emf);
        carFacade = CarFacade.getCarFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        p1 = new Person("Peter", "Quill");
        p2 = new Person("Tony", "Stark");
        p3 = new Person("Steve", "Rogers");
        c1 = new Car("Ford", "Mustang", "AB12345");
        c2 = new Car("Tesla", "Model 3", "CD67890");
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Car.deleteAllRows").executeUpdate();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            em.persist(p1);
            em.persist(p2);
            em.persist(p3);
            em.persist(c1);
            em.persist(c2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void createPerson() {
        PersonDTO person = facade.create(new PersonDTO("Peter", "Quill"));
        assertEquals("Peter", person.getFirstName());
    }

    @Test
    public void getPersonByID() {
        PersonDTO person = facade.getPersonById(p1.getId());
        assertEquals("Peter", person.getFirstName());
        System.out.println(person);
    }

    @Test
    public void addCarToPerson() {
        facade.addCarToPerson(p1.getId(), c2.getId());
        facade.addCarToPerson(p1.getId(), c1.getId());
        PersonDTO person = facade.getPersonById(p1.getId());
        assertEquals(2, person.getCars().size());
        System.out.println(person);
    }

}
