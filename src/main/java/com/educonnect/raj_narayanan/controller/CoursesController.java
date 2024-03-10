package com.educonnect.raj_narayanan.controller;


import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.EXPECTATION_FAILED;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.educonnect.raj_narayanan.dto.response.CommonResponse;
import com.educonnect.raj_narayanan.dto.response.CoursesResponse;
import com.educonnect.raj_narayanan.dto.response.BasicResponse;
import com.educonnect.raj_narayanan.dto.request.CoursesRequest;
import com.educonnect.raj_narayanan.service.CourseServices;

import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import static com.educonnect.raj_narayanan.utils.MyConstant.*;

@RestController
@RequestMapping(ADMIN)
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Courses")
@RequiredArgsConstructor
public class CoursesController {

    private final CourseServices courseServices;

    @PostMapping(COURSE_ADD)
    @Operation(summary = "Create a new course")
    public ResponseEntity<CommonResponse> addCourse(@RequestBody CoursesRequest courseRequest) {
        CommonResponse response = new CommonResponse();
        try{
            response = courseServices.addCourse(courseRequest);
            return new ResponseEntity<>(response, ACCEPTED);
        }
        catch (Exception e){
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, EXPECTATION_FAILED);
        }
    }

    @PutMapping(COURSE_UPDATE)
    @Operation(summary = "Update a course")
    public ResponseEntity<CommonResponse> updateCourse(@RequestBody CoursesRequest courseRequest) {
        CommonResponse response = new CommonResponse();
        try{
            response = courseServices.updateCourse(courseRequest);
            return new ResponseEntity<>(response, ACCEPTED);
        }
        catch (Exception e){
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, EXPECTATION_FAILED);
        }
    }

    


    @DeleteMapping(COURSE_DELETE)
    @Operation(summary = "Delete a course")
    public ResponseEntity<CommonResponse> deleteCourse(@RequestParam String courseId) {
        CommonResponse response = new CommonResponse();
        try{
            response = courseServices.deleteCourse(courseId);
            return new ResponseEntity<>(response, ACCEPTED);
        }
        catch (Exception e){
            response.setMessage(e.getMessage());
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
            return new ResponseEntity<>(response, EXPECTATION_FAILED);
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
