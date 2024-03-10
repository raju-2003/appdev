package com.educonnect.raj_narayanan.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.educonnect.raj_narayanan.dto.request.InstituteRequest;
import com.educonnect.raj_narayanan.dto.response.CommonResponse;
import com.educonnect.raj_narayanan.dto.response.InstituteResponse;
import com.educonnect.raj_narayanan.model.Institute;
import com.educonnect.raj_narayanan.repository.InstituteRepository;
import com.educonnect.raj_narayanan.service.InstituteService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InstituteServiceImpl implements InstituteService{

    private final InstituteRepository instituteRepository;

    @Override
    public CommonResponse addInstitute(InstituteRequest request) {
        Optional<Institute> optionalInstitute = instituteRepository.findByInstitutename(request.getInstitutename());
        if(optionalInstitute.isPresent()) {
            return CommonResponse.builder()
                    .message("Institute already exists")
                    .build();
        } else {
            Institute institute = Institute.builder()
                    .institutename(request.getInstitutename())
                    .institutedescription(request.getInstitutedescription())
                    .instituteaddress(request.getInstituteaddress())
                    .institutephone(request.getInstitutephone())
                    .instituteemail(request.getInstituteemail())
                    .institutewebsite(request.getInstitutewebsite())
                    .noofcoursesavailale(request.getNoofcoursesavailale())
                    .build();
            institute = instituteRepository.save(institute);
            return CommonResponse.builder()
                    .message("Institute added successfully")
                    .build();
        }

        
    }

    @Override
    public CommonResponse updateInstitute(InstituteRequest request) {
        Optional<Institute> optionalInstitute = instituteRepository.findByInstitutename(request.getInstitutename());
        if(optionalInstitute.isPresent()) {
            Institute institute = optionalInstitute.get();
            institute.setInstitutedescription(request.getInstitutedescription());
            institute.setInstituteaddress(request.getInstituteaddress());
            institute.setInstitutephone(request.getInstitutephone());
            institute.setInstituteemail(request.getInstituteemail());
            institute.setInstitutewebsite(request.getInstitutewebsite());
            institute.setNoofcoursesavailale(request.getNoofcoursesavailale());
            instituteRepository.save(institute);
            return CommonResponse.builder()
                    .message("Institute updated successfully")
                    .build();
        } else {
            return CommonResponse.builder()
                    .message("Institute does not exists")
                    .build();
        }
        
    }

    @Override
    public InstituteResponse getInstituteById(String instituteid) {
        Optional<Institute> optionalInstitute = instituteRepository.findByInstituteId(instituteid);
        if(optionalInstitute.isPresent()) {
            Institute institute = optionalInstitute.get();
            return InstituteResponse.builder()
                    .instituteid(institute.getInstituteid())
                    .institutename(institute.getInstitutename())
                    .institutedescription(institute.getInstitutedescription())
                    .instituteaddress(institute.getInstituteaddress())
                    .institutephone(institute.getInstitutephone())
                    .instituteemail(institute.getInstituteemail())
                    .institutewebsite(institute.getInstitutewebsite())
                    .noofcoursesavailale(institute.getNoofcoursesavailale())
                    .build();
        } else {
           throw new RuntimeException("Institute not found");
        }
        
    }

    @Override
    public CommonResponse deleteInstitute(String institutename) {
        Optional<Institute> optionalInstitute = instituteRepository.findByInstitutename(institutename);
        if(optionalInstitute.isPresent()) {
            Institute institute = optionalInstitute.get();
            instituteRepository.delete(institute);
            return CommonResponse.builder()
                    .message("Institute deleted successfully")
                    .build();
        } else {
            return CommonResponse.builder()
                    .message("Institute does not exists")
                    .build();
        }
       
    }

    @Override
    public List<InstituteResponse> getAllInstitutes() {
        List<Institute> institutes = instituteRepository.findAll();
        return institutes.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        
    }

    private InstituteResponse convertToResponse(Institute institute) {
        return InstituteResponse.builder()
                .instituteid(institute.getInstituteid())
                .institutename(institute.getInstitutename())
                .institutedescription(institute.getInstitutedescription())
                .instituteaddress(institute.getInstituteaddress())
                .institutephone(institute.getInstitutephone())
                .instituteemail(institute.getInstituteemail())
                .institutewebsite(institute.getInstitutewebsite())
                .noofcoursesavailale(institute.getNoofcoursesavailale())


                // Map other attributes as needed
                .build();
    }

    @Override
    public InstituteResponse getInstituteByName(String institutename) {
        Optional<Institute> optionalInstitute = instituteRepository.findByInstitutename(institutename);
        if(optionalInstitute.isPresent()) {
            Institute institute = optionalInstitute.get();
            return InstituteResponse.builder()
                    .instituteid(institute.getInstituteid())
                    .institutename(institute.getInstitutename())
                    .institutedescription(institute.getInstitutedescription())
                    .instituteaddress(institute.getInstituteaddress())
                    .institutephone(institute.getInstitutephone())
                    .instituteemail(institute.getInstituteemail())
                    .institutewebsite(institute.getInstitutewebsite())
                    .noofcoursesavailale(institute.getNoofcoursesavailale())
                    .build();
        } else {
           throw new RuntimeException("Institute not found");
        }
    }
    
}
