import javax.swing.*;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.net.URL;
//copyright Russell Baxt, 2013
public class Pong extends JComponent implements MouseMotionListener
{
    int ballyspd = 1;
    int xpos = 100;
	int ypos = 100;
    int ballxspd = 1;
    JFrame window;
	int ballsizex = 50;
	int ballsizey = 50;
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;
	int paddlesizex = 300;
	int paddlesizey = 25;
	int paddlex;
	Ellipse2D.Double ball;
	Rectangle2D.Double paddle;
	int score = 0;
	URL boingAddress;
	AudioClip boingSoundFile;
	
	public static void main(String[] args)
	{
		new Pong().getGoing();
	}

	private void getGoing()
	{
		boingAddress = getClass().getResource("fire.aiff");
		boingSoundFile = JApplet.newAudioClip(boingAddress);
		window = new JFrame();
		window.setVisible(true);
		window.setSize(width, height);
		window.add(this);
		window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
		window.addMouseMotionListener(this);

	}

	public void paint(Graphics g)
	{
		ball = new Ellipse2D.Double(xpos, ypos, ballsizex, ballsizey);
		paddle = new Rectangle2D.Double(paddlex, height - 100, paddlesizex,	paddlesizey);
		if (xpos >= width - ballsizex)
		{
			ballxspd = -ballxspd;
		}
		if (xpos <= 0)
		{
			ballxspd = -ballxspd;
		}
		if (ypos <= 0)
		{
			ballyspd = -ballyspd;
		}
		if (ball.intersects(paddle))
		{
			ballyspd = -ballyspd;
			score++;
			boingSoundFile.play();
		}

		xpos = xpos + ballxspd;
		ypos = ypos + ballyspd;
		Graphics2D g2 = (Graphics2D) g;
		g2.fill(paddle);
		g2.setStroke(new BasicStroke(6f));
		g2.fill(ball);
		g2.setColor(Color.darkGray);
		repaint();
		g2.setColor(Color.black);
		g2.setFont(new Font("Calibri", Font.BOLD, 48));
		g2.drawString("" + score, 250, 100);
		g2.setFont(new Font("Calibri", Font.PLAIN, 24));
		g2.drawString("Your score is ", 85, 100);
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{

	}

	@Override
	public void mouseMoved(MouseEvent jack)
	{
		paddlex = jack.getX();
	}
}
