import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MessageSenderImplTest {
    private GeoService geoService;
    private LocalizationService localizationService;
    private MessageSenderImpl messageSender;

    @BeforeEach
    void setUp() {
        geoService = Mockito.mock(GeoServiceImpl.class);
        localizationService = Mockito.mock(LocalizationServiceImpl.class);
        messageSender = new MessageSenderImpl(geoService, localizationService);
    }

    @Test
    void testSendRussianMessage() {
        // Arrange
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.0.0.1");

        when(geoService.byIp("172.0.0.1")).thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        // Act
        String result = messageSender.send(headers);

        // Assert
        assertEquals("Добро пожаловать", result);
        verify(geoService, times(1)).byIp("172.0.0.1");
        verify(localizationService, times(1)).locale(Country.RUSSIA);
    }

    @Test
    void testSendEnglishMessage() {
        // Arrange
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.0.0.1");

        when(geoService.byIp("96.0.0.1")).thenReturn(new Location("New York", Country.USA, null, 0));
        when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        // Act
        String result = messageSender.send(headers);

        // Assert
        assertEquals("Welcome", result);
        verify(geoService, times(1)).byIp("96.0.0.1");
        verify(localizationService, times(1)).locale(Country.USA);
    }
}


