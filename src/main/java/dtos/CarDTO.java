package dtos;

import entities.Car;
import entities.Person;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO implements Serializable {
    private Long id;
    private String brand;
    private String model;
    private String numberPlate;
    private InnerPersonDTO person;



    public CarDTO(String brand, String model, String numberPlate) {
        this.brand = brand;
        this.model = model;
        this.numberPlate = numberPlate;
    }

    public CarDTO(Car c) {

       if (c.getId() != null){
            this.id = c.getId();
        }
        this.brand = c.getBrand();
        this.model = c.getModel();
        this.numberPlate = c.getNumberPlate();
        if (c.getPerson() != null){
            this.person = new InnerPersonDTO(c.getPerson());
        }
    }


    public  List<CarDTO> getDTOs(List<Car> car){
        List<CarDTO> carDTOs = new ArrayList();
        car.forEach(c->carDTOs.add(new CarDTO(c)));
        return carDTOs;
    }
    @Getter
    @Setter
    @ToString
    @Data
    @EqualsAndHashCode
    public static class InnerPersonDTO implements Serializable {
        private Long id;
        private String firstName;
        private String lastName;
        private List<CarDTO> cars;

        public InnerPersonDTO(Long id, String firstName, String lastName) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;

        }

        public InnerPersonDTO(Person person) {
            if (person.getId() != null){
                this.id = person.getId();
            }
            this.firstName = person.getFirstName();
            this.lastName = person.getLastName();

        }


    }


}
