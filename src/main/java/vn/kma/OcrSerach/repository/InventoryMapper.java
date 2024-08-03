package vn.kma.OcrSerach.repository;

import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;

@Mapper
public interface InventoryMapper {
    @DaoFactory
    FormRepository formRepository();
    @DaoFactory
    DocumentRepository documentRepository();
}