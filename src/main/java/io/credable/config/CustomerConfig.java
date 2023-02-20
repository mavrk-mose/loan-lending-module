package io.credable.config;

import javax.xml.bind.JAXBException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;

import io.credable.services.CustomerClient;

@Configuration
public class CustomerConfig {

    //pass authentication to request payload
    @Bean
    Wss4jSecurityInterceptor securityInterceptor() {
        Wss4jSecurityInterceptor security = new Wss4jSecurityInterceptor();
        security.setSecurementActions("UsernameToken");
        security.setSecurementUsername("admin");
        security.setSecurementPassword("pwd123");
        security.setSecurementPasswordType("PasswordText");
        security.setSecurementMustUnderstand(true);
        return security;
    }

    @Bean
    Jaxb2Marshaller marshaller() throws JAXBException {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("io.credable.data.external.customer");
        //JAXBContext context = JAXBContext.newInstance(CustomerRequest.class);
        return marshaller;
    }


    @Bean
    CustomerClient CustomerClient(Jaxb2Marshaller marshaller) {

        CustomerClient customerClient = new CustomerClient();
        customerClient.setDefaultUri("https://kycapitest.credable.io/service/customer");
        customerClient.setMarshaller(marshaller);
        customerClient.setUnmarshaller(marshaller);
        ClientInterceptor[] interceptor = new ClientInterceptor[]{securityInterceptor(), new SoapInterceptor("")};
        customerClient.setInterceptors(interceptor);
        return customerClient;

    }

}
