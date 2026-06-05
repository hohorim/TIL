package com.inje.conebilling.domain.charge.service.division;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DivisionControlPipeline<T, U> {
	private final List<GroupingRule<T, U>> divisionControls = new ArrayList<>();

	public DivisionControlPipeline<T, U> add(GroupingRule<T, U> rule) {
		this.divisionControls.add(rule);
		return this;
	}

	public <D, M extends Map<U, D>> M grouping2(List<T> list) throws Exception {

		Collector<T, ?, M> colltors = (Collector<T, ?, M>) Collectors.toList();
		for (int i=divisionControls.size()-1; i>=0; i--) {
			colltors = (Collector<T, ?, M>)Collectors.groupingBy(divisionControls.get(i).getClassifier(), HashMap::new, colltors);
		}

		return list.stream().collect(colltors);
	}
	
	public void grouping(List<T> metricsEntities, BiConsumer<List<T>, Map<String, U>> consumer) throws Exception {
		grouping(metricsEntities, 0, consumer, new HashMap<>());
	}

	private void grouping(List<T> list, int index, BiConsumer<List<T>, Map<String, U>> consumer, Map<String, U> keys) throws Exception {

		for (int i = index; i < divisionControls.size(); i++) {
			Map<U, List<T>> groupByList = divisionControls.get(i).division(list);

			for (U key : groupByList.keySet()) {

				Map<String, U> keyList = new HashMap<>(keys);
				keyList.put(divisionControls.get(i).getGroupKey(), key);

				grouping(groupByList.get(key), i + 1, consumer, keyList);

				if (keyList.size() == divisionControls.size()) {
					consumer.accept(groupByList.get(key), keyList);
				}
			}
		}
	}
}
