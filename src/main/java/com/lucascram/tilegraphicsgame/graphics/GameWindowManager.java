package com.lucascram.tilegraphicsgame.graphics;

import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.UIManager;

import com.lucascram.tilegraphicsgame.TileGraphicsGame;
import com.lucascram.tilegraphicsgame.input.ActionEngine;
import com.lucascram.tilegraphicsgame.io.DebugDumper;

public class GameWindowManager {

	public static final int WINWIDTH = 1280;
	public static final int WINHEIGHT = 720;
	
	private JFrame gameWindow;
	
	public GameWindowManager() {
		;
	}
	
	public void initWindow(ActionEngine actionEngine)
	{
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			DebugDumper.displayDebugDialog(DebugDumper.ERR_MESSAGE_LOOKANDFEEL, false);
		}
		
		gameWindow = new JFrame();
		gameWindow.setSize(WINWIDTH, WINHEIGHT);
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.setTitle(TileGraphicsGame.TITLE + " " + TileGraphicsGame.VERSION + " by " + TileGraphicsGame.AUTHOR);
		gameWindow.setResizable(false);
		gameWindow.setIgnoreRepaint(true);
		gameWindow.setLocationRelativeTo(null);
		gameWindow.addKeyListener(actionEngine);
		gameWindow.setVisible(true);
		
		gameWindow.createBufferStrategy(2);
		
	}
	
	public void updateWindow()
	{
		if(gameWindow != null)
		{
			BufferStrategy bf = gameWindow.getBufferStrategy();
			if(!bf.contentsLost())
			{
				bf.show();
			}
		}
		Toolkit.getDefaultToolkit().sync();
	}
	
	public Graphics2D getWindowGraphics()
	{
		if(gameWindow != null)
		{
			BufferStrategy bf = gameWindow.getBufferStrategy();
			return (Graphics2D)bf.getDrawGraphics();
		}
		return null;
	}
	
	public void disposeWindow() {
		gameWindow.dispose();
	}
}
