import java.util.*;
import java.io.*;

/**
 * ----------------------------DETAILS-------------------------------
 * Subject Code: TSE2101
 * Subject: Software Engineering Fundamentals
 * Assignment Title: Patient Oriented Eczema Measurement Program (POEM) in Java
 * Lecturer Name: Dr Ho Sin Ban
 * Group Tutorial Section: TT06
 * Group Members: 
 * - Lis Nadya binti Zaidi [Leader] - 1161102208
 * - Nur Farisha Shahirah binti Abdul Rahman [Secretary] - 1171302013
 * - Ishraf bin Ismay [Editor] - 1161102210
 * - Nuraishah Sabrina binti Shahrir Zaid [Treasurer] - 1161102321
 * ------------------------------------------------------------------
 **/

/**
 * PoemDatabase - Patient Oriented Measurement (POEM) Database
 * @author Lis Nadya binti Zaidi [Leader] - 1161102208
 * @author Nur Farisha Shahirah binti Abdul Rahman [Secretary] - 1171302013
 * @author Ishraf bin Ismay [Editor] - 1161102210
 * @author Nuraishah Sabrina binti Shahrir Zaid [Treasurer] - 1161102321
 **/
public class PoemDatabase{
	//iList - list of existing users 
	ArrayList <User> iList = new ArrayList <User>();

	PoemDatabase(){
		//----------------HARDCODES TO BE DELETED-------------------------
		iList.add(new User("Aishah","Sabrina", 20, "F", 23425435, "Shah Alam", 1161102321, "1234"));
		iList.add(new User("Ishraf","Ismay", 20, "M", 34534523, "Petaling Jaya", 1161102258, "1234"));
		iList.add(new User("Nur","Farisha", 20, "F", 34234234, "Kuala Lumpur", 1161102091, "1234"));
		//-----------------------------------------------------------------
	}
	
	PoemDatabase(User aUser){
		this.iList.add(aUser);
	}
	
	public void save(){
		try {
			PrintWriter save = new PrintWriter(new BufferedWriter(new FileWriter("Database.txt")));
			save.println("| No. | \tFirst Name\t | \tLast Name\t |  Age  |  Gender  | \tPhone No.\t|  \tAddress\t      |   Username   |   Password   |");
			for(int i=0; i<iList.size();i++){
				int num = i+1;
				save.print("  " + num + ". \t");
				save.println(iList.get(i));
			}
			save.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void add(User u){
		this.iList.add(u);
	}
	public User get(int i){
		return iList.get(i);
	}
	public List <User> getList(int i){
		return iList;
	}
	
	public boolean userExists(User aUser){
		if(iList.contains(aUser))
			return true;
		return false;
	}
	
	public boolean userValid(int aUser){
		for(User u:iList){
			if(u.getUser()==aUser)
				return true;
		}
		return false;
	}
	
	public boolean passValid(String aPass){
		for(User u:iList){
			if(u.getPass().equals(aPass))
				return true;
		}
		return false;
	}
}