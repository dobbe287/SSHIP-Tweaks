package tm.mtwModPatcher.sship.features.global.factionFate;

import tm.mtwModPatcher.lib.common.core.features.Feature;
import tm.mtwModPatcher.lib.managers.FactionsDefs;
import tm.mtwModPatcher.lib.common.entities.RegionOwnershipInfo;
import tm.mtwModPatcher.lib.data.world.maps.campaign.CampaignScript;
import tm.mtwModPatcher.lib.managers.FateScriptManager;

import java.util.List;
import java.util.UUID;

/**
 * Created by Tomek on 2016-07-10.
 */
public class ZengidsFate extends Feature {

	@Override
	public void setParamsCustomValues() {

	}

	@Override
	public void executeUpdates() throws Exception {
		_CampaignScript = getFileRegisterForUpdated(CampaignScript.class);

		FateScriptManager fateScriptManager = new FateScriptManager(_CampaignScript);

		// Zengids : 3 starting regions,
		fateScriptManager.writeRegionRangeScript("kwarezm" , 1132 , 1500, 1 , 2 , 3000 ,
				"Bonus on ANY Region lose");


		// ### 1144 - Edessa taken , 1147-48 - Damasqus ###
		List<String> islamF = FactionsDefs.islamFactionsList();
		//factions.addAll(FactionsDefs.orthodoxFactionsList());

		fateScriptManager.writeRegionNegativeOwnershipScript("kwarezm" , 1138 , 1155 , new RegionOwnershipInfo("Edessa" , islamF),
				3000, "1144 - Edessa taken");
		fateScriptManager.writeRegionNegativeOwnershipScript("kwarezm" , 1140 , 1155 , new RegionOwnershipInfo("Damascus" , islamF),
				2000, "1147-48 - Damasqus");

		// Fall of Jerusalem 1187
		//fateScriptManager.writeRegionRangeScript("kwarezm" , 1180 , 1600 , 3 , 7 , 5000 );

	}

	protected CampaignScript _CampaignScript;

	@Override
	public UUID getId() {
		return Id;
	}
	public static UUID Id = UUID.fromString("17b98fcf-7f2d-45c8-9af8-4dbc837e3bb4");

	public ZengidsFate() {
		super("Zengids Fate");

		setDescriptionUrl("http://tmsship.wikidot.com/factions-fate");
	}

}
