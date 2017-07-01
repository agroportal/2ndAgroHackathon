package io.github.agroportal;

import io.github.agroportal.api.data.CascadeFieldMatcher;
import io.github.agroportal.api.data.Dataset;
import io.github.agroportal.api.data.DatasetExtractor;
import io.github.agroportal.data.AgroAPIDatasetExtractor;
import io.github.agroportal.data.CascadeFieldMatcherImpl;
import io.github.agroportal.data.TextFieldMatcher;

/**
 * Hello world!
 *
 */
public class Agrogator
{
    private Agrogator() {
    }

    @SuppressWarnings("LawOfDemeter")
    public static void main(final String[] args )
    {
        final DatasetExtractor extractor = new AgroAPIDatasetExtractor("https://plateforme.api-agro.fr/");

        final Dataset dosesRef = extractor.extract("ift-20162017-doses-de-referencecibleculture");
        final Dataset surfRend = extractor.extract("surfaces-rendements-et-productivites-des-productions-vegetales");
        final Dataset cotations = extractor.extract("cotations-franceagrimer");

        final CascadeFieldMatcher fieldMatcher = new CascadeFieldMatcherImpl();
        fieldMatcher.addFieldMatcher(new TextFieldMatcher());

        dosesRef.consume(fieldMatcher);
        surfRend.consume(fieldMatcher);
        cotations.consume(fieldMatcher);



    }
}
