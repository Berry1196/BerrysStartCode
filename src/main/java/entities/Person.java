package entities;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@NamedQuery(name = "Person.deleteAllRows", query = "DELETE from Person")
@Table(name = "Person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
    private List<Car> cars = new ArrayList();

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public void addCar(Car car) {
        cars.add(car);
        car.setPerson(this);
    }


}
