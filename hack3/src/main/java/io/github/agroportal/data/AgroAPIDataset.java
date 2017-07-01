package io.github.agroportal.data;

import io.github.agroportal.api.data.Dataset;
import io.github.agroportal.api.data.Field;
import io.github.agroportal.api.data.FieldMatcher;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AgroAPIDataset implements Dataset {

    private final List<Field> fields;
    private final String name;

    AgroAPIDataset(final String name) {
        fields = new ArrayList<>();
        this.name = name;
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
            final Field fieldInstance = new AgroApiField(field, this);
            fields.add(fieldInstance);
        }
    }

    @Override
    public void parseLine(final String line) {
        if(line!=null) {
            final String[] split = line.split(";");
            if(split.length==fields.size()) {
                for (int i = 0; i < fields.size(); i++) {
                    fields.get(i).addInstance(split[i]);
                }
            }
        }
    }

    @Override
    public Iterator<Field> iterator() {
        return fields.iterator();
    }

    @Override
    public String getName() {
        return name;
    }
}
