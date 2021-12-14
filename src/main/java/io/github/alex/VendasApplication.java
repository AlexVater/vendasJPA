package io.github.alex;

import io.github.alex.domain.entity.Cliente;
import io.github.alex.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired ClienteRepository clientes){
        return args -> {
            System.out.println("Salvando Clientes");
            clientes.save(new Cliente("Alexsander"));
            clientes.save(new Cliente("Bruno"));
//
            boolean exists = clientes.existsByNome("Alexsander");
            System.out.println(exists);

            List<Cliente> result = clientes.findName("Alex");
            result.forEach(System.out::println);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
