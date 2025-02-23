import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {
    private LocalizationServiceImpl localizationService = new LocalizationServiceImpl();

    @Test
    void testLocaleRussia() {
        String result = localizationService.locale(Country.RUSSIA);
        assertEquals("Добро пожаловать", result);
    }

    @Test
    void testLocaleUSA() {
        String result = localizationService.locale(Country.USA);
        assertEquals("Welcome", result);
    }
}
