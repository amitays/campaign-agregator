package com.adjuster.campaignagregator.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for holding the connection between a Campaign and its Creatives,
 * In a "real world" application, the rest model POJOs and the internal model POJOs will be different
 */
public class AggregatedCampaign {
    private final Campaign campaign;
    private List<Creative> creativeList;

    public AggregatedCampaign(Campaign campaign) {
        this.campaign = campaign;
        this.creativeList = new ArrayList<>();
    }

    public void addCreative(Creative creative){
        creativeList.add(creative);
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public List<Creative> getCreativeList() {
        return creativeList;
    }
}
