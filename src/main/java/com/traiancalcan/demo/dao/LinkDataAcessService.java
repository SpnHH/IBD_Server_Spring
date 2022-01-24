package com.traiancalcan.demo.dao;

import com.traiancalcan.demo.model.Link;
import com.traiancalcan.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Repository("link")
public class LinkDataAcessService implements LinkDao{

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public LinkDataAcessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int createLink(Person person1, Person person2) {
        final String sql = "INSERT INTO link (id, person1, person2)" + "VALUES(?,?,?)";
        var id = UUID.randomUUID();
        jdbcTemplate.update(sql, id, person1.getId(), person2.getId());
        return 1;
    }

    @Override
    public List<Link> getAllLinks() {
        final  String sql = "SELECT id, person1, person2 FROM link";
        return jdbcTemplate.query(sql, new RowMapper<Link>() {
            @Override
            public Link mapRow(ResultSet rs, int rowNum) throws SQLException {
                var id = UUID.fromString(rs.getString("id"));
                var person1 = UUID.fromString(rs.getString("person1"));
                var person2 = UUID.fromString(rs.getString("person2"));
                return new Link(id,person1, person2);
            }
        });

    }

    @Override
    public int deleteLinkById(UUID id) {
        final String sql = "DELETE FROM link WHERE person1=? OR person2=?";
        jdbcTemplate.update(sql, id, id);
        return 0;
    }
}
