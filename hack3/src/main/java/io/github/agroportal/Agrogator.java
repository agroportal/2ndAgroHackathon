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

    public static void main(final String[] args )
    {
        final DatasetExtractor extractor = new AgroAPIDatasetExtractor("https://plateforme.api-agro.fr/");

        final Dataset dosesRef = extractor.extract("ift-20162017-doses-de-referencecibleculture/information/");

        final CascadeFieldMatcher fieldMatcher = new CascadeFieldMatcherImpl();
        fieldMatcher.addFieldMatcher(new TextFieldMatcher());
    }
}
