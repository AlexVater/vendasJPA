package io.github.alex.domain.repository;

import io.github.alex.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query(value = "select * from Cliente c where c.nome like '%:nome%'", nativeQuery = true)
    List<Cliente> findName( @Param("nome") String nome);

    @Query(value = "delete from Cliente c where c.nome =:nome")
    @Modifying
    void deleteByNome(String nome);

    boolean existsByNome(String nome);
}
