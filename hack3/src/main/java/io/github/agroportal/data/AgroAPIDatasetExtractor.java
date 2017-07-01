package io.github.agroportal.data;


import io.github.agroportal.api.data.Dataset;
import io.github.agroportal.api.data.DatasetExtractor;

public class AgroAPIDatasetExtractor implements DatasetExtractor{


    private final String apiUrl;


    public AgroAPIDatasetExtractor(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    @SuppressWarnings("HardcodedFileSeparator")
    @Override
    public Dataset extract(final String datasetName) {
        final String downloadURL = String.format("%s/explore/dataset/%s/download/?format=csv&use_labels_for_header=true",apiUrl,datasetName);
        return null;
    }


    //explore/dataset/liste-des-giee-labellises/download/?format=csv&timezone=Europe/Berlin&use_labels_for_header=true

}
