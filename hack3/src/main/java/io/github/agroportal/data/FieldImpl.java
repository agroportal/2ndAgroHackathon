package io.github.agroportal.data;

import io.github.agroportal.api.data.Field;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class FieldImpl implements Field {

    private final List<String> instances;

    public FieldImpl() {
        instances = new ArrayList<>();
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String generateCorpus() {
        return null;
    }

    @Override
    public Collection<String> getInstances() {
        return null;
    }

    @Override
    public Collection<String> getTypes() {
        return null;
    }

    @Override
    public void addType(String type) {

    }

    @Override
    public boolean isMatched() {
        return false;
    }

    @Override
    public Iterator<String> iterator() {
        return instances.iterator();
    }
}
