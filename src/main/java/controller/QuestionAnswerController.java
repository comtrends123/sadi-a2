package controller;

import model.QuestionAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.QuestionAnswerService;

import java.util.List;

@RestController
public class QuestionAnswerController {
    @Autowired
    private QuestionAnswerService questionAnswerService;

    // Create
    @RequestMapping(path = "/questionAnswerss/create", method = RequestMethod.POST)
    public void saveQuestionAnswerToDatabase(@RequestBody QuestionAnswer questionAnswer){
        questionAnswerService.saveQuestionAnswerToDatabase(questionAnswer);
    }

    // Read
    @RequestMapping(path = "/questionAnswerss/getAll", method = RequestMethod.GET)
    public List<QuestionAnswer> getAllQuestionAnswerss(){
        return questionAnswerService.getAllQuestionAnswerss();
    }

    @RequestMapping(path="/questionAnswerss/readByqid/{qid}", method = RequestMethod.GET)
    public List<QuestionAnswer> getQuestionAnswerssFromDatabaseByqid(@PathVariable int qid){
        return questionAnswerService.getQuestionAnswerssFromDatabaseByqid(qid);
    }

    @RequestMapping(path="/questionAnswerss/readByaid/{aid}", method = RequestMethod.GET)
    public QuestionAnswer getQuestionAnswerFromDatabaseByaid(@PathVariable int aid){
        return questionAnswerService.getQuestionAnswerFromDatabaseByaid(aid);
    }

    // Update
    @RequestMapping(path = "/questionAnswerss/updateByaid", method = RequestMethod.PATCH)
    public void updateqidOfQuestionAnswerFromDatabaseByaid(@RequestBody QuestionAnswer questionAnswerWithNewqid){
        questionAnswerService.updateqidOfQuestionAnswerFromDatabaseByaid(questionAnswerWithNewqid);
    }

    @RequestMapping(path = "/questionAnswerss/updateByqid/{newaid}", method = RequestMethod.PATCH)
    public void updateaidOfQuestionAnswerFromDatabaseByqid(@RequestBody QuestionAnswer oldQuestionAnswer, @PathVariable int newaid){
        questionAnswerService.updateaidOfQuestionAnswerFromDatabaseByqid(oldQuestionAnswer, newaid);
    }

    // Delete
    @RequestMapping(path = "/questionAnswerss/delete/{aid}", method =RequestMethod.DELETE)
    public void deleteQuestionFromDatabase(@PathVariable int aid){
        QuestionAnswer questionAnswer = questionAnswerService.getQuestionAnswerFromDatabaseByaid(aid);
        questionAnswerService.deleteQuestionAnswerFromDatabase(questionAnswer);
    }
}
