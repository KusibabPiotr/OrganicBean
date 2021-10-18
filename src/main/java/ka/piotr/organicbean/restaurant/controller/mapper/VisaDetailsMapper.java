package ka.piotr.organicbean.restaurant.controller.mapper;

import ka.piotr.organicbean.restaurant.model.domain.VisaDetails;
import ka.piotr.organicbean.restaurant.model.dto.VisaDetailsDto;
import org.springframework.stereotype.Component;

@Component
public class VisaDetailsMapper {

    public VisaDetails mapToVisaDetails(final VisaDetailsDto visaDetailsDto){
        return new VisaDetails(visaDetailsDto.getId(),
                visaDetailsDto.getCcNumber(),
                visaDetailsDto.getCcExpiration(),
                visaDetailsDto.getCcCVV());
    }

    public VisaDetailsDto mapToVisaDetailsDto(final VisaDetails visaDetails){
        return new VisaDetailsDto(visaDetails.getId(),
                visaDetails.getCcNumber(),
                visaDetails.getCcExpiration(),
                visaDetails.getCcCVV());
    }
}
