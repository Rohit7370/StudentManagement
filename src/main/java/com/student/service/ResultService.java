package com.student.service;

import com.student.entity.Result;
import com.student.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    // Save result after computing total and average
    public Result saveResult(Result result) {
        if (result != null) {

            result.setTotal(result.getHindi(),result.getEnglish(),result.getMaths());
            result.setAverage(result.getTotal()/3.0);
        }
        return resultRepository.save(result);
    }

    // Get all results
    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

    // Get a single result by ID
    public Result getResultById(Long id) {
        return resultRepository.findById(id).orElse(null);
    }

    // Get overall total and average
    public Object[] getTotalAndAverage() {
        return resultRepository.findTotalAndAverage();
    }

    // Delete result by ID
    public void deleteResult(Long id) {
        resultRepository.deleteById(id);
    }
}
