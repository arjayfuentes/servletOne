package com.exercise.servlet1.core;

import java.util.List;
import java.util.Set;

public class ContactsService {

	private ContactsDao contactsDao = new ContactsDao();
	private Validation check = new Validation();

	//option1 contacts
  	public List<Contact> getContacts(String personId){
    	return contactsDao.getContacts(personId);
  	}

	public boolean checkContactIfExist(String personId, long contactId){
		List<Contact> contacts = contactsDao.getContacts(personId);
		boolean contactExist = false;
		loop: for(Contact con : contacts){
			if(con.getId()==contactId){
				contactExist=true;
				break loop;
			}
		}
		return contactExist;
	}
	
	public boolean checkContactValueIfExist(String personId, String newContactValue){
		boolean exist = false;
		List<Contact> contacts = getContacts(personId);
		for(Contact c: contacts){
			if(c.getContactValue().equals(newContactValue)){
				exist = true;
				break;
			}
		}
		return exist;
	}

	//option2 contacts
  	public void addContact(String personId, Contact newContact){
    	contactsDao.addContact(personId,newContact);
  	}

	//option3 contacts
	public void deleteContact(long contactId){
    	contactsDao.deleteContact(contactId);
  	}

	//option4 contacts
	public void updateContact(long contactId, String newContactValue){
    	contactsDao.updateContact(contactId, newContactValue);
	}

	public Contact getContact(long contactId){
		return contactsDao.getContact(contactId);
	}

	public long checkInputContact(String message, String personId){
		long contactId= 0;
		do{
			contactId = check.inputIdNumber(message);
			if(checkContactIfExist(personId, contactId)==false){
				System.out.print("contactId for person with personId = "+personId+" does not exist. ");
			}
		}while((checkContactIfExist(personId, contactId)) == false);
		return contactId;
	}


}
