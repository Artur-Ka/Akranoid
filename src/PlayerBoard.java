import java.awt.Image;
import java.awt.event.KeyEvent;


public class PlayerBoard 
{
	private int _mainFrameWidth = Loader.FRAME_WIDTH;
	private int _mainFrameHeight = Loader.FRAME_HEIGHT;
	private Image _image = Loader.PLAYER_IMAGE;
	private int _speed = Loader.PLAYER_SPEED;
	private int _width = Loader.PLAYER_IMAGE.getWidth();
	private int _height = Loader.PLAYER_IMAGE.getHeight();
	private int _x;
	private int _y;
	
	public PlayerBoard()
	{
		
	}
	
	public int getMainFrameWidth()
	{
		return _mainFrameWidth;
	}
	
	public int getMainFrameHeight()
	{
		return _mainFrameHeight;
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
	
	public void setX(int x)
	{
		_x = x;
	}
	
	public void setY(int y)
	{
		_y = y;
	}
	
	public void moveRight()
	{
		setX(getX() + getSpeed());
		
		if (getX() > getMainFrameWidth() - getWidth())
			setX(getMainFrameWidth() - getWidth());
	}
	
	public void moveLeft()
	{
		setX(getX() - getSpeed());
		
		if (getX() < 0)
			setX(0);
	}
	
	public void restart()
	{
		setX(getMainFrameWidth() / 2 - getWidth() / 2);
		setY(getMainFrameHeight() - getHeight());
	}
	
	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_LEFT:
				moveLeft();
			break;
			case KeyEvent.VK_RIGHT:
				moveRight();
			break;
		}
	}
	
	public void keyReleased(KeyEvent e)
	{
		
	}
}
