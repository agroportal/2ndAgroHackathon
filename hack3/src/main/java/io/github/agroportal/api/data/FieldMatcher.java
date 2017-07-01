package io.github.agroportal.api.data;


@FunctionalInterface
public interface FieldMatcher{
    boolean accept(Field field);
}
