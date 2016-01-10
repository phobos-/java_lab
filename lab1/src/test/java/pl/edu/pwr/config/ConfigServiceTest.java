package pl.edu.pwr.config;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

/**
 * Created by pawel on 30.10.15.
 */
@RunWith(MockitoJUnitRunner.class)
public class ConfigServiceTest {

    public static final String URL = "www.google.com";
    public static final String EXISTING = "key";
    public static final String NON_EXISTING = "non-exist";

    @Mock
    private Properties p;

    private ConfigService service;

    @Before
    public void setUp() throws Exception {
        service = new ConfigService(p);
    }

    @Test
    public void shouldReturnNullForNonExistingProperty() {
        assertNull(service.getProperty(NON_EXISTING));
    }

    @Test
    public void shouldReturnValidProperty() {
        when(p.getProperty(EXISTING)).thenReturn(URL);
        assertEquals(URL, service.getProperty(EXISTING));
    }

}