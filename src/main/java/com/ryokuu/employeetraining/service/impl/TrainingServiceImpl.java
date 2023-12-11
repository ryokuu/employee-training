package com.ryokuu.employeetraining.service.impl;

/*
 * @author Muhammad Nooryoku Rafshanjany
 */

import com.ryokuu.employeetraining.exception.NotFoundException;
import com.ryokuu.employeetraining.model.dto.GenericResponse;
import com.ryokuu.employeetraining.model.dto.TrainingDto;
import com.ryokuu.employeetraining.model.entity.Training;
import com.ryokuu.employeetraining.model.mapper.TrainingMapper;
import com.ryokuu.employeetraining.repository.TrainingRepository;
import com.ryokuu.employeetraining.service.TrainingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrainingServiceImpl implements TrainingService {

    private final TrainingMapper trainingMapper;

    private final TrainingRepository trainingRepository;

    @Override
    public GenericResponse<Object> saveTraining(TrainingDto trainingDto) {

        GenericResponse<Object> response = new GenericResponse<>();

        try{
            Training savedTraining = trainingRepository.save(trainingMapper.trainingToTrainingDto(trainingDto));
            response.success(trainingMapper.trainingToTrainingDto(savedTraining));
        }catch (Exception e){
            log.error("Save Training Error ->[{}]--[{}]",e.getClass(), e.getMessage());
            return new GenericResponse<>().error(e.getMessage());
        }

        return response;
    }

    @Override
    public GenericResponse<Object> updateTraining(TrainingDto trainingDto) {

        GenericResponse<Object> response = new GenericResponse<>();

        try{
            Optional<Training> existingTraining = trainingRepository.findById(trainingDto.getId());
            if (existingTraining.isPresent()){
                Training training = existingTraining.get();
                training.setTopic(trainingDto.getTopic());
                training.setTrainer(trainingDto.getTrainer());

                trainingDto = trainingMapper.trainingToTrainingDto(trainingRepository.save(training));
                response.success(trainingDto);
            }else {
                throw new NotFoundException(String.format("Training with id %s is not exist", trainingDto.getId()));
            }
        } catch (NotFoundException e) {

            log.warn("Training not found -> [{}]--[{}]",e.getClass(), e.getMessage());
            return new GenericResponse<>().notFound(e.getMessage());
        }catch (Exception e){
            log.error("Delete Training Error ->[{}]--[{}]",e.getClass(), e.getMessage());
            return new GenericResponse<>().error(e.getMessage());
        }

        return response;
    }

    @Override
    public GenericResponse<Object> getTraining(Integer id) {
        GenericResponse<Object> response = new GenericResponse<>();

        try{
            Training fetchTraining = trainingRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Training with id " +id +" not found"));

            response.success(trainingMapper.trainingToTrainingDto(fetchTraining));
        } catch (NotFoundException e) {

            log.warn("Training not found -> [{}]--[{}]",e.getClass(), e.getMessage());
            return new GenericResponse<>().notFound(e.getMessage());
        }catch (Exception e){
            log.error("Get Training Error ->[{}]--[{}]",e.getClass(), e.getMessage());
            return new GenericResponse<>().error(e.getMessage());
        }

        return response;
    }

    @Override
    public GenericResponse<Object> deleteTraining(Integer id) {
        GenericResponse<Object> response = new GenericResponse<>();

        try{
            trainingRepository.deleteById(id);
            response.success("success");

        } catch (Exception e) {
            log.error("Delete Training Error ->[{}]--[{}]",e.getClass(), e.getMessage());
            return new GenericResponse<>().error(e.getMessage());
        }

        return response;
    }

    @Override
    public GenericResponse<Object> getTrainingList(Integer pageNumber, Integer pageSize) {
        GenericResponse<Object> response = new GenericResponse<>();

        try{
            Page<Training> trainings = trainingRepository.findAll(PageRequest.of(pageNumber, pageSize));
            Page<TrainingDto> trainingDtoPage = trainings.map(trainingMapper::trainingToTrainingDto);

            response.success(trainingDtoPage);
        } catch (Exception e) {
            log.error("Get All Training Error ->[{}]--[{}]",e.getClass(), e.getMessage());
            return new GenericResponse<>().error(e.getMessage());
        }

        return response;
    }
}
