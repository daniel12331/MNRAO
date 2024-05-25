package ericsson.project.mnrao;

import ericsson.project.mnrao.models.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Random;


@SpringBootApplication
@EnableScheduling
public class MnraoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MnraoApplication.class, args);

	}
}
