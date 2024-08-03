package vn.kma.OcrSerach.config;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import com.datastax.oss.driver.api.core.DefaultConsistencyLevel;
import com.datastax.oss.driver.api.core.config.DefaultDriverOption;
import com.datastax.oss.driver.api.core.config.DriverConfigLoader;
import com.datastax.oss.driver.api.core.config.ProgrammaticDriverConfigLoaderBuilder;
import com.datastax.oss.driver.internal.core.tracker.RequestLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import vn.kma.OcrSerach.repository.InventoryMapper;
import vn.kma.OcrSerach.repository.InventoryMapperBuilder;

import java.net.InetSocketAddress;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DSEConfig {
    @Value("#{'${spring.data.cassandra.contact-points}'.split(',')}")
    private List<String> hosts;

    @Value("${spring.data.cassandra.username}")
    private String username;

    @Value("${spring.data.cassandra.password}")
    private String password;

    @Value("${spring.data.cassandra.local-datacenter}")
    private String localDatacenter;

    @Value("${spring.data.cassandra.request.timeout:300}")
    private long timeout;

    @Value("${spring.data.cassandra.debug:false}")
    private boolean isDebug;


    private String getConsistencyCode(String consistency) throws Exception {
        final String consistencyFinal = consistency.toUpperCase();
        boolean isMatch = Arrays.stream(DefaultConsistencyLevel.values())
                .anyMatch(cl -> cl.name().equals(consistencyFinal));
        if (isMatch) return consistencyFinal;

        throw new Exception("Invalid consistency level " + consistency);
    }

    private DriverConfigLoader defaultLoader(String consistency) throws Exception {
        ProgrammaticDriverConfigLoaderBuilder loaderBuilder = DriverConfigLoader.programmaticBuilder();

        loaderBuilder.withString(DefaultDriverOption.REQUEST_CONSISTENCY,
                consistency == null ? DefaultConsistencyLevel.LOCAL_ONE.name() : getConsistencyCode(consistency));
        loaderBuilder.withString(DefaultDriverOption.REQUEST_SERIAL_CONSISTENCY, DefaultConsistencyLevel.LOCAL_SERIAL.name());
        loaderBuilder.withDuration(DefaultDriverOption.REQUEST_TIMEOUT, Duration.ofSeconds(timeout));
        loaderBuilder.withInt(DefaultDriverOption.CONNECTION_MAX_REQUESTS, 32768);
        loaderBuilder.withBoolean(DefaultDriverOption.METADATA_SCHEMA_ENABLED, false);

        if (isDebug) {
            loaderBuilder.withClassList(DefaultDriverOption.REQUEST_TRACKER_CLASSES, Collections.singletonList(RequestLogger.class));
            loaderBuilder.withBoolean(DefaultDriverOption.REQUEST_LOGGER_SUCCESS_ENABLED, true);
            loaderBuilder.withBoolean(DefaultDriverOption.REQUEST_LOGGER_SLOW_ENABLED, true);
            loaderBuilder.withBoolean(DefaultDriverOption.REQUEST_LOGGER_ERROR_ENABLED, true);
        }
        return loaderBuilder.build();
    }

    public CqlSessionBuilder cqlSessionDefault(List<String> contactPoints) {
        return CqlSession.builder()
                .addContactPoints(contactPoints.parallelStream().map(host -> host.split(":"))
                        .map(host -> new InetSocketAddress(host[0], Integer.parseInt(host[1])))
                        .collect(Collectors.toList()))
                .withAuthCredentials(username, password);
    }

    @Bean
    public InventoryMapper transInventoryMapper() throws Exception {
        return new InventoryMapperBuilder(cqlSessionDefault(hosts)
                .withConfigLoader(defaultLoader("local_one"))
                .withLocalDatacenter(localDatacenter)
                .build()).build();
    }
}
