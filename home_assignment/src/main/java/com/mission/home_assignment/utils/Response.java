package com.mission.home_assignment.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Response {
    private Map<String, String> errors;
}
