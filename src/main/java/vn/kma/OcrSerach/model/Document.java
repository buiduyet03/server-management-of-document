package vn.kma.OcrSerach.model;

import com.datastax.oss.driver.api.mapper.annotations.*;
import com.datastax.oss.driver.api.mapper.entity.naming.NamingConvention;
import lombok.*;
import java.nio.ByteBuffer;
import java.util.Base64;

@Setter
@Getter
@Builder
@Entity(defaultKeyspace = "kma")
@NamingStrategy(convention = NamingConvention.CASE_INSENSITIVE)
@CqlName("document")
public class Document {
    // Getters và Setters
    @ClusteringColumn
    private String id;
    private String scan;
    private ByteBuffer img; // Sử dụng ByteBuffer cho kiểu dữ liệu blob

    // Constructor mặc định
    public Document() {}

    public Document(String id, String scan, ByteBuffer img) {
        this.id = id;
        this.scan = scan;
        this.img = img;
    }

    // Phương thức để lấy mảng byte từ ByteBuffer
    public String encodeByteBufferToBase64(){
        // Chuyển ByteBuffer thành một mảng byte
        byte[] byteArray;
        if (this.img.hasArray()) {
            byteArray = this.img.array();
        } else {
            byteArray = new byte[this.img.remaining()];
            this.img.get(byteArray);
        }
        // Mã hóa mảng byte thành chuỗi base64
        return Base64.getEncoder().encodeToString(byteArray);
    }
}
