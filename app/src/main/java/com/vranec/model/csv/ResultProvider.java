package com.vranec.model.csv;

import java.util.List;

public interface ResultProvider {

    List<Result> getResults();

    default List<Result> getUniqueSortedResults() {
        return getResults().stream()
                .distinct()
                .sorted()
                .toList();
    }
}
