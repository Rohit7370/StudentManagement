package com.student.controller;

import com.student.entity.Result;
import com.student.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/results")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @PostMapping("/save")
    public Result saveResult(@RequestBody Result result) {
        return resultService.saveResult(result);
    }

    @GetMapping
    public List<Result> getAllResults() {
        System.out.println("result printed...!!");
        return resultService.getAllResults();
    }

    @GetMapping("/{id}")
    public Result getResultById(@PathVariable Long id) {
        return resultService.getResultById(id);
    }

    @GetMapping("/summary")
    public Object[] getTotalAndAverage() {
        return resultService.getTotalAndAverage();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteResult(@PathVariable Long id) {
        resultService.deleteResult(id);
        return "Result deleted successfully!";
    }
}

