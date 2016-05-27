package com.springapp.mvc.dao;

import com.springapp.mvc.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by arifen on 5/26/16.
 */
@Repository("userDao")
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        // TODO Auto-generated method stub
        System.out.println("AMLOG:: user: " + user);
        session.save(user);
        System.out.println("AMLOG:: UserId: " + user.getUserId());
    }

    @Override
    public User findUserByLoginId(String loginId) {

        Session session = this.sessionFactory.getCurrentSession();
        System.out.print("login id "+loginId);
        Query query = session.createQuery("from User where loginId = :loginId ");
        query.setParameter("loginId", loginId);
        List<User> list = query.list();
        if(list != null && list.size()>0){
            return list.get(0);
        }
        return null;
    }
}
