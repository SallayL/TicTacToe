//This is a regular version of Tic Tac Toe.
//03-29-2020 ~
//SL.

import java.util.*;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	 static int[] flag = new int[10];
	 static String name;
	 static JTextField tf;
	 static JButton buttons[] = new JButton[10];  
	 static JButton Exit = new JButton("EXIT GAME");
	 //static String name = JOptionPane.showInputDialog(null,"ENTER YOU NAME:");
	 static JFrame jf = new JFrame("TIC TAC TOE (V-1)");
	
	 public static void main(String[] args)
	   {  
		  //call the pop up window method to prompt the user for a name	 
		  String name = name();
		
		  //start to create container
	      Container contentPane = jf.getContentPane();
	      contentPane.setLayout(new FlowLayout());
	      ActionListener AL = new TicTacToe();
		  
	      //a welcome msg on the top
	      JPanel welcome = new JPanel();
	      welcome = new JPanel(new BorderLayout());
	      welcome.add(new JLabel("WELCOME TO TIC TAC TOE!"));
	      contentPane.add(welcome, BorderLayout.NORTH);
	      
	      //display the name of the player following the welcome msg
	      JPanel showName = new JPanel();
	      showName = new JPanel(new GridLayout(1,1));
	      showName.add(new JLabel("PLAYER: "+ name));
	      contentPane.add(showName);
	      
	      //the layout of the TTT game 
	      for (int i = 0; i < 10; i++){          //button [0] is not used 
	    	  
		         buttons[i] = new JButton("  "); 
		         buttons[i].setBackground(Color.BLACK);
		         buttons[i].addActionListener(AL);
		         flag[i]=0;
		      }
	      
	      flag[0]=1;  //this one will not be used
	      
	      JPanel layout = new JPanel();
	      layout.setLayout(new GridLayout(3, 3,6,6));
	      layout.add(buttons[7]);  
	      layout.add(buttons[8]);  
	      layout.add(buttons[9]); 
	      layout.add(buttons[4]);  
	      layout.add(buttons[5]);  
	      layout.add(buttons[6]); 
	      layout.add(buttons[1]);  
	      layout.add(buttons[2]);  
	      layout.add(buttons[3]); 
	      contentPane.add(layout);
	      
	      //the layout of the EXIT GAME button
	      JPanel exit = new JPanel();
	      exit.setLayout(new BorderLayout());
	      Exit.setBackground(Color.RED);
	      Exit.addActionListener(AL);
	      exit.add(Exit);
	      contentPane.add(exit);
	      
	      
	      
	      jf.pack();
	      jf.setSize(220,230);
	      jf.setLocation(540, 200); //set it to a similar location as the JOptionPane
	      jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      jf.setVisible(true);
	   }

	//a pop up window method to prompt the user for a name
	 public static String name() {
		 
		  name = JOptionPane.showInputDialog(jf,"TELL ME YOUR NAME FIRST", "TIC TAC TOE", JOptionPane.WARNING_MESSAGE);
		 
		 //if user did not input anything, exit the game
		// if (name == null) {
			// System.exit(0);
		 //} did not work, so now assume users will enter thier name, or they play with no name
		 return name;
	 }

	@Override
	public void actionPerformed(ActionEvent e) {
		
		for(int i=1; i<buttons.length;i++) {
			if (e.getSource() == buttons[i]) {
				if(flag[i]==1) {
					JOptionPane.showMessageDialog(null, "Choose another black button!", "MessageDialog", JOptionPane.WARNING_MESSAGE);
			
				}else {
				buttons[i].setText(name);
				flag[i]=1;
				buttons[i].setBackground(Color.WHITE);
				checkResult();	
				bot();
				tie();
				}
			}
		}
		
			
		if (e.getSource() == Exit) {
			System.exit(0);
			
		}
	}
	
	
	//bot takes the next turn here, and need to call the method to check if bot wins or not
	//might need an globel arrayList for this method 
	public static void bot() {
		Random rand=new Random();
		 List<Integer> list = new ArrayList<>();
		int count=0;
		int choice;
		
		for (int i =1;i<flag.length;i++) {
			if (flag[i]==0) {                         //if this button have not been used by the player
				list.add(i);                       //then put it in an local array
				//System.out.println("now is:"+ i);
				count++;                            //increment the local array
			}
		}
		
		//System.out.println("count here is"+count);
		
		//the last button goes to the player
		if(count>1) {
		choice= list.get(rand.nextInt(list.size())); //generate an random number from the local array
		//System.out.println("bot s choice is: "+ choice);
		buttons[choice].setBackground(Color.CYAN);         //set the random number as the button from the bot
		buttons[choice].setText("bot");
		flag[choice]=1;    //set the flag of this button to 1, so the bot cannot choose the same one next time
		}
		checkBot();
		
	}
	
	
	//check if bot won yet, similar to checkResult method, just change the color and call botWon() method
	public static void checkBot() {
		//horizontal
				if (buttons[7].getBackground()==Color.cyan && buttons[8].getBackground()==Color.cyan  && buttons[9].getBackground()==Color.cyan ) {
					botWin();
				}
				
				if (buttons[4].getBackground()==Color.cyan  && buttons[5].getBackground()==Color.cyan  && buttons[6].getBackground()==Color.cyan ) {
					win();
				}
				
				if (buttons[1].getBackground()==Color.cyan  && buttons[2].getBackground()==Color.cyan  && buttons[3].getBackground()==Color.cyan ) {
					botWin();
				}
		       
				//vertical
				if (buttons[7].getBackground()==Color.cyan  && buttons[4].getBackground()==Color.cyan  && buttons[1].getBackground()==Color.cyan ) {
					botWin();
				}
				
				if (buttons[8].getBackground()==Color.cyan  && buttons[5].getBackground()==Color.cyan  && buttons[2].getBackground()==Color.cyan ) {
					botWin();
				}
				
				if (buttons[9].getBackground()==Color.cyan  && buttons[6].getBackground()==Color.cyan  && buttons[3].getBackground()==Color.cyan ) {
					botWin();
				}
				
				//diagonal
				if (buttons[7].getBackground()==Color.cyan  && buttons[5].getBackground()==Color.cyan  && buttons[3].getBackground()==Color.cyan ) {
					botWin();
				}
				
				if (buttons[9].getBackground()==Color.cyan  && buttons[5].getBackground()==Color.cyan  && buttons[1].getBackground()==Color.cyan ) {
					botWin();
				}
	}
	
	//display it if the bot beats the player
	public static void botWin() {
		JOptionPane.showMessageDialog(null, "Too Bad! The Bot is better!", "MessageDialog", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
	}
	
	//check if the player won yet
	public static void checkResult() {
		
		//horizontal
		if (buttons[7].getBackground()==Color.WHITE && buttons[8].getBackground()==Color.WHITE && buttons[9].getBackground()==Color.WHITE) {
			win();
		}
		
		if (buttons[4].getBackground()==Color.WHITE && buttons[5].getBackground()==Color.WHITE && buttons[6].getBackground()==Color.WHITE) {
			win();
		}
		
		if (buttons[1].getBackground()==Color.WHITE && buttons[2].getBackground()==Color.WHITE && buttons[3].getBackground()==Color.WHITE) {
			win();
		}
       
		//vertical
		if (buttons[7].getBackground()==Color.WHITE && buttons[4].getBackground()==Color.WHITE && buttons[1].getBackground()==Color.WHITE) {
			win();
		}
		
		if (buttons[8].getBackground()==Color.WHITE && buttons[5].getBackground()==Color.WHITE && buttons[2].getBackground()==Color.WHITE) {
			win();
		}
		
		if (buttons[9].getBackground()==Color.WHITE && buttons[6].getBackground()==Color.WHITE && buttons[3].getBackground()==Color.WHITE) {
			win();
		}
		
		//diagonal
		if (buttons[7].getBackground()==Color.WHITE && buttons[5].getBackground()==Color.WHITE && buttons[3].getBackground()==Color.WHITE) {
			win();
		}
		
		if (buttons[9].getBackground()==Color.WHITE && buttons[5].getBackground()==Color.WHITE && buttons[1].getBackground()==Color.WHITE) {
			win();
		}
	}
	
	//if the player won
	public static void win() {
        JOptionPane.showMessageDialog(null, "Congradulation! You Won!", "MessageDialog", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
	}
	
	//if the game is a tie
	public static void tie() {
		int yes=0;
		for(int i =1;i<flag.length;i++) {
			if(flag[i]==1) {
				yes++;
			}
		}
		//System.out.println("flagged is:"+yes);
		
		if(yes==9) {
			JOptionPane.showMessageDialog(null, "Oops! No winner for this round.", "MessageDialog", JOptionPane.INFORMATION_MESSAGE);
	        System.exit(0);
		}
	}
}
