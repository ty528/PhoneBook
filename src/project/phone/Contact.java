package project.phone;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class Contact
{
	private Set <String> phoneBook;
	private final String name;
	private String email;
	private final LocalDate dob;
	public Contact(Set<String> phoneBook, String name, String email, LocalDate dob)
	{
		super();
		this.phoneBook = phoneBook;
		this.name = name;
		this.email = email;
		this.dob = dob;
	}
	public Set<String> getPhoneBook() 
	{
		return phoneBook;
	}
	@Override
	public String toString()
	{
		return "Contact [phoneBook=" + phoneBook + ", name=" + name + ", email=" + email + ", dob=" + dob + "]";
	}
	public void setPhoneBook(Set<String> phoneBook)
	{
		this.phoneBook.addAll(phoneBook);
	}
	public String getName() 
	{
		return name;
	}
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public LocalDate getDob()
	{
		return dob;
	}
	@Override
	public int hashCode() 
	{
		int year=dob.getYear();
		int month=dob.getMonthValue();
		int date=dob.getDayOfMonth();
		int length=email.length();
		int lengthName=name.length();
		return year+month+date+length+lengthName;
	}
	@Override
	public boolean equals(Object obj) 
	{
		if(obj instanceof Contact)
		{
			Contact ctemp=(Contact)obj;
			if(this.name.equals(ctemp.getName()) && this.email.equals(ctemp.getEmail()) &&
				this.getPhoneBook().containsAll(ctemp.getPhoneBook()) && ctemp.dob.equals(ctemp.getDob()))
			{
				return true;
			}
		}
		return false;
	}
	public void deletePhoneNumber(String mNo)
	{
		phoneBook.remove(mNo);
	}
	

}
