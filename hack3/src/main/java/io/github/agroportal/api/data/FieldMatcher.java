package io.github.agroportal.api.data;


import java.util.function.Consumer;

@FunctionalInterface
interface FieldMatcher extends Consumer<Field> {

}
