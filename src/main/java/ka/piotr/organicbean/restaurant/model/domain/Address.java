package ka.piotr.organicbean.restaurant.model.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ADDRESSES")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int flatNumber;
    private int houseNumber;
    private String street;
    private String city;
    private String postCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        return id.equals(address.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
