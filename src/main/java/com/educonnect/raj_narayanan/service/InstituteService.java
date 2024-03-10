package com.educonnect.raj_narayanan.service;

import java.util.List;

import com.educonnect.raj_narayanan.dto.request.InstituteRequest;
import com.educonnect.raj_narayanan.dto.response.CommonResponse;
import com.educonnect.raj_narayanan.dto.response.InstituteResponse;

public interface InstituteService {

    CommonResponse addInstitute(InstituteRequest request);

    CommonResponse updateInstitute(InstituteRequest request);

    InstituteResponse getInstituteById(String instituteid);

    CommonResponse deleteInstitute(String institutename);

    List<InstituteResponse> getAllInstitutes();

    InstituteResponse getInstituteByName(String institutename);

    
    
}
