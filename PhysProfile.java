import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

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
 * PhysProfile - Patient Oriented Eczema Measurement Program (POEM) for Physician Profile
 * @author Lis Nadya binti Zaidi [Leader] - 1161102208
 * @author Nur Farisha Shahirah binti Abdul Rahman [Secretary] - 1171302013
 * @author Ishraf bin Ismay [Editor] - 1161102210
 * @author Nuraishah Sabrina binti Shahrir Zaid [Treasurer] - 1161102321
 **/
public class PhysProfile{
	//pd - Poem Database
	PoemDatabase pd = new PoemDatabase();
	
	//-------------------HARDCODE--------------------
	//patient - list of patients  
	User[] patient = {pd.get(0), pd.get(1), pd.get(2)};
	//patDate - list of appointment date 
	String[] patDate = {"4/10/2018", "4/10/2018", "5/10/2018"};
	//patTime - list of appointment time 
	String[] patTime = {"11:00", "13:00", "10:00"};
	//iPhysician - physician info
	User iPhysician = new User("Ewana","Ayesha", 20, "F", 1112488212, "SJMC", 1161102210, "1234");
	//-----------------------------------------------
	
	//iframe - program frame 
	static JFrame iFrame = new JFrame("POEM - Physician Profile");
	//-----------------------------------------------
	//Page color settings
	//-----------------------------------------------
	Color topNav = new Color(255, 255, 255); //Nav Background
	Color topNavBorder = new Color(206, 206, 206); //Nav Border
	Color topNavFont = new Color(130, 126, 126); //Nav Font 
	
	Color topTitle = new Color(122, 13, 13); //Title Background
	Color topTitleFont = new Color(252, 252, 252); //Title Font
	Color topTitleBorder = new Color(86, 7, 7); //Title Border
	String titleFont = "Calligraphia"; //Title Font Style
	int titleFontSize = 15;
	
	Color userInfoBg = new Color(252, 252, 252); //User info background
	Color pageBg = new Color(242, 243, 244); //Page Background
	Color pageFont =  new Color(0,0,0); //Page Font
	String pageFontStyle = "Calligraphia"; //Page font style
	int pageFontSize = 11;
	//-----------------------------------------------
	//iNavName - navigation button names
	String[] iNavName = {"Home", "Profile", "Pending", "Sign out"};
	//iNavButton - navigation buttons
	JButton[] iNavButton = new JButton[4];
	//iLabel - labels for the text fields
	JLabel[] iLabel=new JLabel[3];
	//iLabelName - labels for the text fields names
	String[] iLabelName = {"Physician Info", "Name: ", "Hospital: "};
	
	PhysProfile(){
		initialize();
	}
	
	public void initialize(){
		iFrame.setLayout(new BorderLayout());
		iFrame.setBounds(0,0,630,630);
		iFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		iFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		iFrame.setMinimumSize(new Dimension(630,630));
		
        JPanel lContainer = new JPanel(new GridLayout(2,1));
		lContainer.setBackground(pageBg);
		//--------------------------------
		//Navigation
		//--------------------------------
		JPanel lNav = new JPanel(new GridLayout(1,3));
		lNav.setPreferredSize(new Dimension(630,10));
		lNav.setBackground(topNav);
		lNav.setBorder(BorderFactory.createMatteBorder(0,0,1,0, topNavBorder));
		for(int i=0; i<4; i++){
			iNavButton[i]= new JButton(iNavName[i]);
			iNavButton[i].setContentAreaFilled(false);
			iNavButton[i].setForeground(topNavFont);
			iNavButton[i].setFont(new Font(pageFontStyle,Font.BOLD, pageFontSize));
			iNavButton[i].setBorderPainted(false);
			lNav.add(iNavButton[i]);
		}
		
		//--------------------------------
		//Page title
		//--------------------------------
		JPanel titlePanel = new JPanel();
		JLabel title = new JLabel("Patient Oriented Eczema Measurement (Physician)");
		title.setFont(new Font(titleFont,Font.BOLD, titleFontSize));
		title.setForeground(topTitleFont);
		title.setHorizontalAlignment(JLabel.CENTER);
		titlePanel.add(title);
		titlePanel.setBorder(BorderFactory.createMatteBorder(0,0,1,0, topTitleBorder));
		titlePanel.setBackground(topTitle);
		
		//--------------------------------
		//Top panel
		//--------------------------------
		lContainer.add(titlePanel);
		lContainer.add(lNav);
		
		//--------------------------------
		//Page panel
		//--------------------------------
		Box lCenter = new Box(BoxLayout.Y_AXIS);
		lCenter.setOpaque(false);
		lCenter.add(Box.createVerticalGlue());
		
		JPanel lPage = new JPanel(new GridLayout(3,1));
		lPage.setBorder(new MatteBorder(20, 20, 20, 20, pageBg));
		lPage.setBackground(pageBg);
		
		Dimension expectedDimension = new Dimension(600,800);
		lPage.setPreferredSize(expectedDimension);
		lPage.setMaximumSize(expectedDimension);
		lPage.setMinimumSize(expectedDimension);
		
		//--------------------------------
		//USER PANEL
		//--------------------------------
		JPanel lUserPanel = new JPanel(new GridLayout(1,2));
		lUserPanel.setBorder(new MatteBorder(5, 0, 0, 0, topTitle));
		lUserPanel.setBackground(userInfoBg);
		//--------------------------------
		//Icon Box
		//--------------------------------
		JButton lIcon = new JButton();
		lIcon.setBorder(new MatteBorder(20, 0, 20, 0, userInfoBg));
		lIcon.setContentAreaFilled(false);
		lIcon.setIcon(new ImageIcon("physician.png"));
		lIcon.setBorder(BorderFactory.createEmptyBorder());
		lUserPanel.add(lIcon);
		
		//--------------------------------
		//Information Box
		//--------------------------------
		
		JPanel lInfoPanel = new JPanel(new GridLayout(3,1));
		lInfoPanel.setBackground(userInfoBg);
		lInfoPanel.setBorder(new MatteBorder(20,0, 20, 0, userInfoBg));
		
		for(int i=0; i<3; i++){
			if(i==1)
				iLabelName[i]+=iPhysician.getName();
			else if(i==2)
				iLabelName[i]+=iPhysician.getAddress();
			
			iLabel[i] = new JLabel(iLabelName[i]);
			iLabel[i].setForeground(pageFont);
			iLabel[i].setFont(new Font(pageFontStyle,Font.BOLD, pageFontSize));
			if(i==0){
				iLabel[i].setForeground(topTitle);
				iLabel[i].setFont(new Font(pageFontStyle,Font.BOLD, 15));
			}
			lInfoPanel.add(iLabel[i]);
		}
		lUserPanel.add(lInfoPanel);
		lPage.add(lUserPanel);
		//--------------------------------
		//STATUS PANEL
		//--------------------------------
		JPanel lStatusPanel = new JPanel(new GridLayout(1,2));
		lStatusPanel.setBorder(new MatteBorder(20, 0, 0, 0, pageBg));
		//--------------------------------
		//Patient Name Box
		//--------------------------------
		JPanel lTracker = new JPanel(new GridLayout(patient.length+1,1));
		
		lTracker.setBackground(userInfoBg);
		lTracker.setBorder(new MatteBorder(5, 0, 0, 0, topTitle));
		
		JLabel lTrackerTitle = new JLabel("Patient", SwingConstants.CENTER);
		lTrackerTitle.setForeground(topTitle);
		lTracker.add(lTrackerTitle);
		
		
		for(int i=0; i<patient.length; i++){
			JLabel lTrackerList = new JLabel("["+i+"] "+patient[i].getName(), SwingConstants.LEFT);
			lTrackerList.setBorder(new MatteBorder(0, 15, 0, 15, topNav));
			lTracker.add(lTrackerList);
		}
		
		lStatusPanel.add(lTracker);
		
		//--------------------------------
		//Appointment Date AND Time Box
		//--------------------------------
		JPanel lAppDateBox = new JPanel(new GridLayout(1,2));
		lAppDateBox.setBackground(pageBg);
		lAppDateBox.setBorder(new MatteBorder(0, 10, 0, 0, pageBg));
		//--------------------------------
		//Appointment Date Box
		//--------------------------------
		JPanel lAppointmentBox = new JPanel(new GridLayout(1,1));
		lAppointmentBox.setBackground(pageBg);
		lAppointmentBox.setBorder(new MatteBorder(0, 0, 0, 10, pageBg));
		
		JPanel lAppointment = new JPanel(new GridLayout(patient.length+1,1));
		lAppointment.setBackground(userInfoBg);
		lAppointment.setBorder(new MatteBorder(5, 0, 0, 0, topTitle));
		
		JLabel lAppointmentTitle = new JLabel("Date", SwingConstants.CENTER);
		lAppointmentTitle.setForeground(topTitle);
		lAppointment.add(lAppointmentTitle);
		
		for(int i=0; i<patient.length; i++){
			JLabel lAppointmentList = new JLabel(patDate[i], SwingConstants.CENTER);
			lAppointmentList.setBorder(new MatteBorder(0, 15, 0, 15, topNav));
			lAppointment.add(lAppointmentList);
		}
		
		lAppointmentBox.add(lAppointment);
		lAppDateBox.add(lAppointmentBox);
		//--------------------------------
		//Appointment Time Box
		//--------------------------------
		JPanel lAppTime = new JPanel(new GridLayout(patient.length+1,1));
		
		lAppTime.setBackground(userInfoBg);
		lAppTime.setBorder(new MatteBorder(5, 0, 0, 0, topTitle));
		
		JLabel lAppTimeTitle = new JLabel("Time", SwingConstants.CENTER);
		lAppTimeTitle.setForeground(topTitle);
		lAppTime.add(lAppTimeTitle);
		
		for(int i=0; i<patient.length; i++){
			JLabel lTimeList = new JLabel(patTime[i], SwingConstants.CENTER);
			lTimeList.setBorder(new MatteBorder(0, 15, 0, 15, topNav));
			lAppTime.add(lTimeList);
		}
		
		lAppDateBox.add(lAppTime);
		lStatusPanel.add(lAppDateBox);
		
		lPage.add(lStatusPanel);
		
		lCenter.add(lPage);
		lCenter.add(Box.createVerticalGlue());
		iFrame.add(lContainer, BorderLayout.NORTH);
		iFrame.add(lCenter, BorderLayout.CENTER);
		iFrame.getContentPane().setBackground(pageBg);
		iFrame.pack();
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PhysProfile window = new PhysProfile();
					window.iFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
        });
	}
	
}