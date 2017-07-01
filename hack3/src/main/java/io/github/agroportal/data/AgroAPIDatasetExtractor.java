package io.github.agroportal.data;


import io.github.agroportal.api.data.Dataset;
import io.github.agroportal.api.data.DatasetExtractor;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.logging.log4j.core.util.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class AgroAPIDatasetExtractor implements DatasetExtractor{

    private final String downloadURL;

    @SuppressWarnings("HardcodedFileSeparator")
    public AgroAPIDatasetExtractor(final String apiUrl, final String datasetName) {
        downloadURL = String.format("%s/explore/dataset/%s/download/?format=csv&use_labels_for_header=true",apiUrl,datasetName);
    }

    @Override
    public Dataset extract() {
        String out = null;
        try {
            out = new Scanner(new URL(downloadURL).openStream(), "UTF-8").useDelimiter("\\A").next();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(out);

        return null;
    }


    //explore/dataset/liste-des-giee-labellises/download/?format=csv&timezone=Europe/Berlin&use_labels_for_header=true

}
