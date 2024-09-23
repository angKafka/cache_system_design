package org.rdutta.cache_system_design.user.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.rdutta.cache_system_design.user.utilities.Regex;
import org.rdutta.cache_system_design.user.utilities.ValidationMessages;

public record EmployeeDTO(
        @NotNull(message = ValidationMessages.FIRST_NAME_NULL)
        String firstname,
        @NotNull(message = ValidationMessages.LAST_NAME_NULL)
        String lastname,
        @NotNull(message = ValidationMessages.EMAIL_NULL)
        @Email(regexp = Regex.EMAIL_REGEX, message = ValidationMessages.EMAIL_NOT_VALID)
        String email,
        @NotNull(message = ValidationMessages.NUMBER_NULL)
        @Pattern(regexp = Regex.NUMBER_REGEX, message = ValidationMessages.NOT_VALID_NUMBER)
        String phone,
        @NotNull(message = ValidationMessages.DEPARTMENT_NULL)
        String department
) {
}
