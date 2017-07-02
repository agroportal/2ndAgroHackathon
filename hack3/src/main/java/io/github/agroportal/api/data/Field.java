package io.github.agroportal.api.data;

import java.util.Collection;

public interface Field extends Iterable<String>{
    Dataset getDataset();
    String getName();
    String generateCorpus();
    String generateCorpus(int length);
    Collection<String> getInstances();
    Collection<String> getTypes();
    void addType(String type);
    void addInstance(String instance);
    boolean isMatched();
    void setMatched(boolean matched);
}
