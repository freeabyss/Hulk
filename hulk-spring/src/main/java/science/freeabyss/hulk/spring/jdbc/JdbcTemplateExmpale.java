package science.freeabyss.hulk.spring.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import science.freeabyss.model.DocEntityModel;

/**
 * Created by abyss on 08/15/16.
 */
public class JdbcTemplateExmpale {
    private JdbcTemplate jdbcTemplate;

    public void addSpiter(DocEntityModel docEntity) {
        String sql = "";
        jdbcTemplate.update(sql,
                docEntity.getContent(),
                docEntity.getTitle(),
                docEntity.getDocType(),
                docEntity.getKeywords());

    }
}
