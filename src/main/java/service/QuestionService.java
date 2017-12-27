package service;

import model.Question;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Create
    @Transactional
    public void saveQuestionToDatabase(Question question){
        sessionFactory.getCurrentSession().save(question);
    }

    // Read
    @Transactional
    public List<Question> getAllQuestions() {
        Query query = sessionFactory.getCurrentSession().createQuery("from Question");
        return query.list();
    }

    @Transactional
    public Question getQuestionFromDatabaseByqid(int qid){
        List<Question> questions = getAllQuestions();
        Question questionResult = new Question();
        for (Question question:questions) {
            if (question.getQid() == qid) {
                questionResult = question;
            }
        }
        return questionResult;
    }

    @Transactional
    public List<Question> getQuestionFromDatabaseByKeyword(String keyword){
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Question.class);
        criteria.add(Restrictions.like("question", keyword, MatchMode.ANYWHERE));
        return criteria.list();
    }

    // Update
    @Transactional
    public void updateQuestionFromDatabase(Question newQuestion){
        Question question = getQuestionFromDatabaseByqid(newQuestion.getQid());
        question.setQuestion(newQuestion.getQuestion());
        sessionFactory.getCurrentSession().update(question);
    }

    // Delete
    @Transactional
    public void deleteQuestionFromDatabase(Question question){
        sessionFactory.getCurrentSession().delete(question);
    }
}
