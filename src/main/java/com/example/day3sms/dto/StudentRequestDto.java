package com.example.day3sms.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequestDto {
    @NotBlank(message = "name can not be blank")
    private String name;

    @Min(value = 5, message = "Age can not be less than 1")
    @Max(value=90,message = "Age can not be greater than 90")
    private int age;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email can not be blank")
    private String email;
}
