package tm.mtwModPatcher.sship.global.factionFate;

import tm.mtwModPatcher.lib.managers.FateScriptManager;
import tm.mtwModPatcher.lib.common.core.features.Feature;
import tm.mtwModPatcher.lib.fileEntities.data.world.maps.campaign.CampaignScript;

import java.util.UUID;

/**
 * Created by Tomek on 2016-07-21.
 */
public class SpainFate extends Feature {

	@Override
	public void executeUpdates() throws Exception {
		_CampaignScript = getFileRegisterForUpdated(CampaignScript.class);

		FateScriptManager fateScriptManager = new FateScriptManager(_CampaignScript);

		// ### SPAIN : 5 initial regions ####
		fateScriptManager.writeRegionRangeScript("spain" , 1132 , 1170 , 2 , 4 , 4000 ,
				"bonus on ANY Region lose");
	}

	protected CampaignScript _CampaignScript;

	@Override
	public UUID getId() {
		return Id;
	}
	public static UUID Id = UUID.randomUUID();

	public SpainFate() {
		super("Spain Fate");

		setDescriptionUrl("http://tmsship.wikidot.com/factions-fate");
	}
}