package com.mission.home_assignment.model.address;

import com.mission.home_assignment.model.state.State;
import com.mission.home_assignment.annotations.StateValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @NotNull(message = "state is null")
    @StateValidation(message = "Only israel is required")
    private State state;
    @NotBlank(message = "city is required")
    @Size(min = 3, max = 20, message = "city length must be between 3-20")
    private String city;
    @NotBlank(message = "street is required")
    @Size(min = 3, max = 50, message = "street length must be between 3-50")
    private String street;
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private String zipcode;
    @NotNull(message = "only true or false is required")
    private Boolean containsAnimals;
}

