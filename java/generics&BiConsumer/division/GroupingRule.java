package com.inje.conebilling.domain.charge.service.division;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GroupingRule<E, U> {
    
    private final String groupKey;
    private final Function<E, U> classifier;

    public Map<U, List<E>> division(List<E> targetList) throws Exception {
        return targetList.stream().collect(Collectors.groupingBy(classifier));
    }

    public String getGroupKey(){
        return this.groupKey;
    }

    public Function<E,U> getClassifier(){
        return this.classifier;
    }
}
