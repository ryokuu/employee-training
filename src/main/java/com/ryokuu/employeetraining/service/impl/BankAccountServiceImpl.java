package com.ryokuu.employeetraining.service.impl;

import com.ryokuu.employeetraining.exception.NotFoundException;
import com.ryokuu.employeetraining.model.dto.BankAccountDto;
import com.ryokuu.employeetraining.model.dto.GenericResponse;
import com.ryokuu.employeetraining.model.entity.BankAccount;
import com.ryokuu.employeetraining.model.mapper.BankAccountMapper;
import com.ryokuu.employeetraining.repository.BankAccountRepository;
import com.ryokuu.employeetraining.repository.EmployeeRepository;
import com.ryokuu.employeetraining.service.BankAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
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
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    private final BankAccountMapper bankAccountMapper;

    private final EmployeeRepository employeeRepository;

    @Override
    public GenericResponse<Object> saveBankAccount(BankAccountDto bankAccountDto) {

        GenericResponse<Object> response = new GenericResponse<>();

        Integer employeeId =  bankAccountDto.getEmployee().getId();

        try{
            BankAccount savedBankAccount = bankAccountRepository.save(bankAccountMapper.bankAccountDtoToBankAccount(bankAccountDto));

            bankAccountRepository.updateSetEmployeeId(savedBankAccount.getId(),employeeId);

            bankAccountDto = bankAccountMapper.bankAccountToBankAccountDto(savedBankAccount);
            bankAccountDto.getEmployee().setName(employeeRepository.findNameById(employeeId).getName());

            response.success(bankAccountDto);
        }catch (DataIntegrityViolationException de){
            log.warn("Error Insert BankAccount -> [{}]--[{}]",de.getClass(), de.getMessage());

            return new GenericResponse<>().badRequest("Bank account for employee id "+ employeeId + " already exist");
        } catch (Exception e) {
            log.error("Error Insert BankAccount -> [{}]--[{}]",e.getClass(), e.getMessage());

            return new GenericResponse<>().error(e.getMessage());
        }
        return response;
    }

    @Override
    public GenericResponse<Object> updateBankAccount(BankAccountDto bankAccountDto) {

        GenericResponse<Object> response = new GenericResponse<>();

        try {
            Optional<BankAccount> existingAccount = bankAccountRepository.findById(bankAccountDto.getId());
            if (existingAccount.isPresent()){
                BankAccount existing = existingAccount.get();
                existing.setAccountName(bankAccountDto.getAccountName());
                existing.setAccountNumber(bankAccountDto.getAccountNumber());
                existing.setBankName(bankAccountDto.getBankName());
                existing.setBranchName(bankAccountDto.getAccountName());

                bankAccountDto = bankAccountMapper.bankAccountToBankAccountDto(bankAccountRepository.save(existing));
                bankAccountDto.getEmployee().setName(existing.getEmployee().getName());

                response.success(bankAccountDto);
            }else{
                throw new NotFoundException(String.format("BankAccount with id %s is not exist", bankAccountDto.getId()));
            }
        } catch (NotFoundException e) {

            log.warn("Bank account not found -> [{}]--[{}]",e.getClass(), e.getMessage());
            return new GenericResponse<>().notFound(e.getMessage());
        } catch (Exception e) {

            log.error("Bank account not found -> [{}]--[{}]",e.getClass(), e.getMessage());
            return new GenericResponse<>().error(e.getMessage());
        }

        return response;
    }

    @Override
    public GenericResponse<Object> getBankAccount(Integer id) {

        GenericResponse<Object> response = new GenericResponse<>();

        try {

            BankAccount fetchBank = bankAccountRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Bank Account with id " +id +" not found"));

            response.success(bankAccountMapper.bankAccountToBankAccountDto(fetchBank));

        } catch (NotFoundException ne){
            log.info("Bank Account Not Found ->[{}]--[{}]",ne.getClass(), ne.getMessage());
            return new GenericResponse<>().badRequest(ne.getMessage());
        }
        catch (Exception e) {
            log.error("Get Bank Account Error ->[{}]--[{}]",e.getClass(), e.getMessage());
            return new GenericResponse<>().error(e.getMessage());
        }

        return response;
    }

    @Override
    public GenericResponse<Object> deleteBankAccount(Integer id) {

        GenericResponse<Object> response = new GenericResponse<>();

        try{
            bankAccountRepository.deleteById(id);
            response.success("success");

        } catch (Exception e) {
            log.error("Delete BankAccount Error ->[{}]--[{}]",e.getClass(), e.getMessage());
            return new GenericResponse<>().error(e.getMessage());
        }

        return response;
    }

    @Override
    public GenericResponse<Object> getBankAccountList(Integer pageNumber, Integer pageSize) {

        GenericResponse<Object> response = new GenericResponse<>();

        try{
            Page<BankAccount> bankAccounts = bankAccountRepository.findAll(PageRequest.of(pageNumber, pageSize));
            Page<BankAccountDto> bankAccountPage = bankAccounts.map(bankAccountMapper::bankAccountToBankAccountDto);

            response.success(bankAccountPage);


        }catch (Exception e){
            log.error("Get BankAccounts Error ->[{}]--[{}]",e.getClass(), e.getMessage());
            return new GenericResponse<>().error(e.getMessage());
        }

        return response;
    }
}
