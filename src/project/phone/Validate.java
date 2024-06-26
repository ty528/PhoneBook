package project.phone;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;

public interface Validate 
{
	//To add Contact In PhoneBook Set
	public static void addContact(Set <Contact> phoneBook) throws Exception
	{
		Set <String> mNo=new HashSet<String>();
		String name;
		String email;
		LocalDate dob;
		String mtemp=null;
		Scanner sc=new Scanner(System.in);
		while(true)
		{
			System.out.println("Enter Phone Number");
			mtemp=sc.next();
			//Checking Mobile Number is already in PhoneBook Or Not 
			for(Contact c:phoneBook)
			{
				Set<String> temp=c.getPhoneBook();
				for(String s:temp)
				{
					if(s.equals(mtemp))
					{
						throw new Exception("Already Register With Our PhoneBook.\n Please Register With Another Mobile Number");
					}
				}
			}
			//Validating Inputed Mobile  Number Is valid Or Not
			if(!(validatePhoneNumber(mtemp)))
			{
					throw new Exception ("Please Enter Valid Number");
			}
			mNo.add(mtemp);
			System.out.println("Do You Want To add One More Mobile Number? Yes Or No ");
			if(sc.next().toLowerCase().equals("no"))
			{
				break;
			}
		}
		sc.nextLine();
		System.out.println("Enter  Full Name Please ");
		name=sc.nextLine();
		//Validating Name
		if(!(validateName(name)))
		{
			throw new Exception ("Please Enter Valid Name");
		}
		System.out.println("Enter Email id Only Gmail id (abc@gmail.com)");
		System.out.println("First characte Shoul be alphbet or Number and Allowed Symbol Only _ . $ ");
		email=sc.next();
		//Checking  For Email Is Already Present In PhoneBook 
		for(Contact c:phoneBook)
		{
			if(c.getEmail().equals(email))
			{
				throw new Exception("Email Is Already Register With US \n Please Provide Another Email");
			}
		}
		// Validating Email
		if(!(validateEmail(email)))
		{
			throw new Exception ("Please Provide Valid Email Id ");
		}
		System.out.println("Enter Date Of Birth(YYYY-MM-DD)");
		dob=LocalDate.parse(sc.next());
		//Validate DOB
		if(!(validateDob(dob)))
		{
			throw new Exception ("Please Enter Valid Date Of Birth");
		}
		phoneBook.add(new Contact(mNo,name,email,dob));
		System.out.println("Contact Details Added Succesfully \n  Thank You For Using Our App");
	}
	//   defn  of ValidateDob()  
	public static boolean validateDob(LocalDate dob)
	{
		if(LocalDate.now().equals(dob))
		{
			return false;
		}
		return true;
	}
	// defn of validateEmail()
	public static boolean validateEmail(String email) 
	{
		Pattern p=Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9_.$]*@gmail[.]com");
		Matcher m=p.matcher(email);
		if(m.find() && m.group().equals(email))
		{
			return true;
		}
		return false;
	}
	//defn of validatePhoneNumber()
	public static boolean validatePhoneNumber(String mtemp)
	{
		if(mtemp.length()<10 || mtemp.length()>10)
		{
			return false;
		}
		else
		{
			Pattern p=Pattern.compile("[6-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]");
			Matcher m=p.matcher(mtemp);
			if(m.find() && m.group().equals(mtemp))
			{
				return true;
			}
			return false;
		}
	}
	// defn of validateName()
	public static boolean validateName(String name) 
	{
		Pattern p=Pattern.compile("[a-zA-Z]*[\\s]*[a-zA-Z]*");
		Matcher m=p.matcher(name);
		if(m.find() && m.group().equals(name))
		{
			return true;
		}
		return false;
	}
	//displayContact() for Displaying All Contact Present In PhoneBook
	public static void displayContact(Set<Contact> phoneBook)
	{
		Consumer<Contact> c=c1->System.out.println(c1);
		phoneBook.stream().forEach(c);
		
	}
	// update() for updating details 
	public static void update(Set<Contact> phoneBook) throws Exception 
	{
		String name;
		LocalDate dt;
		Contact ctemp=null;
		int choice;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Name");
		name=sc.nextLine();
		System.out.println("Enter DOB(YYYY-MM-DD)");
		dt=LocalDate.parse(sc.next());
		for(Contact c:phoneBook)
		{
			if(c.getDob().equals(dt) && c.getName().equals(name))
			{
				ctemp=c;
				break;
			}
		}
		if(ctemp!=null)
		{
			int choice2;
			String mNum;
			String email=null;
			String mTemp=null;
			Contact c1=null;
			Set <String> s = new HashSet<>(); 
			System.out.println("1. For To Update Phone Number");
			System.out.println("2. For To Update Email");
			choice=sc.nextInt();
			switch(choice)
			{
			case 1:
					System.out.println("1. For Delete Phone Number");
					System.out.println("2. For Add Phone Number");
					choice2=sc.nextInt();
					switch(choice2)
					{
					case 1:
						System.out.println("Enter Mobile Number");
						mNum=sc.next();
						if(!(validatePhoneNumber(mNum)))
						{
							throw new Exception("Please Provide Valid Mobile Number");
						}
						s=ctemp.getPhoneBook();
						for(String s1:s)
						{
							if(s1.equals(mNum))
							{
								mTemp=mNum;
								ctemp.deletePhoneNumber(mNum);
								
								break;
							}
						}
						if(mTemp!=null)
						{
							System.out.println("Deleted Succesfully");
						}
						else
						{
							System.out.println("Data Not Found");
						}
						s=ctemp.getPhoneBook();
						if(s.size()==0)
						{
							phoneBook.remove(ctemp);
						}
						break;
					case 2:
						System.out.println("Enter Mobile Number To Add");
						mNum=sc.next();
						for(Contact c:phoneBook)
						{
							Set<String> temp=c.getPhoneBook();
							for(String s1:temp)
							{
								if(s1.equals(mNum))
								{
									throw new Exception("Already Register With Our PhoneBook.\n Please Register With Another Mobile Number");
								}
							}
						}
						if(!(validatePhoneNumber(mNum)))
						{
							throw new Exception ("Please Enter Valid Number");
						}
						
						s.add(mNum);
						ctemp.setPhoneBook(s);
						break;
					default:
						System.out.println("Please Enter Valid Choice");
						break;
					}
				break;
			case 2:
				System.out.println("Please Enter Email Id Only Gmail Id (abc@gmail.com)");
				email=sc.next();
				for(Contact c:phoneBook)
				{
					if(c.getEmail().equals(email))
					{
						throw new Exception ("Already Register \nPlease Provide Another email ");
					}
				}
				if(!(validateEmail(email)))
				{
					throw new Exception ("Enter Valid Email ID");
				}
				ctemp.setEmail(email);
				break;
			default:
				System.out.println("Please Enter Valid Choice");
				break;
			}
		}
		else
		{
			throw new Exception ("Data Not Found");
		}
		
	}
	// remove() to delete contact whose age is greater than 80
	public static void remove(Set<Contact> phoneBook) 
	{
		Set <Contact> s1=new HashSet<>();
		int count=0;
		for(Contact c:phoneBook)
		{
			if(LocalDate.now().compareTo(c.getDob())>80)
			{
				s1.add(c);
				count++;
			}
		}
		phoneBook.removeAll(s1);
		if(count==0)
		{
			System.out.println("No Member Whose Age Is Greater Than 80");
		}
		else
		{
			System.out.println("Succesfully Deleted");
		}
	
	}
}
