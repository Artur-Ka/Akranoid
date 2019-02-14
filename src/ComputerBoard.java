import java.awt.Image;


public class ComputerBoard
{
	private int _mainFrameWidth = Loader.FRAME_WIDTH;
	private Image _image = Loader.COMP_IMAGE;
	private int _speed = Loader.COMP_SPEED;
	private int _width = Loader.COMP_IMAGE.getWidth();
	private int _height = Loader.COMP_IMAGE.getHeight();
	private int _x;
	private int _y;
	private Ball _ball;
	
	public ComputerBoard()
	{
		
	}
	
	public int getMainFrameWidth()
	{
		return _mainFrameWidth;
	}
	
	public Image getImage()
	{
		return _image;
	}
	
	public int getWidth()
	{
		return _width;
	}
	
	public int getHeight()
	{
		return _height;
	}
	
	public int getSpeed()
	{
		return _speed;
	}
	
	public int getX()
	{
		return _x;
	}
	
	public int getY()
	{
		return _y;
	}
	
	public Ball getBall()
	{
		return _ball;
	}
	
	public void setX(int x)
	{
		_x = x;
	}
	
	public void setY(int y)
	{
		_y = y;
	}
	
	public void setBall(Ball ball)
	{
		_ball = ball;
	}
	
	public void move()
	{
		if (getBall().getVectorY() < 0 && getBall().getY() < Loader.START_THINKING)
		{
			int ballX = getBall().getX();
			int ballY = getBall().getY();
			int ballSpeed = getBall().getSpeed();
			double ballVectorX = getBall().getVectorX();
			double ballVectorY = getBall().getVectorY();
			
			while(ballY > getHeight())
			{
				ballX += (int) (ballVectorX * ballSpeed);
				ballY += (int) (ballVectorY * ballSpeed);
			}
			
			if ((getX() + getWidth() / 2) > ballX && getX() > 0)
			{
				setX(getX() - getSpeed());
				
				if (getX() + getWidth() > getMainFrameWidth())
					setX(getMainFrameWidth() - getWidth());
			}
			
			if ((getX() + getWidth() / 2) < ballX && getX() < getMainFrameWidth() - getWidth())
			{
				setX(getX() + getSpeed());
				
				if (getX() < 0)
					setX(0);
			}
		}
	}
	
	public void restart()
	{
		setX(getMainFrameWidth() / 2 - getWidth() / 2);
		setY(getHeight());
	}
}
