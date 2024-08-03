package vn.kma.OcrSerach.model;

import com.datastax.oss.driver.api.mapper.annotations.*;
import com.datastax.oss.driver.api.mapper.entity.naming.NamingConvention;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(defaultKeyspace = "kma")
@NamingStrategy(convention = NamingConvention.CASE_INSENSITIVE)
@CqlName("forms")
public class Form {
    @PartitionKey
    private String id;
    @CqlName("type")
    private String type;
}
