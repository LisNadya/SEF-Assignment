import java.awt.event.*;
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
 * Poem - Patient Oriented Eczema Measurement Program (POEM) for Patient 
 * @author Lis Nadya binti Zaidi [Leader] - 1161102208
 * @author Nur Farisha Shahirah binti Abdul Rahman [Secretary] - 1171302013
 * @author Ishraf bin Ismay [Editor] - 1161102210
 * @author Nuraishah Sabrina binti Shahrir Zaid [Treasurer] - 1161102321
 **/
public class Poem{
	//pd - Poem Database
	PoemDatabase pd = new PoemDatabase();
	//-------------------HARDCODE--------------------
	User patient = pd.get(0);
	//-----------------------------------------------
	//iFrame - frame 
	static JFrame iFrame = new JFrame();
	//--------------------PAGES---------------------
	MainMenu pageH = new MainMenu();
	UserProfile pageU = new UserProfile();
	EditProfile pageE = new EditProfile();
	Result pageR = new Result();
	Guide pageG = new Guide();
	SignIn pageSI = new SignIn();
	SignUp pageSU = new SignUp();
	Test pageT  = new Test();
	//-----------------------------------------------
	Poem(){
		iFrame = pageSI.getFrame();
	}
	
	class NavigationListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton lNav = (JButton)e.getSource();
			String lNavName = lNav.getName();
			boolean isCont = false;
			switch (lNavName) {
			case "Login":
				isCont = pageSI.authValid();
				if(isCont==true){
					iFrame.dispose();
					iFrame = pageH.getFrame();
					iFrame.setVisible(true);
				}
				break;
				
			case "Sign Up":
				iFrame.dispose();
				iFrame = pageSU.getFrame();
				iFrame.setVisible(true);
				break;
				
			case "Home":
				iFrame.dispose();
				iFrame = pageH.getFrame();
				iFrame.setVisible(true);
				break;
				
			case "Cancel":
			case "Profile":
				iFrame.dispose();
				iFrame = pageU.getFrame();
				iFrame.setVisible(true);
				break;
				
			case "Edit":
				iFrame.dispose();
				iFrame = pageE.getFrame();
				iFrame.setVisible(true);
				break;
				
			case "Test":
				iFrame.dispose();
				iFrame = pageT.getFrame();
				iFrame.setVisible(true);
				break;
				
			case "Result":
				iFrame.dispose();
				iFrame = new Result().getFrame();
				iFrame.setVisible(true);
				break;
				
			case "Guide":
				iFrame.dispose();
				iFrame = pageG.getFrame();
				iFrame.setVisible(true);
				break;
				
			case "Go Back":
			case "Sign Out":
				iFrame.dispose();
				iFrame = pageSI.getFrame();
				iFrame.setVisible(true);
				break;
				
			case "ConfirmE":
				isCont = pageE.newUserInfo();
				if(isCont==true){
					iFrame.dispose();
					iFrame = new UserProfile().getFrame();
					iFrame.setVisible(true);
				}
				break;
				
			case "ConfirmSU":
				isCont = pageSU.newUserInfo();
				if(isCont==true){
					iFrame.dispose();
					iFrame = pageSI.getFrame();
					iFrame.setVisible(true);
				}
				break;
				
			}
		}
	}
	
	/**
	 * SignIn - Sign in page
	 * @author Lis Nadya binti Zaidi [Leader] - 1161102208
	 **/
	private class SignIn{
		//iframe - program frame 
		JFrame iFrame = new JFrame("POEM - Sign In");
		//--------------------------------
		//Page color settings
		//--------------------------------
		Color pageBg = new Color(242, 243, 244);  //Page Background
		Color pageFont =  new Color(0,0,0); //Page Font
		String pageFontStyle = "Calligraphia"; //Page font style
		int pageFontSize = 11;
		//--------------------------------
		//iLabel - labels for the text fields
		JLabel[] iLabel=new JLabel[2];
		//iLabelName - labels for the text fields names
		String[] iLabelName = {"User ID", "Password"};
		//iLabelField - user input
		JTextField iLabelField = new JTextField();
		//iPassField - password input 
		JPasswordField iPassField = new JPasswordField();
		//iError - error message
		String iError = "Please fill in your ID and password";
		String iErrorID = "Incorrect ID";
		String iErrorPass = "Incorrect password";

		SignIn(){
			initialize();
		}
		
		public JFrame getFrame(){
			return iFrame;
		}
		
		public void clearField(){
			iLabelField.setText("");
			iPassField.setText("");
		}
		
		public boolean authValid(){
			int userID;
			String userPass;
			
			if(iLabelField.getText().isEmpty()||iPassField.getPassword().length==0)
				JOptionPane.showMessageDialog(iFrame, iError);
			else{
				userID = Integer.valueOf(iLabelField.getText());
				userPass = new String(iPassField.getPassword());
				
				if(pd.userValid(userID)==true){
					if(pd.passValid(userPass)==true){
						clearField();
						return true;
					}
					else
						JOptionPane.showMessageDialog(iFrame, iErrorPass);
				}
				else
					JOptionPane.showMessageDialog(iFrame, iErrorID);
			}
				
			return false;
		}
		
		
		public void initialize(){
			iFrame.setLayout(new BorderLayout());
			iFrame.setBounds(0,0,630,630);
			iFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
			iFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			iFrame.setMinimumSize(new Dimension(630,630));
			JPanel lContainer = new JPanel();
			
			//--------------------------------
			//Page panel
			//--------------------------------
			Box lCenter = new Box(BoxLayout.Y_AXIS);
			lCenter.add(Box.createVerticalGlue());
			
			JPanel lPage = new JPanel(new GridLayout(6,1));
			lPage.setBackground(pageBg);
			
			Dimension expectedDimension = new Dimension(200,500);
			lPage.setPreferredSize(expectedDimension);
			lPage.setMaximumSize(expectedDimension);
			lPage.setMinimumSize(expectedDimension);
			
			JButton lIcon = new JButton();
			
			lIcon.setContentAreaFilled(false);
			lIcon.setIcon(new ImageIcon("poem.png"));
			lIcon.setBorder(BorderFactory.createEmptyBorder());
			lPage.add(lIcon);
			
			JPanel lInfo = new JPanel(new GridLayout(4,1));
			lInfo.setBackground(pageBg);
			lInfo.setMaximumSize(new Dimension(150,150));
			
			for(int i=0; i<2; i++){
				iLabel[i] = new JLabel(iLabelName[i], SwingConstants.LEFT);
				iLabel[i].setForeground(pageFont);
				lInfo.add(iLabel[i]);
				if(i==0){
					lInfo.add(iLabelField);
				}
				else{
					lInfo.add(iPassField);
				}
			}
			lPage.add(lInfo);
			
			JPanel lNavBorder = new JPanel(new GridLayout(1,2));
			lNavBorder.setOpaque(false);
			lNavBorder.setMaximumSize(new Dimension(150,25));
			
			JPanel lNav1 = new JPanel();
			lNav1.setBorder(new EmptyBorder(0, 5, 0, 0));
			lNav1.setOpaque(false);
			
			JButton lNavSI = new JButton("Login");
			lNavSI.setName("Login");
			lNavSI.setBorder(new EmptyBorder(10, 30, 10, 30));
			lNavSI.setBackground(Color.WHITE);
			lNavSI.addActionListener(new NavigationListener());
			
			JPanel lNav2 = new JPanel();
			lNav2.setBorder(new EmptyBorder(0, 0, 0, 5));
			lNav2.setOpaque(false);
			
			JButton lNavSU = new JButton("Sign Up");
			lNavSU.setName("Sign Up");
			lNavSU.setBorder(new EmptyBorder(10, 25, 10, 25));
			lNavSU.setBackground(Color.WHITE);
			lNavSU.addActionListener(new NavigationListener());
			
			lNav1.add(lNavSI);
			lNavBorder.add(lNav1);
			lNav2.add(lNavSU);
			lNavBorder.add(lNav2);
			lPage.add(lNavBorder);
			
			lCenter.add(lPage);
			lCenter.add(Box.createVerticalGlue());
			
			iFrame.getContentPane().add(lContainer, BorderLayout.NORTH);
			iFrame.getContentPane().add(lCenter, BorderLayout.CENTER);
			iFrame.getContentPane().setBackground(pageBg);
			iFrame.pack();
		}
		
	}
	
	/**
	 * SignUp - Sign up page
	 * @author Lis Nadya binti Zaidi [Leader] - 1161102208
	 **/
	private class SignUp{
		//iframe - program frame 
		JFrame iFrame = new JFrame("POEM - Sign Up");
		//iNavName - navigation button names
		String[] iNavName = {"Go Back", "Confirm"};
		//iNavButton - navigation buttons
		JButton[] iNavButton = new JButton[2];
		//iLabel - labels for the text fields
		JLabel[] iLabel=new JLabel[8];
		//iLabelName - labels for the text fields names
		String[] iLabelName = {"First Name: ", "Last Name: ", "Age: ", "Gender: ", "Phone Number: ", "Address: ", "Username: ", "Password: "};
		//iField - user inputs 
		JTextField[] iField = new JTextField[8];
		//iError - error message
		String iError = "Please fill in all of the details to create a new profile";
		//--------------------------------
		//Page color settings
		//--------------------------------
		Color topNav = new Color(255, 255, 255); //Nav Background
		Color topNavBorder = new Color(206, 206, 206); //Nav Border
		Color topNavFont = new Color(0,0,0); //Nav Font 
		
		Color topTitle = new Color(122, 13, 13); //Title Background
		Color topTitleFont = new Color(252, 252, 252); //Title Font
		Color topTitleBorder = new Color(86, 7, 7); //Title Border
		String titleFont = "Calligraphia"; //Title Font Style
		int titleFontSize = 15;
		
		Color pageBg = new Color(242, 243, 244); //Page Background
		Color pageFont =  new Color(0,0,0); //Page Font
		String pageFontStyle = "Calligraphia"; //Page font style
		int pageFontSize = 11;
		//--------------------------------
		SignUp(){
			initialize();
		}
		
		public JFrame getFrame(){
			return iFrame;
		}
		
		public void initialize(){
			iFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
			iFrame.setLayout(new BorderLayout());
			iFrame.setBounds(0,0,630,630);
			iFrame.setMinimumSize(new Dimension(630,630));
			JPanel lContainer = new JPanel(new GridLayout(2,1));
			//--------------------------------
			//Navigation
			//--------------------------------
			JPanel lNav = new JPanel(new GridLayout(1,3));
			lNav.setPreferredSize(new Dimension(630,10));
			lNav.setBackground(topNav);
			lNav.setBorder(BorderFactory.createMatteBorder(0,0,1,0, topNavBorder));
			for(int i=0; i<2; i++){
				iNavButton[i]= new JButton(iNavName[i]);
				if(i==1)
					iNavButton[i].setName("ConfirmSU");
				else
					iNavButton[i].setName(iNavName[i]);
				iNavButton[i].addActionListener(new NavigationListener());
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
			JLabel title = new JLabel("Patient Oriented Eczema Measurement");
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
			
			JPanel lPage = new JPanel(new GridLayout(11,2));
			lPage.setBackground(pageBg);
			
			Dimension expectedDimension = new Dimension(500,500);
			lPage.setPreferredSize(expectedDimension);
			lPage.setMaximumSize(expectedDimension);
			lPage.setMinimumSize(expectedDimension);
			
			for(int j=0; j<5; j++){
				for(int i=0; i<8; i++){
					boolean isCont=false;
					if(j==0){
						if(i==0||i==1)
							isCont=true;
					}
					else if(j==1){
						if(i==2||i==3)
							isCont=true;
					}
					else if(j==2){
						if(i==4||i==5)
							isCont=true;
					}
					else if(j==3){
						if(i==6||i==7)
							isCont=true;
					}
					else if(j==4){
						if(i==8||i==9)
							isCont=true;
					}
					
					if(isCont==true){
						iLabel[i] = new JLabel(iLabelName[i], SwingConstants.LEFT);
						iLabel[i].setForeground(pageFont);
						iLabel[i].setFont(new Font(pageFontStyle,Font.BOLD, pageFontSize));
						lPage.add(iLabel[i]);
					}
				}
				for(int i=0; i<8; i++){
					boolean isCont=false;
					if(j==0){
						if(i==0||i==1)
							isCont=true;
					}
					else if(j==1){
						if(i==2||i==3)
							isCont=true;
					}
					else if(j==2){
						if(i==4||i==5)
							isCont=true;
					}
					else if(j==3){
						if(i==6||i==7)
							isCont=true;
					}
					if(isCont==true){
						iField[i] = new JTextField(20);
						JPanel wrapper = new JPanel(new FlowLayout(0, 0, FlowLayout.LEADING));
						wrapper.setBackground(pageBg);
						wrapper.add( iField[i] );
						lPage.add(wrapper);
					}
				}
			}
			
			lCenter.add(lPage);
			lCenter.add(Box.createVerticalGlue());
			iFrame.add(lContainer, BorderLayout.NORTH);
			iFrame.add(lCenter, BorderLayout.CENTER);
			iFrame.getContentPane().setBackground(pageBg);
			iFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			iFrame.pack();
			
		}
		
		public void clearField(){
			for(int i=0;i<8;i++)
					iField[i].setText("");
		}
		
		public boolean newUserInfo(){
			boolean isValid=true;
			String[] s = new String[8];
			
			for(int i=0; i<8; i++){
				if(iField[i].getText().isEmpty()){
					isValid = false;
					break;
				}
				s[i] = iField[i].getText();
			}
			
			if(isValid==true){
				User lPatient = new User(s[0],s[1],Integer.valueOf(s[2]),s[3],Integer.valueOf(s[4]),s[5],Integer.valueOf(s[6]),s[7]);
				pd.add(lPatient);
				pd.save();
				clearField();
			}
			else{
				JOptionPane.showMessageDialog(iFrame, iError);
			}
			return isValid;
		}
	}

	/**
	 * MainMenu - Main menu page
	 * @author Ishraf bin Ismay [Editor] - 1161102210
	 **/
	private class MainMenu{
		//iframe - program frame 
		JFrame iFrame = new JFrame("POEM - Home");
		String iFrameName = "Home";
		//--------------------------------
		//Page color settings
		//--------------------------------
		Color topNav = new Color(255, 255, 255); //Nav Background
		Color topNavBorder = new Color(206, 206, 206); //Nav Border
		Color topNavFont = new Color(0,0,0); //Nav Font 
		
		Color topTitle = new Color(122, 13, 13); //Title Background
		Color topTitleFont = new Color(252, 252, 252); //Title Font
		Color topTitleBorder = new Color(86, 7, 7); //Title Border
		String titleFont = "Calligraphia"; //Title Font Style
		int titleFontSize = 15;
		
		Color userInfoBg = new Color(252, 252, 252); 
		Color pageBg = new Color(242, 243, 244); //Page Background
		Color pageFont =  new Color(0,0,0); //Page Font
		String pageFontStyle = "Calligraphia"; //Page font style
		int pageFontSize = 11;
		//--------------------------------
		
		//iNavName - navigation button names
		String[] iNavName = {"Profile", "Test", "Guide", "Sign Out"};
		//iNavButton - navigation buttons
		JButton[] iNav = new JButton[4];
		
		MainMenu(){
			initialize();
		}
		
		public void initialize(){
			iFrame.setLayout(new BorderLayout());
			iFrame.setBounds(0,0,650,650);
			iFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
			iFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			iFrame.setMinimumSize(new Dimension(650,650));
			JPanel lContainer = new JPanel(new GridLayout(2,1));
			lContainer.setBackground(pageBg);
			
			//--------------------------------
			//Page title
			//--------------------------------
			JPanel titlePanel = new JPanel();
			JLabel title = new JLabel("Patient Oriented Eczema Measurement");
			title.setFont(new Font(titleFont,Font.BOLD, titleFontSize));
			title.setForeground(topTitleFont);
			title.setHorizontalAlignment(JLabel.CENTER);
			titlePanel.add(title);
			titlePanel.setBorder(BorderFactory.createMatteBorder(0,0,1,0, topTitleBorder));
			titlePanel.setBackground(topTitle);
			
			JPanel lWelc = new JPanel();
			JLabel lWelcLabel = new JLabel("Welcome "+patient.getFirstName()+" !");
			lWelc.setBackground(topNav);
			lWelc.setBorder(BorderFactory.createMatteBorder(0,0,1,0, topNavBorder));
			lWelc.add(lWelcLabel);
			//--------------------------------
			//Top panel
			//--------------------------------
			lContainer.add(titlePanel);
			lContainer.add(lWelc);
			
			//--------------------------------
			//Page panel
			//--------------------------------
			Box lCenter = new Box(BoxLayout.Y_AXIS);
			lCenter.setOpaque(false);
			lCenter.add(Box.createVerticalGlue());
			
			JPanel lPage = new JPanel(new GridLayout(2,1));
			lPage.setBorder(new MatteBorder(10, 0, 0, 0, topTitle));
			lPage.setBackground(topNav);
			
			Dimension expectedDimension = new Dimension(400,400);
			lPage.setPreferredSize(expectedDimension);
			lPage.setMaximumSize(expectedDimension);
			lPage.setMinimumSize(expectedDimension);
			
			
			//--------------------------------
			//Icon Box
			//--------------------------------
			JButton lIcon = new JButton();
			lIcon.setEnabled(false);
			lIcon.setBorder(new MatteBorder(20, 20, 20, 20, topNav));
			lIcon.setContentAreaFilled(false);
			lIcon.setIcon(new ImageIcon("user.png"));
			lIcon.setDisabledIcon(new ImageIcon("user.png"));
			
			lPage.add(lIcon);
			
			//--------------------------------
			//Navigation Box
			//--------------------------------
			JPanel lNavPanel = new JPanel(new GridLayout(2,2));
			Color lNavPanelBg = topNav;
			lNavPanel.setBorder(new MatteBorder(20, 20, 20, 20, lNavPanelBg));
			for(int i=0; i<4; i++){
				iNav[i] = new JButton(iNavName[i]);
				iNav[i].setName(iNavName[i]);
				iNav[i].setBackground(topTitle);
				if(i==0||i==2)
					iNav[i].setBorder(new MatteBorder(0, 0, 10, 10, lNavPanelBg));
				if(i==1)
					iNav[i].setBorder(new MatteBorder(0, 10, 10, 0, lNavPanelBg));
				if(i==2)
					iNav[i].setBorder(new MatteBorder(10, 0, 0, 10, lNavPanelBg));
				if(i==3)
					iNav[i].setBorder(new MatteBorder(10, 10, 0, 0, lNavPanelBg));
				iNav[i].setForeground(topTitleFont);
				iNav[i].addActionListener(new NavigationListener());
				lNavPanel.add(iNav[i]);
			}
			lPage.add(lNavPanel);
			
			lCenter.add(lPage);
			lCenter.add(Box.createVerticalGlue());
			iFrame.add(lContainer, BorderLayout.NORTH);
			iFrame.add(lCenter, BorderLayout.CENTER);
			iFrame.getContentPane().setBackground(pageBg);
			iFrame.pack();
		}
		
		public JFrame getFrame(){
			return iFrame;
		}
		
		
	}

	/**
	 * UserProfile - User profile page
	 * @author Lis Nadya binti Zaidi [Leader] - 1161102208
	 **/
	private class UserProfile{
		//iframe - program frame 
		JFrame iFrame = new JFrame("POEM - User Profile");
		String iFrameName = "Profile";
		//--------------------------------
		//Page color settings
		//--------------------------------
		Color topNav = new Color(255, 255, 255); //Nav Background
		Color topNavBorder = new Color(206, 206, 206); //Nav Border
		Color topNavFont = new Color(0,0,0); //Nav Font 
		
		Color topTitle = new Color(122, 13, 13); //Title Background
		Color topTitleFont = new Color(252, 252, 252); //Title Font
		Color topTitleBorder = new Color(86, 7, 7); //Title Border
		String titleFont = "Calligraphia"; //Title Font Style
		int titleFontSize = 15;
		
		Color userInfoBg = new Color(252, 252, 252); 
		Color pageBg = new Color(242, 243, 244); //Page Background
		Color pageFont =  new Color(0,0,0); //Page Font
		String pageFontStyle = "Calligraphia"; //Page font style
		int pageFontSize = 11;
		//--------------------------------
		//iNavName - navigation button names
		String[] iNavName = {"Home", "Profile", "Test", "Result", "Guide", "Sign Out"};
		//iNavButton - navigation buttons
		JButton[] iNavButton = new JButton[6];
		//iLabel - labels for the text fields
		JLabel[] iLabel=new JLabel[10];
		//iLabelName - labels for the text fields names
		String[] iLabelName = {"First Name: ", "Last Name: ", "Age: ", "Gender: ", "Contact: ", "Address: ", "Username: ", "Password: "};
		
		UserProfile(){
			initialize();
		}
		
		public void initialize(){
			iFrame.setLayout(new BorderLayout());
			iFrame.setBounds(0,0,650,650);
			iFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			iFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
			iFrame.setMinimumSize(new Dimension(650,650));
			
			JPanel lContainer = new JPanel(new GridLayout(2,1));
			lContainer.setBackground(pageBg);
			//--------------------------------
			//Navigation
			//--------------------------------
			JPanel lNav = new JPanel(new GridLayout(1,3));
			lNav.setPreferredSize(new Dimension(630,10));
			lNav.setBackground(topNav);
			lNav.setBorder(BorderFactory.createMatteBorder(0,0,1,0, topNavBorder));
			for(int i=0; i<6; i++){
				iNavButton[i]= new JButton(iNavName[i]);
				iNavButton[i].setName(iNavName[i]);
				iNavButton[i].setContentAreaFilled(false);
				iNavButton[i].setForeground(topNavFont);
				iNavButton[i].setFont(new Font(pageFontStyle,Font.BOLD, pageFontSize));
				iNavButton[i].setBorderPainted(false);
				if(iNavName[i].equals(iFrameName))
					iNavButton[i].setEnabled(false);
				iNavButton[i].addActionListener(new NavigationListener());
				lNav.add(iNavButton[i]);
			}
			
			//--------------------------------
			//Page title
			//--------------------------------
			JPanel titlePanel = new JPanel();
			JLabel title = new JLabel("Patient Oriented Eczema Measurement");
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
			
			Dimension expectedDimension = new Dimension(600,700);
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
			lIcon.setIcon(new ImageIcon(patient.getPicture()));
			lIcon.setBorder(BorderFactory.createEmptyBorder());
			lUserPanel.add(lIcon);
			
			//--------------------------------
			//Information Box
			//--------------------------------
			JPanel lInfoPanel = new JPanel(new GridLayout(5,2));
			lInfoPanel.setBackground(userInfoBg);
			lInfoPanel.setBorder(new MatteBorder(20,0, 20, 0, userInfoBg));
			for(int i=0; i<8; i++){
				if(i==0)
					iLabelName[i]+=patient.getFirstName();
				else if(i==1)
					iLabelName[i]+=patient.getLastName();
				else if(i==2)
					iLabelName[i]+= Integer.toString(patient.getAge());
				else if(i==3)
					iLabelName[i]+=patient.getGender();
				else if(i==4)
					iLabelName[i]+= Integer.toString(patient.getPhoneNo());
				else if(i==5)
					iLabelName[i]+=patient.getAddress();
				else if(i==6)
					iLabelName[i]+= Integer.toString(patient.getUser());
				else if(i==7)
					iLabelName[i]+=patient.getPass();
				
				iLabel[i] = new JLabel(iLabelName[i]);
				lInfoPanel.add(iLabel[i]);
			}
			
			JButton lEdit = new JButton("Edit");
			lEdit.setName("Edit");
			lEdit.addActionListener(new NavigationListener());
			lEdit.setBackground(topTitle);
			lEdit.setForeground(topTitleFont);
			lInfoPanel.add(lEdit,2,8);
			
			lUserPanel.add(lInfoPanel);
			lPage.add(lUserPanel);
			//--------------------------------
			//STATUS PANEL
			//--------------------------------
			JPanel lStatusPanel = new JPanel(new GridLayout(1,2));
			
			lStatusPanel.setBorder(new MatteBorder(20, 0, 0, 0, pageBg));
			//--------------------------------
			//Tracker Box
			//--------------------------------
			JPanel lTracker = new JPanel(new GridLayout(5,1));
			
			lTracker.setBackground(userInfoBg);
			lTracker.setBorder(new MatteBorder(5, 0, 0, 0, topTitle));
			
			JLabel lTrackerTitle = new JLabel("Order Status", SwingConstants.CENTER);
			lTrackerTitle.setForeground(pageFont);
			lTracker.add(lTrackerTitle);
			
			for(int i=0; i<4; i++){
				JLabel lTrackerList = new JLabel();
				if(i==0)
					lTrackerList = new JLabel("You have no orders", SwingConstants.CENTER);
				lTrackerList.setForeground(pageFont);
				lTracker.add(lTrackerList);
			}
			lStatusPanel.add(lTracker);
			//--------------------------------
			//Appointment Box
			//--------------------------------
			JPanel lAppointmentBox = new JPanel(new GridLayout(1,1));
			lAppointmentBox.setBackground(pageBg);
			lAppointmentBox.setBorder(new MatteBorder(0, 20, 0, 0, pageBg));
			
			JPanel lAppointment = new JPanel(new GridLayout(5,1));
			lAppointment.setBackground(userInfoBg);
			lAppointment.setBorder(new MatteBorder(5, 0, 0, 0, topTitle));
			
			JLabel lAppointmentTitle = new JLabel("Appointment Status", SwingConstants.CENTER);
			lAppointmentTitle.setForeground(pageFont);
			
			lAppointment.add(lAppointmentTitle);
			
			for(int i=0; i<4; i++){
				JLabel lAppointmentList = new JLabel();
				if(i==0)
					lAppointmentList = new JLabel("You have no future appointments", SwingConstants.CENTER);
				lAppointmentList.setForeground(pageFont);
				lAppointment.add(lAppointmentList);
			}
			
			lAppointmentBox.add(lAppointment);
			lStatusPanel.add(lAppointmentBox);
			
			lPage.add(lStatusPanel);
			
			lCenter.add(lPage);
			lCenter.add(Box.createVerticalGlue());
			iFrame.add(lContainer, BorderLayout.NORTH);
			iFrame.add(lCenter, BorderLayout.CENTER);
			iFrame.getContentPane().setBackground(pageBg);
			iFrame.pack();
		}
		
		public JFrame getFrame(){
			return iFrame;
		}
	}

	/**
	 * EditProfile - Edit user profile page
	 * @author Lis Nadya binti Zaidi [Leader] - 1161102208
	 **/
	private class EditProfile{
		//iframe - program frame 
		JFrame iFrame = new JFrame("POEM - User Profile");
		String iFrameName = "Profile";
		//--------------------------------
		//Page color settings
		//--------------------------------
		Color topNav = new Color(255, 255, 255); //Nav Background
		Color topNavBorder = new Color(206, 206, 206); //Nav Border
		Color topNavFont = new Color(0,0,0); //Nav Font 
		
		Color topTitle = new Color(122, 13, 13); //Title Background
		Color topTitleFont = new Color(252, 252, 252); //Title Font
		Color topTitleBorder = new Color(86, 7, 7); //Title Border
		String titleFont = "Calligraphia"; //Title Font Style
		int titleFontSize = 15;
		
		Color userInfoBg = new Color(252, 252, 252); 
		Color pageBg = new Color(242, 243, 244); //Page Background
		Color pageFont =  new Color(0,0,0); //Page Font
		String pageFontStyle = "Calligraphia"; //Page font style
		int pageFontSize = 11;
		//--------------------------------
		//iNavName - navigation button names
		String[] iNavName = {"Home", "Profile", "Test", "Result", "Guide", "Sign Out"};
		//iNavButton - navigation buttons
		JButton[] iNavButton = new JButton[6];
		JLabel[] insLabel = new JLabel[8];
		String[] insLabelN = {"First Name: ", "Last Name: ", "Age: ", "Gender: ", "Phone Number: ", "Address: ", "Username: ", "Password: "};
		JTextField[] insField = new JTextField[8];
		JTextField picField = new JTextField();
		JButton[] iPageButton = new JButton[2];
		JButton lIcon = new JButton();
		String[] iPageButtonN = {"Confirm","Cancel"};
		//iError - error message
		String iError = "Please don't leave any info empty.";
		
		EditProfile(){
			initialize();
		}
		
		public void initialize(){
			iFrame.setLayout(new BorderLayout());
			iFrame.setBounds(0,0,650,650);
			iFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			iFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
			iFrame.setMinimumSize(new Dimension(650,650));
			
			JPanel lContainer = new JPanel(new GridLayout(2,1));
			lContainer.setBackground(pageBg);
			//--------------------------------
			//Navigation
			//--------------------------------
			JPanel lNav = new JPanel(new GridLayout(1,3));
			lNav.setPreferredSize(new Dimension(630,10));
			lNav.setBackground(topNav);
			lNav.setBorder(BorderFactory.createMatteBorder(0,0,1,0, topNavBorder));
			for(int i=0; i<6; i++){
				iNavButton[i]= new JButton(iNavName[i]);
				iNavButton[i].setName(iNavName[i]);
				iNavButton[i].setContentAreaFilled(false);
				iNavButton[i].setForeground(topNavFont);
				iNavButton[i].setFont(new Font(pageFontStyle,Font.BOLD, pageFontSize));
				iNavButton[i].setBorderPainted(false);
				if(iNavName[i].equals(iFrameName))
					iNavButton[i].setEnabled(false);
				iNavButton[i].addActionListener(new NavigationListener());
				lNav.add(iNavButton[i]);
			}
			
			//--------------------------------
			//Page title
			//--------------------------------
			JPanel titlePanel = new JPanel();
			JLabel title = new JLabel("Patient Oriented Eczema Measurement");
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
			
			JPanel lWhole = new JPanel(new GridLayout(1,2));
			lWhole.setBorder(new MatteBorder(10,0,0,0, topTitle));
			lWhole.setBackground(topNav);
			
			Dimension expectedDimension = new Dimension(600,500);
			lWhole.setPreferredSize(expectedDimension);
			lWhole.setMaximumSize(expectedDimension);
			lWhole.setMinimumSize(expectedDimension);
			
			//--------------------------------
			//Icon Box
			//--------------------------------
			JPanel lEditPic = new JPanel(new GridLayout(2,1));
			lEditPic.setBackground(topNav);
			lEditPic.setBorder(new MatteBorder(10,10,10,10, topNav));
			
			lIcon.setBorder(new MatteBorder(20, 0, 20, 0, topNav));
			lIcon.setContentAreaFilled(false);
			lIcon.setIcon(new ImageIcon(patient.getPicture()));
			lIcon.setBorder(BorderFactory.createEmptyBorder());
			lEditPic.add(lIcon);
			
			JPanel lPicPanel = new JPanel(new GridLayout(4,1));
			lPicPanel.setBorder(new MatteBorder(5, 5, 5, 5, topNav));
			lPicPanel.setBackground(topNav);
			
			JLabel lPicN = new JLabel("Picture: ");
			lPicN.setForeground(pageFont);
			lPicPanel.add(lPicN);
			picField = new JTextField(patient.getPicture());
			lPicPanel.add(picField);
			
			for(int i=0; i<2; i++){
				JPanel butPan = new JPanel(new GridLayout(1,1));
				butPan.setBackground(topNav);
				butPan.setBorder(new MatteBorder(10, 0, 5, 0, topNav));
				iPageButton[i]=new JButton(iPageButtonN[i]);
				if(i==1)
					iPageButton[i].setName(iPageButtonN[i]);
				else
					iPageButton[i].setName("ConfirmE");
				iPageButton[i].addActionListener(new NavigationListener());
				iPageButton[i].setBackground(topTitle);
				iPageButton[i].setForeground(topTitleFont);
				butPan.add(iPageButton[i]);
				lPicPanel.add(butPan);
			}
			
			lEditPic.add(lPicPanel);
			
			lWhole.add(lEditPic);
			//--------------------------------
			//Information Box
			//--------------------------------
			JPanel lPage = new JPanel(new GridLayout(4,2));
			lPage.setBackground(topNav);
			lPage.setBorder(new MatteBorder(10,10,10,10, topNav));
			for(int j=0; j<8; j++){
				JPanel lInsPanel = new JPanel(new GridLayout(2,1));
				lInsPanel.setBorder(new MatteBorder(5, 5, 5, 5, topNav));
				lInsPanel.setBackground(topNav);
				for(int i=0; i<8; i++){
					boolean addTrue=false;
					if(j==i)
						addTrue=true;
					if(addTrue){
						insLabel[i]=new JLabel(insLabelN[i], SwingConstants.LEFT);
						insLabel[i].setForeground(pageFont);
						insLabel[i].setFont(new Font(pageFontStyle,Font.BOLD, pageFontSize));
						lInsPanel.add(insLabel[i]);
						if(i==0)
							insField[i]=new JTextField(patient.getFirstName());
						else if(i==1)
							insField[i]=new JTextField(patient.getLastName());
						else if(i==2)
							insField[i]=new JTextField(String.valueOf(patient.getAge()));
						else if(i==3)
							insField[i]=new JTextField(patient.getGender());
						else if(i==4)
							insField[i]=new JTextField(String.valueOf(patient.getPhoneNo()));
						else if(i==5)
							insField[i]=new JTextField(patient.getAddress());
						else if(i==6)
							insField[i]=new JTextField(String.valueOf(patient.getUser()));
						else if(i==7)
							insField[i]=new JTextField(patient.getPass());
						lInsPanel.add(insField[i]);
					}
				}
				lPage.add(lInsPanel);
			}
			
			lWhole.add(lPage);
			
			lCenter.add(lWhole);
			lCenter.add(Box.createVerticalGlue());
			iFrame.add(lContainer, BorderLayout.NORTH);
			iFrame.add(lCenter, BorderLayout.CENTER);
			iFrame.getContentPane().setBackground(pageBg);
			iFrame.pack();
		}
		
		public JFrame getFrame(){
			return iFrame;
		}
		
		
		public boolean newUserInfo(){
			boolean isValid=true;
			String[] s = new String[8];
			
			for(int i=0; i<7; i++){
				if(insField[i].getText().isEmpty()){
					isValid = false;
					break;
				}
				s[i] = insField[i].getText();
			}
			
			if(picField.getText().isEmpty())
				isValid = false;
			
			if(isValid==true){
				patient.setPicture(picField.getText());
				lIcon.setIcon(new ImageIcon(patient.getPicture()));
				for(int i=0; i<8; i++){
					if(i==0)
						patient.setFirstName(s[i]);
					else if(i==1)
						patient.setLastName(s[i]);
					else if(i==2)
						patient.setAge(Integer.parseInt(s[i]));
					else if(i==3)
						patient.setGender(s[i]);
					else if(i==4)
						patient.setPhoneNo(Integer.parseInt(s[i]));
					else if(i==5)
						patient.setAddress(s[i]);
					else if(i==6)
						patient.setUsername(Integer.parseInt(s[i]));
					else if(i==7)
						patient.setPassword(s[i]);
				}
			}
			else{
				JOptionPane.showMessageDialog(iFrame, iError);
			}
			return isValid;
		}
	}

	/**
	 * Test - Questionnaire page
	 * @author Nur Farisha Shahirah binti Abdul Rahman [Secretary] - 1171302013
	 **/
	private class Test{
		//iframe - program frame 
		JFrame iFrame = new JFrame("POEM - Test");
		String iFrameName = "Test";
		//--------------------------------
		//Page color settings
		//--------------------------------
		Color TopNav = new Color(255, 255, 255); //Nav Background
		Color TopNavBorder = new Color(206, 206, 206); //Nav Border
		Color TopNavFont = new Color(0,0,0); //Nav Font 
		
		Color TopTitle = new Color(122, 13, 13); //Title Background
		Color TopTitleFont = new Color(252, 252, 252); //Title Font
		Color TopTitleBorder = new Color(86, 7, 7); //Title Border
		String TitleFont = "Calligraphia"; //Title Font Style
		int TitleFontSize = 15;
		
		Color PageBg = new Color(242, 243, 244); //Page Background
		Color PageFont =  new Color(0,0,0); //Page Font
		String PageFontStyle = "Calligraphia"; //Page font style
		int PageFontSize = 11;
		//--------------------------------
		
		//iNavName - navigation button names
		String[] iNavName = {"Home", "Profile", "Test", "Result", "Guide", "Sign Out"};
		//iNavButton - navigation buttons
		JButton[] iNavButton = new JButton[6];
		//iRadioGroup - to group the choices
		ButtonGroup iRadioGroup1=new ButtonGroup();
		//iRadioGroup - to group the choices
		ButtonGroup iRadioGroup2=new ButtonGroup();
		//iRadioGroup - to group the choices
		ButtonGroup iRadioGroup3=new ButtonGroup();
		//iRadioGroup - to group the choices
		ButtonGroup iRadioGroup4=new ButtonGroup();
		//iRadioGroup - to group the choices
		ButtonGroup iRadioGroup5=new ButtonGroup();
		//iRadioGroup - to group the choices
		ButtonGroup iRadioGroup6=new ButtonGroup();
		//iRadioGroup - to group the choices
		ButtonGroup iRadioGroup7=new ButtonGroup();
		//iRadio - choices
		JRadioButton[] iRadio=new JRadioButton[4];
		//iRadioName - choices label
		String[] iRadioName = {"Never", "1-3 days", "4-6 days", "Every day"};
		//iQuesPanel - question panel
		JLabel[] iQuesPanel = new JLabel[7];
		//iQues - questions
		String[] iQues = {	"1. How many days has your skin been itchy because of your eczema?",
							"2. How many nights has your sleep been disturbed because of your eczema?",
							"3. How many days has your skin been bleeding because of your eczema?",
							"4. How many days has your skin been weeping or oozing clear fluid because of your eczema?",
							"5. How many days has your skin been cracked because of your eczema?",
							"6. How many days has your skin been flaking off because of your eczema?",
							"7. How many days has your skin felt dry or rough because of your eczema?"
						};
		//iResult - result
		JButton iResult;
		//iCond - condition
		String[] iCond = {"Clear or almost clear",
							"Mild eczema",
							"Moderate eczema",
							"Severe eczema",
							"Very severe eczema",
							"Test iScore is invalid. Please take the test again"
						};
		//iScore - score
		int iScore=0;
		//iWeek - week
		int iWeek = 0;
		//condition - condition
		String condition = "";
		
		Test(){
			initialize();
		}
		
		public void initialize(){
			iFrame.setLayout(new BorderLayout());
			iFrame.setBounds(0,0,650,650);
			iFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			iFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
			iFrame.setMinimumSize(new Dimension(630,630));
			JPanel lContainer = new JPanel(new GridLayout(2,1));
			lContainer.setBackground(PageBg);
			//--------------------------------
			//Navigation
			//--------------------------------
			JPanel lNav = new JPanel(new GridLayout(1,3));
			lNav.setPreferredSize(new Dimension(630,10));
			lNav.setBackground(TopNav);
			lNav.setBorder(BorderFactory.createMatteBorder(0,0,1,0, TopNavBorder));
			for(int i=0; i<6; i++){
				iNavButton[i]= new JButton(iNavName[i]);
				iNavButton[i].setName(iNavName[i]);
				iNavButton[i].addActionListener(new NavigationListener());
				iNavButton[i].setContentAreaFilled(false);
				iNavButton[i].setForeground(TopNavFont);
				iNavButton[i].setFont(new Font(PageFontStyle,Font.BOLD, PageFontSize));
				iNavButton[i].setBorderPainted(false);
				if(iNavName[i].equals(iFrameName))
					iNavButton[i].setEnabled(false);
				lNav.add(iNavButton[i]);
			}
			
			//--------------------------------
			//Page title
			//--------------------------------
			JPanel titlePanel = new JPanel();
			JLabel title = new JLabel("Patient Oriented Eczema Measurement");
			title.setFont(new Font(TitleFont,Font.BOLD, TitleFontSize));
			title.setForeground(TopTitleFont);
			title.setHorizontalAlignment(JLabel.CENTER);
			titlePanel.add(title);
			titlePanel.setBorder(BorderFactory.createMatteBorder(0,0,1,0, TopTitleBorder));
			titlePanel.setBackground(TopTitle);
			
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
			JPanel lPage = new JPanel(new GridLayout(12,1));
			lPage.setBorder(new MatteBorder(20, 20, 20, 20, PageBg));
			lPage.setBackground(PageBg);
			
			Dimension expectedDimension = new Dimension(650,650);
			lPage.setPreferredSize(expectedDimension);
			lPage.setMaximumSize(expectedDimension);
			lPage.setMinimumSize(expectedDimension);
			
			for(int i=0; i<7; i++){
				JPanel lQuesPanel = new JPanel(new GridLayout(2,1));
				lQuesPanel.setBackground(PageBg);
				iQuesPanel[i] = new JLabel(iQues[i]);
				iQuesPanel[i].setFont(new Font(PageFontStyle,Font.BOLD, PageFontSize));
				iQuesPanel[i].setForeground(PageFont);
				lQuesPanel.add(iQuesPanel[i]);
				
				JPanel lAnswerPanel = new JPanel(new GridLayout(1,4));
				lAnswerPanel.setBackground(PageBg);
				for(int j=0; j<4; j++){
					iRadio[j] = new JRadioButton(iRadioName[j]);
					iRadio[j].setBackground(PageBg);
					iRadio[j].setActionCommand(iRadioName[j]);
					iRadio[j].addActionListener(new AnswerListener());
					if(i==0)
						iRadioGroup1.add(iRadio[j]);
					else if(i==1)
						iRadioGroup2.add(iRadio[j]);
					else if(i==2)
						iRadioGroup3.add(iRadio[j]);
					else if(i==3)
						iRadioGroup4.add(iRadio[j]);
					else if(i==4)
						iRadioGroup5.add(iRadio[j]);
					else if(i==5)
						iRadioGroup6.add(iRadio[j]);
					else if(i==6)
						iRadioGroup7.add(iRadio[j]);
					
					lAnswerPanel.add(iRadio[j]);
				}
				lQuesPanel.add(lAnswerPanel);
				lPage.add(lQuesPanel);
			}
			
			JPanel lBottom = new JPanel(new GridLayout(1,3));
			lBottom.setBackground(PageBg);
			JButton lEmpty1 = new JButton();
			lEmpty1.setContentAreaFilled(false);
			lEmpty1.setEnabled(false);
			lEmpty1.setBorder(new EmptyBorder(0, 0, 0, 0));
			JButton lEmpty2 = new JButton();
			lEmpty2.setContentAreaFilled(false);
			lEmpty2.setBorder(new EmptyBorder(0, 0, 0, 0));
			lEmpty2.setEnabled(false);
			
			iResult = new JButton("Show Result");
			iResult.setActionCommand("Show Result");
			iResult.setBackground(TopNav);
			iResult.addActionListener(new AnswerListener());
			
			lBottom.add(lEmpty1);
			lBottom.add(iResult);
			lBottom.add(lEmpty2);
			lPage.add(lBottom);
			
			lCenter.add(lPage);
			lCenter.add(Box.createVerticalGlue());
			iFrame.add(lContainer, BorderLayout.NORTH);
			iFrame.add(lCenter, BorderLayout.CENTER);
			iFrame.getContentPane().setBackground(PageBg);
			iFrame.pack();
		}
		
		class AnswerListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				String lButton = e.getActionCommand();
				
				if(lButton.equals("Never")){
					iScore += 0;
				}
				else if(lButton.equals("1-3 days")){
					iScore += 1;
				}
				else if(lButton.equals("4-6 days")){
					iScore += 2;
				}
				else if(lButton.equals("Every day")){
					iScore += 3;
				}
				else if(lButton.equals("Show Result")){
					boolean hasEmpty = checkEmpty();
					if(!hasEmpty){
						dispResult();
						clearSelection();
					}
					else
						JOptionPane.showMessageDialog(iFrame, "Please ensure you have checked every box!");
				}
			}
			
		}
		
		class GroupButtonUtils {
			public String getSelected(ButtonGroup buttonGroup) {
				for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
					AbstractButton button = buttons.nextElement();
					
					if (button.isSelected()) {
						return button.getText();
					}
				}

				return "";
			}
		}
		
		public void clearSelection(){
			iRadioGroup1.clearSelection();
			iRadioGroup2.clearSelection();
			iRadioGroup3.clearSelection();
			iRadioGroup4.clearSelection();
			iRadioGroup5.clearSelection();
			iRadioGroup6.clearSelection();
			iRadioGroup7.clearSelection();
			iScore = 0;
			condition = "";
			iWeek++;
		}
		
		public boolean checkEmpty(){
			String hasButton = "";
			GroupButtonUtils gpu = new GroupButtonUtils();
			
			for(int i=0; i<7; i++){
				if(i==0)
					hasButton = gpu.getSelected(iRadioGroup1);
				else if(i==1)
					hasButton = gpu.getSelected(iRadioGroup2);
				else if(i==2)
					hasButton = gpu.getSelected(iRadioGroup3);
				else if(i==3)
					hasButton = gpu.getSelected(iRadioGroup4);
				else if(i==4)
					hasButton = gpu.getSelected(iRadioGroup5);
				else if(i==5)
					hasButton = gpu.getSelected(iRadioGroup6);
				else if(i==6)
					hasButton = gpu.getSelected(iRadioGroup7);
				
				if(hasButton.equals("")){
					return true;
				}
			}
			return false;
		}
		
		public JFrame getFrame(){
			return iFrame;
		}
		
		public void dispResult(){
			String showScore = "Your score is: " + iScore;
			if(iScore>=0&&iScore<=4){
				condition = "Clear or almost clear";
			}
			else if(iScore>4&&iScore<=8){
				condition = "Mild eczema";
			}
			else if(iScore>8&&iScore<=12){
				condition = "Moderate eczema";
			}
			else if(iScore>12&&iScore<=16){
				condition = "Severe eczema";
			}
			else if(iScore>16&&iScore<=21){
				condition = "Very severe eczema";
			}
			showScore+= "\n"+condition;
			
			patient.setResult(iWeek, iScore, condition);
			JOptionPane.showMessageDialog(iFrame,showScore);
		}
		
	}

	/**
	 * Guide - Guide page
	 * @author Nuraishah Sabrina binti Shahrir Zaid [Treasurer] - 1161102321
	 **/
	private class Guide{
		//iframe - program frame 
		JFrame iFrame = new JFrame("POEM - Guide");
		String iFrameName = "Guide";
		//--------------------------------
		//Page color settings
		//--------------------------------
		Color topNav = new Color(255, 255, 255); //Nav Background
		Color topNavBorder = new Color(206, 206, 206); //Nav Border
		Color topNavFont = new Color(0,0,0); //Nav Font 
		
		Color topTitle = new Color(122, 13, 13); //Title Background
		Color topTitleFont = new Color(252, 252, 252); //Title Font
		Color topTitleBorder = new Color(86, 7, 7); //Title Border
		String titleFont = "Calligraphia"; //Title Font Style
		int titleFontSize = 15;
		
		Color userInfoBg = new Color(252, 252, 252); 
		Color pageBg = new Color(242, 243, 244); //Page Background
		Color pageFont =  new Color(0,0,0); //Page Font
		String pageFontStyle = "Calligraphia"; //Page font style
		int pageFontSize = 11;
		//--------------------------------
		//iNavName - navigation button names
		String[] iNavName = {"Home", "Profile", "Test", "Result", "Guide", "Sign Out"};
		//iNavButton - navigation buttons
		JButton[] iNavButton = new JButton[6];
		//iGuide - guidelines
		String[] iGuide = { "<html>Each of the seven questions carries equal weight and is scored from 0 to 4 as follows:<br/>",
							">> Never    = 0<br/>",
							">> 1-3 days = 1<br/>",
							">> 4-6 days = 2<br/>",
							">> Everyday = 3</html>",
							"<html><br/>To help patients understand their scores, the following bandings have been established:<br/>",
							">> 0-4   = Clear or almost clear<br/>",
							">> 5-8   = Mild eczema<br/>",
							">> 9-12  = Moderate eczema<br/>",
							">> 12-16 = Severe eczema<br/>",
							">> 16-20 = Very severe eczema</html>"};
		//iGuideTitle - guidelines' titles
		String[] iGuideTitle = {"How is the scoring done?",
								"What does a poem score mean?"};
		Guide(){
			initialize();
		}
		
		public void initialize(){
			iFrame.setLayout(new BorderLayout());
			iFrame.setBounds(0,0,650,650);
			iFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			iFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
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
			for(int i=0; i<6; i++){
				iNavButton[i]= new JButton(iNavName[i]);
				iNavButton[i].setName(iNavName[i]);
				iNavButton[i].setContentAreaFilled(false);
				iNavButton[i].setForeground(topNavFont);
				iNavButton[i].setFont(new Font(pageFontStyle,Font.BOLD, pageFontSize));
				iNavButton[i].setBorderPainted(false);
				if(iNavName[i].equals(iFrameName))
					iNavButton[i].setEnabled(false);
				iNavButton[i].addActionListener(new NavigationListener());
				lNav.add(iNavButton[i]);
			}
			
			//--------------------------------
			//Page title
			//--------------------------------
			JPanel titlePanel = new JPanel();
			JLabel title = new JLabel("Patient Oriented Eczema Measurement");
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
			
			Dimension expectedDimension = new Dimension(600,1100);
			lPage.setPreferredSize(expectedDimension);
			lPage.setMaximumSize(expectedDimension);
			lPage.setMinimumSize(expectedDimension);
			
			//--------------------------------
			//Guide Panel
			//--------------------------------
			JPanel lGuidePanel = new JPanel(new GridLayout(2,1));
			lGuidePanel.setBackground(topNav);
			lGuidePanel.setBorder(new MatteBorder(10, 0, 0, 0, topTitle));
			
			for(int i=0; i<2; i++){
				JPanel lGuideInfo = new JPanel(new BorderLayout());
				lGuideInfo.setBackground(topNav);
				lGuideInfo.setBorder(new MatteBorder(20, 20, 20, 20, topNav));
				
				JLabel lGuideTitle = new JLabel(iGuideTitle[i], SwingConstants.LEFT);
				lGuideTitle.setBorder(new MatteBorder(0, 40, 0, 0, topTitle));
				lGuideTitle.setForeground(topTitle);
				lGuideTitle.setFont(new Font(titleFont,Font.BOLD, titleFontSize));
				lGuideInfo.add(lGuideTitle, BorderLayout.NORTH);
				
				String s = "";
				for(int j=0; j<11; j++){
					if(i==0){
						if(j>=0&&j<=4){
							s+=iGuide[j];
						}
					}
					else{
						if(j>=5&&j<=10){
							s+=iGuide[j];
						}
					}
				}
				JLabel lGuide = new JLabel(s);
				lGuide.setForeground(pageFont);
				lGuideInfo.add(lGuide, BorderLayout.CENTER);
				lGuidePanel.add(lGuideInfo);
			}
			lPage.add(lGuidePanel);
			
			lCenter.add(lPage);
			lCenter.add(Box.createVerticalGlue());
			iFrame.add(lContainer, BorderLayout.NORTH);
			iFrame.add(lCenter, BorderLayout.CENTER);
			iFrame.getContentPane().setBackground(pageBg);
			iFrame.pack();
		}
		
		public JFrame getFrame(){
			return iFrame;
		}
	}	
	
	/**
	 * Result - Results page
	 * @author Nur Farisha Shahirah binti Abdul Rahman [Secretary] - 1171302013
	 **/
	private class Result{
		//iframe - program frame 
		JFrame iFrame = new JFrame("POEM - Result");
		String iFrameName = "Result";
		//--------------------------------
		//Page color settings
		//--------------------------------
		Color topNav = new Color(255, 255, 255); //Nav Background
		Color topNavBorder = new Color(206, 206, 206); //Nav Border
		Color topNavFont = new Color(0,0,0); //Nav Font 
		
		Color topTitle = new Color(122, 13, 13); //Title Background
		Color topTitleFont = new Color(252, 252, 252); //Title Font
		Color topTitleBorder = new Color(86, 7, 7); //Title Border
		String titleFont = "Calligraphia"; //Title Font Style
		int titleFontSize = 15;
		
		Color userInfoBg = new Color(252, 252, 252); 
		Color pageBg = new Color(242, 243, 244); //Page Background
		Color pageFont =  new Color(0,0,0); //Page Font
		String pageFontStyle = "Calligraphia"; //Page font style
		int pageFontSize = 11;
		//--------------------------------
		//iNavName - navigation button names
		String[] iNavName = {"Home", "Profile", "Test", "Result", "Guide", "Sign Out"};
		//iNavButton - navigation buttons
		JButton[] iNavButton = new JButton[6];
		
		Result(){
			initialize();
		}
		
		public void initialize(){
			iFrame.setLayout(new BorderLayout());
			iFrame.setBounds(0,0,650,650);
			iFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			iFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
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
			for(int i=0; i<6; i++){
				iNavButton[i]= new JButton(iNavName[i]);
				iNavButton[i].setName(iNavName[i]);
				iNavButton[i].setContentAreaFilled(false);
				iNavButton[i].setForeground(topNavFont);
				iNavButton[i].setFont(new Font(pageFontStyle,Font.BOLD, pageFontSize));
				iNavButton[i].setBorderPainted(false);
				if(iNavName[i].equals(iFrameName))
					iNavButton[i].setEnabled(false);
				iNavButton[i].addActionListener(new NavigationListener());
				lNav.add(iNavButton[i]);
			}
			
			//--------------------------------
			//Page title
			//--------------------------------
			JPanel titlePanel = new JPanel();
			JLabel title = new JLabel("Patient Oriented Eczema Measurement");
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
			
			JPanel lPage = new JPanel(new GridLayout(1,1));
			lPage.setBorder(new MatteBorder(20, 20, 20, 20, pageBg));
			lPage.setBackground(pageBg);
			
			Dimension expectedDimension = new Dimension(500,500);
			lPage.setPreferredSize(expectedDimension);
			lPage.setMaximumSize(expectedDimension);
			lPage.setMinimumSize(expectedDimension);
			
			//--------------------------------
			//Result Panel
			//--------------------------------
			int lSizePage = patient.getResultLength();
			
			if(lSizePage>0){
				JPanel lResultPanel = new JPanel(new GridLayout(lSizePage,1));
				lResultPanel.setBackground(topNav);
				lResultPanel.setBorder(new TitledBorder("Result"));
				
				for(int i=0; i<lSizePage; i++){
					JPanel lResultInfo = new JPanel(new GridLayout(3,1));
					lResultInfo.setBackground(topNav);
					lResultInfo.setBorder(new MatteBorder(20, 20, 20, 20, topNav));
					
					JLabel lResultWeek = new JLabel("Week: "+i);
					lResultWeek.setForeground(pageFont);
					lResultInfo.add(lResultWeek);
					
					JLabel lResult = new JLabel("Score: "+patient.getScore(i));
					lResult.setForeground(pageFont);
					lResultInfo.add(lResult);
					
					JLabel lResultPresc = new JLabel("Prescription: "+patient.getPrescription(i));
					lResultPresc.setForeground(pageFont);
					lResultInfo.add(lResultPresc);
					
					lResultPanel.add(lResultInfo);
				}
				lPage.add(lResultPanel);
			}
			lCenter.add(lPage);
			lCenter.add(Box.createVerticalGlue());
			iFrame.add(lContainer, BorderLayout.NORTH);
			iFrame.add(lCenter, BorderLayout.CENTER);
			iFrame.getContentPane().setBackground(pageBg);
			iFrame.pack();
		}
		
		public JFrame getFrame(){
			return iFrame;
		}

	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Poem window = new Poem();
					window.iFrame.setVisible(true);
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
        });
	}
}