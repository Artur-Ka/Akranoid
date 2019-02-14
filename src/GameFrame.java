
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.UIManager;


public class GameFrame extends JFrame
{
	private Timer _mainTimer;
	private GameTimer _gameTimer;
	private PlayerBoard _playerBoard;
	private ComputerBoard _computerBoard;
	private Ball _ball;
	private int _maxPoints;
	private int _playerPoints;
	private int _compPoints;
	private boolean _goal;
	
	public GameFrame()
	{
		setTitle("Арканоид (Карпенко А. В.)");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setIconImage(Loader.LOGO_IMAGE);
		setSize(Loader.FRAME_WIDTH, Loader.FRAME_HEIGHT);
		setLocationRelativeTo(null);
		setVisible(true);
		
		_mainTimer = new Timer(Loader.FRAME_FREQUENCY, new Action());
		_gameTimer = new GameTimer();
		_playerBoard = new PlayerBoard();
		_computerBoard = new ComputerBoard();
		_ball = new Ball(getPlayerBoard(), getComputerBoard());
		getComputerBoard().setBall(getBall());
		
		startGame();
	}
	
	public Timer getMainTimer()
	{
		return _mainTimer;
	}
	
	public PlayerBoard getPlayerBoard()
	{
		return _playerBoard;
	}
	
	public ComputerBoard getComputerBoard()
	{
		return _computerBoard;
	}
	
	public Ball getBall()
	{
		return _ball;
	}
	
	public int getMaxPoints()
	{
		return _maxPoints;
	}
	
	public int getPlayerPoints()
	{
		return _playerPoints;
	}
	
	public int getCompPoints()
	{
		return _compPoints;
	}
	
	public boolean getGoal()
	{
		return _goal;
	}
	
	public void setMaxPoints(int maxPoints)
	{
		_maxPoints = maxPoints;
	}
	
	public void setGoal(boolean goal)
	{
		_goal = goal;
	}
	
	private void increasePlayerPoints()
	{
		_playerPoints += 1;
	}
	
	private void increaseCompPoints()
	{
		_compPoints += 1;
	}
	
	private void resetPlayerPoints()
	{
		_playerPoints = 0;
	}
	
	private void resetCompPoints()
	{
		_compPoints = 0;
	}
	
	private void startRound()
	{
		setGoal(false);
		getMainTimer().start();
		getPlayerBoard().restart();
		getComputerBoard().restart();
		getBall().restart();
		getMainTimer().start();
	}
	
	private void stopRound()
	{
		setGoal(true);
		getMainTimer().stop();
		_gameTimer.schedule(new Task(), 2000);
	}
	
	private void startGame()
	{
		UIManager.put("OptionPane.okButtonText", "Принять");
		UIManager.put("OptionPane.cancelButtonText", "Выход");
		
		resetPlayerPoints();
		resetCompPoints();
		
		try
		{
			setMaxPoints(Integer.parseInt(JOptionPane.showInputDialog("До скольки голов играем?")));
		}
		catch (Exception e)
		{
			System.exit(0);
		}
		
		if (getMaxPoints() > 0)
		{
			setGoal(false);
			getPlayerBoard().restart();
			getComputerBoard().restart();
			getBall().restart();
			getMainTimer().start();
			addKeyListener(new MyKeyAdapter());
		}
	}
	
	private void stopGame(int param)
	{
		getMainTimer().stop();
		
		String text1 = null;
		String score = "Счет - " + getPlayerPoints() + " : " + getCompPoints();
		Object[] text2 = {score, "Сыграть еще раз?"};
		
		switch (param)
		{
			case 0:
				text1 = "Вы проиграли!";
			break;
			case 1:
				text1 = "Вы победили!";
			break;
		}
		
		UIManager.put("OptionPane.yesButtonText", "Играть");
		UIManager.put("OptionPane.noButtonText", "Выход");
		
		int dialog = JOptionPane.showConfirmDialog(this, text2, text1, JOptionPane.YES_NO_OPTION);
		
		if (dialog == JOptionPane.YES_OPTION)
			startGame();
		
		else if (dialog == JOptionPane.NO_OPTION)
			System.exit(0);
	}
	
	public void paint(Graphics g)
	{
		g.drawImage(Loader.BACK_IMAGE, 0, 0, null);
		g.drawImage(getPlayerBoard().getImage(), getPlayerBoard().getX(), getPlayerBoard().getY(), null);
		g.drawImage(getComputerBoard().getImage(), getComputerBoard().getX(), getComputerBoard().getY(), null);
		g.drawImage(getBall().getImage(), getBall().getX(), getBall().getY(), null);
		
		if (getGoal())
		{
			int middleWidth = Loader.FRAME_WIDTH / 2 - Loader.GOAL_IMAGE.getWidth() / 2;
			int middleHeight = Loader.FRAME_HEIGHT / 2 - Loader.GOAL_IMAGE.getHeight() / 2;
			
			g.drawImage(Loader.GOAL_IMAGE, middleWidth, middleHeight, null);
		}
	}
	
	private class MyKeyAdapter extends KeyAdapter
	{
		public void keyPressed(KeyEvent e)
		{
			getPlayerBoard().keyPressed(e);
		}
		
		public void keyReleased(KeyEvent e)
		{
			getPlayerBoard().keyReleased(e);
		}
	}
	
	private class Action implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			repaint();
			getBall().move();
			getComputerBoard().move();
			
			if (getBall().getY() <= 0)
			{
				increasePlayerPoints();
				stopRound();
			}
			
			if (getBall().getY() + getBall().getHeight() >= getHeight())
			{
				increaseCompPoints();
				stopRound();
			}
		}
	}
	
	private class Task extends TimerTask
	{
		@Override
		public void run() 
		{
			if (getMaxPoints() > getPlayerPoints() && getMaxPoints() > getCompPoints())
				startRound();
			
			else
			{
				if (getCompPoints() > getPlayerPoints())
					stopGame(0);
				
				else if (getPlayerPoints() > getCompPoints())
					stopGame(1);
			}
		}
	}
}
