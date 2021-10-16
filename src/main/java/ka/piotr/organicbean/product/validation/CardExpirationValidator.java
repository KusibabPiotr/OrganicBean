package ka.piotr.organicbean.product.validation;

import lombok.SneakyThrows;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CardExpirationValidator implements ConstraintValidator<ValidCardExpiration,String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value.isEmpty() || value.isBlank()) return false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy").withZone(ZoneId.of("UTC"));
        try{
            YearMonth yearMonth = YearMonth.parse(value, formatter);
            return yearMonth.isAfter(YearMonth.now());
        } catch (DateTimeParseException e){
            return false;
        }
    }
}
