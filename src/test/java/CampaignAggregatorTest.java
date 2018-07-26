import java.util.Arrays;
import com.adjuster.campaignagregator.model.Campaign;
import com.adjuster.campaignagregator.model.Creative;
import com.adjuster.campaignagregator.service.CampaignAggregator;
import com.adjuster.campaignagregator.service.CampaignDataProvider;
import com.adjuster.campaignagregator.service.InvalidCampaignException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CampaignAggregatorTest {
    private final int CAMPAIGN_ID = 1234;
    @Mock
    CampaignDataProvider campaignDataProvider;

    @Mock
    Campaign campaign;

    @Mock
    Creative creative;

    CampaignAggregator campaignAggregator;

    @BeforeEach
    void setup() {
        initMocks(this);
        when(campaign.getId()).thenReturn(CAMPAIGN_ID);
        when(creative.getParentId()).thenReturn(CAMPAIGN_ID);
        when(campaignDataProvider.fetchCampaigns()).thenReturn(new Campaign[]{campaign});
        when(campaignDataProvider.fetchCreatives()).thenReturn(new Creative[]{creative, creative});

        campaignAggregator = new CampaignAggregator(campaignDataProvider);
        campaignAggregator.init();
    }

    @Test
    void whenCalculatingClicksThenSumCreativeClicks() throws InvalidCampaignException {
        when(creative.getClicks()).thenReturn(5);

        Assertions.assertEquals(10, campaignAggregator.getCampaignClicks(CAMPAIGN_ID));
    }

    @Test
    void whenCalculatingImpressionsThenSumCreativeImpressions() throws InvalidCampaignException {
        when(creative.getImpressions()).thenReturn(5);

        Assertions.assertEquals(10, campaignAggregator.getCampaignImpressions(CAMPAIGN_ID));
    }

    @Test
    void whenCalculatingImpressionsOfInvalidCampaignIdThenThrowException() throws InvalidCampaignException {
        Assertions.assertThrows(InvalidCampaignException.class, ()-> campaignAggregator.getCampaignImpressions(777));
    }
}
