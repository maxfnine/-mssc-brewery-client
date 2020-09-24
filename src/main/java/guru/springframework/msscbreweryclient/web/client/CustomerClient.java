package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
public class CustomerClient {
    public final String CUSTOMER_PATH_V1="/api/v1/customer/";

    @Value("${sfg.brewery.apihost}")
    private String apiHost;
    private final RestTemplate restTemplate;
    public void setApiHost(String apihost){
        this.apiHost=apihost;
    }

    public CustomerClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate= restTemplateBuilder.build();
    }

    public CustomerDto getCustomerById(UUID uuid){
        return restTemplate.getForObject(apiHost+CUSTOMER_PATH_V1+uuid.toString(),CustomerDto.class);
    }

    public URI saveNewCustomer(CustomerDto customerDto){
        return restTemplate.postForLocation(apiHost+CUSTOMER_PATH_V1,customerDto);
    }

    public  void updateCustomer(UUID uuid,CustomerDto customerDto){
        restTemplate.put(apiHost+CUSTOMER_PATH_V1+uuid.toString(),customerDto);
    }

    public void deleteCustomer(UUID uuid){
        restTemplate.delete(apiHost+CUSTOMER_PATH_V1+uuid.toString());
    }

}
