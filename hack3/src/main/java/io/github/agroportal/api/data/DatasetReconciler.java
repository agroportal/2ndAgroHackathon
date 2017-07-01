package io.github.agroportal.api.data;


import java.util.Set;

public interface DatasetReconciler {
    void addDataset(Dataset dataset);
    Set<FieldMapping> reconcile();
}
