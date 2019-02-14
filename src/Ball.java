import java.awt.Image;


public class Ball 
{
	private Image _image = Loader.BALL_IMAGE;
	private int _mainFrameWidth = Loader.FRAME_WIDTH;
	private int _mainFrameHeight = Loader.FRAME_HEIGHT;
	private int _speed = Loader.BALL_SPEED;
	private int _width = Loader.BALL_IMAGE.getWidth();
	private int _height = Loader.BALL_IMAGE.getHeight();
	private PlayerBoard _playerBoard;
	private ComputerBoard _computerBoard;
	private int _x;
	private int _y;
	private double _vectorX;
	private double _vectorY;
	
	public Ball(PlayerBoard playerBoard, ComputerBoard computerBoard)
	{
		_playerBoard = playerBoard;
		_computerBoard = computerBoard;
	}
	
	public int getMainFrameWidth()
	{
		return _mainFrameWidth;
	}
	
	public int getMainFrameHeight()
	{
		return _mainFrameHeight;
	}
	
	public PlayerBoard getPlayer()
	{
		return _playerBoard;
	}
	
	public ComputerBoard getComputer()
	{
		return _computerBoard;
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
	
	public double getVectorX()
	{
		return _vectorX;
	}
	
	public double getVectorY()
	{
		return _vectorY;
	}
	
	public void setX(int x)
	{
		_x = x;
	}
	
	public void setY(int y)
	{
		_y = y;
	}
	
	public void setVectorX(double vectorX)
	{
		_vectorX = vectorX;
	}
	
	public void setVectorY(double vectorY)
	{
		_vectorY = vectorY;
	}
	
	public boolean isAboveBoard()
	{
		return getX() + getWidth() / 2 > getPlayer().getX() 
				&& getX() + getWidth() / 2 < getPlayer().getX() + getPlayer().getWidth();
	}
	
	public boolean isUnderBoard()
	{
		return getX() + getWidth() / 2 > getComputer().getX() 
				&& getX() + getWidth() / 2 < getComputer().getX() + getComputer().getWidth();
	}
	
	public void move()
	{
		double length = Math.sqrt(getVectorX() * getVectorX() + getVectorY() * getVectorY());
		
		setVectorX(getVectorX() / length);
		setVectorY(getVectorY() / length);
				
		if (((getX() + getWidth()) >= getMainFrameWidth() && getVectorX() > 0) ||
				(getX() <= 0 && getVectorX() < 0))
			setVectorX(getVectorX() * -1);
			
		if ((((getY() + getHeight()) + (getPlayer().getHeight() - 10) >= getMainFrameHeight() 
				&& getVectorY() > 0) && isAboveBoard())
				|| (getY() - getHeight()) - (getComputer().getHeight() + 10) <= 0 && getVectorY() < 0 && isUnderBoard())
			setVectorY(getVectorY() * -1);
		
		setX(getX() + ((int) (getVectorX() * getSpeed())));
		setY(getY() + ((int) (getVectorY() * getSpeed())));
	}
	
	public void restart()
	{
		setX(getMainFrameWidth() / 2 - getWidth() / 2);
		setY(getMainFrameHeight() / 2 - getHeight() / 2);
		
		int randX = -10 + (int) (Math.random() * 20);
		if (randX == 0)
			randX = 10;
		int randY = -20 + (int) (Math.random() * 30);
		if (randY == 0)
			randY = 20;
		setVectorX(randX);
		setVectorY(randY);
//		setVectorX(-10 + (int) (Math.random() * 21));
//		setVectorY(-10 + (int) (Math.random() * 21));
	}
}
