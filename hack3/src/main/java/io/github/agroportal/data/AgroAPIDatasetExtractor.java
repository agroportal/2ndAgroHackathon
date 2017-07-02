package io.github.agroportal.data;


import io.github.agroportal.api.data.Dataset;
import io.github.agroportal.api.data.DatasetExtractor;
import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class AgroAPIDatasetExtractor implements DatasetExtractor {

    private final String apiUrl;
    private final Jedis jedis;


    public AgroAPIDatasetExtractor(final String apiUrl, final Jedis jedis) {
        this.apiUrl = apiUrl;
        this.jedis = jedis;
    }

    @SuppressWarnings({"HardcodedFileSeparator", "LawOfDemeter"})
    @Override
    public Dataset extract(final String datasetName) throws IOException {
        final String downloadURL = String.format("%s/explore/dataset/%s/download/?format=csv&use_labels_for_header=true", apiUrl, datasetName);

        final String datasetKey = String.format("key_dataset_%s",datasetName);

        final URL csvUrl = new URL(downloadURL);
        final URLConnection conn = csvUrl.openConnection();
        final Dataset agroDataset = new AgroAPIDataset(datasetName);

        //agroDataset.parseHeader(firstLine);
        //agroDataset.parseLine(line);

        final StringBuilder builder = new StringBuilder();
        if (jedis.exists(datasetKey)) {
            builder.append(jedis.get(datasetKey));
        } else {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                final String firstLine = bufferedReader.readLine();
                builder.append(firstLine).append(System.lineSeparator());
                String line = "";
                while (line != null) {
                    line = bufferedReader.readLine();
                    builder.append(line).append(System.lineSeparator());
                }
            }
            jedis.set(datasetKey,builder.toString());
        }
        final String[] datasetLines = builder.toString().split(System.lineSeparator());

        agroDataset.parseHeader(datasetLines[0]);
        for(int i=1; i< datasetLines.length;i++) {
            agroDataset.parseLine(datasetLines[i]);
        }
        return agroDataset;
    }


    //explore/dataset/liste-des-giee-labellises/download/?format=csv&timezone=Europe/Berlin&use_labels_for_header=true

}
