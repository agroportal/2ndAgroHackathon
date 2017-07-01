package io.github.agroportal.data;


import io.github.agroportal.api.data.CascadeFieldMatcher;
import io.github.agroportal.api.data.Field;
import io.github.agroportal.api.data.FieldMatcher;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CascadeFieldMatcherImpl implements CascadeFieldMatcher {

    private final List<FieldMatcher> matchers;

    public CascadeFieldMatcherImpl() {
        matchers = new ArrayList<>();
    }

    @Override
    public void addFieldMatcher(final FieldMatcher fieldMatcher) {
        matchers.add(fieldMatcher);
    }

    @Override
    public boolean accept(final Field field) {
        final Iterator<FieldMatcher> matcherIterator = matchers.iterator();
        boolean matched = false;
        while (matcherIterator.hasNext() && !matched){
            final FieldMatcher fieldMatcher = matcherIterator.next();
            matched = fieldMatcher.accept(field);
        }

        return matched;
    }
}
