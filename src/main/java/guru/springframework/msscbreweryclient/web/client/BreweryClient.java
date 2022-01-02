package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.config.ClientSettings;
import guru.springframework.msscbreweryclient.web.model.BeerDto;
import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
public class BreweryClient {
    private final String beerPathV1;
    private final String customerPathV1;
    private final RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder, ClientSettings clientSetting) {
        this.restTemplate = restTemplateBuilder.build();
        this.beerPathV1 = clientSetting.getBreweryApiHost() + clientSetting.getBeerEndpoint();
        this.customerPathV1 = clientSetting.getBreweryApiHost() + clientSetting.getCustomerEndpoint();
    }

    public BeerDto getBeerById(UUID uuid){
        return restTemplate.getForObject( beerPathV1 + uuid.toString(), BeerDto.class);
    }

    public URI saveNewBeer(BeerDto beerDto){
        return restTemplate.postForLocation( beerPathV1, beerDto);
    }

    public void updateBeer(UUID uuid, BeerDto beerDto){
        restTemplate.put( beerPathV1 + uuid, beerDto);
    }

    public void deleteBeer(UUID uuid){
        restTemplate.delete( beerPathV1 + uuid );
    }

    public CustomerDto getCustomerById(UUID customerId) {
        return restTemplate.getForObject(customerPathV1 + customerId.toString(), CustomerDto.class);
    }

    public URI saveNewCustomer(CustomerDto customerDto) {
        return  restTemplate.postForLocation( customerPathV1, customerDto);
    }

    public void updateCustomer(UUID customerId, CustomerDto customerDto) {
        restTemplate.put( customerPathV1 + customerId, customerDto);
    }

    public void deleteCustomer(UUID customerId) {
        restTemplate.delete( customerPathV1 + customerId);
    }
}