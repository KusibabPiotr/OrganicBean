package ka.piotr.organicbean.product.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PostCodeValidator implements ConstraintValidator<ValidPostCode,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value.isEmpty() || value.isBlank()) return false;

        return Pattern.compile("^(\\d{2}[- .]?)\\d{3}$").matcher(value).matches();
    }
}
