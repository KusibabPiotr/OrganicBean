package ka.piotr.organicbean.restaurant.controller.mapper;

import ka.piotr.organicbean.restaurant.model.domain.VisaDetails;
import ka.piotr.organicbean.restaurant.model.dto.VisaDetailsDto;

public class VisaDetailsMapper {

    private VisaDetailsMapper() {
    }

    public static VisaDetails mapToVisaDetails(final VisaDetailsDto visaDetailsDto){
        return new VisaDetails(visaDetailsDto.getId(),
                visaDetailsDto.getCcNumber(),
                visaDetailsDto.getCcExpiration(),
                visaDetailsDto.getCcCVV());
    }

    public static VisaDetailsDto mapToVisaDetailsDto(final VisaDetails visaDetails){
        return new VisaDetailsDto(visaDetails.getId(),
                visaDetails.getCcNumber(),
                visaDetails.getCcExpiration(),
                visaDetails.getCcCVV());
    }
}
