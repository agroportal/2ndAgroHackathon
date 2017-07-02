package io.github.agroportal;

import io.github.agroportal.api.data.*;
import io.github.agroportal.data.AgroAPIDatasetExtractor;
import io.github.agroportal.data.AgroApiDatasetReconciler;
import io.github.agroportal.data.CascadeFieldMatcherImpl;
import io.github.agroportal.data.TextFieldMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.Set;

/**
 * Hello world!
 */
public final class Agrogator {
    private static final Logger logger = LoggerFactory.getLogger(Agrogator.class);
    private static final double THRESHOLD = 0.7d;

    private Agrogator() {
    }

    @SuppressWarnings("LawOfDemeter")
    public static void main(final String[] args) {
        try (Jedis jedis = new Jedis()) {

            final DatasetExtractor extractor = new AgroAPIDatasetExtractor("https://plateforme.api-agro.fr/", jedis);

            try {
                logger.info("Loading dataset...");
                final Dataset dosesRef = extractor.extract("ift-20162017-doses-de-referencecibleculture");
                logger.info("Loading dataset...");
                final Dataset surfRend = extractor.extract("surfaces-rendements-et-productivites-des-productions-vegetales");
                logger.info("Loading dataset...");
                final Dataset cotations = extractor.extract("cotations-franceagrimer");

                final CascadeFieldMatcher fieldMatcher = new CascadeFieldMatcherImpl();
                fieldMatcher.addFieldMatcher(new TextFieldMatcher());

                logger.info("Matching and annotating fields...");
                dosesRef.consume(fieldMatcher);
                logger.info("Matching and annotating fields...");
                surfRend.consume(fieldMatcher);
                logger.info("Matching and annotating fields...");
                cotations.consume(fieldMatcher);

                final DatasetReconciler reconciler = new AgroApiDatasetReconciler(THRESHOLD);
                reconciler.addDataset(dosesRef);
                reconciler.addDataset(surfRend);
                reconciler.addDataset(cotations);

                logger.info("Reconciling...");
                final Set<FieldMapping> mappings = reconciler.reconcile();

                final StringBuilder mappingString = new StringBuilder();
                mappingString.append("Mapping ").append(dosesRef.getName()).append(" ").append(surfRend.getName()).append(System.lineSeparator());
                for (final FieldMapping mapping : mappings) {
                    for (final Field field : mapping.getFields()) {
                        mappingString.append(field.getName());
                        mappingString.append("|");
                    }
                    mappingString.append(" Confidence ").append(mapping.getConfidence()).append(System.lineSeparator());
                }
                logger.info(mappingString.toString());

            } catch (final IOException e) {
                logger.error(e.getLocalizedMessage());
            }
        }

    }
}
