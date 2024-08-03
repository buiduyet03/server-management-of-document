package vn.kma.OcrSerach.model;

import lombok.Getter;
import lombok.Setter;
import java.nio.ByteBuffer;
import java.util.Base64;

@Setter
@Getter
public class DocumentClient {
    private String id;
    private String scan;
    // Initialize to empty array if null
    private String image;

    public DocumentClient() {}

    public DocumentClient(String id, String scan, String image) {
        this.id = id; // Sinh id bằng UUID
        this.scan = scan;
        this.image = image;
    }

    public ByteBuffer decodeBase64ToByteBuffer() {
        if (this.image == null || this.image.isEmpty()) {
            throw new IllegalArgumentException("Base64 string is null or empty");
        }

        try {
            // Xóa bỏ khoảng trắng nếu có
            String base64Image = this.image.replaceAll("\\s+", "");
            byte[] byteArray = Base64.getDecoder().decode(base64Image);

            // Sử dụng ByteBuffer.allocateDirect để tạo ByteBuffer
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(byteArray.length);
            byteBuffer.put(byteArray);
            byteBuffer.flip(); // Chuẩn bị để đọc bộ đệm
            return byteBuffer;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Failed to decode base64 string", e);
        }
    }

}
