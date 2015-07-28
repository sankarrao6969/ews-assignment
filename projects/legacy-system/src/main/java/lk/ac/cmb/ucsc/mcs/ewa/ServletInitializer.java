package lk.ac.cmb.ucsc.mcs.ewa;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.ImportResource;

import lk.ac.cmb.ucsc.mcs.ewa.service.ChannelService;
import lk.ac.cmb.ucsc.mcs.ewa.service.DoctorService;

@Configuration
@EnableAutoConfiguration
@ImportResource({ "classpath:META-INF/cxf/cxf.xml", "classpath:META-INF/cxf/cxf-servlet.xml" })
public class ServletInitializer extends SpringBootServletInitializer {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(EwaAssignmentApplication.class);
    }

    // Replaces the need for web.xml
    @Bean
    public ServletRegistrationBean servletRegistrationBean(ApplicationContext context) {
        return new ServletRegistrationBean(new CXFServlet(), "/services/*");
    }

    @DependsOn("servletRegistrationBean")
    // Replaces cxf-servlet.xml
    @Bean
    // <jaxws:endpoint id="doctorServiceEndpoint"
    // implementor="lk.ac.cmb.ucsc.mcs.ewa.service.DoctorServiceImpl.DoctorServiceImpl" address="/DoctorService"/>
    public Endpoint doctorServiceEndpoint(DoctorService doctorService) {
        Endpoint endpoint = createNewEndpoint(doctorService);
        endpoint.publish("/DoctorService");
        return endpoint;
    }

    @Bean
    public Endpoint channelServiceEndpoint(ChannelService channelService) {
        Endpoint endpoint = createNewEndpoint(channelService);
        endpoint.publish("/ChannelService");
        return endpoint;
    }

    private Endpoint createNewEndpoint(Object implementor) {
        Bus bus = (Bus) applicationContext.getBean(Bus.DEFAULT_BUS_ID);
        EndpointImpl endpoint = new EndpointImpl(bus, implementor);
        LoggingInInterceptor loggingInInterceptor = new LoggingInInterceptor();
        loggingInInterceptor.setPrettyLogging(true);
        LoggingOutInterceptor loggingOutInterceptor = new LoggingOutInterceptor();
        loggingOutInterceptor.setPrettyLogging(true);
        endpoint.getInInterceptors().add(loggingInInterceptor);
        endpoint.getOutInterceptors().add(loggingOutInterceptor);
        return endpoint;
    }
}
