package lk.ac.cmb.ucsc.mcs.ewa;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lk.ac.cmb.ucsc.mcs.ewa.entity.ChannelRecord;
import lk.ac.cmb.ucsc.mcs.ewa.entity.Doctor;
import lk.ac.cmb.ucsc.mcs.ewa.entity.Speciality;
import lk.ac.cmb.ucsc.mcs.ewa.exception.ChannelRecordExistsException;
import lk.ac.cmb.ucsc.mcs.ewa.exception.DoctorNotFoundException;
import lk.ac.cmb.ucsc.mcs.ewa.exception.LoginException;
import lk.ac.cmb.ucsc.mcs.ewa.service.ChannelService;
import lk.ac.cmb.ucsc.mcs.ewa.service.DoctorService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = EwaAssignmentApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port=0")
public class EwaAssignmentApplicationTests {

    private DoctorService doctorService;

    private ChannelService channelService;

    @Value("${local.server.port}")
    private int serverPort;

    @Before
    public void setUp() {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        final String servicesUrl = "http://localhost:" + this.serverPort + "/services/";

        factory.setAddress(servicesUrl + "DoctorService");
        factory.setServiceClass(DoctorService.class);
        doctorService = (DoctorService) factory.create();

        factory.setAddress(servicesUrl + "ChannelService");
        factory.setServiceClass(ChannelService.class);
        channelService = (ChannelService) factory.create();
    }

    @Test
    public void testDoctorLogin() {
        String username = "ms123";
        String password = "123";
        try {
            doctorService.login(username, password);
        } catch (LoginException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testGetDoctors() {
        List<Doctor> doctors = doctorService.getDoctors();
        assertTrue(!doctors.isEmpty());
    }

    @Test
    public void testFindDoctorsByFirstName() {
        String firstName = "anura";
        List<Doctor> doctors = doctorService.findDoctorsByFirstName(firstName);
        assertTrue(!doctors.isEmpty());
        assertTrue("There is only one result", doctors.size() == 1);
        assertTrue("Name matches", doctors.get(0).getFirstName().equalsIgnoreCase(firstName));
    }

    @Test
    public void testFindDoctorsByLastName() {
        String lastName = "perera";
        List<Doctor> doctors = doctorService.findDoctorsByLastName(lastName);
        assertTrue(!doctors.isEmpty());
        assertTrue("There is only one result", doctors.size() == 1);
        assertTrue("Name matches", doctors.get(0).getLastName().equalsIgnoreCase(lastName));
    }

    @Test
    public void testFindDoctorsByFirstNameAndLastName() {
        String firstName = "anura";
        String lastName = "perera";
        List<Doctor> doctors = doctorService.findDoctorsByFirstNameAndLastName(firstName, lastName);
        assertTrue(!doctors.isEmpty());
        assertTrue("There is only one result", doctors.size() == 1);
        assertTrue("First Name matches", doctors.get(0).getFirstName().equalsIgnoreCase(firstName));
        assertTrue("Last Name matches", doctors.get(0).getLastName().equalsIgnoreCase(lastName));
    }

    @Test
    public void testFindDoctorsBySpeciality() {
        long specialityId = 1;
        List<Doctor> doctors = doctorService.findDoctorsBySpeciality(specialityId);
        assertTrue(!doctors.isEmpty());
        assertTrue("There is only one result", doctors.size() == 1);
    }

    @Test
    public void testGetSpecialities() {
        List<Speciality> specialities = doctorService.getSpecialities();
        assertTrue(!specialities.isEmpty());
    }

    @Test
    public void testFindSpecialities() {
        String speciality = "dental";
        List<Speciality> specialities = doctorService.findSpecialities(speciality);
        assertTrue("There are multiple results", specialities.size() >= 1);
    }

    @Test
    public void testCreateChannelRecord() {
        long doctorId = 1;
        String patientName = "Isuru";
        String date = "2015-07-27";
        String time = "18:30";

        try {
            ChannelRecord channelRecord = channelService.createChannelRecord(doctorId, patientName, date, time);
            assertNotNull("Channel Record Created", channelRecord);
        } catch (ChannelRecordExistsException e) {
            fail(e.getMessage());
        } catch (DoctorNotFoundException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testFindChannelRecordsOfDoctorOnDate() {
        long doctorId = 1;
        String patientName = "Isuru";
        String date = "2015-07-28";
        String time = "18:30";

        try {
            ChannelRecord channelRecord = channelService.createChannelRecord(doctorId, patientName, date, time);
            assertNotNull("Channel Record Created", channelRecord);
        } catch (ChannelRecordExistsException e) {
            fail(e.getMessage());
        } catch (DoctorNotFoundException e) {
            fail(e.getMessage());
        }

        try {
            List<ChannelRecord> channelRecords = channelService.findChannelRecordsOfDoctorOnDate(doctorId, date);
            assertTrue("There is one channel record", channelRecords.size() == 1);
        } catch (DoctorNotFoundException e) {
            fail(e.getMessage());
        }
    }
}
