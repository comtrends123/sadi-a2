package service;

import model.UserInfo;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserInfoService {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Create
    @Transactional
    public void saveUserInfoToDatabase(UserInfo userInfo){
        sessionFactory.getCurrentSession().save(userInfo);
    }

    // Read
    @Transactional
    public List<UserInfo> getAllUserInfoss() {
        Query query = sessionFactory.getCurrentSession().createQuery("from UserInfo");
        return query.list();
    }

    @Transactional
    public UserInfo getUserInfoFromDatabaseByuid(int uid){
        List<UserInfo> userInfoss = getAllUserInfoss();
        UserInfo userInfoResult = new UserInfo();
        for (UserInfo userInfo:userInfoss) {
            if (userInfo.getUid() == uid) {
                userInfoResult = userInfo;
            }
        }
        return userInfoResult;
    }

    @Transactional
    public UserInfo getUserInfoFromDatabaseByUsernane(String username){
        List<UserInfo> userInfoss = getAllUserInfoss();
        UserInfo userInfoResult = new UserInfo();
        for (UserInfo userInfo:userInfoss) {
            if (userInfo.getUsername().equals(username)) {
                userInfoResult = userInfo;
            }
        }
        return userInfoResult;
    }

    @Transactional
    public List<UserInfo> getUserInfoFromDatabaseByName(String name){
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UserInfo.class);
        criteria.add(Restrictions.like("name", name, MatchMode.ANYWHERE));
        return criteria.list();
    }

    // If True == admin
    // If False == normal user
    @Transactional
    public List<UserInfo> getUserInfoFromDatabaseByRole(Boolean role){
        List<UserInfo> userInfoss = getAllUserInfoss();
        List<UserInfo> userInfossResult = new ArrayList<UserInfo>();
        for (UserInfo userInfo:userInfoss) {
            if (userInfo.getRole() == role) {
                userInfossResult.add(userInfo);
            }
        }
        return userInfossResult;
    }

    // Update

    // Change username
    @Transactional
    public void updateUsernameOfUserInfoFromDatabase(UserInfo userInfoWithNewUsername){
        UserInfo userInfo = getUserInfoFromDatabaseByuid(userInfoWithNewUsername.getUid());
        if (userInfo.getPassword().equals(userInfoWithNewUsername.getPassword())) {
            userInfo.setUsername(userInfoWithNewUsername.getUsername());
            sessionFactory.getCurrentSession().update(userInfo);
        }

    }

    // Change password
    @Transactional
    public void updatePasswordOfUserInfoFromDatabase(UserInfo userInfoWithNewPassword, String oldpassword) {
        UserInfo userInfo = getUserInfoFromDatabaseByuid(userInfoWithNewPassword.getUid());
        if (userInfo.getPassword().equals(oldpassword)) {
            userInfo.setPassword(userInfoWithNewPassword.getPassword());
            sessionFactory.getCurrentSession().update(userInfo);
        }
    }

    // Change name
    @Transactional
    public void updateNameOfUserInfoFromDatabase(UserInfo userInfoWithNewName){
        UserInfo userInfo = getUserInfoFromDatabaseByuid(userInfoWithNewName.getUid());
        if (userInfo.getPassword().equals(userInfoWithNewName.getPassword())) {
            userInfo.setName(userInfoWithNewName.getName());
            sessionFactory.getCurrentSession().update(userInfo);
        }

    }

    // Change role
    @Transactional
    public void updateRoleOfUserInfoFromDatabase(UserInfo userInfoWithNewRole){
        UserInfo userInfo = getUserInfoFromDatabaseByuid(userInfoWithNewRole.getUid());
        if (userInfo.getPassword().equals(userInfoWithNewRole.getPassword())) {
            userInfo.setRole(userInfoWithNewRole.getRole());
            sessionFactory.getCurrentSession().update(userInfo);
        }

    }

    // Delete
    @Transactional
    public void deleteUserInfoFromDatabase(UserInfo userInfo){
        sessionFactory.getCurrentSession().delete(userInfo);
    }
}
