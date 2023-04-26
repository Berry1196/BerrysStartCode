package facades;

import dtos.CarDTO;
import dtos.PersonDTO;
import entities.Car;
import entities.Person;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class PersonFacade {

    private static PersonFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private PersonFacade() {
    }



    /**
     * @param _emf
     * @return an instance of this facade class.
     */
    public static PersonFacade getPersonFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public PersonDTO create(PersonDTO personDTO) {
        Person person = new Person(personDTO.getFirstName(), personDTO.getLastName());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(person);
    }

    public List<CarDTO> getAllCars() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Car> query = em.createQuery("SELECT c FROM Car c", Car.class);
        List<Car> cars = query.getResultList();
        return new CarDTO().getDTOs(cars);
    }

    // Get a car by id
    public PersonDTO getPersonById(Long id) {
        EntityManager em = emf.createEntityManager();
        Person person = em.find(Person.class, id);
        if (person == null) {
            throw new NullPointerException("No person with that id");
        }
        return new PersonDTO(person);
    }


    public void deleteCar(Long id) {
        EntityManager em = emf.createEntityManager();
        Car car = em.find(Car.class, id);
        try {
            em.getTransaction().begin();
            em.remove(car);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    //Add a car to a person
    public void addCarToPerson(Long personId, Long carId) {
        EntityManager em = emf.createEntityManager();
        Person person = em.find(Person.class, personId);
        Car car = em.find(Car.class, carId);
        try {
            em.getTransaction().begin();
            person.addCar(car);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }

    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        PersonFacade fe = getPersonFacade(emf);
//        CarFacade ce = CarFacade.getCarFacade(emf);
////        Person p1 = new Person("Jens", "Jensen");
//        Car c1 = new Car("Tesla", "Model 3", "12345678");
//        fe.create(new PersonDTO(p1));
//        ce.create(new CarDTO(c1));
//       fe.addCarToPerson(4L, 4L);
//       System.out.println(c1.getPerson_cars().getId());

        System.out.println(fe.getPersonById(1L));

    }


}
