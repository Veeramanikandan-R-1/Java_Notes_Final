
package com.example.spring_app_jdbc_h2db.repo;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.spring_app_jdbc_h2db.model.Alien;

@Repository
public class AlienRepo {

    private final JdbcTemplate template;

    public AlienRepo(JdbcTemplate template) {
        this.template = template;
    }

    public void save(Alien alien) {

        String sql = """
            INSERT INTO alien (id, name, tech)
            VALUES (?, ?, ?)
        """;

        template.update(
            sql,
            alien.getId(),
            alien.getName(),
            alien.getTech()
        );
    }

    public List<Alien> findAll() {

        String sql = "SELECT * FROM alien";

        return template.query(sql, (rs, rowNum) -> {

            Alien alien = new Alien();

            alien.setId(rs.getInt("id"));
            alien.setName(rs.getString("name"));
            alien.setTech(rs.getString("tech"));

            return alien;
        });
    }
}
 