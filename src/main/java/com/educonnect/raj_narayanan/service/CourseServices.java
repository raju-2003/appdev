package com.educonnect.raj_narayanan.service;

import java.util.List;

import com.educonnect.raj_narayanan.dto.request.CoursesRequest;
import com.educonnect.raj_narayanan.dto.response.CommonResponse;
import com.educonnect.raj_narayanan.dto.response.CoursesResponse;


public interface CourseServices {

    CommonResponse addCourse(CoursesRequest courseRequest);

    CommonResponse updateCourse(CoursesRequest courseRequest);

    CommonResponse deleteCourse(String courseId);

    List<CoursesResponse> getAllCourses();

    List<CoursesResponse> getAllCoursesbyCoursename(String coursename);

    List<CoursesResponse> getAllCoursesbyInstitutename(String institutename);

    List<CoursesResponse> getCourseById(String courseid);



    
    
}
