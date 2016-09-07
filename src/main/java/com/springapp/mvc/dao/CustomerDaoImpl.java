package com.springapp.mvc.dao;

import com.springapp.mvc.model.Customer;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by arifen on 7/5/16.
 */
@Repository("customerDao")
public class CustomerDaoImpl implements CustomerDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveCustomer(Customer customer) {
        Session session = this.sessionFactory.getCurrentSession();
        // TODO Auto-generated method stub
        System.out.println("AMLOG:: user: " + customer);
        session.saveOrUpdate(customer);
        System.out.println("AMLOG:: CustomerId: " + customer.getCustomerId());
    }

    @Override
    public List<Customer> findCustomerByUserId(String userId) {
        Session session = this.sessionFactory.getCurrentSession();
        System.out.print("customer Id " + userId);
        Query query = session.createQuery("from Customer where customerId = :userId ");
        query.setParameter("userId", userId);
        List<Customer> list = query.list();
        return list;
    }

    @Override
    public List<Customer> findCustomerByPackageId(long packageId) {
        Session session = this.sessionFactory.getCurrentSession();
        System.out.print("Package Id " + packageId);
        Query query = session.createQuery("from Customer where package_id = :packageId ");
        query.setParameter("packageId", packageId);
        List<Customer> list = query.list();
        return list;
    }

    @Override
    public Map<String, Object> findAllCustomerPagination(int begin, int pageSize) {
        Map<String, Object> customerMap = new HashMap<String, Object>();
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.eq("status", "active"));
        criteria.setFirstResult(begin);
        criteria.setMaxResults(pageSize);
        //Query query = session.createQuery("from Customer ");
        List<Customer> list = criteria.list();//query.list();
        customerMap.put("customeList", list);

        Criteria criteriaa = session.createCriteria(Customer.class);
        criteriaa.setProjection(Projections.rowCount());
        Long total = (Long) criteriaa.uniqueResult();
        customerMap.put("total", total);

        return customerMap;
    }

    @Override
    public Long findActiveCustomersByPackageId(long packageId) {
       /* Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.and(Restrictions.eq("status", "active"),Restrictions.eq("package_id", packageId)));
        *//*criteria.add(Restrictions.eq("status", "active"));
        criteria.add(Restrictions.eq("package_id", packageId));*//*
        *//*criteria.setProjection(Projections.rowCount());
        return (Long) criteria.uniqueResult();*//*
        return Long.valueOf(criteria.list().size());*/
        Session session = this.sessionFactory.getCurrentSession();
        System.out.print("Package Id " + packageId);
        Query query = session.createQuery("from Customer where package_id = :packageId  and status = :status");
        query.setParameter("packageId", packageId);
        query.setParameter("status", "active");
        List<Customer> list = query.list();
        return Long.valueOf(list.size());
    }

    @Override
    public Long findAllActiveCustomer() {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.eq("status", "active"));
        criteria.setProjection(Projections.rowCount());
        return (Long) criteria.uniqueResult();
    }

    public List<Customer> findAllCustomer() {
        System.out.println("customer dao");
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Customer.class);
        /*criteria.setFirstResult(0);
        criteria.setMaxResults(10);*/
        //Query query = session.createQuery("from Customer ");
        List<Customer> list = criteria.list();//query.list();
        criteria.setProjection(Projections.rowCount());
        Long total = (Long) criteria.uniqueResult();
        System.out.print("customerlist size customer dao" + list.size());
        return list;
    }

    @Override
    public Customer findCustomerById(long id) {
        /*
        there is subtle difference between get and load method
        */
        Session session = this.sessionFactory.getCurrentSession();
        return (Customer) session.get(Customer.class, id);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        Session session = this.sessionFactory.getCurrentSession();
        /*
        * first remove customer from package set(list of customer) for delete customer .
        * */
        customer.getaPackage().getCustomerSet().remove(customer);
        session.delete(customer);
    }
}
