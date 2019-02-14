import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;


public class Loader 
{
	// Текстуры
	public static BufferedImage LOGO_IMAGE;
	public static BufferedImage BACK_IMAGE;
	public static BufferedImage PLAYER_IMAGE;
	public static BufferedImage COMP_IMAGE;
	public static BufferedImage BALL_IMAGE;
	public static BufferedImage GOAL_IMAGE;
	
	// Настройки окна
	public static int FRAME_WIDTH;
	public static int FRAME_HEIGHT;
	public static int FRAME_FREQUENCY;
	
	// Игровые параметры
	public static int PLAYER_SPEED;
	public static int COMP_SPEED;
	public static int BALL_SPEED;
	public static int START_THINKING; // Точка по оси y, начиная с которой АИ начинает рассчет падения мяча
	
	public Loader()
	{
		loadTexture();
		loadConfigs();
	}
	
	private void loadTexture()
	{
		try
		{
			LOGO_IMAGE = ImageIO.read(new File("data/images/logo.png"));
			BACK_IMAGE = ImageIO.read(new File("data/images/background.jpg"));
			PLAYER_IMAGE = ImageIO.read(new File("data/images/player.png"));
			COMP_IMAGE = ImageIO.read(new File("data/images/computer.png"));
			BALL_IMAGE = ImageIO.read(new File("data/images/ball.png"));
			GOAL_IMAGE = ImageIO.read(new File("data/images/goal.png"));
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Ошибка! Текстуры не были найдены!", "Ошибка!", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}
	
	private void loadConfigs()
	{
		try
		{
			File file = new File("data/settings.properties");
			Properties properties = new Properties();
			
			try (InputStream is = new FileInputStream(file))
			{
				properties.load(is);
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
			}
		
			FRAME_WIDTH = Integer.parseInt(properties.getProperty("FRAME_WIDTH", "600"));
			FRAME_HEIGHT = Integer.parseInt(properties.getProperty("FRAME_HEIGHT", "700"));
			FRAME_FREQUENCY = Integer.parseInt(properties.getProperty("FRAME_FREQUENCY", "30"));
			PLAYER_SPEED = Integer.parseInt(properties.getProperty("PLAYER_SPEED", "10"));
			COMP_SPEED = Integer.parseInt(properties.getProperty("COMP_SPEED", "10"));
			BALL_SPEED = Integer.parseInt(properties.getProperty("BALL_SPEED", "15"));
			START_THINKING = Integer.parseInt(properties.getProperty("START_THINKING", "200"));
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
			System.exit(0);
		}
	}
}
