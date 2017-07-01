package io.github.agroportal.data;


import io.github.agroportal.api.data.Dataset;
import io.github.agroportal.api.data.DatasetExtractor;

public class AgroAPIDatasetExtractor implements DatasetExtractor{


    private final String downloadURL;

    @SuppressWarnings("HardcodedFileSeparator")
    public AgroAPIDatasetExtractor(final String apiUrl, final String datasetName) {
        downloadURL = String.format("%s/explore/dataset/%s/download/?format=csv&use_labels_for_header=true",apiUrl,datasetName);
    }

    @Override
    public Dataset extract() {
        return null;
    }


    //explore/dataset/liste-des-giee-labellises/download/?format=csv&timezone=Europe/Berlin&use_labels_for_header=true

}
