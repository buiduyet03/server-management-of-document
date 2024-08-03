package vn.kma.OcrSerach.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.kma.OcrSerach.model.Form;
import vn.kma.OcrSerach.service.FormService;

import java.util.List;

@RestController
@RequestMapping("/forms")
public class FormController {

    @Autowired
    private FormService formService;

    @PostMapping
    public void addForm(@RequestBody Form form) {
        formService.save(form);
    }

    @GetMapping
    public List<Form> findFormBySolr(@RequestParam String search){
        return formService.findFormBySolr(search);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteForm(@PathVariable String id) {
        formService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
