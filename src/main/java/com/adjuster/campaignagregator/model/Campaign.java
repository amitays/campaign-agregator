package com.adjuster.campaignagregator.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *REST level POJO containing the Campaign data
 */
public class Campaign {
    private final int id;
    private final String name;
    private final String advertiser;
    private final String cpm;
    private final Date endDate;
    private final Date startDate;

    @JsonCreator
    public Campaign(@JsonProperty("id") int id,
            @JsonProperty("name") String name,
            @JsonProperty("advertiser") String advertiser,
            @JsonProperty("cpm") String cpm,
            @JsonProperty("endDate") Date endDate,
            @JsonProperty("startDate") Date startDate) {
        this.id = id;
        this.name = name;
        this.advertiser = advertiser;
        this.cpm = cpm;
        this.endDate = endDate;
        this.startDate = startDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAdvertiser() {
        return advertiser;
    }

    public String getCpm() {
        return cpm;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    @Override
    public String toString() {
        return String.format("%s - %s, dates: %s - %s, CPM=%s", advertiser, name, startDate, endDate, cpm);
    }
}
