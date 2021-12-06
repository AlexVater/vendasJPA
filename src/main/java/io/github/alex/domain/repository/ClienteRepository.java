package io.github.alex.domain.repository;

import io.github.alex.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ClienteRepository {

    private static String SELECT_ALL = "SELECT * FROM CLIENTE";
    private static String UPDATE = "UPDATE CLIENTE SET NOME = ? WHERE ID = ?";
    private static String DELETE = "DELETE FROM CLIENTE WHERE ID = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Cliente save(Cliente cliente){
        entityManager.persist(cliente);
        return cliente;
    }

    public Cliente update(Cliente cliente){
        jdbcTemplate.update(UPDATE, cliente.getNome(), cliente.getId());
        return cliente;
    }

    public void delete(Cliente cliente){
        Integer id = cliente.getId();
        jdbcTemplate.update(DELETE, id);
    }

    public List<Cliente> findByName(String nome){
        return jdbcTemplate.query(
                SELECT_ALL.concat(" WHERE NOME LIKE ? "),
                new Object[]{"%" + nome + "%"},
                getClienteRowMapper());
    }

    public List<Cliente> getAll(){
        return jdbcTemplate.query(SELECT_ALL, getClienteRowMapper());
    }

    private RowMapper<Cliente> getClienteRowMapper() {
        return (resultSet, i) -> {
            Integer id = resultSet.getInt("ID");
            String nome = resultSet.getString("NOME");
            return new Cliente(id, nome);
        };
    }
}
