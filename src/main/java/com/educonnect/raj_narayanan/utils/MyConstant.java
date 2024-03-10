package com.educonnect.raj_narayanan.utils;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.HEAD;
import static org.springframework.http.HttpMethod.PATCH;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

import java.util.Arrays;
import java.util.List;

public class MyConstant {

        //Admin
        public static final String ADMIN = "/api/v1/admin";

        // Request Mappings
        public static final String AUTH = "/api/v1/auth";
        public static final String USER = "/api/v1/user";

        
        
        
        // Auth 
        public static final String LOGIN = "/login";
        public static final String REGISTER = "/register";
        public static final String LOGOUT = "/logout";
        public static final String FORGOT_PASSWORD = "/forgot-password";
        public static final String RESET_PASSWORD = "/reset-password";
        
        //student 
        public static final String STUDENT_ADD = "/student/add";
        public static final String STUDENT_UPDATE = "/student/update";
        public static final String STUDENT_GET = "/student/get";
        public static final String STUDENT_DELETE = "/student/delete";


        //institute
        public static final String INSTITUTE_ADD = "/institute/add";
        public static final String INSTITUTE_UPDATE = "/institute/update";
        public static final String INSTITUTE_GET = "/institute/get";
        public static final String INSTITUTE_GET_NAME = "/institute/get-name";
        public static final String INSTITUTE_DELETE = "/institute/delete";
        public static final String INSTITUTE_GET_ALL = "/institute/get-all";

        //course
        public static final String COURSE_ADD = "/course/add";
        public static final String COURSE_UPDATE = "/course/update";
        public static final String COURSE_GET = "/course/get";
        public static final String COURSE_DELETE = "/course/delete";
        public static final String COURSE_GET_ALL = "/course/get-all";
        public static final String COURSE_GET_ALL_INSTITUTE = "/course/get-all/institutename";
        public static final String COURSE_GET_ALL_COURSENAME = "/course/get-all/coursename";
        public static final String COURSE_GET_ALL_COURSEID = "/course/get/courseid";

        //admission
        public static final String ADMISSION_ADD = "/admission/add";
        public static final String ADMISSION_UPDATE = "/admission/update";
        public static final String ADMISSION_GET = "/admission/get";
        public static final String ADMISSION_DELETE = "/admission/delete";
        public static final String ADMISSION_GET_ALL = "/admission/get-all";
        


        // Web Security
        public static final String[] WHITELIST_URL = {
                        "/api/v1/auth/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html/",
                        "/v3/api-docs/**"
        };
        public static final List<String> ORIGINS = Arrays.asList("http://localhost:4000", "http://localhost:5173");
        public static final List<String> HEADERS = Arrays.asList(AUTHORIZATION, CONTENT_TYPE);
        public static final List<String> METHODS = Arrays.asList(GET.name(), POST.name(), PATCH.name(),
                        PUT.name(), DELETE.name(), HEAD.name());

        // JsonWebToken
        public static final String JWT_LOCALHOST_URL = "http://localhost:8181";
        public static final String JWT_SECURITY_SCHEME_NAME = "bearerAuth";
        public static final String JWT_SCHEME = "bearer";
        public static final String JWT_DESCRIPTION = "Provide the JWT token.";
        public static final String JWT_BEARER_FORMAT = "JWT";

        //swagger
        public static final String SWAGGER_TITLE = "EduConnect";
        public static final String SWAGGER_DESCRIPTION = "EduConnect - Online College Admission Portal";
        public static final String SWAGGER_VERSION = "1.0";
        public static final String SWAGGER_CONTACT_NAME = "Raj Narayanan";
        public static final String SWAGGER_CONTACT_URL = "https://www.linkedin.com/in/raj-narayanan-7b3b3b1b7/";
        public static final String SWAGGER_CONTACT_EMAIL = "2003braj@gmail.com";
        public static final String SWAGGER_LICENSE = "Apache 2.0";
        public static final String SWAGGER_LICENSE_URL = "http://www.apache.org/licenses/LICENSE-2.0.html";
}