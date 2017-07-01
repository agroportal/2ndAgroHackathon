package io.github.agroportal.data;

import io.github.agroportal.api.data.Dataset;
import io.github.agroportal.api.data.DatasetReconciler;
import io.github.agroportal.api.data.FieldMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class AgroApiDatasetReconciler implements DatasetReconciler {

    private final List<Dataset> datasets;

    public AgroApiDatasetReconciler() {
        datasets = new ArrayList<>();
    }

    @Override
    public void addDataset(final Dataset dataset) {
        datasets.add(dataset);

    }

    @Override
    public Set<FieldMapping> reconcile() {


        return null;
    }
}
