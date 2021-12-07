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

            List<Cliente> todosClientes = clientes.getAll();
            todosClientes.forEach(System.out::println);

            System.out.println("Atualizando Clientes");
            todosClientes.forEach(cliente -> {
                cliente.setNome(cliente.getNome() + "-atualizado");
                clientes.update(cliente);
            });

            todosClientes = clientes.getAll();
            todosClientes.forEach(System.out::println);

            System.out.println("Buscando Clientes");
            clientes.findByName("san").forEach(System.out::println);

            System.out.println("Deletando Clientes");
            clientes.getAll().forEach(clientes::delete);

            todosClientes = clientes.getAll();
            if (todosClientes.isEmpty()){
                System.out.println("Nenhum Cliente Encontrado");
            }else {
                todosClientes.forEach(System.out::println);
            }


        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
