package com.adjuster.campaignagregator.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import com.adjuster.campaignagregator.model.AggregatedCampaign;
import com.adjuster.campaignagregator.model.Creative;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Initialised via the CampaignDataProvider, holds the Campaigns mapping.
 * Performs statistic operations on the stored campaigns.
 * In a real world application, this class can be plugged-in with different statistical operations for a more dynamic kind of extendability
 */
@Component
public class CampaignAggregator {
    private static final Logger LOGGER = LoggerFactory.getLogger(CampaignAggregator.class);

    private final Map<Integer, AggregatedCampaign> aggregatedCampaignMap = new HashMap<>();
    private final CampaignDataProvider campaignDataProvider;

    public CampaignAggregator(CampaignDataProvider campaignDataProvider) {
        this.campaignDataProvider = campaignDataProvider;
    }

    @PostConstruct
    public void init() {
        //fetch and insert all campaigns to aggregatedCampaignMap
        Arrays.stream(campaignDataProvider.fetchCampaigns()).forEach((campaign -> aggregatedCampaignMap.put(campaign.getId(), new AggregatedCampaign(campaign))));

        //fetch and insert all creatives to there aggregatedCampaign, NOTE: for this exercise, we assume all campaigns exist in campaigns list
        Arrays.stream(campaignDataProvider.fetchCreatives()).forEach(creative -> aggregatedCampaignMap.get(creative.getParentId()).addCreative(creative));
    }

    public int getCampaignImpressions(int campaignId) throws InvalidCampaignException {
        return getCampaignImpressions(getAggregatedCampaign(campaignId));
    }

    public int getCampaignClicks(int campaignId) throws InvalidCampaignException {
        return getCampaignClicks(getAggregatedCampaign(campaignId));
    }

    public void printCampaignSummary() {
        aggregatedCampaignMap.values().forEach(this::printCampaignSummary);
    }

    private void printCampaignSummary(AggregatedCampaign aggregatedCampaign) {
        LOGGER.info("{}, impressions={}, clicks={}",aggregatedCampaign.getCampaign().toString() ,
                getCampaignImpressions(aggregatedCampaign), getCampaignClicks(aggregatedCampaign));
    }

    private AggregatedCampaign getAggregatedCampaign(int campaignId) throws InvalidCampaignException {
        if(!aggregatedCampaignMap.containsKey(campaignId)){
            throw new InvalidCampaignException(campaignId);
        }

        return aggregatedCampaignMap.get(campaignId);
    }

    private int getCampaignImpressions(AggregatedCampaign aggregatedCampaign) {
        return aggregatedCampaign.getCreativeList().stream().mapToInt(Creative::getImpressions).sum();
    }

    private int getCampaignClicks(AggregatedCampaign aggregatedCampaign) {
        return aggregatedCampaign.getCreativeList().stream().mapToInt(Creative::getClicks).sum();
    }
}
