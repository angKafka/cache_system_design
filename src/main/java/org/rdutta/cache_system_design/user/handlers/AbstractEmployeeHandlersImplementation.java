package org.rdutta.cache_system_design.user.handlers;


import org.rdutta.cache_system_design.user.dto.EmployeeDTO;

public abstract class AbstractEmployeeHandlersImplementation implements EmployeeHandlers {

    protected EmployeeHandlers employeeHandlers;

    @Override
    public void setNext(EmployeeHandlers handlers) {
        this.employeeHandlers = handlers;
    }

    protected void passToNext(EmployeeDTO dto) throws Exception {
        if(this.employeeHandlers != null) {
            this.employeeHandlers.handle(dto);
        }
    }
}
