package guru.springframework.msscbreweryclient.web.client;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

@Configuration
public class ClientConfig {

    @Bean
    @ConfigurationProperties(prefix = "client.config")
    // Can be defined on pojo class and after Spring boot 2.2 it is added to context automatically
    public ClientSettings clientSettings() {
        return new ClientSettings();
    }

    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory(ClientSettings clientSettings) {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setDefaultMaxPerRoute(clientSettings.getDefaultConnections());
        connectionManager.setMaxTotal(clientSettings.getMaxConnections());

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(clientSettings.getRequestTimeout())
                .setSocketTimeout(clientSettings.getSocketTimeout())
                .build();

        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(requestConfig)
                .build();

        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }
}
