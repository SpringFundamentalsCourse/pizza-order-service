package api.autoconfiguration;

import api.CustomerMasterDataClient;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@ConditionalOnMissingBean(CustomerMasterDataClient.class)
@ConditionalOnProperty(prefix = "custom.customerMasterData", name = "enabled", havingValue = "true")
public class CustomerMasterDataAutoConfiguration {

    @Bean
    public CustomerMasterDataClient registerBean(){
        System.out.println("Creating Customer Master Client from Auto Configuration");
        return new CustomerMasterDataClient();
    }
}
