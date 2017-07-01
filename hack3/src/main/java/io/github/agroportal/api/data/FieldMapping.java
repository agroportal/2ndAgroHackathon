package io.github.agroportal.api.data;

import java.util.Set;

public interface FieldMapping {
    Set<Field> getFields();
    double getConfidence();
}
