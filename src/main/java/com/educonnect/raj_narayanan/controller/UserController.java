package com.educonnect.raj_narayanan.controller;

import static com.educonnect.raj_narayanan.utils.MyConstant.USER;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educonnect.raj_narayanan.dto.response.BasicResponse;
import com.educonnect.raj_narayanan.dto.response.CoursesResponse;
import com.educonnect.raj_narayanan.dto.response.InstituteResponse;
import com.educonnect.raj_narayanan.service.CourseServices;
import com.educonnect.raj_narayanan.service.InstituteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import static com.educonnect.raj_narayanan.utils.MyConstant.COURSE_GET_ALL;
import static com.educonnect.raj_narayanan.utils.MyConstant.COURSE_GET_ALL_COURSEID;
import static com.educonnect.raj_narayanan.utils.MyConstant.COURSE_GET_ALL_COURSENAME;
import static com.educonnect.raj_narayanan.utils.MyConstant.COURSE_GET_ALL_INSTITUTE;
import static com.educonnect.raj_narayanan.utils.MyConstant.INSTITUTE_GET;
import static com.educonnect.raj_narayanan.utils.MyConstant.INSTITUTE_GET_NAME;
import static com.educonnect.raj_narayanan.utils.MyConstant.INSTITUTE_GET_ALL;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.EXPECTATION_FAILED;

@RestController
@RequestMapping(USER)
@PreAuthorize("hasRole('USER')")
@Tag(name = "USER")
@RequiredArgsConstructor
public class UserController {

     private final InstituteService instituteService;
     private final CourseServices courseServices;


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

   

    @GetMapping(INSTITUTE_GET_ALL)
    @Operation(summary = "See all institutes")
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

    
    @GetMapping(COURSE_GET_ALL)
    @Operation(summary = "Get all courses")
    public ResponseEntity<BasicResponse<CoursesResponse>> getAllCourses() {
        BasicResponse<CoursesResponse> response = new BasicResponse<>();
        try{
            response.setData(courseServices.getAllCourses());
            return new ResponseEntity<>(response, ACCEPTED);
        }
        catch (Exception e){
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(COURSE_GET_ALL_COURSENAME + "/{coursename}")
    @Operation(summary = "Get all courses by coursename")
    public ResponseEntity<BasicResponse<CoursesResponse>> getAllCoursesbyCoursename(@PathVariable String coursename){
        BasicResponse<CoursesResponse> response = new BasicResponse<>();
        try{
            response.setData(courseServices.getAllCoursesbyCoursename(coursename));
            return new ResponseEntity<>(response, ACCEPTED);
        }
        catch (Exception e){
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(COURSE_GET_ALL_INSTITUTE + "/{institutename}")
    @Operation(summary = "Get all courses by institutename")
    public ResponseEntity<BasicResponse<CoursesResponse>> getAllCoursesbyInstitutename(@PathVariable String institutename){
        BasicResponse<CoursesResponse> response = new BasicResponse<>();
        try{
            response.setData(courseServices.getAllCoursesbyInstitutename(institutename));
            return new ResponseEntity<>(response, ACCEPTED);
        }
        catch (Exception e){
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    @GetMapping(COURSE_GET_ALL_COURSEID + "/{courseid}")
    @Operation(summary = "Get course details by course name")
    public ResponseEntity<BasicResponse<CoursesResponse>> getCourseById(@PathVariable String courseid){
        BasicResponse<CoursesResponse> response = new BasicResponse<>();
        try{
            response.setData(courseServices.getCourseById(courseid));
            return new ResponseEntity<>(response, ACCEPTED);
        }
        catch (Exception e){
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    


}
