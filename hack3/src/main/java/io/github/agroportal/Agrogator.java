package io.github.agroportal;

import io.github.agroportal.api.data.CascadeFieldMatcher;
import io.github.agroportal.api.data.Dataset;
import io.github.agroportal.api.data.DatasetExtractor;
import io.github.agroportal.data.AgroAPIDatasetExtractor;
import io.github.agroportal.data.CascadeFieldMatcherImpl;
import io.github.agroportal.data.TextFieldMatcher;

import java.io.IOException;

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


        try {
            final Dataset dosesRef = extractor.extract("ift-20162017-doses-de-referencecibleculture/information/");
        } catch (IOException e) {
            e.printStackTrace();
        }

        final CascadeFieldMatcher fieldMatcher = new CascadeFieldMatcherImpl();
        fieldMatcher.addFieldMatcher(new TextFieldMatcher());
    }
}
