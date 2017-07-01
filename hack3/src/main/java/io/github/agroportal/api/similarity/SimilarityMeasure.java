package io.github.agroportal.api.similarity;


@FunctionalInterface
public interface SimilarityMeasure {
    double compute(String first, String second);
}
