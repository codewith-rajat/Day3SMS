package com.example.day3sms.controller;

import com.example.day3sms.dto.StudentRequestDto;
import com.example.day3sms.dto.StudentResponseDto;
import com.example.day3sms.model.StudentModel;
import com.example.day3sms.service.StudentService;
import com.example.day3sms.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class StudentController{
    private final StudentService service;
    private final JwtUtil jwtUtil;

    public StudentController(StudentService service, JwtUtil jwtUtil) {
        this.service = service;
        this.jwtUtil = jwtUtil;
    }
    // Create function API
    private void checkToken(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Invalid Token");
        }
        String token = authHeader.substring(7);
        jwtUtil.validateTokenAndGetEmail(token);
    }

    @PostMapping("/addstudent")
    public StudentResponseDto addStudent(@RequestHeader(value = "Authorization") String authHeader,
                                         @Valid @RequestBody StudentRequestDto student){
        checkToken(authHeader);
        return service.addStudent(student);
    }

    @GetMapping("/students")
    public List<StudentResponseDto> getStudents(
            @RequestHeader(value = "Authorization", required = false) String authHeader
    ){
        checkToken(authHeader);
        return service.getStudents();
    }

    @GetMapping("/students/{id}")
    public StudentResponseDto getStudentById(@RequestHeader(value = "Authorization") String authHeader,
                                             @PathVariable String id){
        checkToken(authHeader);
        return service.getStudentById(id);
    }

    @PutMapping("/update/{id}")
    public StudentResponseDto updateStudent(@RequestHeader(value = "Authorization") String authHeader,
                                            @PathVariable String id,
                                            @Valid  @RequestBody StudentRequestDto student){
        checkToken(authHeader);
        return service.updateStudent(id,student);
    }

    @PatchMapping("/patch/{id}")
    public StudentResponseDto patchStudent(@RequestHeader(value = "Authorization") String authHeader,
                                           @PathVariable String id,
                                           @Valid @RequestBody StudentRequestDto student) {
        // Use your service update method
        checkToken(authHeader);
        return service.updateStudent(id, student);
    }


    @DeleteMapping("delete/{id}")
    public void deleteStudent(@RequestHeader(value = "Authorization") String authHeader,
                              @PathVariable String id){
        checkToken(authHeader);
        service.deleteStudent(id);
    }
}
