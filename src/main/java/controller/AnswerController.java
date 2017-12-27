package controller;

import model.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.AnswerService;
import java.util.List;

@RestController
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    // Create
    @RequestMapping(path = "/answers/create", method = RequestMethod.POST)
    public void saveAnswerToDatabase(@RequestBody Answer answer){
        answerService.saveAnswerToDatabase(answer);
    }

    // Read
    @RequestMapping(path = "/answers/getAll", method = RequestMethod.GET)
    public List<Answer> getAnswers(){
        return answerService.getAllAnswers();
    }

    @RequestMapping(path ="/answers/readByaid/{aid}", method = RequestMethod.GET)
    public Answer getAnswerFromDatabaseByaid(@PathVariable int aid){
        return answerService.getAnswerFromDatabaseByaid(aid);
    }

    @RequestMapping(path ="/answers/read/{keyword}", method = RequestMethod.GET)
    public List<Answer> getAnswerFromDatabaseByKeyword(@PathVariable String keyword){
        return answerService.getAnswerFromDatabaseByKeyword(keyword);
    }

    // Update
    @RequestMapping(path = "/answers/updateByaid", method = RequestMethod.PATCH)
    public void updateAnswerFromDatabase(@RequestBody Answer newAnswer){
        answerService.updateAnswerFromDatabase(newAnswer);
    }

    // Delete
    @RequestMapping(path = "/answers/deleteByaid/{aid}", method = RequestMethod.DELETE)
    public void deleteAnswerFromDatabase(@PathVariable int aid){
        Answer answer = answerService.getAnswerFromDatabaseByaid(aid);
        answerService.deleteAnswerFromDatabase(answer);
    }
}
