package com.exercise.servlet1.core;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.lang3.math.NumberUtils;

public class Validation {

	private Scanner read = new Scanner(System.in);
	private List<String> errors = new ArrayList<String>();
	
	public List<String> checkPersonValid( String firstName, String middleName, String lastName, 
				String gwa, String birthDate, String employed, String dateHired, String [] roleId , 
				String houseNo, String street, String barangay, String city, String zipCode, 
				String contactMobile, String contactLandline, String contactEmail ){
		
		checkName(firstName, middleName, lastName, gwa, birthDate, dateHired, houseNo, street, barangay, city,zipCode);
		checkGwa(gwa);
		checkDate(birthDate,"birthDate");
		checkDate(dateHired,"dateHired");
		checkRole(employed,roleId);
		checkNumber(houseNo,"houseNo");
		checkNumber(zipCode,"zipCode");
		checkContactNumber(contactMobile,11,"mobile");
		checkContactNumber(contactLandline,7,"landline");
		checkContactEmail(contactEmail);
		
		return errors;
	}
	
	public void clearErrors(){
		errors.clear();
	}
	
	public void checkName(String firstName, String middleName, String lastName, 
		String gwa, String birthDate, String dateHired,
		String houseNo, String street, String barangay, String city, String zipCode ){
		
		if(firstName.isEmpty()|| middleName.isEmpty() || lastName.isEmpty() 
			|| gwa.isEmpty() || birthDate.isEmpty() || dateHired.isEmpty() 
			|| houseNo.isEmpty() || street.isEmpty() || barangay.isEmpty() || city.isEmpty() || zipCode.isEmpty()){
			errors.add("Empty required fields.");
		}
		
	}
		
	public void checkGwa(String gwa){
		int min = 1;
		int max = 5;
		try{
	      float f = Float.valueOf(gwa);
	      if(f<1 && f>5){
	    	  errors.add("Invalid Gwa");
	      }
	    }
	    catch (NumberFormatException e){
	    	errors.add("Invalid Gwa");
	    }
	}
	
	public void checkDate(String dateIn, String dateType){		  
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date checkDate=null;
		try {
			checkDate = formatter.parse(dateIn);
		} catch (ParseException e) {
			errors.add("Invalid date format for "+dateType);
		}
	}
	
	public void checkRole(String employed, String [] roleId){
		if(Boolean.parseBoolean(employed)){
			if(roleId.length==0){
				errors.add("Employed choice is YES.Choose a role");
			}
		}
		
	}
	
	public void checkNumber(String addressNum, String num){
		if(NumberUtils.isDigits(addressNum)!=true){
			errors.add("Invalid input in "+num+" field");
		}
	}

	public void checkContactNumber(String contactValue,int length, String num){
		if(!contactValue.isEmpty()){
			if(NumberUtils.isDigits(contactValue)!=true){
				errors.add("Invalid input in "+num+" field");
			}
		}
	}
	
	public void checkContactEmail(String contactEmail){
		if(!contactEmail.isEmpty()){
			EmailValidator emailValid = EmailValidator.getInstance();
			if(!emailValid.isValid(contactEmail)){
				errors.add("Invalid email");
			}
		}
	}
	

	public int inputNumber(String numberType){
		int number=0;
		System.out.printf("Enter %s: ",numberType);
		while (!read.hasNextInt()) {
			System.out.printf("Not a number! Enter %s: ",numberType);
			read.next();
		}
		number = read.nextInt();
		read.nextLine();
		return number;
	}

	public long inputIdNumber(String message){
		long number=0;
		System.out.printf("Enter %s: ",message);
		while (!read.hasNextLong()) {
			System.out.printf("Not a number! Enter %s: ",message);
			read.next();
		}
		number = read.nextLong();
		read.nextLine();
		return number;
	}

	public int inputNumber(String numberType, int min, int max){
		int number=0;
		do {
			System.out.print("Enter "+numberType+"("+min+"-"+max+"): ");
			while (!read.hasNextInt()) {
				System.out.printf("Not a number! Enter"+numberType+"("+min+"-"+max+"): ");
				read.next();
			}
			number = read.nextInt();
			read.nextLine();
			if(number<min || number> max){
				System.out.print("Invalid Input! ");
			}
		} while (number<min || number >max);
	  	return number;
	}

	public int inputNumber(String numberType,int length){
		int number=0;
		do {
			System.out.printf("Enter %s (%d digits): ",numberType,length);
			while (!read.hasNextInt()) {
			  	System.out.printf("Not a number! Enter %s (%d digits): ",numberType,length);
			  	read.next();
			}
			number = read.nextInt();
			read.nextLine();
			if(Integer.toString(number).length()!=length) {
				System.out.print("Invalid length of integers. ");
			}
		} while (Integer.toString(number).length()!=length);
		return number;
	}

	public String inputContactNumber(String message,int length){
		long number=0;
		String contactValue= null;
		do {
			System.out.printf("Enter "+message+" ("+length+" digits) : ");
			while (!read.hasNextLong()) {
			  	System.out.printf("Not a number!Re-enter value");
			  	read.next();
			}
			contactValue = read.next();
			read.nextLine();
			if(contactValue.length()!=length) {
				System.out.print("Invalid length of integers. ");
			}
		} while (contactValue.length()!=length);
		return contactValue;
	}

	public String inputString(String stringType){
		System.out.print("Enter "+stringType+": ");
		String string = read.nextLine();
		while (string.length()==0){
		   if(string.length()==0){
		   System.out.print("Empty input. Re-enter "+stringType+": ");
		   }
		   string = read.nextLine();
		}
		string=string.trim();
		string=StringUtils.capitalize(string);
		return string;
	}

	public String inputIdPerson(String message){
		System.out.print("Enter "+message+": ");
		String string = read.nextLine();
		while (string.length()==0){
		   if(string.length()==0){
		   System.out.print("Empty input. Re-enter "+message+": ");
		   }
		   string = read.nextLine();
		}
		string=string.trim();
		string=StringUtils.capitalize(string);
		return string;
	}

	public String inputEmail(){
		EmailValidator emailValid = EmailValidator.getInstance();
		boolean validEmail =false;
		String email=null;
		while(validEmail==false){
			System.out.print("Enter email address: ");
			email=read.next();
			if(emailValid.isValid(email)){
				validEmail=true;
			}
			else{
				System.out.print("Invalid Email. ");
			}

		}
		return email;
	}

	public float inputGwa(){
		System.out.print("Enter GWA: ");
		int min = 1;
		int max = 5;
		float input=0;
		do {
			 while (!read.hasNextFloat()) {
			  	System.out.print("Not a float number!. Re-enter Gwa: ");
			  	read.next();
			 }
			 input = read.nextFloat();
			 read.nextLine();
			 if(input<min || input> max){
				 System.out.print("Invalid input GWA should be from 1-5 ! ");
			 }
		 } while (input<min || input >max);
		 DecimalFormat twoDForm = new DecimalFormat("#.##");
		 return (float) Float.valueOf(twoDForm.format(input));
	}

	public boolean inputYesOrNo(String askBoolean){
		System.out.print(askBoolean+" (Y/N) ");
		boolean ask = false;
		String input = read.next();
		boolean correct=false;
		while(correct==false){
			if(input.equalsIgnoreCase("Y")){
				correct= true;
				ask=true;
				break;
			}
			else if(input.equalsIgnoreCase("N")){
				correct= true;
				ask=false;
				break;
			}
			else{
			System.out.print("Invalid input. Only (Y/N) or (y/n) : ");
			}
			input = read.next();
		}
		return ask;
	}

	public Boolean inputEmployed(String askBoolean){
		System.out.print(askBoolean+" (Y/N) ");
		boolean ask = false;
		String input = read.next();
		boolean correct=false;
		while(correct==false){
			if(input.equalsIgnoreCase("Y")){
				correct= true;
				ask=true;
				break;
			}
			else if(input.equalsIgnoreCase("N")){
				correct= true;
				ask=false;
				break;
			}
			else{
			System.out.print("Invalid input. Only (Y/N) or (y/n) : ");
			}
			input = read.next();
		}
		return Boolean.valueOf(ask);
	}

	public Date inputDate(String dateType){
		System.out.print("Enter "+dateType+" (MM/DD/YYYY): ");
		String input = read.nextLine();
		while(!input.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")){
				System.out.print("Invalid date format. Re-enter date: ");
				input = read.next();
		}
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date checkDate=null;
		try {
			checkDate = formatter.parse(input);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return checkDate;
	}

	
	
	

}
