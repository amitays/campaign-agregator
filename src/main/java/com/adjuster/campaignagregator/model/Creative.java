package com.adjuster.campaignagregator.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *REST level POJO containing the Creative data
 */
public class Creative {
    private final int id;
    private final int parentId;
    private final String name;
    private final int clicks;
    private final int impressions;
    private final int conversions;
    private final int views;

    @JsonCreator
    public Creative(@JsonProperty("id") int id,
            @JsonProperty("parentId") int parentId,
            @JsonProperty("name") String name,
            @JsonProperty("clicks") int clicks,
            @JsonProperty("impressions") int impressions,
            @JsonProperty("conversions") int conversions,
            @JsonProperty("views") int views) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.clicks = clicks;
        this.impressions = impressions;
        this.conversions = conversions;
        this.views = views;
    }

    public int getId() {
        return id;
    }

    public int getParentId() {
        return parentId;
    }

    public String getName() {
        return name;
    }

    public int getClicks() {
        return clicks;
    }

    public int getImpressions() {
        return impressions;
    }

    public int getConversions() {
        return conversions;
    }

    public int getViews() {
        return views;
    }
}
