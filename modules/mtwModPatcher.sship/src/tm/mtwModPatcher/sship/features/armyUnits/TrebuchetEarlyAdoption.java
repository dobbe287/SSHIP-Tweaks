package tm.mtwModPatcher.sship.features.armyUnits;

import lombok.val;
import tm.mtwModPatcher.lib.common.core.features.Feature;
import tm.mtwModPatcher.lib.common.core.features.fileEntities.LineNotFoundEx;
import tm.mtwModPatcher.lib.data.exportDescrBuilding.ExportDescrBuilding;
import tm.mtwModPatcher.lib.data.exportDescrUnit.ExportDescrUnitTyped;
import tm.mtwModPatcher.lib.data.unitModels.BattleModels;
import tm.mtwModPatcher.lib.managers.FactionsDefs;

import java.util.UUID;

/**
 * Created by tomek on 25.05.2017.
 */
public class TrebuchetEarlyAdoption extends Feature {
	@Override
	public void setParamsCustomValues() {

	}

	@Override
	public void executeUpdates() throws Exception {
		exportDescrBuilding = getFileRegisterForUpdated(ExportDescrBuilding.class);
		exportDescrUnit = getFileRegisterForUpdated(ExportDescrUnitTyped.class);
		battleModels = getFileRegisterForUpdated(BattleModels.class);

		val trebuchetName = "ME Trebuchet";
		exportDescrUnit.loadUnit(trebuchetName).Ownership = "all";
		exportDescrUnit.loadUnit(trebuchetName).OwnershipEra1 = "all";
		battleModels.copyModelBlocksData("me_trebuchet_crew", "aragon", "moors");


		int index;
		String orgLine;
		String requiresRaw = "factions { northern_european, eastern_european, teutonic_order, timurids, southern_european, } and hidden_resource ";
		String requiresPart = "";

		for (val islamFacionName : FactionsDefs.islamFactionsList()) {
			if(!requiresPart.isEmpty())
				requiresPart +=" or ";

			requiresPart += requiresRaw + islamFacionName;
		}

		String unitRequires = " " + requiresPart;
		String buildingRequires = " or " + requiresPart;

		val lines = exportDescrBuilding.getLines();

		// ## Castle Siege ##
		index = exportDescrBuilding.findBuildingRequiresLine("castle_siege", "c_siege_works" ,"castle");
		if(index <=0) throw new LineNotFoundEx("City siege building not found");

		orgLine = lines.getLine(index);
		lines.replaceLine(index, orgLine + buildingRequires);

		exportDescrBuilding.addRecuitment("castle_siege", "c_siege_works" ,"castle",
				trebuchetName,1,0.067, 1, 0 , unitRequires);
	}

	private ExportDescrBuilding exportDescrBuilding;
	private ExportDescrUnitTyped exportDescrUnit;
	private BattleModels battleModels;

	public TrebuchetEarlyAdoption() {
		super("Trebuchet Early Adoption");

		addCategory("Units");

		setDescriptionShort("Earlier access to trebuchet by 'adopting' technology from conquered muslim castles");
		setDescriptionUrl("http://tmsship.wikidot.com/trebuchet-early-adoption");
	}

	@Override
	public UUID getId() {
		return Id;
	}
	public static UUID Id = UUID.fromString("e4f3a21b-3977-48a5-8ff1-460e5b770a37");
}
