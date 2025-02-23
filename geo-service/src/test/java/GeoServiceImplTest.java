import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {
    private GeoServiceImpl geoService = new GeoServiceImpl();

    @Test
    void testByIpRussianSegment() {
        Location location = geoService.byIp("172.0.0.1");
        assertEquals(Country.RUSSIA, location.getCountry());
    }

    @Test
    void testByIpAmericanSegment() {
        Location location = geoService.byIp("96.0.0.1");
        assertEquals(Country.USA, location.getCountry());
    }

    @Test
    void testByIpLocalhost() {
        Location location = geoService.byIp("127.0.0.1");
        assertNull(location.getCountry());
    }
}
