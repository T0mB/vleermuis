package com.planner.vleermuis;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class VleermuisApplication {

	public static void main(String[] args) {
		Application.launch(PlannerApplication.class, args);
	}

}
