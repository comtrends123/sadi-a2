package service;

import model.Result;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ResultService {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Create
    @Transactional
    public void saveResultToDatabase(Result result){
        sessionFactory.getCurrentSession().save(result);
    }

    // Read
    @Transactional
    public List<Result> getAllResults() {
        Query query = sessionFactory.getCurrentSession().createQuery("from Result");
        return query.list();
    }

    @Transactional
    public Result getResultFromDatabaseByrid(int rid){
        List<Result> results = getAllResults();
        Result resultR = new Result();
        for (Result result:results) {
            if (result.getRid() == rid) {
                resultR = result;
            }
        }
        return resultR;
    }

    // Update & Delete
    // Update and Delete result is logically not allowed
}
