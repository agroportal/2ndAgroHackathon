package io.github.agroportal.data;

import io.github.agroportal.api.data.Field;
import io.github.agroportal.api.data.FieldMatcher;

public class TextFieldMatcher implements FieldMatcher {
    @Override
    public boolean accept(final Field field) {

        return true;
    }
}
