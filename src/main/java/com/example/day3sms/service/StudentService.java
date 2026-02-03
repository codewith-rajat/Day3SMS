package com.example.day3sms.service;

import com.example.day3sms.dto.StudentRequestDto;
import com.example.day3sms.dto.StudentResponseDto;
import  com.example.day3sms.model.StudentModel;
import com.example.day3sms.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

//    public StudentModel addStudent(StudentModel student) {
//        return repository.save(student);
//    }

    public StudentResponseDto addStudent(StudentRequestDto dto){
        StudentModel student = new StudentModel();
        student.setName(dto.getName());
        student.setAge(dto.getAge());
        student.setEmail(dto.getEmail());

        StudentModel saved = repository.save(student);

        return new StudentResponseDto(
                saved.getId(),
                saved.getName(),
                saved.getAge(),
                saved.getEmail());
    }

    public List<StudentModel> getStudents() {
        return repository.findAll();
    }

    public StudentModel getStudentById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    public StudentModel updateStudent(String id, StudentModel student){
        StudentModel existingStudent = repository.findById((id))
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        existingStudent.setName(student.getName());
        existingStudent.setAge((student.getAge()));
        return repository.save(existingStudent);
    }

    public void deleteStudent(String id) {
        repository.deleteById(id);
    }
}
