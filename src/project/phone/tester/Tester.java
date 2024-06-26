package project.phone.tester;

import java.time.LocalDate;
import java.util.Scanner;
import project.phone.*;
import java.util.*;

public interface Tester 
{

	public static void main(String[] args) 
	{
		Set <String> mNo;
		String name;
		String email;
		LocalDate dob;
		int choice;
		boolean exit=false;
		Set <Contact> phoneBook=new HashSet<>();
		Scanner sc= new Scanner(System.in);
		while(!exit)
		{
				//Scanner sc= new Scanner(System.in);
				System.out.println("1. Add New contact ");
				System.out.println("2. Display All Contact");
				System.out.println("3. Update Contact details for Given  Name  And DOB");
				System.out.println("4. Remove All Contacts who are above 80 Year");
				System.out.println("5. Exit");
				choice=sc.nextInt();
				try
				{
					switch(choice)
					{
						case 1:
							Validate.addContact(phoneBook);
							break;
						case 2:
							Validate.displayContact(phoneBook);
							break;
						case 3:
							Validate.update(phoneBook);
							break;
						case 4:
							Validate.remove(phoneBook);
							break;
						case 5:
							exit=true;
							sc.close();
							break;
						default:
							break;
					}
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
		}
	}
}
