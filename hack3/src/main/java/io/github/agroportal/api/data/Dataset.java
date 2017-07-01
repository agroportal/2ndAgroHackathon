package io.github.agroportal.api.data;

public interface Dataset extends Iterable<Field> {
    String getName();
    void consume (FieldMatcher fieldMatcher);
    void parseHeader(String header);
    void parseLine(String line);
}
