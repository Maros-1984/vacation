package com.vranec;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.assertj.core.api.Assertions.assertThat;

@WireMockTest(httpPort = 18080)
public class FischerDownloaderTest {

    private final String URL = new Configuration().getListings().getFirst();

    @Test
    void download_givenUrl_downloadsTheDocument() {
        stubFor(get(WireMock.anyUrl())
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("fischer.json")));

        var downloader = new FischerDownloader(URL);

        assertThat(downloader.getTours()).hasSizeGreaterThan(6);
    }
}
