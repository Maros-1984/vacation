package com.vranec;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationTest {

    @Test
    void loadConfiguration_loadsConfigurationFromJsonFile() {
        var configuration = new Configuration();

        assertThat(configuration.getListings()).contains(
                "https://www.fischer.cz/vysledky-vyhledavani"
                        + "?ac1=2&d=63220|63281|63311|63314|63316|63319|63324|63333|63341|63362|"
                        + "63390|63402|63408|63409|63442|63471&dd=2025-07-18&ea=349&ka1=7|9&kc1=2"
                        + "&m=5&ms=0&nn=7|8&pt=200000&qf=109_0_200|386_1_0|108_1_0&rd=2025-08-24&"
                        + "sortby=Rating.TripAdvisor&sortorder=1&to=4312&tt=1");
    }
}
