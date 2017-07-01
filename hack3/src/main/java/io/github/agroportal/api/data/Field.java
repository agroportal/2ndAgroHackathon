package io.github.agroportal.api.data;

import java.util.Collection;

public interface Field extends Iterable<String>{
    String getName();
    String generateCorpus();
    Collection<String> getInstances();
    Collection<String> getTypes();
    void addType(String type);
    boolean isMatched();
}
