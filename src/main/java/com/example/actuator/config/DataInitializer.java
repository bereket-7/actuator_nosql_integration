package com.example.actuator.config;

import com.example.actuator.model.Product;
import com.example.actuator.respository.ProductRepository;
import org.apache.catalina.User;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Arrays;

//@Configuration
//public class DataInitializer {

//    @Bean
//    ApplicationRunner initData(MongoTemplate mongoTemplate) {
//        return args -> {
//            mongoTemplate.save(new User("Alice", "alice@example.com"));
//            mongoTemplate.save(new User("Bob", "bob@example.com"));
//        };
//    }
//}

@Configuration
public class DataInitializer {

    @Bean
    ApplicationRunner initProducts(ProductRepository productRepository) {
        return args -> {
            if (productRepository.count() == 0) {
                Product product1 = new Product("Laptop", "High-performance laptop", 1200.99, 10);
                Product product2 = new Product("Smartphone", "Latest model smartphone", 699.49, 25);
                Product product3 = new Product("Headphones", "Noise-cancelling headphones", 199.99, 50);

                productRepository.saveAll(Arrays.asList(product1, product2, product3));

                System.out.println("Sample products have been added to the database.");
            } else {
                System.out.println("Products already exist in the database.");
            }
        };
    }
}
