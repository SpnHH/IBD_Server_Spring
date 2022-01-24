package com.traiancalcan.demo.dao;

import com.traiancalcan.demo.model.Person;
import org.flywaydb.core.internal.jdbc.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PersonMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet rs) throws SQLException {
        var id = UUID.fromString(rs.getString("id"));
        var name = rs.getString("name");
        var role = rs.getString("role");
        var link = rs.getString("link");
        return new Person(id,name, role, link);
    }
}
