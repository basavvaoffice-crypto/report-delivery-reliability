package com.test.report_delivery_reliability;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

@SpringBootApplication
public class ReportDeliveryReliabilityApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReportDeliveryReliabilityApplication.class, args);
	}

	@Bean
	CommandLineRunner processReports() {
		return args -> {
			String filePath = "data/report_jobs.json";
			ObjectMapper mapper = new ObjectMapper();

			try {
				// Load jobs from JSON file
				Map<String, Object> data = mapper.readValue(Files.readAllBytes(Paths.get(filePath)), new TypeReference<Map<String, Object>>() {});
List<Job> jobs = mapper.convertValue(data.get("jobs"), new TypeReference<List<Job>>() {});

				// Process jobs
				JobProcessor processor = new JobProcessor();
				processor.process(jobs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
	}
}
