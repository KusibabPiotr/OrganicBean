package ka.piotr.organicbean.restaurant.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = CvvValidator.class)
public @interface ValidCvv {

    String message() default "Doesn't seem to be a valid Cvv!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
