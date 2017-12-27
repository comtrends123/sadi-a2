package controller;

import model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.QuestionService;
import java.util.List;

@RestController
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    // Create
    @RequestMapping(path = "/questions/create", method = RequestMethod.POST)
    public void saveQuestionToDatabase(@RequestBody Question question){
        questionService.saveQuestionToDatabase(question);
    }

    // Read
    @RequestMapping(path = "/questions/getAll", method = RequestMethod.GET)
    public List<Question> getQuestions(){
        return questionService.getAllQuestions();
    }

    @RequestMapping(path="/questions/readByqid/{qid}", method = RequestMethod.GET)
    public Question getQuestionFromDatabaseByqid(@PathVariable int qid){
        return questionService.getQuestionFromDatabaseByqid(qid);
    }

    @RequestMapping(path="/questions/readByKeyword/{keyword}", method = RequestMethod.GET)
    public List<Question> getQuestionFromDatabaseByKeyword(@PathVariable String keyword){
        return questionService.getQuestionFromDatabaseByKeyword(keyword);
    }

    // Update
    @RequestMapping(path = "/questions/updateByqid", method = RequestMethod.PATCH)
    public void updateQuestionFromDatabase(@RequestBody Question newQuestion){
        questionService.updateQuestionFromDatabase(newQuestion);
    }

    // Delete
    @RequestMapping(path = "/questions/deleteByqid/{qid}", method =RequestMethod.DELETE)
    public void deleteQuestionFromDatabase(@PathVariable int qid){
        Question question = questionService.getQuestionFromDatabaseByqid(qid);
        questionService.deleteQuestionFromDatabase(question);
    }
}