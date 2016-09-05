package com.exercise.servlet1.core;

import java.util.*;
import org.hibernate.*;
import com.exercise.servlet1.HibernateUtil;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;


public class RoleDao {


    public List<Role> getRoles() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<Role> roles = new ArrayList<Role>();
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Role.class)
                    .setCacheable(true)
                    .addOrder(Order.asc("id"));
            roles = criteria.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return roles;
    }

    public Role getRole(long roleId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Role role = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Role.class)
                .add(Restrictions.eq("id",roleId))
                .setCacheable(true);
            role = (Role) criteria.uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return role;
    }

    public Set <Role> getPersonRoles(String personId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Set <Role> personRoles = new HashSet<Role>();
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Person.class)
                    .add(Restrictions.eq("id",personId))
                    .setCacheable(true);
            Person person = (Person)criteria.uniqueResult();
            personRoles.addAll(person.getRoles());
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return personRoles;
    }



    public Role getPersonRole(String personId, long roleId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Set<Role> personRoles = null;
        Person person = null;
        Role role = null;
        try {
            tx = session.beginTransaction();
            person = (Person) session.get(Person.class, personId);
            personRoles = person.getRoles();
            for (Role r : personRoles) {
                if (r.getId() == roleId) {
                    role = r;
                }
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return role;
    }

    //option2 done
    public void addPersonRole(String personId, Role role) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Person person = (Person) session.get(Person.class, personId);
            person.getRoles().add(role);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    //option 3 done
    public void deletePersonRole(String personId, long roleId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Set<Role> personRoles = null;
        Role role = null;
        try {
            tx = session.beginTransaction();
            Person person = (Person) session.get(Person.class, personId);
            personRoles = person.getRoles();
            for (Role r : personRoles) {
                if (r.getId() == roleId) {
                    role = r;
                    break;
                }
            }
            person.getRoles().remove(role);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    //option 5
    public void addRole(Role role) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(role);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    //option 6
    public void updateRole(long roleId,String updatedRole) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Role role = (Role) session.get(Role.class, roleId);
            role.setRoleName(updatedRole);
            session.merge(role);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Role sample(long roleId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Role role = null;
        try {
            tx = session.beginTransaction();
            role = (Role) session.get(Role.class, roleId);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return role;
    }
    
    public void deleteRole(long roleId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Role role = (Role) session.get(Role.class, roleId);
            session.delete(role);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
