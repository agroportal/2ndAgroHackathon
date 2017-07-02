package io.github.agroportal.data;

import io.github.agroportal.api.data.Field;
import io.github.agroportal.api.data.FieldMatcher;
import org.json.simple.parser.ParseException;
import org.sifrproject.annotations.api.input.AnnotationParser;
import org.sifrproject.annotations.api.model.Annotation;
import org.sifrproject.annotations.api.model.AnnotationFactory;
import org.sifrproject.annotations.exceptions.InvalidFormatException;
import org.sifrproject.annotations.exceptions.NCBOAnnotatorErrorException;
import org.sifrproject.annotations.input.BioPortalJSONAnnotationParser;
import org.sifrproject.annotations.model.BioPortalLazyAnnotationFactory;
import org.sifrproject.annotatorclient.BioportalAnnotatorFactory;
import org.sifrproject.annotatorclient.BioportalAnnotatorQueryBuilder;
import org.sifrproject.annotatorclient.api.BioPortalAnnotator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextFieldMatcher implements FieldMatcher {

    private static final String PRODUCTION_KEY = "2ae951bf-2d83-42a7-944a-295a843d3307";
    @SuppressWarnings("HardcodedLineSeparator")
    private static final Pattern SPECIAL_CHARS = Pattern.compile("[\t\n\r]");

    private final Logger logger = LoggerFactory.getLogger(TextFieldMatcher.class);

    @Override
    public boolean accept(final Field field) {
        final String corpus = field.generateCorpus();
        final BioPortalAnnotator annotator = BioportalAnnotatorFactory.createDefaultAnnotator("http://services.agroportal.lirmm.fr/annotator/", PRODUCTION_KEY);
        final Matcher matcher = SPECIAL_CHARS.matcher(corpus);
        final BioportalAnnotatorQueryBuilder queryBuilder = BioportalAnnotatorQueryBuilder.DEFAULT
                .text(matcher.replaceAll(" ").trim()).expand_mappings(true)
                .lemmatize(false).ontologies("CROPUSAGE-FR");//.recognizer("unitex");
        try {
            final String annotationOutput = annotator.runQuery(queryBuilder.build());
            final AnnotationFactory annotationFactory = new BioPortalLazyAnnotationFactory();
            final AnnotationParser parser = new BioPortalJSONAnnotationParser(annotationFactory);
            try {
                final List<Annotation> annotationList = parser.parseAnnotations(annotationOutput);
                for(final Annotation annotation: annotationList){
                    field.addType(annotation.getAnnotatedClass().getType());
                }
            } catch (final ParseException | NCBOAnnotatorErrorException | InvalidFormatException e) {
                logger.error(e.getLocalizedMessage());
            }
        } catch (final IOException e) {
            logger.error(e.getLocalizedMessage());
        }
        return true;
    }
}
