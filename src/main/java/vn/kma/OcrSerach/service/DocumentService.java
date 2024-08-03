package vn.kma.OcrSerach.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.kma.OcrSerach.model.Document;
import vn.kma.OcrSerach.repository.InventoryMapper;
import java.util.List;

@Service
public class DocumentService {

    @Autowired
    private InventoryMapper inventoryMapper;

    public void save(Document document) {
            inventoryMapper.documentRepository().save(document);
    }
    public List<Document> findDocumentBySolr(String search) {
        return inventoryMapper.documentRepository().findDocumentBySolr(search).all();
    }

    public void delete(String id) {
        inventoryMapper.documentRepository().deleteById(id);
    }
}
