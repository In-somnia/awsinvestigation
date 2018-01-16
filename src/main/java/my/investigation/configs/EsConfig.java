package my.investigation.configs;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "es")
public class EsConfig {
    private RestHighLevelClient client;
    private String host;
    private int port;
    private String scheme;

    @PostConstruct
    public void init() {
        client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));
    }

    @PreDestroy
    public void destroy() throws IOException {
        client.close();
    }

    @Bean
    public RestHighLevelClient getClient() {
        return client;
    }
}
