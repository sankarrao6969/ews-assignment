package lk.ac.cmb.ucsc.mcs.ewa;

import org.apache.cxf.Bus;
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
import org.springframework.context.annotation.ImportResource;

import lk.ac.cmb.ucsc.mcs.ewa.service.ChannelServiceImpl;
import lk.ac.cmb.ucsc.mcs.ewa.service.DoctorServiceImpl;

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

    // Replaces cxf-servlet.xml
    @Bean
    // <jaxws:endpoint id="doctorServiceEndpoint"
    // implementor="lk.ac.cmb.ucsc.mcs.ewa.service.DoctorServiceImpl.DoctorServiceImpl" address="/DoctorService"/>
    public EndpointImpl doctorService() {
        EndpointImpl endpoint = createNewEndpoint(new DoctorServiceImpl());
        endpoint.publish("/DoctorService");
        return endpoint;
    }

    @Bean
    public EndpointImpl channelService() {
        EndpointImpl endpoint = createNewEndpoint(new ChannelServiceImpl());
        endpoint.publish("/ChannelService");
        return endpoint;
    }

    private EndpointImpl createNewEndpoint(Object implementor) {
        Bus bus = (Bus) applicationContext.getBean(Bus.DEFAULT_BUS_ID);
        applicationContext.getAutowireCapableBeanFactory().autowireBean(implementor);
        EndpointImpl endpoint = new EndpointImpl(bus, implementor);
        return endpoint;
    }
}
