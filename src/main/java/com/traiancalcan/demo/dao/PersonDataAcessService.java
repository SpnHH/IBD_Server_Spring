package com.traiancalcan.demo.dao;

import com.traiancalcan.demo.model.Person;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class PersonDataAcessService implements PersonDao{

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDataAcessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int insertPerson(UUID id, Person person) {
        final String sql = "INSERT INTO person (id, name)" + "VALUES(?,?)";
        jdbcTemplate.update(sql, id, person.getName());
        return 1;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        for(Person person: selectAllPersons()){
            if(person.getId().equals(id)){
                return Optional.ofNullable(person);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Person> selectAllPersons() {
        final  String sql = "SELECT id, name FROM person";
        return jdbcTemplate.query(sql, new RowMapper<Person>() {
            @Override
            public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
                var id = UUID.fromString(rs.getString("id"));
                var name = rs.getString("name");
                return new Person(id,name);
            }
        });

    }

    @Override
    public int deletePersonById(UUID id) {

        final String sql = "DELETE FROM person WHERE id=?";
        jdbcTemplate.update(sql, id);
        return 0;
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        String sql = "Update person SET name = ? WHERE id = ?";
        return jdbcTemplate.update(sql, person.getName(), id);
    }
}
