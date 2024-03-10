package com.educonnect.raj_narayanan.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.educonnect.raj_narayanan.dto.request.InstituteRequest;
import com.educonnect.raj_narayanan.dto.response.BasicResponse;
import com.educonnect.raj_narayanan.dto.response.CommonResponse;
import com.educonnect.raj_narayanan.dto.response.InstituteResponse;
import com.educonnect.raj_narayanan.service.InstituteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import static com.educonnect.raj_narayanan.utils.MyConstant.ADMIN;
import static com.educonnect.raj_narayanan.utils.MyConstant.INSTITUTE_ADD;
import static com.educonnect.raj_narayanan.utils.MyConstant.INSTITUTE_DELETE;
import static com.educonnect.raj_narayanan.utils.MyConstant.INSTITUTE_GET;
import static com.educonnect.raj_narayanan.utils.MyConstant.INSTITUTE_GET_ALL;
import static com.educonnect.raj_narayanan.utils.MyConstant.INSTITUTE_GET_NAME;
import static com.educonnect.raj_narayanan.utils.MyConstant.INSTITUTE_UPDATE;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.EXPECTATION_FAILED;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping(ADMIN)
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Institute")
@RequiredArgsConstructor
public class InstituteController {

    private final InstituteService instituteService;

    @PostMapping(INSTITUTE_ADD)
    @Operation(summary = "Add a new institute")
    public ResponseEntity<CommonResponse> addInstitute(@RequestBody InstituteRequest request) {
        CommonResponse response = new CommonResponse();
        try {
            response = instituteService.addInstitute(request);
            return new ResponseEntity<>(response, ACCEPTED);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage("Institute addition failed due to an unexpected error.");
            return new ResponseEntity<>(response, EXPECTATION_FAILED);
        }
    }

    @PutMapping(INSTITUTE_UPDATE)
    @Operation(summary = "Update an existing institute")
    public ResponseEntity<CommonResponse> updateInstitute(@RequestBody InstituteRequest request) {
        CommonResponse response = new CommonResponse();
        try {
            response = instituteService.updateInstitute(request);
            return new ResponseEntity<>(response, ACCEPTED);
        } catch (Exception e) {
            response.setMessage("Institute update failed due to an unexpected error.");
            return new ResponseEntity<>(response, EXPECTATION_FAILED);
        }
    }

    @GetMapping(INSTITUTE_GET + "/{instituteid}")
    @Operation(summary = "See all institute by institute id")
    public ResponseEntity<BasicResponse<InstituteResponse>> getInstitute(@PathVariable String instituteid) {
        BasicResponse<InstituteResponse> response = new BasicResponse<>();
        try {
            InstituteResponse instituteResponse = instituteService.getInstituteById(instituteid);
            response.setData(List.of(instituteResponse));
            return new ResponseEntity<>(response, ACCEPTED);
        } catch (Exception e) {
            response.setMessage("Institute retrieval failed due to an unexpected error.");
            return new ResponseEntity<>(response, EXPECTATION_FAILED);
        }
    }

    @GetMapping(INSTITUTE_GET_NAME + "/{institutename}")
    @Operation(summary = "See all institute by institute name")
    public ResponseEntity<BasicResponse<InstituteResponse>> getInstituteByName(@PathVariable String institutename) {
        BasicResponse<InstituteResponse> response = new BasicResponse<>();
        try {
            InstituteResponse instituteResponse = instituteService.getInstituteByName(institutename);
            response.setData(List.of(instituteResponse));
            return new ResponseEntity<>(response, ACCEPTED);
        } catch (Exception e) {
            response.setMessage("Institute retrieval failed due to an unexpected error.");
            return new ResponseEntity<>(response, EXPECTATION_FAILED);
        }
    }
    @DeleteMapping(INSTITUTE_DELETE)
    @Operation(summary = "Delete an institute by name")
    public ResponseEntity<CommonResponse> deleteInstitute(@RequestParam String institutename) {
        CommonResponse response = new CommonResponse();
        try {
            response = instituteService.deleteInstitute(institutename);
            return new ResponseEntity<>(response, ACCEPTED);
        } catch (Exception e) {
            response.setMessage("Institute deletion failed due to an unexpected error.");
            return new ResponseEntity<>(response, EXPECTATION_FAILED);
        }
    }

    @GetMapping(INSTITUTE_GET_ALL)
    @Operation(summary = "Get all institutes")
    public ResponseEntity<BasicResponse<InstituteResponse>> getAllInstitutes() {
        BasicResponse<InstituteResponse> response = new BasicResponse<>();
        try {
            List<InstituteResponse> instituteResponse = instituteService.getAllInstitutes();
            response.setData(instituteResponse);
            return new ResponseEntity<>(response, ACCEPTED);
        } catch (Exception e) {
            response.setMessage("Institute retrieval failed due to an unexpected error.");
            return new ResponseEntity<>(response, EXPECTATION_FAILED);
        }
    }
    
}
