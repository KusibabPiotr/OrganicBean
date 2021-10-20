package ka.piotr.organicbean.restaurant.model.domain;

import ka.piotr.organicbean.restaurant.model.AllergenType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ALLERGENS")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Allergen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;
    @Enumerated(EnumType.STRING)
    private AllergenType allergenType;

}
