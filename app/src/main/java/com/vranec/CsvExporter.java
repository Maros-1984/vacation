package com.vranec;

import com.vranec.model.csv.Result;
import com.vranec.model.csv.ResultProvider;
import lombok.SneakyThrows;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.math.RoundingMode;

public class CsvExporter {

    @SneakyThrows
    public void export(ResultProvider resultProvider) {
        var csvFormat = CSVFormat.EXCEL.builder().setAutoFlush(true).build();

        try (var printer = new CSVPrinter(new FileWriter("results.csv"), csvFormat)) {
            printer.printRecord("Name", "Price (CZK)", "TripAdvisor Rating", "TripAdvisor Review Count", "Departure Time", "Arrival Time",
                    "Flight duration (min)", "Country", "County", "City", "Link");

            for (Result result : resultProvider.getUniqueSortedResults()) {
                printer.print(result.getName().trim());
                printer.print(result.getPriceCzk());
                printer.print(result.getTripAdvisorRating().setScale(2, RoundingMode.HALF_UP));
                printer.print(result.getTripAdvisorReviewCount());
                printer.print(result.getDepartureTime());
                printer.print(result.getArrivalTime());
                printer.print(result.getFlightDurationMinutes());
                printer.print(result.getCountry());
                printer.print(result.getCounty());
                printer.print(result.getCity());
                printer.print(result.getLink());

                printer.println();
            }
        }
    }
}
