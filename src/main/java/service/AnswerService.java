package service;

import model.Answer;
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
public class AnswerService {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Create
    @Transactional
    public void saveAnswerToDatabase(Answer answer){
        sessionFactory.getCurrentSession().save(answer);
    }

    // Read
    @Transactional
    public List<Answer> getAllAnswers() {
        Query query = sessionFactory.getCurrentSession().createQuery("from Answer");
        return query.list();
    }

    @Transactional
    public Answer getAnswerFromDatabaseByaid(int aid){
        List<Answer> answers = getAllAnswers();
        Answer answerResult = new Answer();
        for (Answer answer:answers) {
            if (answer.getAid() == aid) {
                answerResult = answer;
            }
        }
        return answerResult;
    }

    @Transactional
    public List<Answer> getAnswerFromDatabaseByKeyword(String keyword){
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Answer.class);
        criteria.add(Restrictions.like("answer", keyword, MatchMode.ANYWHERE));
        return criteria.list();
    }

    // Update
    @Transactional
    public void updateAnswerFromDatabase(Answer newAnswer){
        Answer answer = getAnswerFromDatabaseByaid(newAnswer.getAid());
        answer.setAnswer(newAnswer.getAnswer());
        answer.setCorrectcheck(newAnswer.isCorrectcheck());
        sessionFactory.getCurrentSession().update(answer);
    }

    // Delete
    @Transactional
    public void deleteAnswerFromDatabase(Answer answer){
        sessionFactory.getCurrentSession().delete(answer);
    }
}
