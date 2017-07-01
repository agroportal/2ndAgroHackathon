package io.github.agroportal.data;


import io.github.agroportal.api.data.Dataset;
import io.github.agroportal.api.data.DatasetExtractor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class AgroAPIDatasetExtractor implements DatasetExtractor {

    private final String apiUrl;


    public AgroAPIDatasetExtractor(final String apiUrl) {
        this.apiUrl = apiUrl;
    }

    @SuppressWarnings({"HardcodedFileSeparator", "LawOfDemeter"})
    @Override
    public Dataset extract(final String datasetName) throws IOException {
        final String downloadURL = String.format("%s/explore/dataset/%s/download/?format=csv&use_labels_for_header=true", apiUrl, datasetName);

        final URL csvUrl = new URL(downloadURL);
        final URLConnection conn = csvUrl.openConnection();
        final Dataset agroDataset = new AgroAPIDataset();

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            final String firstLine = bufferedReader.readLine();
            agroDataset.parseHeader(firstLine);
            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                agroDataset.parseLine(line);
            }
        }
        return agroDataset;
    }


    //explore/dataset/liste-des-giee-labellises/download/?format=csv&timezone=Europe/Berlin&use_labels_for_header=true

}
