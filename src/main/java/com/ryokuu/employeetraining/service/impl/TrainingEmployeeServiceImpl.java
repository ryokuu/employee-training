package com.ryokuu.employeetraining.service.impl;

import com.ryokuu.employeetraining.exception.NotFoundException;
import com.ryokuu.employeetraining.model.dto.EmployeeDto;
import com.ryokuu.employeetraining.model.dto.GenericResponse;
import com.ryokuu.employeetraining.model.dto.TrainingEmployeeDto;
import com.ryokuu.employeetraining.model.entity.Employee;
import com.ryokuu.employeetraining.model.entity.Training;
import com.ryokuu.employeetraining.model.entity.TrainingEmployee;
import com.ryokuu.employeetraining.model.mapper.TrainingEmployeeMapper;
import com.ryokuu.employeetraining.repository.EmployeeRepository;
import com.ryokuu.employeetraining.repository.TrainingEmployeeRepository;
import com.ryokuu.employeetraining.repository.TrainingRepository;
import com.ryokuu.employeetraining.service.TrainingEmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

/*
 * @author Muhammad Nooryoku Rafshanjany
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class TrainingEmployeeServiceImpl implements TrainingEmployeeService {

    private final TrainingEmployeeRepository trainingEmployeeRepository;

    private final EmployeeRepository employeeRepository;

    private final TrainingRepository trainingRepository;

    private final TrainingEmployeeMapper trainingEmployeeMapper;

    @Override
    public GenericResponse<Object> saveTrainingEmployee(TrainingEmployeeDto trainingEmployeeDto) {
        GenericResponse<Object> response = new GenericResponse<>();

        try{
            Employee employee = employeeRepository.findById(trainingEmployeeDto.getEmployee().getId())
                    .orElseThrow(()-> new NotFoundException(String.format("Employee for id %s is not found", trainingEmployeeDto.getEmployee().getId())));
            Training training = trainingRepository.findById(trainingEmployeeDto.getTraining().getId())
                    .orElseThrow(()-> new NotFoundException(String.format("Training for id %s is not found", trainingEmployeeDto.getTraining().getId())));

            TrainingEmployee trainingEmployee = new TrainingEmployee();
            trainingEmployee.setTraining(training);
            trainingEmployee.setEmployee(employee);
            trainingEmployee.setDateOfTraining(trainingEmployeeDto.getDateOfTraining());

            TrainingEmployee savedTraining = trainingEmployeeRepository.save(trainingEmployee);
            response.success(trainingEmployeeMapper.trainingToTrainingDto(savedTraining));
        } catch (NotFoundException e){
            log.warn("Either Employee or Training is not found -> [{}]--[{}]",e.getClass(), e.getMessage());
            return new GenericResponse<>().notFound(e.getMessage());
        }
        catch (Exception e){
            log.error("Save Training Employee Error ->[{}]--[{}]",e.getClass(), e.getMessage());
            return new GenericResponse<>().error(e.getMessage());
        }

        return response;
    }

    @Override
    public GenericResponse<Object> updateTrainingEmployee(TrainingEmployeeDto trainingEmployeeDto) {
        GenericResponse<Object> response = new GenericResponse<>();
        try {

            boolean isTrainingEmployeeExist = trainingEmployeeRepository.existsById(trainingEmployeeDto.getId());
            if(isTrainingEmployeeExist){
                trainingEmployeeRepository.updateTrainingEmployee(trainingEmployeeDto.getId(),
                        trainingEmployeeDto.getEmployee().getId(),
                        trainingEmployeeDto.getTraining().getId(),
                        trainingEmployeeDto.getDateOfTraining());

                TrainingEmployee updated = trainingEmployeeRepository.findById(trainingEmployeeDto.getId())
                        .orElseThrow(()-> new NotFoundException(String.format("Updated Training Employee for id %s is not found", trainingEmployeeDto.getTraining().getId())));

                response.success(trainingEmployeeMapper.trainingToTrainingDto(updated));
            }else{
                throw new NotFoundException(String.format("Training Employee for id %s is not found", trainingEmployeeDto.getTraining().getId()));
            }

        }catch (NotFoundException e){
            log.warn("Either Employee or Training is not found -> [{}]--[{}]",e.getClass(), e.getMessage());
            return new GenericResponse<>().notFound(e.getMessage());
        }
        catch (Exception e){
            log.error("Update Training Employee Error ->[{}]--[{}]",e.getClass(), e.getMessage());
            return new GenericResponse<>().error(e.getMessage());
        }

        return response;
    }

    @Override
    public GenericResponse<Object> getTrainingEmployee(Integer id){
        GenericResponse<Object> response = new GenericResponse<>();
        try{
            TrainingEmployee trainingEmployee = trainingEmployeeRepository.findById(id)
                    .orElseThrow(()-> new NotFoundException("Employee for id : "+ id+ " is not found"));

            response.success(trainingEmployeeMapper.trainingToTrainingDto(trainingEmployee));
        }catch (NotFoundException ne){
            log.info("Training Employee is Not Found ->[{}]--[{}]",ne.getClass(), ne.getMessage());
            return new GenericResponse<>().badRequest(ne.getMessage());
        }catch (Exception e){
            log.error("Get Training Employee Error ->[{}]--[{}]",e.getClass(), e.getMessage());
            return new GenericResponse<>().error(e.getMessage());
        }

        return response;
    }

    @Override
    public GenericResponse<Object> deleteTrainingEmployee(Integer id) {
        GenericResponse<Object> response = new GenericResponse<>();

        try{
            trainingEmployeeRepository.deleteById(id);
            response.success("success");
        }catch (Exception e){
            log.error("Delete Employee Error ->[{}]--[{}]",e.getClass(), e.getMessage());
            return new GenericResponse<>().error(e.getMessage());
        }

        return response;
    }

    @Override
    public GenericResponse<Object> getTrainingEmployeeList(Integer pageNumber, Integer pageSize) {
        GenericResponse<Object> response = new GenericResponse<>();

        try{
            Page<TrainingEmployee> trainingEmployees = trainingEmployeeRepository.findAll(PageRequest.of(pageNumber, pageSize));
            Page<TrainingEmployeeDto> trainingEmployeeDtos = trainingEmployees.map(trainingEmployeeMapper::trainingToTrainingDto);

            response.success(trainingEmployeeDtos);
        }catch (Exception e){
            log.error("Get Training Employee List Error ->[{}]--[{}]",e.getClass(), e.getMessage());
            return new GenericResponse<>().error(e.getMessage());
        }

        return response;
    }
}
