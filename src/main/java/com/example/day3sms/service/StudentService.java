package com.example.day3sms.service;

import com.example.day3sms.dto.StudentRequestDto;
import com.example.day3sms.dto.StudentResponseDto;
import com.example.day3sms.exception.StudentNotFoundException;
import com.example.day3sms.model.StudentModel;
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

    public StudentResponseDto addStudent(StudentRequestDto dto) {
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

    public List<StudentResponseDto> getStudents() {

        return repository.findAll()
                .stream()
                .map(student -> new StudentResponseDto(
                        student.getId(),
                        student.getName(),
                        student.getAge(),
                        student.getEmail()))
                .toList();
    }

    public StudentResponseDto getStudentById(String id) {
        StudentModel student = repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));
        return new StudentResponseDto(
                student.getId(),
                student.getName(),
                student.getAge(),
                student.getEmail()
        );
    }

    public StudentResponseDto updateStudent(String id, StudentRequestDto student) {
        StudentModel existingStudent = repository.findById((id))
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));
        existingStudent.setName(student.getName());
        existingStudent.setAge((student.getAge()));
        existingStudent.setEmail((student.getEmail()));

        StudentModel updated = repository.save(existingStudent);
        return new StudentResponseDto(
                updated.getId(),
                updated.getName(),
                updated.getAge(),
                updated.getEmail()
        );
    }

    public void deleteStudent(String id) {
        repository.deleteById(id);
    }
}
