package ka.piotr.organicbean.product.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value.isEmpty() || value.isBlank()) return false;
        value = value.replaceAll("[\\s()-]", "");
        return Pattern.compile("^\\d{9}$").matcher(value).matches();
    }
}
