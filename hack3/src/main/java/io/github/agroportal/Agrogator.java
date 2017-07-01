package io.github.agroportal;

import io.github.agroportal.api.data.CascadeFieldMatcher;
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
        //https://plateforme.api-agro.fr/explore/dataset/liste-des-giee-labellises/download/?format=csv&timezone=Europe/Berlin&use_labels_for_header=true
        final DatasetExtractor extractor = new AgroAPIDatasetExtractor("https://plateforme.api-agro.fr/");


        final CascadeFieldMatcher fieldMatcher = new CascadeFieldMatcherImpl();
        fieldMatcher.addFieldMatcher(new TextFieldMatcher());
    }
}
