package ka.piotr.organicbean.product.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CUSTOMERS")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String houseNumber;
    private String street;
    private String city;
    private String postCode;
    private String phoneNumber;
    @OneToMany
    private List <Order> orderList;
    private String ccNumber;
    private String ccExpiration;
    private String ccCVV;
}
