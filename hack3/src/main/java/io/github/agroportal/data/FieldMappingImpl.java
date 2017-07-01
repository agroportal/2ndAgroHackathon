package io.github.agroportal.data;

import io.github.agroportal.api.data.Field;
import io.github.agroportal.api.data.FieldMapping;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class FieldMappingImpl implements FieldMapping {

    private final Set<Field> fields;

    private final double confidence;

    public FieldMappingImpl(final Set<Field> fields, final double confidence) {
        this.fields = new HashSet<>(fields);
        this.confidence = confidence;
    }

    @Override
    public Set<Field> getFields() {
        return Collections.unmodifiableSet(fields);
    }

    @Override
    public double getConfidence() {
        return confidence;
    }


}
