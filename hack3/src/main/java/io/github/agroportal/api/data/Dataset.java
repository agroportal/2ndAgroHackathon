package io.github.agroportal.api.data;

public interface Dataset extends Iterable<Field> {
    void consume (FieldMatcher fieldMatcher);
    void parseHeader(String header);
    void parseLine(String line);
}
