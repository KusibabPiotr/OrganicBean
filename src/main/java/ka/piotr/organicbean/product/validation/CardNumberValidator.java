package ka.piotr.organicbean.product.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class CardNumberValidator implements ConstraintValidator<ValidCardNumber,String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value.isEmpty() || value.isBlank()) return false;
        value = value.replaceAll("[\\s()-]", "");
        return Pattern.compile("^\\d{16}$").matcher(value).matches();
    }
}
