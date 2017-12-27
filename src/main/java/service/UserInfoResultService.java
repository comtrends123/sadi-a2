package service;

import model.UserInfoResult;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserInfoResultService {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Create
    @Transactional
    public void saveUserInfoResultToDatabase(UserInfoResult userInfoResult){
        sessionFactory.getCurrentSession().save(userInfoResult);
    }

    // Read
    @Transactional
    public List<UserInfoResult> getAllUserInfoResults() {
        Query query = sessionFactory.getCurrentSession().createQuery("from UserInfoResult");
        return query.list();
    }

    @Transactional
    public List<UserInfoResult> getUserInfoResultsFromDatabaseByuid(int uid){
        List<UserInfoResult> userInfoResults = getAllUserInfoResults();
        List<UserInfoResult> userInfoResultsR = new ArrayList<UserInfoResult>();
        for (UserInfoResult userInfoResult:userInfoResults) {
            if (userInfoResult.getUid() == uid) {
                userInfoResultsR.add(userInfoResult);
            }
        }
        return userInfoResultsR;
    }

    @Transactional
    public UserInfoResult getUserInfoResultFromDatabaseByrid(int rid){
        List<UserInfoResult> userInfoResults = getAllUserInfoResults();
        UserInfoResult userInfoResultR = new UserInfoResult();
        for (UserInfoResult userInfoResult:userInfoResults) {
            if (userInfoResult.getRid() == rid) {
                userInfoResultR = userInfoResult;
            }
        }
        return userInfoResultR;
    }

    // Update & Delete
    // Update and Delete result is logically not allowed
}
