package tm.mtwModPatcher.sship.features.ai;

import tm.mtwModPatcher.lib.common.core.features.ResourcesProvider;

/**
 * Created by tomek on 23.05.2017.
 */
public class TestingCampaignAiFt extends CampaignAiConfigurator {

	@Override
	public void setParamsCustomValues() {
		initializeDefaults();
	}

	@Override
	protected void initializeDefaults() {

	}

	public TestingCampaignAiFt(ResourcesProvider resourcesProvider) {
		super("TestingCampaignAiFt", resourcesProvider);

	}
}
