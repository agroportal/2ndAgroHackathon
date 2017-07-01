package io.github.agroportal.data;

import io.github.agroportal.api.data.Dataset;
import io.github.agroportal.api.data.Field;
import io.github.agroportal.api.data.FieldMatcher;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AgroAPIDataset implements Dataset {

    private final List<Field> fields;

    public AgroAPIDataset() {
        fields = new ArrayList<>();
    }

    @Override
    public void consume(final FieldMatcher fieldMatcher) {
        for(final Field field: fields){
            if(!field.isMatched()) {
                fieldMatcher.accept(field);
            }
        }
    }

    @Override
    public void parseHeader(final String header) {
        final String[] fields = header.split(";");
    }

    @Override
    public void parseLine(final String line) {

    }

    @Override
    public Iterator<Field> iterator() {
        return fields.iterator();
    }
}
