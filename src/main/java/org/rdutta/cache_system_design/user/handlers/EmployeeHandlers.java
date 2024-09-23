package org.rdutta.cache_system_design.user.handlers;

import org.rdutta.cache_system_design.user.dto.EmployeeDTO;

public interface EmployeeHandlers {
    void handle(EmployeeDTO dto) throws Exception;
    void setNext(EmployeeHandlers handlers);
}
