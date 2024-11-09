package com.example.actuator.config;

import com.example.actuator.model.Product;
import com.example.actuator.respository.ProductRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

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

//@Configuration
//public class DataInitializer {
//
//    @Bean
//    ApplicationRunner initProducts(ProductRepository productRepository) {
//        return args -> {
//            if (productRepository.count() == 0) {
//                Product product1 = new Product("Laptop", "High-performance laptop", 1200.99, 10);
//                Product product2 = new Product("Smartphone", "Latest model smartphone", 699.49, 25);
//                Product product3 = new Product("Headphones", "Noise-cancelling headphones", 199.99, 50);
//
//                productRepository.saveAll(Arrays.asList(product1, product2, product3));
//
//                System.out.println("Sample products have been added to the database.");
//            } else {
//                System.out.println("Products already exist in the database.");
//            }
//        };
//    }
//}


//@Configuration
//public class DataInitializer {
//
//    private static final int MIN_PRODUCTS = 5;
//
//    @Bean
//    ApplicationRunner initProducts(ProductRepository productRepository) {
//        return args -> {
//            long currentCount = productRepository.count();
//
//            if (currentCount < MIN_PRODUCTS) {
//                Faker faker = new Faker();
//                List<Product> products = new ArrayList<>();
//
//                for (int i = 0; i < (MIN_PRODUCTS - currentCount); i++) {
//                    Product product = new Product(
//                            faker.commerce().productName(),
//                            faker.lorem().sentence(),
//                            Double.parseDouble(faker.commerce().price()),
//                            faker.number().numberBetween(1, 100)
//                    );
//                    products.add(product);
//                }
//                productRepository.saveAll(products);
//                System.out.println((MIN_PRODUCTS - currentCount) + " random products have been added to the database.");
//            } else {
//                System.out.println("Sufficient products already exist in the database.");
//            }
//        };
//    }
//}




@Configuration
public class DataInitializer {

    private static final int MIN_EXISTING_PRODUCTS = 10; // Minimum existing products to skip generation
    private static final int TOTAL_PRODUCTS = 100;       // Target number of products to generate

    @Bean
    ApplicationRunner initProducts(ProductRepository productRepository) {
        return args -> {
            long currentCount = productRepository.count();

            // If there are fewer than 10 products, generate products to reach 100
            if (currentCount < MIN_EXISTING_PRODUCTS) {
                Faker faker = new Faker();
                List<Product> products = new ArrayList<>();

                // Generate up to 100 products, filling the difference
                for (int i = 0; i < (TOTAL_PRODUCTS - currentCount); i++) {
                    Product product = new Product(
                            faker.commerce().productName(),
                            faker.lorem().sentence(),
                            Double.parseDouble(faker.commerce().price()),
                            faker.number().numberBetween(1, 100)
                    );
                    products.add(product);
                }

                productRepository.saveAll(products);
                System.out.println((TOTAL_PRODUCTS - currentCount) + " random products have been added to the database.");
            } else {
                System.out.println("Database already contains " + currentCount + " products. No new products generated.");
            }
        };
    }
}