package com.vranec;

import com.vranec.model.csv.Result;
import com.vranec.model.csv.ResultProvider;
import lombok.SneakyThrows;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;

public class CsvExporter {

    @SneakyThrows
    public void export(ResultProvider resultProvider) {
        var csvFormat = CSVFormat.EXCEL.builder().setAutoFlush(true).build();

        try (var printer = new CSVPrinter(new FileWriter("results.csv"), csvFormat)) {
            printer.printRecord("Name");

            for (Result result : resultProvider.getResults()) {
                printer.print(result.getName().trim());

                printer.println();
            }
        }
    }
}
