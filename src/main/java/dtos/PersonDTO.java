package dtos;

import entities.Car;
import entities.Person;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PersonDTO implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private List<InnerCarDTO> cars = new ArrayList();

    public PersonDTO(String firstName, String lastName, List<InnerCarDTO> cars) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cars = cars;
    }

    public PersonDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public PersonDTO(Person p) {
        if (p.getId() != null) {
            this.id = p.getId();
        }
        this.firstName = p.getFirstName();
        this.lastName = p.getLastName();
        if(p.getCars() != null)
        {
            p.getCars().forEach(c -> this.cars.add(new InnerCarDTO(c)));
        }
    }


    public List<PersonDTO> getDTOs(List<Person> persons) {
        List<PersonDTO> personDTOs = new ArrayList();
        persons.forEach(p -> personDTOs.add(new PersonDTO(p)));
        return personDTOs;
    }

    @EqualsAndHashCode
    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    public class InnerCarDTO implements Serializable {
        private Long id;
        private String brand;
        private String model;
        private String numberPlate;

        public InnerCarDTO(Long id, String brand, String model, String numberPlate) {
            this.id = id;
            this.brand = brand;
            this.model = model;
            this.numberPlate = numberPlate;
        }

        public InnerCarDTO(Car c) {
            if (c.getId() != null) {
                this.id = c.getId();
            }
            this.brand = c.getBrand();
            this.model = c.getModel();
            this.numberPlate = c.getNumberPlate();
        }


    }
}
