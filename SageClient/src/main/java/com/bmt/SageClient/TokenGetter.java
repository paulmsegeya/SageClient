package com.bmt.SageClient;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class TokenGetter 
{
	
	
	public static void scheduleTokenGetter()
	{
		java.util.Timer timer = new java.util.Timer();
		timer.scheduleAtFixedRate(new java.util.TimerTask() 
		{
			  @Override
			  public void run() {							 
				  getToken();
			  }
		}, 0, 15 * 60 * 1000); // get token every 15mins
	} 
	
	
	public static void getToken()
	{
		java.io.File tokenFileOriginal = new java.io.File(System.getProperty("user.dir") + "/tokenGetter/token.txt");
		long tokenFileOriginalModifiedTime = tokenFileOriginal.lastModified();		
		
		try 
		{
			Runtime.getRuntime().exec( System.getProperty("user.dir") + "/tokenGetter/APISampleWinFormsApp.exe", null, new java.io.File(System.getProperty("user.dir") + "/tokenGetter/")); //run seperate application to generate new token
			
			java.util.Timer timer = new java.util.Timer();
			timer.scheduleAtFixedRate(new java.util.TimerTask() 
			{
				  @Override
				  public void run() 
				  {							 
					  java.io.File tokenFileNew = new java.io.File(System.getProperty("user.dir") + "/tokenGetter/token.txt");
					  if(tokenFileNew.lastModified() != tokenFileOriginalModifiedTime) 
					  {						  
							try 
							{
								System.out.println("Latest Access token has been applied");
								Robot robot = new Robot();
								robot.keyPress(KeyEvent.VK_ENTER);
								
								Scanner tokenScanner;
								try 
								{
									tokenScanner = new Scanner(new java.io.File(System.getProperty("user.dir") + "/tokenGetter/token.txt"));
									String token = tokenScanner.next();
									tokenScanner.close();
									GlobalVars.accessToken = token;
								} catch (FileNotFoundException e) {
									e.printStackTrace();
								}					

							} catch (AWTException e1) {
								e1.printStackTrace();
							}					  
						  	
							
						  timer.cancel();
						  timer.purge();
					  }
					  
				  }
			}, 0, 1000);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
