package ma.enset.customersservice;

import ma.enset.customersservice.entities.Customer;
import ma.enset.customersservice.repositories.CustomersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CustomersServiceApplication implements CommandLineRunner {
    CustomersRepository customersRepository;
    RepositoryRestConfiguration repositoryRestConfiguration;

    public CustomersServiceApplication(CustomersRepository customersRepository, RepositoryRestConfiguration repositoryRestConfiguration) {
        this.customersRepository = customersRepository;
        this.repositoryRestConfiguration = repositoryRestConfiguration;
    }

    public static void main(String[] args) {
        SpringApplication.run(CustomersServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repositoryRestConfiguration.exposeIdsFor(Customer.class);

        for (int i=0; i<10; i++){
            Customer customers = Customer.builder()
                    .id(null)
                    .name("name"+ (i+1))
                    .email("name" + (i+1) + "@gmail.com")
                    .build();

            customersRepository.save(customers);
        }
    }
}
