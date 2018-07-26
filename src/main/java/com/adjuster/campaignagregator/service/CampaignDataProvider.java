package com.adjuster.campaignagregator.service;

import com.adjuster.campaignagregator.model.Campaign;
import com.adjuster.campaignagregator.model.Creative;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Uses Spring Web RestTemplate to consume the campaigns and creatives endpoints
 * uses yml configuration for the REST Urls
 */
@Component
public class CampaignDataProvider {
    @Value("${rest.campaignsUrl}")
    private String campaignsUrl;

    @Value("${rest.creativesUrl}")
    private String creativesUrl;

    @Autowired
    private RestTemplate restTemplate;

    public Campaign[] fetchCampaigns(){
        return restTemplate.getForObject( campaignsUrl, Campaign[].class);
    }

    public Creative[] fetchCreatives(){
        return restTemplate.getForObject( creativesUrl, Creative[].class);
    }
}
