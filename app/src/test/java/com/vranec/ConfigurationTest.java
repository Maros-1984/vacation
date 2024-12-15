package com.vranec;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationTest {

    @Test
    void loadConfiguration_loadsConfigurationFromJsonFile() {
        var configuration = new Configuration();

        assertThat(configuration.getListings()).contains(
                "https://www.fischer.cz/api/searchapi/getsearchresult?"
                        + "ac1=2&d=63220%7C63281%7C63311%7C63314%7C63316%7C63319%7C63324%7C63333%7C63341%"
                        + "7C63362%7C63390%7C63402%7C63408%7C63409%7C63442%7C63471&dd=2025-07-18&ea=349&"
                        + "ka1=7%7C9&kc1=2&m=5&ms=0&nn=7%7C8&pt=200000&qf=109_0_200%7C386_1_0%7C108_1_0&"
                        + "rd=2025-08-24&sortby=Rating.TripAdvisor&sortorder=1&to=4312&tt=1&sc=residential");
    }
}
