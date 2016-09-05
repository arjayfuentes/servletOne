package com.exercise.servlet1.core;

import java.text.ParseException;
import java.util.*;

import com.exercise.servlet1.HibernateUtil;
import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.apache.commons.lang3.time.DateUtils;

public class PersonDao{

	public Person getPerson(String personId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Person person = null;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			person = (Person) session.get(Person.class,personId);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return person;
	}

	public Address getPersonAddress(String personId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Person person = null;
		Address address = new Address();
		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Person.class);
			criteria.add(Restrictions.eq("id",personId));
			person= (Person) criteria.uniqueResult();
			address = person.getAddress();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return address;
	}

	public List<Person> getPersons(String order) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List <Person> persons = null;
		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Person.class);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			if (order.equals("lastName")) {
				criteria.addOrder(Order.asc("lastName"));
			} else if (order.equals("dateHired")) {
				criteria.addOrder(Order.asc("dateHired"));
			} else {
				criteria.addOrder(Order.asc("id"));
			}
			persons = (List <Person>)criteria.list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return persons;
	}
	
	public List<Person> getPersonsSearch(String searchType, String searchValue){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List <Person> persons = null;
		Query query =null;
		try {
			tx = session.beginTransaction();
			switch(searchType){
				case "id" :
					query = session.createQuery("FROM Person p WHERE p.id = :searchValue");
					query.setParameter("searchValue",searchValue);
					break;
				case "lastName" :
					query = session.createQuery("FROM Person p WHERE p.lastName = :searchValue");
					query.setParameter("searchValue",searchValue);
					break;
				case "dateHired" :
					Date dateSearchValue=DateUtils.parseDate(searchValue, "MM-dd-yyyy");
					query = session.createQuery("FROM Person p WHERE p.dateHired = :searchValue");
					query.setParameter("searchValue",dateSearchValue);
					break;
				case "gwa":
					float gwaSearchValue = Float.parseFloat(searchValue);
					query = session.createQuery("FROM Person p WHERE p.gwa = :searchValue");
					query.setParameter("searchValue",gwaSearchValue);
					break;
				default:
					break;
			}	
		    persons = query.list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return persons;
	}

	public void addPerson(Person person) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(person);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void deletePerson(String personId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Person person = (Person) session.get(Person.class, personId);
			Set<Role> roles = person.getRoles();
			person.getRoles().removeAll(roles);
			session.delete(person);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void updatePerson(String personId, Person updatedPerson){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Person person =(Person)session.get(Person.class, personId);
			person.setFirstName(updatedPerson.getFirstName());
			person.setMiddleName(updatedPerson.getMiddleName());
			person.setLastName(updatedPerson.getLastName());
			person.setSuffix(updatedPerson.getSuffix());
			person.setTitle(updatedPerson.getTitle());
			person.setBirthDate(updatedPerson.getBirthDate());
			person.setEmployed(updatedPerson.getEmployed());
			person.setGwa(updatedPerson.getGwa());
			person.setDateHired(updatedPerson.getDateHired());
			person.setAddress(updatedPerson.getAddress());
			person.setRoles(updatedPerson.getRoles());
			session.merge(person);
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}

}
