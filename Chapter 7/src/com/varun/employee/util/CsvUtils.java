package com.varun.employee.util;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import com.opencsv.CSVReader;

/**
 * @author Varun
 */
public final class CsvUtils {

    public static List<String[]> parseCsv(Reader reader) throws IOException {

        final CSVReader csvReader = new CSVReader(reader);
        return csvReader.readAll();

    }
}
