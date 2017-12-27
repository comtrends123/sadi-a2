package controller;

import model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.ResultService;

import java.util.List;

@RestController
public class ResultController {
    @Autowired
    private ResultService resultService;

    // Create
    @RequestMapping(path = "/results/create", method = RequestMethod.POST)
    public void saveResultToDatabase(@RequestBody Result result){
        resultService.saveResultToDatabase(result);
    }

    // Read
    @RequestMapping(path = "/results/getAll", method = RequestMethod.GET)
    public List<Result> getAllResults(){
        return resultService.getAllResults();
    }

    @RequestMapping(path="/results/readByrid/{rid}", method = RequestMethod.GET)
    public Result getResultFromDatabaseByrid(@PathVariable int rid){
        return resultService.getResultFromDatabaseByrid(rid);
    }

    // Update & Delete
    // Update and Delete result is logically not allowed
}
