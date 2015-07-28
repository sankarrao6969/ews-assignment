package lk.ac.cmb.ucsc.mcs.ewa;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import lk.ac.cmb.ucsc.mcs.ewa.dto.PatientChannel;
import lk.ac.cmb.ucsc.mcs.ewa.entity.Patient;
import lk.ac.cmb.ucsc.mcs.ewa.entity.PatientHistory;
import lk.ac.cmb.ucsc.mcs.ewa.exception.PatientHistoryExistsException;
import lk.ac.cmb.ucsc.mcs.ewa.exception.PatientNotFoundException;
import lk.ac.cmb.ucsc.mcs.ewa.service.NewSystemService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = EwaAssignmentApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port=0")
public class EwaAssignmentApplicationTests {

    private NewSystemService newSystemService;

    @Value("${local.server.port}")
    private int serverPort;

    @Before
    public void setUp() {
        final String servicesUrl = "http://localhost:" + this.serverPort + "/api/";
        List<Object> providers = new ArrayList<Object>();
        JacksonJaxbJsonProvider jacksonJaxbJsonProvider = new JacksonJaxbJsonProvider();
        providers.add(jacksonJaxbJsonProvider);
        newSystemService = JAXRSClientFactory.create(servicesUrl, NewSystemService.class, providers, true);
    }

    @Test
    public void testFindPatientsByFirstName() {
        String firstName = "isuru";
        List<Patient> patients = newSystemService.findPatientsByFirstName(firstName);
        assertTrue(!patients.isEmpty());
        assertTrue("There is only one result", patients.size() == 1);
        assertTrue("Name matches", patients.get(0).getFirstName().equalsIgnoreCase(firstName));
    }

    @Test
    public void testFindPatientsByLastName() {
        String lastName = "perera";
        List<Patient> patients = newSystemService.findPatientsByLastName(lastName);
        assertTrue(!patients.isEmpty());
        assertTrue("There is only one result", patients.size() == 1);
        assertTrue("Name matches", patients.get(0).getLastName().equalsIgnoreCase(lastName));
    }

    @Test
    public void testFindPatientsByFirstNameAndLastName() {
        String firstName = "isuru";
        String lastName = "perera";
        List<Patient> patients = newSystemService.findPatientsByFirstNameAndLastName(firstName, lastName);
        assertTrue(!patients.isEmpty());
        assertTrue("There is only one result", patients.size() == 1);
        assertTrue("First Name matches", patients.get(0).getFirstName().equalsIgnoreCase(firstName));
        assertTrue("Last Name matches", patients.get(0).getLastName().equalsIgnoreCase(lastName));
    }

    public PatientHistory createPatientHistory(long patientId, long channelId)
            throws PatientHistoryExistsException, PatientNotFoundException {
        PatientChannel patientChannel = new PatientChannel();
        patientChannel.setPatientId(patientId);
        patientChannel.setChannelId(channelId);
        return newSystemService.createPatientHistory(patientChannel);
    }

    @Test
    public void testCreatePatientHistory() {
        try {
            PatientHistory patientHistory = createPatientHistory(1, 1);
            assertNotNull("Patient History Created", patientHistory);
        } catch (PatientHistoryExistsException e) {
            fail(e.getMessage());
        } catch (PatientNotFoundException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testFindPatientHistoryList() {
        try {
            List<PatientHistory> patientHistoryList = newSystemService
                    .findPatientHistory(createPatientHistory(1, 2).getPatient().getId());
            assertTrue("Found results", patientHistoryList.size() >= 1);
        } catch (PatientHistoryExistsException e) {
            fail(e.getMessage());
        } catch (PatientNotFoundException e) {
            fail(e.getMessage());
        }

    }

    @Test
    public void testFindPatientHistory() {
        try {
            PatientHistory patientHistory = createPatientHistory(1, 3);
            assertTrue(patientHistory.equals(newSystemService.findPatientHistory(patientHistory.getPatient().getId(),
                    patientHistory.getChannelId())));
        } catch (PatientHistoryExistsException e) {
            fail(e.getMessage());
        } catch (PatientNotFoundException e) {
            fail(e.getMessage());
        }
    }
}
