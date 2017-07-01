package io.github.agroportal.api.data;

import java.util.function.Supplier;

public interface Dataset extends Iterable<Supplier<Field>> {
    void consume (FieldMatcher fieldMatcher);
}
