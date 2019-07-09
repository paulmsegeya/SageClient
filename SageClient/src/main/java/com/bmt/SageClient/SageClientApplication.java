package com.bmt.SageClient;


import java.util.Scanner;
import com.bmt.SageClient.GlobalVars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SageClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SageClientApplication.class, args);	
		
		askForToken();
	}
	
	
	public static void askForToken()
	{
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.println("Enter 'T' to provide access token or 'Q' to terminate program.");
			String option = scanner.nextLine();
			
			if(option.toLowerCase().equals("q")) System.exit(0);
			
			else if(option.toLowerCase().equals("t"))
			{
				System.out.println("Enter Access Token: ");
				GlobalVars.accessToken = scanner.nextLine();
			}
					
			
		}
	}

}
