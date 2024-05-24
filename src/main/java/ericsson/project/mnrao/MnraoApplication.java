package ericsson.project.mnrao;

import ericsson.project.mnrao.services.RUDataSimulator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MnraoApplication {
	//test CI new sonar test
	public static void main(String[] args) {
		SpringApplication.run(MnraoApplication.class, args);
		RUDataSimulator ruds = new RUDataSimulator();
		ruds.generateNodeData();
	}
}
