package com.leds.Service.Impl;

import com.leds.Repository.EnquiryRepository;
import com.leds.Service.EnquiryService;
import com.leds.entity.Enquiry;
import com.leds.exception.ResourceNotFoundException;
import com.leds.payload.EnquiryDto;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class EnquiryServiceImpl implements EnquiryService {

    private EnquiryRepository enquiryRepository;

    public EnquiryServiceImpl(EnquiryRepository enquiryRepository) {

        this.enquiryRepository = enquiryRepository;
    }

    @Override
    public EnquiryDto createEnqiury(EnquiryDto enquiryDto) {
        //convert into entity
        Enquiry enquiry = new Enquiry();
        enquiry.setTitle(enquiryDto.getTitle());
        enquiry.setDescription((enquiryDto.getDescription()));
        enquiry.setContent(enquiryDto.getContent());
        Enquiry newenquiry = enquiryRepository.save(enquiry);

        //response back to postman
        EnquiryDto dto = new EnquiryDto();
        dto.setId(newenquiry.getId());
        dto.setTitle(newenquiry.getTitle());
        dto.setDescription(newenquiry.getDescription());
        dto.setContent(newenquiry.getContent());

        return dto;
    }

    @Override
    public List<EnquiryDto> listAllEnquiry() {
        List<Enquiry> enquirys = enquiryRepository.findAll();

        List<EnquiryDto> enquiryDtos = enquirys.stream().map(enquiry -> mapTODto(enquiry)).collect(Collectors.toList());
        return enquiryDtos;
    }

    @Override
    public EnquiryDto findById(long id) {
        Enquiry enquiry = enquiryRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Led NOT FOUND WITH ID: "+id)
        );
        return mapTODto(enquiry);
    }
    @Override
    public EnquiryDto updateEnquiry(long id, EnquiryDto enquiryDto) {
        Enquiry enquiry = enquiryRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Led NOT FOUND WITH ID: "+id)
        );
        Enquiry newenquiry = mapToEntity(enquiryDto);
        newenquiry.setId(id);
        //save and convert back into the dto
        Enquiry updateEnquiry = enquiryRepository.save(newenquiry);
        EnquiryDto dto = mapTODto(updateEnquiry);
        return dto;
    }

    @Override
    public void deleteEnquiryById(long id) {
        Enquiry enquiry = enquiryRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Led NOT FOUND WITH ID: "+id)
        );
        enquiryRepository.deleteById(id);
    }

    EnquiryDto mapTODto(Enquiry enquiry) {  //--------------------2
        EnquiryDto dto = new EnquiryDto();
        dto.setId(enquiry.getId());
        dto.setTitle(enquiry.getTitle());
        dto.setDescription(enquiry.getDescription());
        dto.setContent(enquiry.getContent());
        return dto;
    }

    Enquiry mapToEntity(EnquiryDto enquiryDto){  // ------------  4
        Enquiry enquiry = new Enquiry();
        enquiry.setId(enquiryDto.getId());
        enquiry.setTitle(enquiryDto.getTitle());
        enquiry.setContent(enquiryDto.getContent());
        enquiry.setDescription((enquiryDto.getDescription()));
        return enquiry;
    }

}