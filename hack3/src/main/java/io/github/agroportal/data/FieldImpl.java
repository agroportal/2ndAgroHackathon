package io.github.agroportal.data;

import io.github.agroportal.api.data.Field;

import java.util.*;

public class FieldImpl implements Field {

    private final List<String> instances;
    private final List<String> types;
    private boolean matched;

    public FieldImpl() {
        instances = new ArrayList<>();
        types = new ArrayList<>();
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String generateCorpus() {
        final StringBuilder builder = new StringBuilder();
        for(final String instance: instances){
            builder.append(instance);
            builder.append(" ");
        }
        return builder.toString();
    }

    @Override
    public Collection<String> getInstances() {
        return Collections.unmodifiableList(instances);
    }

    @Override
    public Collection<String> getTypes() {
        return Collections.unmodifiableCollection(types);
    }

    @Override
    public void addType(final String type) {
        types.add(type);
    }

    @Override
    public boolean isMatched() {
        return matched;
    }

    @Override
    public void setMatched(final boolean matched) {
        this.matched = matched;
    }

    @Override
    public Iterator<String> iterator() {
        return instances.iterator();
    }
}
