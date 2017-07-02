package io.github.agroportal.data;

import io.github.agroportal.api.data.Dataset;
import io.github.agroportal.api.data.DatasetReconciler;
import io.github.agroportal.api.data.Field;
import io.github.agroportal.api.data.FieldMapping;
import io.github.agroportal.similarity.TverskiIndex;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class AgroApiDatasetReconciler implements DatasetReconciler {

    private final List<Dataset> datasets;
    private TverskiIndex tversky;
    private double threshold;

    public AgroApiDatasetReconciler(final double threshold) {
        datasets = new ArrayList<>();
        tversky = new TverskiIndex(1, 0, 0, false, true);
        this.threshold = threshold;
    }

    @Override
    public void addDataset(final Dataset dataset) {
        datasets.add(dataset);

    }


    @Override
    public Set<FieldMapping> reconcile() {
        final Set<FieldMapping> result = new HashSet<>();
        
        for(final Dataset dataset : datasets) {
            for(final Dataset dataset2: datasets) {
                if(dataset != dataset2) {
                    for (final Field field1 : dataset) {
                        for (final Field field2 : dataset2) {
                            final List<String> field1Content = new ArrayList<>(field1.getTypes());
                            final List<String> field2Content = new ArrayList<>(field2.getTypes());
                            final double sim = tversky.compute(field1Content, field2Content);

                            if (sim >= threshold) {
                                final Set<Field> associatedFields = new HashSet<>();
                                associatedFields.add(field1);
                                associatedFields.add(field2);
                                final FieldMapping mapping = new FieldMappingImpl(associatedFields, sim);
                                result.add(mapping);
                            }
                        }
                    }
                }
            }
        }
        
        return result;
    }
}
