/**
 * WhackAMole: creates a very rudimentary whack-a-mole game where the user must click the red squares before
 * 		it vanishes and a new one generates at a random location. 
 * @author Chad Gallati
 * @version 1.0 Nov 21 2013
 */
//import statements
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;


public class WhackAMole implements Runnable, MouseListener{

	private static int mouseX, mouseY; //mouse location
	private static int pts;
	private static int ballX, ballY; //mole location
	private static JFrame window = new JFrame("Whack-A-Mole");
	private static Graphics g;
	private Thread thread;

	public WhackAMole(){
		ballX=0;
		ballY=0;
		mouseX = 0;
		mouseY =0;

	}

	public void run() {
		
		//random location generated and mole displayed
		Graphics g = window.getContentPane().getGraphics();
		ballX=(int)(Math.random()*680);
		ballY=(int)(Math.random()*680);
		g.setColor(Color.RED);
		g.fillRect(ballX, ballY, 20, 20);
		try {
			Thread.sleep(1100);  // mole is present for 1 second
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//score displayed and mole is covered with blue square
		g.setColor(Color.WHITE);
		g.fillRect(0, 650, 100, 30);
		g.setColor(Color.BLACK);
		g.drawString("Score:"+pts, 20, 670);
		g.setColor(Color.BLUE);
		g.fillRect(ballX, ballY, 20, 20);
		
		//new mole generated
		WhackAMole mole= new WhackAMole();
		mole.thread = new Thread(mole);
		mole.thread.start();

	}

	/*
	 * location of mouse determined and checks to see if mouse click is within mole. If so, 1 point awarded
	 */
	public void mouseClicked(MouseEvent a) {
		mouseX = a.getLocationOnScreen().x;
		mouseY = a.getLocationOnScreen().y;
		if (mouseX >= ballX && mouseX <= mouseX+20 && mouseY-50 >= ballY && mouseY-50 <= ballY+20){
			pts++;
		}

	}
	
	//unused mouseListener methods
	public void mouseEntered(MouseEvent a) {}
	public void mouseExited(MouseEvent a) {}
	public void mousePressed(MouseEvent a) {}
	public void mouseReleased(MouseEvent a) {}

	/*
	 * initializes game by creating first mole thread and setting up frame
	 */
	public static void main(String[] args){
		WhackAMole mole= new WhackAMole();
		window.addMouseListener(mole);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(700, 700);
		Container c = window.getContentPane();
		c.setBackground(Color.BLUE);
		window.setVisible(true);
		mole.thread = new Thread(mole);
		mole.thread.start();
	}

}
