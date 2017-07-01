package io.github.agroportal.data;

import io.github.agroportal.api.data.Dataset;
import io.github.agroportal.api.data.Field;
import io.github.agroportal.api.data.FieldMatcher;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AgroAPIDataset implements Dataset {

    private final List<Field> fields;

    AgroAPIDataset() {
        fields = new ArrayList<>();
    }

    @Override
    public void consume(final FieldMatcher fieldMatcher) {
        for (final Field field : fields) {
            if (!field.isMatched()) {
                fieldMatcher.accept(field);
            }
        }
    }

    @Override
    public void parseHeader(final String header) {
        final String[] split = header.split(";");
        for (final String field : split) {
            final Field fieldInstance = new AgroApiField(field);
            fields.add(fieldInstance);
        }
    }

    @Override
    public void parseLine(final String line) {
        final String[] split = line.split(";");
        for (int i = 0; i<fields.size();i++) {
            fields.get(i).addInstance(split[i]);
        }
    }

    @Override
    public Iterator<Field> iterator() {
        return fields.iterator();
    }
}
