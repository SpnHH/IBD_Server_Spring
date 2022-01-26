package com.traiancalcan.demo.dao;

import com.traiancalcan.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository("postgres")
public class PersonDataAcessService implements PersonDao{

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDataAcessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int insertPerson(UUID id, Person person) {
        final String sql = "INSERT INTO person (id, name, link)" + "VALUES(?,?,?)";
        String brainMapLink = "https://www.brainmap.ro/" + person.getName().toLowerCase(Locale.ROOT).replaceAll("\\s", "-");
        jdbcTemplate.update(sql, id, person.getName(), brainMapLink);
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
        final  String sql = "SELECT id, name, role, link FROM person";
        return jdbcTemplate.query(sql, new RowMapper<Person>() {
            @Override
            public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
                var id = UUID.fromString(rs.getString("id"));
                var name = rs.getString("name");
                var role = rs.getString("role");
                var link = rs.getString("link");
                return new Person(id,name, role, link);
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

    @Override
    public Optional<Person> getPersonByName(String name) {
        List<Person> personList = new ArrayList<>();
        personList = selectAllPersons();
        for(Person person: personList){
            if(person.getName().toLowerCase(Locale.ROOT).equals(name.toLowerCase(Locale.ROOT))){
                return Optional.ofNullable(person);
            }
        }
        return Optional.empty();
    }
}
