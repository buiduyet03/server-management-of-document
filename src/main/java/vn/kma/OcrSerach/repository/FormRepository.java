package vn.kma.OcrSerach.repository;


import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Delete;
import com.datastax.oss.driver.api.mapper.annotations.Insert;
import com.datastax.oss.driver.api.mapper.annotations.Select;
import vn.kma.OcrSerach.model.Form;

@Dao
public interface FormRepository {

    @Select(customWhereClause = "solr_query  = :search")
    PagingIterable<Form> findFormBySolr(String search);

    @Insert
    void save(Form form);

    @Delete(entityClass = Form.class)
    void deleteById(String id);
}
