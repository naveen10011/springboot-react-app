package com.naveen.ExpenseTracker.Service.ServiceImpl;

import com.naveen.ExpenseTracker.DTO.EarningDTO;
import com.naveen.ExpenseTracker.Entity.Earning;
import com.naveen.ExpenseTracker.ExceptionHandler.ResourceNotFoundException;
import com.naveen.ExpenseTracker.Mapper.EarningMapper;
import com.naveen.ExpenseTracker.Repository.EarningRepo;
import com.naveen.ExpenseTracker.Service.ServiceInterface.EarningServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EarningServiceImpl implements EarningServiceInterface {

    @Autowired
    private EarningMapper earningMapper;

    @Autowired
    private EarningRepo earningRepo;

    @Override
    public EarningDTO createEarning(EarningDTO earningDTO) {
        Earning earning = earningMapper.earningDTOtoEarning(earningDTO);
        Earning savedEarning = earningRepo.save(earning);
        return earningMapper.earningToEarningDTO(savedEarning);
    }

    @Override
    public EarningDTO getEarningById(Long id) {
        Earning earning = earningRepo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Earning with id " + id + " not found"));
        return earningMapper.earningToEarningDTO(earning);
    }

    @Override
    public List<EarningDTO> getAllEarnings() {
        List<Earning> earnings = earningRepo.findAll();
        return earnings.stream()
                .map(earning -> earningMapper.earningToEarningDTO(earning))
                .toList();
    }

    @Override
    public EarningDTO updateEarning(EarningDTO earningDTO, Long id) {
        Earning earning = earningRepo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Earning with id " + id + " not found"));
        earning.setEarningname(earningDTO.getEarningname());
        earning.setTotalamount(earningDTO.getTotalamount());
        earning.setDate(earningDTO.getDate());
        Earning updatedEarning = earningRepo.save(earning);
        return earningMapper.earningToEarningDTO(updatedEarning);
    }

    @Override
    public EarningDTO deleteEarning(Long id) {
        Earning earning = earningRepo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Earning with id " + id + " not found"));
        earningRepo.delete(earning);
        return earningMapper.earningToEarningDTO(earning);
    }

    @Override
    public List<EarningDTO> getEarningByUserId(Long userId) {
        List<Earning> earnings = earningRepo.findByUserInfo_userid(userId);
        return earnings.stream()
                .map(earning -> earningMapper.earningToEarningDTO(earning))
                .toList();
    }
}

