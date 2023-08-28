package com.leds.Service;

import com.leds.payload.EnquiryDto;
import java.util.List;

public interface EnquiryService {
      EnquiryDto createEnqiury(EnquiryDto enquiryDto);

      List<EnquiryDto> listAllEnquiry();

      EnquiryDto findById(long id);

      EnquiryDto updateEnquiry(long id, EnquiryDto enquiryDto);

      void deleteEnquiryById(long id);
}
