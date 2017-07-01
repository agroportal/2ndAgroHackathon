package io.github.agroportal.data;

import io.github.agroportal.api.data.Field;

import java.util.*;

public class AgroApiField implements Field {

    private final List<String> instances;
    private final List<String> types;
    private boolean matched;

    private final String name;

    public AgroApiField(final String name) {
        instances = new ArrayList<>();
        types = new ArrayList<>();
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String generateCorpus() {
        final StringBuilder builder = new StringBuilder();
        for (final String instance : instances) {
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
    public void addInstance(final String instance) {
        instances.add(instance);
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
