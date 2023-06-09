package entities;

import dtos.PersonDTO;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@NamedQuery(name = "Car.deleteAllRows", query = "DELETE from Car")
@Table(name = "Cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private String numberPlate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;

    public Car(String brand, String model, String numberPlate) {
        this.brand = brand;
        this.model = model;
        this.numberPlate = numberPlate;
    }


}

