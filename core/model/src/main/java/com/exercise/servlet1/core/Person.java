package com.exercise.servlet1.core;

import org.hibernate.annotations.*;
import java.util.Date;
import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Entity

@Table(name = "Person")
public class Person implements Serializable
{
	@Id
	@GenericGenerator(name = "custom_id", strategy = "com.exercise.servlet1.CustomPersonId")
	@GeneratedValue(generator = "custom_id")
	@Column(name = "id")
	private String id;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "middleName")
	private String middleName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "suffix")
	private String suffix;

	@Column(name = "title")
	private String title;

	@Column(name = "birthDate")
	@Type(type="date")
	private Date birthDate;


	@Column(name = "employed" )
	private Boolean employed;

	@Column(name = "gwa")
	private float gwa;

	@Column(name = "dateHired")
	@Type(type="date")
	private Date dateHired;

	@OneToMany(cascade=CascadeType.ALL , fetch = FetchType.EAGER)
	@JoinColumn(name="personId")
	private Set<Contact> contacts;

	@ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name="person_role", joinColumns={ @JoinColumn (name="personId")}, inverseJoinColumns = {@JoinColumn(name="roleId")})
	private Set<Role> roles;

	@OneToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval=true)
	@JoinColumn(name="addressId")
	private Address address;

	public Person(){}

	public Person(String firstName, String middleName,String lastName, String suffix, String title,
					Date birthDate,Boolean employed,float gwa, Date dateHired, Address address , Set<Contact> contacts,Set<Role> roles){

		this.firstName=firstName;
		this.middleName=middleName;
		this.lastName=lastName;
		this.suffix=suffix;
		this.title=title;
		this.birthDate=birthDate;
		this.gwa=gwa;
		this.employed=employed;
		this.dateHired=dateHired;
		this.address=address;
		this.roles = roles;
		this.contacts= contacts;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Boolean getEmployed() {
		return employed;
	}

	public void setEmployed(Boolean employed) {
		this.employed = employed;
	}

	public float getGwa() {
		return gwa;
	}

	public void setGwa(float gwa) {
		this.gwa = gwa;
	}

	public Date getDateHired() {
		return dateHired;
	}

	public void setDateHired(Date dateHired) {
		this.dateHired = dateHired;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
