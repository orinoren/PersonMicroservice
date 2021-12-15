package com.mission.home_assignment.model.person;

import com.mission.home_assignment.model.address.Address;
import com.mission.home_assignment.model.gender.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("person")
@NotNull(message = "person is required")
@Builder
public class Person {

    public Person(String id, String name, Integer age, Gender gender, Double height, Address address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.address = address;
    }

    @NotBlank(message = "id is required")
    @Size(min = 3, max = 40)
    private String id;

    @NotBlank(message = "name is required")
    @Size(min = 3, max = 20, message = "name length must be between 3-20 ")
    private String name;

    @NotNull(message = "age is required")
//  @Range(min = 0, max = 200, message = "age must be between 0-200")
    @Min(value = 0, message = "age must be greater then 0")
    @Max(value = 200, message = "age must be less then 200")
    private Integer age;

    @NotNull(message = "gender is required")
    private Gender gender;

    @NotNull(message = "height is required")
    @Min(value = 0, message = "height must be above 0")
    private Double height;

    @Min(value = 0, message = "weight must be above 0")
    private Double weight;

    @Valid
    @NotNull(message = "address is required")
    private Address address;
}
