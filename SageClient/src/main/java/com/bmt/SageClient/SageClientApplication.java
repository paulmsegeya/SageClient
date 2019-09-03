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
		SpringApplicationBuilder builder = new SpringApplicationBuilder(SageClientApplication.class);
        builder.headless(false).run(args);
		TokenGetter.scheduleTokenGetter();
		new HideToSystemTray();
		startFrontEnd();
	}

	@Bean 
	   public CommandLineRunner demo(InitialiserInterface appInitialiser) {
	      return (args) -> {
	          appInitialiser.setSageApiConnectionData();	    	  
	          appInitialiser.startFrontEnd();
	      };
	   }
	
	
	
	
	
	public static void startFrontEnd(){		
		 try {
			 	Process process = Runtime.getRuntime().exec( System.getProperty("user.dir") + "/startFrontEnd.bat", null, new java.io.File(System.getProperty("user.dir")));
			 	
		    } catch (IOException  e) {
		        e.printStackTrace();
		    }
	}

}
