package org.rdutta.cache_system_design.user.handlers.validations;

import jakarta.validation.ValidationException;
import org.rdutta.cache_system_design.user.dto.EmployeeDTO;
import org.rdutta.cache_system_design.user.handlers.AbstractEmployeeHandlersImplementation;
import org.rdutta.cache_system_design.user.utilities.Regex;
import org.rdutta.cache_system_design.user.utilities.ValidationMessages;
import org.springframework.stereotype.Component;

@Component
public class PhoneValidationCheck extends AbstractEmployeeHandlersImplementation {

    @Override
    public void handle(EmployeeDTO dto) throws Exception {
        if(dto.phone().matches(Regex.NUMBER_REGEX)){
            throw new ValidationException(ValidationMessages.NOT_VALID_NUMBER);
        }

        passToNext(dto);
    }
}
