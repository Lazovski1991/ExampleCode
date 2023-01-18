package org.example;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class SearchResultResponse {
    private List<SearchResult> searchResult;

    @Data
    @Accessors(chain = true)
    public static class SearchResult {
        private String groupName;
        private List<Services> servicesList;
    }

    @Data
    @Accessors(chain = true)
    public static class Services {
        private String id;
        private String name;
        private Boolean isPartnersOffer;
        private String description;
        private String imageUrl;
        private String groupName;
    }
}
