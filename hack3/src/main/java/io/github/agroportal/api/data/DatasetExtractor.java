package io.github.agroportal.api.data;

@FunctionalInterface
public interface DatasetExtractor {
    Dataset extract();
}
