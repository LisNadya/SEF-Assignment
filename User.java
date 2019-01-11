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
 * User - User of Patient Oriented Measurement (POEM)
 * @author Lis Nadya binti Zaidi [Leader] - 1161102208
 * @author Nur Farisha Shahirah binti Abdul Rahman [Secretary] - 1171302013
 * @author Ishraf bin Ismay [Editor] - 1161102210
 * @author Nuraishah Sabrina binti Shahrir Zaid [Treasurer] - 1161102321
 **/
public class User{
	//iPic - profile picture
	private String iPic = "user.png";
	//iName - full name
	private String iName;
	//iFirstName - first name
	private String iFirstName;
	//iLastName - last name
	private String iLastName;
	//iAge - age
	private int iAge;
	//iGender - gender
	private String iGender;
	//iPhoneNo - phone number
	private int iPhoneNo;
	//iAddress - address 
	private String iAddress;
	//iUser - username
	private int iUser;
	//iPass - password
	private String iPass;
	//iResultList - result list
	private ArrayList <Score> iResultList = new ArrayList <Score>();
	
	
	public User(String aFirstName, String aLastName, int aAge, String aGender, int aPhoneNo, String aAddress, int aUser, String aPass){
		setName(aFirstName, aLastName);
		setFirstName(aFirstName);
		setLastName(aLastName);
		setAge(aAge);
		setGender(aGender);
		setPhoneNo(aPhoneNo);
		setAddress(aAddress);
		setUsername(aUser);
		setPassword(aPass);
	}
	
	public void setName(String aFirstName, String aLastName){
		iName = aFirstName +" "+ aLastName;
	}
	public void setFirstName(String aFirstName){
		iFirstName=aFirstName;
	}
	public void setLastName(String aLastName){
		iLastName=aLastName;
	}
	public void setAge(int aAge){
		iAge=aAge;
	}
	public void setGender(String aGender){
		iGender=aGender;
	}
	public void setPhoneNo(int aPhoneNo){
		iPhoneNo = aPhoneNo;
	}
	public void setAddress(String aAddress){
		iAddress=aAddress;
	}
	public void setUsername(int aUser){
		iUser=aUser;
	}
	public void setPassword(String aPass){
		iPass=aPass;
	}
	public void setPicture(String aPic){
		iPic = aPic;
	}
	public void setResult(int aWeek, int aScore, String aCondition){
		iResultList.add(new Score(aWeek, aScore, aCondition));
	}
	
	public ArrayList<Score> getResultList(){
		return iResultList;
	}
	public String getPicture(){
		return iPic;
	}
	public int getResultLength(){
		return iResultList.size();
	}
	public int getScore(int aWeek){
		int lScore = 0;
		for(Score s:iResultList){
			if(s.getWeek()==aWeek){
				lScore = s.getScore();
				break;
			}
		}
		return lScore;
	}
	public String getPrescription(int aWeek){
		String lPresc = "";
		for(Score s:iResultList){
			if(s.getWeek()==aWeek){
				lPresc = s.getPrescription();
				break;
			}
		}
		return lPresc;
	}
	public String getName(){
		return iName;
	}
	public String getFirstName(){
		return iFirstName;
	}
	public String getLastName(){
		return iLastName;
	}
	public int getAge(){
		return iAge;
	}
	public String getGender(){
		return iGender;
	}
	public int getPhoneNo(){
		return iPhoneNo;
	}
	public String getAddress(){
		return iAddress;
	}
	public int getUser(){
		return iUser;
	}
	public String getPass(){
		return iPass;
	}
	
	public String toString(){
		return "          " + iFirstName + "                " + iLastName + "            " + iAge + "        " + iGender + "            " + iPhoneNo + "            " + iAddress + "         " + iUser + "        " + iPass + "    ";
	}
	
	private class Score{
		//iWeek - week
		private int iWeek;
		//iScore - score
		private int iScore;
		//iPrescription - prescription
		private String iPrescription;
		
		public Score(int aWeek, int aScore, String aPrescription){
			setWeek(aWeek);
			setScore(aScore);
			setPrescription(aPrescription);
		}
		
		public int getWeek(){
			return iWeek;
		}
		
		public int getScore(){
			return iScore;
		}
		
		public String getPrescription(){
			return iPrescription;
		}
		
		public void setWeek(int aWeek){
			iWeek = aWeek;
		}
		
		public void setScore(int aScore){
			iScore=aScore;
		}
		
		public void setPrescription(String aPrescription){
			iPrescription=aPrescription;
		}
		
		public String toString(){
			return getWeek()+" - "+getScore() +" - "+ getPrescription();
		}
	}
	
}