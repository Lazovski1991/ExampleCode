package org.example;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class SearchResultCollector
        implements Collector<SearchResultResponse.Services,
        ArrayListMultimap<String, SearchResultResponse.Services>,
        List<SearchResultResponse.SearchResult>> {

    @Override
    public Supplier<ArrayListMultimap<String, SearchResultResponse.Services>> supplier() {
        return ArrayListMultimap::create;
    }

    @Override
    public BiConsumer<ArrayListMultimap<String, SearchResultResponse.Services>, SearchResultResponse.Services> accumulator() {
        return (map, el) -> {
            map.put(el.getGroupName(), el);
        };
    }

    @Override
    public BinaryOperator<ArrayListMultimap<String, SearchResultResponse.Services>> combiner() {
        return (searchResult1, searchResults2) -> {
            searchResult1.putAll(searchResults2);
            return searchResult1;
        };
    }

    @Override
    public Function<ArrayListMultimap<String, SearchResultResponse.Services>, List<SearchResultResponse.SearchResult>> finisher() {
        return (map) -> {
            List<SearchResultResponse.SearchResult> result = new ArrayList<>();

            map
                    .asMap()
                    .forEach((key, value) -> {
                        SearchResultResponse.SearchResult searchResult = new SearchResultResponse.SearchResult()
                                .setGroupName(key)
                                .setServicesList(map.get(key));
                        result.add(searchResult);
                    });

            return result;
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Sets.immutableEnumSet(Characteristics.UNORDERED);
    }

    public static SearchResultCollector toSearchResultCollector() {
        return new SearchResultCollector();
    }
}
