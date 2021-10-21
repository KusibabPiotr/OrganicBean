package ka.piotr.organicbean.security.model.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

//@Entity(name = "USERS")
//@NoArgsConstructor
@AllArgsConstructor
@Getter
//@Setter
public class User {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @NotNull
//    private Long id;
    private String username;
    private String password;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        User user = (User) o;
//
//        return id.equals(user.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return id.hashCode();
//    }

}