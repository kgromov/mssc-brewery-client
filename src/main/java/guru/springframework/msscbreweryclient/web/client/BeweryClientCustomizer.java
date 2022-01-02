package guru.springframework.msscbreweryclient.web.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class BeweryClientCustomizer implements RestTemplateCustomizer {
    private final ClientHttpRequestFactory clientHttpRequestFactory;
    @Autowired private ClientSettings clientSettings;

    @PostConstruct
    public void init() {
        System.out.println(clientSettings);
    }

    @Override
    public void customize(RestTemplate restTemplate) {
        restTemplate.setRequestFactory(clientHttpRequestFactory);
    }
}
