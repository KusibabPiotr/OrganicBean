package ka.piotr.organicbean.restaurant.controller.mapper;

import ka.piotr.organicbean.restaurant.model.domain.VisaDetails;
import ka.piotr.organicbean.restaurant.model.dto.VisaDetailsDto;

public class VisaDetailsMapper {

    private VisaDetailsMapper() {
    }

    public static VisaDetails mapToVisaDetails(final VisaDetailsDto visaDetailsDto){
        return VisaDetails.builder()
                .id(visaDetailsDto.getId())
                .ccNumber(visaDetailsDto.getCcNumber())
                .ccExpiration(visaDetailsDto.getCcExpiration())
                .ccCVV(visaDetailsDto.getCcCVV())
                .build();
    }

    public static VisaDetailsDto mapToVisaDetailsDto(final VisaDetails visaDetails){
        return VisaDetailsDto.builder()
                .id(visaDetails.getId())
                .ccNumber(visaDetails.getCcNumber())
                .ccExpiration(visaDetails.getCcExpiration())
                .ccCVV(visaDetails.getCcCVV())
                .build();
    }
}
