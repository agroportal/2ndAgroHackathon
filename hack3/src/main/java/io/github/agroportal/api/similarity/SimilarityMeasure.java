package io.github.agroportal.api.similarity;


import java.util.List;

public interface SimilarityMeasure {
    double compute(String first, String second);
    double compute(final List<String> first, final List<String> second);
}
