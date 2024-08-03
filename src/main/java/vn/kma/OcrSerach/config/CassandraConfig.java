package vn.kma.OcrSerach.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.kma.OcrSerach.CassandraHelper;

@Configuration
public class CassandraConfig {

    @Bean
    public CassandraHelper cassandraHelper() {
        CassandraHelper cassandraHelper = new CassandraHelper();
        cassandraHelper.connect("172.16.10.82", 9042, "DC_HDDT", "cassandra", "cassandra");
        return cassandraHelper;
    }
}
