package com.bmt.SageClient;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.bmt.SageClient.gui.HideToSystemTray;
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

}
