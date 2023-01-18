package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<SearchResultResponse.Services> services = List.of(new SearchResultResponse.Services()
                        .setId("id1")
                        .setName("name1")
                        .setIsPartnersOffer(true)
                        .setDescription("description1")
                        .setImageUrl("imageUrl1")
                        .setGroupName("group1"),
                new SearchResultResponse.Services()
                        .setId("id2")
                        .setName("name2")
                        .setIsPartnersOffer(true)
                        .setDescription("description2")
                        .setImageUrl("imageUrl2")
                        .setGroupName("group2"),
                new SearchResultResponse.Services()
                        .setId("id3")
                        .setName("name3")
                        .setIsPartnersOffer(true)
                        .setDescription("description3")
                        .setImageUrl("imageUrl3")
                        .setGroupName("group2"));

        collect(services);


    }

    public static List<SearchResultResponse.SearchResult> collect(List<SearchResultResponse.Services> services) {

        return services
                .stream()
                .collect(SearchResultCollector.toSearchResultCollector());

    }
}
