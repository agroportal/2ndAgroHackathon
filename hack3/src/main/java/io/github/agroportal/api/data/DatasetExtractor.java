package io.github.agroportal.api.data;

import java.io.IOException;
import java.net.MalformedURLException;

@FunctionalInterface
public interface DatasetExtractor {
    Dataset extract(final String datasetName) throws IOException;
}
