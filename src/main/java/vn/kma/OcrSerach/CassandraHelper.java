package vn.kma.OcrSerach;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.metadata.schema.TableMetadata;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CassandraHelper {
    private CqlSession session;

    public void connect(String contactPoint, int port, String datacenter, String username, String password) {
        session = CqlSession.builder()
                .addContactPoint(new InetSocketAddress(contactPoint, port))
                .withAuthCredentials(username, password)
                .withLocalDatacenter(datacenter)
                .build();
    }

    public void close() {
        if (session != null) {
            session.close();
        }
    }

    public CompletableFuture<List<String>> getTables(String keyspace) {
        return CompletableFuture.supplyAsync(() -> {
            List<String> tables = new ArrayList<>();
            session.getMetadata().getKeyspace(CqlIdentifier.fromCql(keyspace)).ifPresent(ks -> {
                for (TableMetadata table : ks.getTables().values()) {
                    tables.add(table.getName().asInternal());
                }
            });
            return tables;
        });
    }
}
