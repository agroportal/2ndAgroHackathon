package io.github.agroportal.data;

import io.github.agroportal.api.data.Dataset;
import io.github.agroportal.api.data.DatasetReconciler;
import io.github.agroportal.api.data.Field;
import io.github.agroportal.api.data.FieldMapping;
import io.github.agroportal.similarity.TverskiIndex;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
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
        Set<FieldMapping> result = new HashSet<>();
        
        for(Dataset dataset : datasets) {
            for(Dataset dataset2: datasets) {
                if(dataset != dataset2) {
                    Iterator<Field> fieldIt = dataset.iterator();
                    while(fieldIt.hasNext()) {
                        Field field1 = fieldIt.next();
                        Iterator<Field> fieldIt2 = dataset.iterator();
                        
                        while(fieldIt2.hasNext()) {
                            Field field2 = fieldIt2.next();
                            List<String> field1Content = (List<String>) field1.getInstances();
                            List<String> field2Content = (List<String>) field2.getInstances();
                            double sim = tversky.compute(field1Content, field2Content);
                            
                            if(sim >= threshold) {
                                Set<Field> associatedFields = new HashSet<>();
                                associatedFields.add(field1);
                                associatedFields.add(field2);
                                FieldMapping mapping = new FieldMappingImpl(associatedFields, sim);
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
