package com.leds.controller;

import com.leds.Service.EnquiryService;
import com.leds.payload.EnquiryDto;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enquiry")
public class EnquiryController {

    private EnquiryService enquiryService;
    public EnquiryController(EnquiryService enquiryService) {

        this.enquiryService = enquiryService;
    }
    //http://localhost:8080/api/enquiry
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<EnquiryDto> createEnquiry(@RequestBody EnquiryDto enquiryDto){
        EnquiryDto dto = enquiryService.createEnqiury(enquiryDto);
        //return dto as response
        return  new ResponseEntity<>(dto,HttpStatus.CREATED);
    }
    //http://localhost:8080/api/enquiry
    @GetMapping
    public List<EnquiryDto> listAllEnquiry(){
        List<EnquiryDto> enquiryDtos = enquiryService.listAllEnquiry();
        return enquiryDtos;
    }
    //http://localhost:8080/api/enquiry/1
    @GetMapping("/{id}")
    public ResponseEntity<EnquiryDto> getEnquiryById(@PathVariable("id") long id){
        EnquiryDto dto  = enquiryService.findById(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    //http://localhost:8080/api/posts/1
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")        // -------------- 4
    public ResponseEntity<EnquiryDto> updateEnquiry(
            @RequestBody EnquiryDto enquiryDto,
            @PathVariable("id") long id
    ) {
        EnquiryDto dto = enquiryService.updateEnquiry(id, enquiryDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    //http://localhost:8080/api/enquiry/1
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEnquiryById(@PathVariable("id") long id) {
        enquiryService.deleteEnquiryById(id);

        return new ResponseEntity<>("ENQUIRY IS DELETED",HttpStatus.OK);
    }

}

