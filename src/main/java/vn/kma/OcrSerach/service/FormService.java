package vn.kma.OcrSerach.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.kma.OcrSerach.model.Form;
import vn.kma.OcrSerach.repository.InventoryMapper;

import java.util.List;

@Service
public class FormService {

    @Autowired
    private InventoryMapper inventoryMapper;

    public void save(Form form) {
        inventoryMapper.formRepository().save(form);
    }

    public List<Form> findFormBySolr(String search) {
        return inventoryMapper.formRepository().findFormBySolr(search).all();
    }

    public void delete(String id) {
        inventoryMapper.formRepository().deleteById(id);
    }
}
