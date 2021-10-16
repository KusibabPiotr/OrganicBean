package ka.piotr.organicbean.product.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = PostCodeValidator.class)
public @interface ValidPostCode {

    String message() default "Doesn't seem to be a valid post code!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
