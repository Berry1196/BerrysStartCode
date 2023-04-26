package facades;

import dtos.CarDTO;
import dtos.PersonDTO;
import entities.Car;
import entities.Person;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarFacadeTest {

    private static EntityManagerFactory emf;
    private static CarFacade facade;
    private static PersonFacade personFacade;

    Car c1, c2;
    Person p1, p2, p3;

    public CarFacadeTest() {


    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = CarFacade.getCarFacade(emf);
        personFacade = PersonFacade.getPersonFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {

    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        c1 = new Car("Ford", "Mustang", "AB12345");
        c2 = new Car("Tesla", "Model 3", "CD67890");
        p1 = new Person("Peter", "Quill");
        p2 = new Person("Tony", "Stark");
        p3 = new Person("Steve", "Rogers");
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Car.deleteAllRows").executeUpdate();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            em.persist(c1);
            em.persist(c2);
            em.persist(p1);
            em.persist(p2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void getCarByID() {
        CarDTO car = facade.getCarById(c1.getId());
        assertEquals("Ford", car.getBrand());
        System.out.println(car);
    }

    @Test
    public void getOwnerId() {
        PersonDTO person = personFacade.getPersonById(p1.getId());
        personFacade.addCarToPerson(person.getId(), c1.getId());
        assertEquals(person.getId(), facade.getOwnerId(c1.getId()));
        System.out.println(c1);
    }
}
