package com.microservices.inventoryservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import com.microservices.inventoryservice.model.Inventory;
import com.microservices.inventoryservice.repository.InventoryRepository;

@SpringBootApplication
@EnableEurekaClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
		
		@Bean
		public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
			return args->{
				Inventory inventory=new Inventory();
				inventory.setSkuCode("iphone13");
				inventory.setQuantity(3);
				Inventory inventory1=new Inventory();
				inventory.setSkuCode("iphone67");
				inventory.setQuantity(1);
				Inventory inventory2=new Inventory();
				inventory.setSkuCode("iphone65");
				inventory.setQuantity(1);
				Inventory inventory3=new Inventory();
				inventory.setSkuCode("iphone_red");
				inventory.setQuantity(0);
				inventoryRepository.save(inventory);
				inventoryRepository.save(inventory1);
				inventoryRepository.save(inventory2);
				inventoryRepository.save(inventory3);
				
			};
		
		
	}

}
