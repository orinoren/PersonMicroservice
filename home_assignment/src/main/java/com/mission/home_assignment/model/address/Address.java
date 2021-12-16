package com.mission.home_assignment.model.address;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mission.home_assignment.config.BooleanDeserializer;
import com.mission.home_assignment.model.state.State;
import com.mission.home_assignment.annotations.StateValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @NotNull(message = "state is required")
    @StateValidation(message = "Only israel is required")
    private State state;
    @NotBlank(message = "city is required")
    @Size(min = 3, max = 20, message = "city length must be between 3-20")
    private String city;
    @NotBlank(message = "street is required")
    @Size(min = 3, max = 50, message = "street length must be between 3-50")
    private String street;
    @NotBlank(message = "zipcode is required")
    @Pattern(regexp="^(0|[1-9][0-9]*)$" ,message = "zip code need to be only numbers")
    private String zipcode;
    @NotNull(message = "only true or false is required")
    @JsonDeserialize(using = BooleanDeserializer.class)
    private Boolean containsAnimals;
}

