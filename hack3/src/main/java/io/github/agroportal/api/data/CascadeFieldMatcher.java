package io.github.agroportal.api.data;

public interface CascadeFieldMatcher extends FieldMatcher{
    void addFieldMatcher(FieldMatcher fieldMatcher);
}
