package com.bmt.SageClient;

import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

import com.bmt.SageClient.gui.HideToSystemTray;
import com.bmt.SageClient.service.Initialiser;
import com.bmt.SageClient.service.InitialiserInterface;
import com.bmt.SageClient.service.TokenGetter;

@SpringBootApplication
public class SageClientApplication {


	public static void main(String[] args) {
		//SpringApplication.run(SageClientApplication.class, args);	
		SpringApplicationBuilder builder = new SpringApplicationBuilder(SageClientApplication.class);
        builder.headless(false).run(args);
		TokenGetter.scheduleTokenGetter();
		//new HideToSystemTray();
		getInput();
		//startFrontEnd();
	}

	@Bean // TO DO: make daos extend a class that contains the headers which runs a timer to get the keys stored in 'globalVars'
	   public CommandLineRunner demo(InitialiserInterface appInitialiser) {
	      return (args) -> {
	          appInitialiser.setSageApiConnectionData();	    	  
	      };
	   }
	
	
	public static void getInput()
	{		
			while(true) 
			{
				System.out.println("Enter 'T' to get access token    /      'Q' to terminate program   /     'R' to read token from file  ");
				Scanner scanner = new Scanner(System.in);
				String option = scanner.nextLine();
				
				if(option.toLowerCase().equals("q")) System.exit(0);				
				else if(option.toLowerCase().equals("t")) TokenGetter.getToken();
				else if(option.toLowerCase().equals("r")) TokenGetter.readToken();						
			}
	}
	
	
	
	public static void startFrontEnd()
	{
		try {
			System.out.println(Runtime.getRuntime().exec("cmd /c dir"));
			//Runtime.getRuntime().exec("http-server -p 3000  -o");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,  "Could not start browser user interface!\nSystem will terminate.");
			e.printStackTrace();
		}
	}

}
