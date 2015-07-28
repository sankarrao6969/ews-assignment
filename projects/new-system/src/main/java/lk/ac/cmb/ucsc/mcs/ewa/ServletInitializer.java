package lk.ac.cmb.ucsc.mcs.ewa;

import java.text.SimpleDateFormat;

import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import lk.ac.cmb.ucsc.mcs.ewa.service.PatientService;

@Configuration
@EnableAutoConfiguration
@ImportResource({ "classpath:META-INF/cxf/cxf.xml" })
public class ServletInitializer extends SpringBootServletInitializer {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(EwaAssignmentApplication.class);
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean(ApplicationContext context) {
        return new ServletRegistrationBean(new CXFServlet(), "/api/*");
    }

    @Bean
    public Server rsServer(PatientService patientService) {
        Server server = createEndpoint(patientService).create();
        LoggingInInterceptor loggingInInterceptor = new LoggingInInterceptor();
        LoggingOutInterceptor loggingOutInterceptor = new LoggingOutInterceptor();
        server.getEndpoint().getInInterceptors().add(loggingInInterceptor);
        server.getEndpoint().getOutInterceptors().add(loggingOutInterceptor);
        return server;
    }

    private JAXRSServerFactoryBean createEndpoint(PatientService patientService) {
        Bus bus = (Bus) applicationContext.getBean(Bus.DEFAULT_BUS_ID);
        JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
        endpoint.setServiceBean(patientService);
        // Convert objects to JSON
        JacksonJaxbJsonProvider jacksonJaxbJsonProvider = new JacksonJaxbJsonProvider();
        ObjectMapper objectMapper = new ObjectMapper();
        // Set date format for java.util.Date
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        jacksonJaxbJsonProvider.setMapper(objectMapper);
        endpoint.setProvider(jacksonJaxbJsonProvider);
        endpoint.setAddress("/");
        endpoint.setBus(bus);
        return endpoint;
    }

}