package vn.kma.OcrSerach.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.kma.OcrSerach.model.Document;
import vn.kma.OcrSerach.model.DocumentClient;
import vn.kma.OcrSerach.service.DocumentService;

import java.util.stream.Collectors;


import java.util.List;

@RestController
@RequestMapping("/document")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping
    public String addDocument(@RequestBody DocumentClient documentClient) {
        try {
            // Tạo một đối tượng Document
            Document document = new Document(
                    documentClient.getId(),
                    documentClient.getScan(),
                    documentClient.decodeBase64ToByteBuffer()
            );
            // Lưu đối tượng Document vào Cassandra
            documentService.save(document);
            return "{\"status\":\"success\", \"id\":\"" + document.getId() + "\"}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"status\":\"failure\"}";
        }
    }

    @GetMapping
    public List<DocumentClient> findDocumentBySolr(String search){
        List<Document> documents = documentService.findDocumentBySolr(search);
        // Thay đổi và thêm thông tin trước khi gửi cho client
        return documents.stream()
                .map(doc -> {
                    // Tạo mới đối tượng DocumentClient với các thông số thay đổi
                    DocumentClient client = new DocumentClient();
                    client.setId(doc.getId());
                    client.setScan(doc.getScan());
                    client.setImage(doc.encodeByteBufferToBase64());
                    return client;
                })
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable String id) {
        documentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
