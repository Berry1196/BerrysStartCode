package dtos;

import entities.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@ToString
public class PersonDTO {
private Long id;
    private String firstName;
    private String lastName;
    private List<CarDTO> cars;

    public PersonDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public PersonDTO(Person p) {
        if (p.getId() != null){
            this.id = p.getId();
        }
        this.firstName = p.getFirstName();
        this.lastName = p.getLastName();
        this.cars = new CarDTO().getDTOs(p.getCars());
    }

    public List<PersonDTO> getDTOs(List<Person> persons){
        List<PersonDTO> personDTOs = new ArrayList();
        persons.forEach(p->personDTOs.add(new PersonDTO(p)));
        return personDTOs;
    }
}
