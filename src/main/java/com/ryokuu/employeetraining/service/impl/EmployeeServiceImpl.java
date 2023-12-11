package com.ryokuu.employeetraining.service.impl;

import com.ryokuu.employeetraining.exception.NotFoundException;
import com.ryokuu.employeetraining.model.dto.EmployeeDto;
import com.ryokuu.employeetraining.model.dto.GenericResponse;
import com.ryokuu.employeetraining.model.entity.Employee;
import com.ryokuu.employeetraining.model.mapper.EmployeeMapper;
import com.ryokuu.employeetraining.repository.EmployeeDetailRepository;
import com.ryokuu.employeetraining.repository.EmployeeRepository;
import com.ryokuu.employeetraining.service.EmployeeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/*
 * @author Muhammad Nooryoku Rafshanjany
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final EmployeeDetailRepository employeeDetailRepository;

    private final EmployeeMapper employeeMapper;

    @Transactional
    @Override
    public GenericResponse<Object> insertEmployee(EmployeeDto employeeDto) {

        GenericResponse<Object> response = new GenericResponse<>();

        try {
            Employee savedEmployee = employeeRepository.save(employeeMapper.employeeDtoToEmployee(employeeDto));
            employeeDetailRepository.setEmployeeId(savedEmployee.getEmployeeDetail().getId(), savedEmployee.getId());

            response.success(employeeMapper.employeeToEmployeeDto(savedEmployee));

        }catch (Exception e) {
            log.error("Error Insert -> [{}]--[{}]",e.getClass(), e.getMessage());

            return new GenericResponse<>().error(e.getMessage());
        }

        return response;
    }

    @Transactional
    @Override
    public GenericResponse<Object> updateEmployee(EmployeeDto employeeDto) {

        GenericResponse<Object> response = new GenericResponse<>();
        try{
            Employee existingEmployee = employeeRepository.findById(employeeDto.getId())
                    .orElseThrow(() -> new NotFoundException("Employee for id "+ employeeDto.getId()+" is not found"));

            employeeMapper.updateEmployeeFromEmployeeDto(employeeDto, existingEmployee);

            Employee updatedEmployee = employeeRepository.save(existingEmployee);
            response.success(employeeMapper.employeeToEmployeeDto(updatedEmployee));
        } catch (NotFoundException ne){
            log.info("Employee Not Found ->[{}]--[{}]",ne.getClass(), ne.getMessage());
            return new GenericResponse<>().badRequest(ne.getMessage());
        }
        catch (Exception e) {
            log.error("Update Employee Error ->[{}]--[{}]",e.getClass(), e.getMessage());
            return new GenericResponse<>().error(e.getMessage());
        }

        return response;
    }

    @Override
    public GenericResponse<Object> deleteEmployee(Integer id) {

        GenericResponse<Object> response = new GenericResponse<>();

        try{
            employeeRepository.deleteById(id);
            response.success("success");

        } catch (Exception e) {
            log.error("Delete Employee Error ->[{}]--[{}]",e.getClass(), e.getMessage());
            return new GenericResponse<>().error(e.getMessage());
        }

        return response;
    }

    @Override
    public GenericResponse<Object> getEmployee(Integer id) {
        GenericResponse<Object> response = new GenericResponse<>();
        try{
            Employee existingEmployee = employeeRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Employee for id : "+ id +" is not found"));

            response.success(employeeMapper.employeeToEmployeeDto(existingEmployee));
        }
        catch (NotFoundException ne){
            log.info("Employee Not Found ->[{}]--[{}]",ne.getClass(), ne.getMessage());
            return new GenericResponse<>().badRequest(ne.getMessage());
        }
        catch (Exception e) {
            log.error("Get Employee Error ->[{}]--[{}]",e.getClass(), e.getMessage());
            return new GenericResponse<>().error(e.getMessage());
        }

        return response;
    }

    @Override
    public GenericResponse<Object> getEmployeeList(Integer page, Integer size) {

        GenericResponse<Object> response = new GenericResponse<>();
        try{
            Page<Employee> employees = employeeRepository.findAll(PageRequest.of(page, size));
            Page<EmployeeDto> employeeDtos = employees.map(employeeMapper::employeeToEmployeeDto);

            response.success(employeeDtos);
        }catch (Exception e) {
            log.error("Get Employee Error ->[{}]--[{}]",e.getClass(), e.getMessage());
            return new GenericResponse<>().error(e.getMessage());
        }

        return response;
    }
}
