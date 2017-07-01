package io.github.agroportal.data;


import io.github.agroportal.api.data.Dataset;
import io.github.agroportal.api.data.DatasetExtractor;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.logging.log4j.core.util.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class AgroAPIDatasetExtractor implements DatasetExtractor{

    private final String apiUrl;


    public AgroAPIDatasetExtractor(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    @SuppressWarnings("HardcodedFileSeparator")
    @Override
    public Dataset extract(final String datasetName) throws IOException {
        final String downloadURL = String.format("%s/explore/dataset/%s/download/?format=csv&use_labels_for_header=true",apiUrl,datasetName);

        URL csvUrl = new URL(downloadURL);
        URLConnection conn = csvUrl.openConnection();
        String line;
        boolean firstLine = true;
        AgroAPIDataset agroDataset = new AgroAPIDataset();

        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(conn.getInputStream()) );
        while( (line = bufferedReader.readLine()) != null )
        {
            if (firstLine) {
                agroDataset.parseHeader(line);
                firstLine = false;
            } else {
                agroDataset.parseLine(line);
            }
        }
        return agroDataset;
    }


    //explore/dataset/liste-des-giee-labellises/download/?format=csv&timezone=Europe/Berlin&use_labels_for_header=true

}
