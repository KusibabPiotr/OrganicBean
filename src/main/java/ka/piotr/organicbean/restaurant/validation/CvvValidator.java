package ka.piotr.organicbean.restaurant.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class CvvValidator implements ConstraintValidator<ValidCvv,String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value.isEmpty() || value.isBlank()) return false;
        value = value.replaceAll("[\\s()-]", "");
        return Pattern.compile("^\\d{3}$").matcher(value).matches();
    }
}
