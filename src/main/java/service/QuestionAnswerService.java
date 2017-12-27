package service;

import model.QuestionAnswer;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionAnswerService {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Create
    @Transactional
    public void saveQuestionAnswerToDatabase(QuestionAnswer questionAnswer){
        sessionFactory.getCurrentSession().save(questionAnswer);
    }

    // Read
    @Transactional
    public List<QuestionAnswer> getAllQuestionAnswerss() {
        Query query = sessionFactory.getCurrentSession().createQuery("from QuestionAnswer");
        return query.list();
    }

    @Transactional
    public List<QuestionAnswer> getQuestionAnswerssFromDatabaseByqid(int qid){
        List<QuestionAnswer> questionAnswers = getAllQuestionAnswerss();
        List<QuestionAnswer> questionAnswerResult = new ArrayList<QuestionAnswer>();
        for (QuestionAnswer questionAnswer:questionAnswers) {
            if (questionAnswer.getQid() == qid) {
                questionAnswerResult.add(questionAnswer);
            }
        }
        return questionAnswerResult;
    }

    @Transactional
    public QuestionAnswer getQuestionAnswerFromDatabaseByaid(int aid){
        List<QuestionAnswer> questionAnswers = getAllQuestionAnswerss();
        QuestionAnswer questionAnswerResult = new QuestionAnswer();
        for (QuestionAnswer questionAnswer:questionAnswers) {
            if (questionAnswer.getAid() == aid) {
                questionAnswerResult = questionAnswer;
            } 
        }
        return questionAnswerResult;
    }

    // Update
    @Transactional
    public void updateqidOfQuestionAnswerFromDatabaseByaid(QuestionAnswer questionAnswerWithNewqid){
        QuestionAnswer questionAnswer = getQuestionAnswerFromDatabaseByaid(questionAnswerWithNewqid.getAid());
        questionAnswer.setQid(questionAnswerWithNewqid.getQid());
        sessionFactory.getCurrentSession().update(questionAnswer);
    }

    @Transactional
    public void updateaidOfQuestionAnswerFromDatabaseByqid(QuestionAnswer oldQuestionAnswer, int newaid){
        QuestionAnswer questionAnswer = getQuestionAnswerFromDatabaseByaid(oldQuestionAnswer.getAid());
        questionAnswer.setAid(newaid);
        sessionFactory.getCurrentSession().update(questionAnswer);
    }

    // Delete
    @Transactional
    public void deleteQuestionAnswerFromDatabase(QuestionAnswer questionAnswer){
        sessionFactory.getCurrentSession().delete(questionAnswer);
    }

}
