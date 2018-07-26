package com.adjuster.campaignagregator.service;

/**
 * module specific exception
 */
public class InvalidCampaignException extends Exception {
    InvalidCampaignException(int campaignId){
        super("Invalid campaignId=" + campaignId);
    }
}
