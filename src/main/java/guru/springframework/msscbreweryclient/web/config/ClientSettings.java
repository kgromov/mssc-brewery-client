package guru.springframework.msscbreweryclient.web.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientSettings {
    private String breweryApiHost;
    private String beerEndpoint;
    private String customerEndpoint;

    private int maxConnections;
    private int defaultConnections;
    private int requestTimeout;
    private int socketTimeout;
}
