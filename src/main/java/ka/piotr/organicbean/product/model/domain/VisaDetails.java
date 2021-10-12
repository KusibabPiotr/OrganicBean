package ka.piotr.organicbean.product.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "VISA_DETAILS")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VisaDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ccNumber;
    private String ccExpiration;
    private String ccCVV;
}
