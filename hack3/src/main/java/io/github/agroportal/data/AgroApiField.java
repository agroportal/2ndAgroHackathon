package io.github.agroportal.data;

import io.github.agroportal.api.data.Dataset;
import io.github.agroportal.api.data.Field;

import java.util.*;

public class AgroApiField implements Field {

    private final List<String> instances;
    private final List<String> types;
    private boolean matched;

    private final String name;

    private final Dataset dataset;

    private Set<String> values;

    public AgroApiField(final String name, final Dataset dataset) {
        instances = new ArrayList<>();
        types = new ArrayList<>();
        this.name = name;
        this.dataset = dataset;
        values = new HashSet<>();
    }

    @Override
    public Dataset getDataset() {
        return dataset;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String generateCorpus() {
        values.addAll(instances);
        final StringBuilder builder = new StringBuilder();
        for (final String value : values) {
            builder.append(value).append(" ");
        }
        return builder.toString();
    }

    @Override
    public String generateCorpus(final int length) {
        final String corpus = generateCorpus();
        return corpus.substring(0, Math.min(corpus.length(), length));
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
