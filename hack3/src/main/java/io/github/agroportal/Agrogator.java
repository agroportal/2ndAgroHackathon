package io.github.agroportal;

import io.github.agroportal.api.data.CascadeFieldMatcher;
import io.github.agroportal.api.data.Dataset;
import io.github.agroportal.api.data.DatasetExtractor;
import io.github.agroportal.data.AgroAPIDatasetExtractor;
import io.github.agroportal.data.CascadeFieldMatcherImpl;
import io.github.agroportal.data.TextFieldMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public final class Agrogator
{
    private static final Logger logger = LoggerFactory.getLogger(Agrogator.class);

    private Agrogator() {
    }

    @SuppressWarnings("LawOfDemeter")
    public static void main(final String[] args )
    {
        final DatasetExtractor extractor = new AgroAPIDatasetExtractor("https://plateforme.api-agro.fr/");

        try {
            final Dataset dosesRef = extractor.extract("ift-20162017-doses-de-referencecibleculture");
            final Dataset surfRend = extractor.extract("surfaces-rendements-et-productivites-des-productions-vegetales");
            final Dataset cotations = extractor.extract("cotations-franceagrimer");

            final CascadeFieldMatcher fieldMatcher = new CascadeFieldMatcherImpl();
            fieldMatcher.addFieldMatcher(new TextFieldMatcher());

            dosesRef.consume(fieldMatcher);
            surfRend.consume(fieldMatcher);
            cotations.consume(fieldMatcher);



        } catch (final IOException e) {
            logger.error(e.getLocalizedMessage());
        }

    }
}
