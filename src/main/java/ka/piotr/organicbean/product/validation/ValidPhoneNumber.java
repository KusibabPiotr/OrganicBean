package ka.piotr.organicbean.product.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneNumberValidator.class)
public @interface ValidPhoneNumber {

    String message() default "Doesn't seem to be a valid number!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
