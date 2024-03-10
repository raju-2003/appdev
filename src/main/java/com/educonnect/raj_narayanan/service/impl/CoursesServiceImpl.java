package com.educonnect.raj_narayanan.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.educonnect.raj_narayanan.dto.request.CoursesRequest;
import com.educonnect.raj_narayanan.dto.response.CommonResponse;
import com.educonnect.raj_narayanan.dto.response.CoursesResponse;
import com.educonnect.raj_narayanan.model.Courses;
import com.educonnect.raj_narayanan.model.Institute;
import com.educonnect.raj_narayanan.repository.CoursesRepository;
import com.educonnect.raj_narayanan.repository.InstituteRepository;
import com.educonnect.raj_narayanan.service.CourseServices;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CoursesServiceImpl implements CourseServices {

    private final InstituteRepository instituteRepository;
    private final CoursesRepository coursesRepository;

    @Override
    public CommonResponse addCourse(CoursesRequest courseRequest) {
        Optional<Institute> optionalInstitute = instituteRepository
                .findByInstitutename(courseRequest.getInstitutename());
        if (optionalInstitute.isPresent()) {
            Institute institute = optionalInstitute.get();
            // Create the course entity
            Courses course = Courses.builder()
                    .coursename(courseRequest.getCoursename())
                    .coursedescription(courseRequest.getCoursedescription())
                    .courseduration(courseRequest.getCourseduration())
                    .coursefee(courseRequest.getCoursefee())
                    .noofseats(courseRequest.getNoofseats())
                    .institute(institute)
                    .build();

            // Save the course
            coursesRepository.save(course);

            // Construct the response
            return CommonResponse.builder()
                    .message("Course added successfully")
                    .build();
        } else {
            return CommonResponse.builder()
                    .message("Institute not found")
                    .build();
        }
    }

    @Override
    public CommonResponse updateCourse(CoursesRequest courseRequest) {
        List<Courses> courses = coursesRepository.findByCoursename(courseRequest.getCoursename());
        if (courses.isEmpty()) {
            return CommonResponse.builder()
                    .message("Course not found")
                    .build();
        } else {
            for (Courses course : courses) {
                course.setCoursedescription(courseRequest.getCoursedescription());
                course.setCourseduration(courseRequest.getCourseduration());
                course.setCoursefee(courseRequest.getCoursefee());
                course.setNoofseats(courseRequest.getNoofseats());
                coursesRepository.save(course);
            }
            return CommonResponse.builder()
                    .message("Courses updated successfully")
                    .build();
        }
    }

    @Override
    public CommonResponse deleteCourse(String courseId) {
        Optional<Courses> optionalCourse = coursesRepository.findById(courseId);
        if (optionalCourse.isPresent()) {
            coursesRepository.delete(optionalCourse.get());
            return CommonResponse.builder()
                    .message("Course deleted successfully")
                    .build();
        } else {
            return CommonResponse.builder()
                    .message("Course not found")
                    .build();
        }
    }

    @Override
    public List<CoursesResponse> getAllCourses() {
        List<Courses> courses = coursesRepository.findAll();
        if (courses.isEmpty()) {
            throw new RuntimeException("Courses not found");
        }
        return courses.stream().map(course -> CoursesResponse.builder()
                .courseid(course.getCourseid())
                .coursename(course.getCoursename())
                .coursedescription(course.getCoursedescription())
                .courseduration(course.getCourseduration())
                .coursefee(course.getCoursefee())
                .noofseats(course.getNoofseats())
                .institutename(course.getInstitute().getInstitutename())
                .build()).collect(Collectors.toList());
    }

    @Override
    public List<CoursesResponse> getAllCoursesbyCoursename(String coursename) {
        List<Courses> courses = coursesRepository.findByCoursename(coursename);
        if (courses.isEmpty()) {
            throw new RuntimeException("Courses not found");
        }
        return courses.stream().map(course -> CoursesResponse.builder()
                .courseid(course.getCourseid())
                .coursename(course.getCoursename())
                .coursedescription(course.getCoursedescription())
                .courseduration(course.getCourseduration())
                .coursefee(course.getCoursefee())
                .noofseats(course.getNoofseats())
                .institutename(course.getInstitute().getInstitutename())
                .build()).collect(Collectors.toList());
    }

    @Override
    public List<CoursesResponse> getAllCoursesbyInstitutename(String institutename) {
        Optional<Institute> optionalInstitute = instituteRepository.findByInstitutename(institutename);
        if (optionalInstitute.isPresent()) {
            Institute institute = optionalInstitute.get();
            List<Courses> courses = coursesRepository.findByInstitute(institute);
            if (courses.isEmpty()) {
                throw new RuntimeException("Courses not found");
            }
            return courses.stream().map(course -> CoursesResponse.builder()
                    .courseid(course.getCourseid()) 
                    .coursename(course.getCoursename())
                    .coursedescription(course.getCoursedescription())
                    .courseduration(course.getCourseduration())
                    .coursefee(course.getCoursefee())
                    .noofseats(course.getNoofseats())
                    .institutename(institutename)
                    .build()).collect(Collectors.toList());
        } else {
            throw new RuntimeException("Institute not found");
        }
    }

    @Override
    public List<CoursesResponse> getCourseById(String courseid) {
        Optional<Courses> optionalCourse = coursesRepository.findById(courseid);
        if (optionalCourse.isPresent()) {
            Courses course = optionalCourse.get();
            return List.of(CoursesResponse.builder()
                    .courseid(course.getCourseid())
                    .coursename(course.getCoursename())
                    .coursedescription(course.getCoursedescription())
                    .courseduration(course.getCourseduration())
                    .coursefee(course.getCoursefee())
                    .noofseats(course.getNoofseats())
                    .institutename(course.getInstitute().getInstitutename())
                    .build());
        } else {
            throw new RuntimeException("Course not found");
        }
    }

}
