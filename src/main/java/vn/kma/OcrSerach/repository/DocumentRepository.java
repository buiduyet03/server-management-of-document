package vn.kma.OcrSerach.repository;


import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Insert;
import com.datastax.oss.driver.api.mapper.annotations.Select;
import com.datastax.oss.driver.api.mapper.annotations.Delete;
import vn.kma.OcrSerach.model.Document;

@Dao
public interface DocumentRepository {

    @Select(customWhereClause = "solr_query = :search")
    PagingIterable<Document> findDocumentBySolr(String search);

    @Insert
    void save(Document document);

    @Delete(entityClass = Document.class)
    void deleteById(String id);
}
