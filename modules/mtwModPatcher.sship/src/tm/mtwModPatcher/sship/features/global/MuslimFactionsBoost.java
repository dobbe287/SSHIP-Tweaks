package tm.mtwModPatcher.sship.features.global;

import lombok.Getter;
import lombok.Setter;
import lombok.val;
import tm.common.collections.ArrayUniqueList;
import tm.common.collections.ListUnique;
import tm.mtwModPatcher.lib.common.core.features.params.ParamId;
import tm.mtwModPatcher.lib.common.core.features.params.ParamIdBoolean;
import tm.mtwModPatcher.lib.common.core.features.params.ParamIdDouble;
import tm.mtwModPatcher.lib.data.exportDescrBuilding.UnitRecruitmentQueries;
import tm.mtwModPatcher.lib.managers.FactionsDefs;
import tm.mtwModPatcher.lib.common.core.features.PatcherLibBaseEx;
import tm.mtwModPatcher.lib.common.core.features.Feature;
import tm.mtwModPatcher.lib.data._root.DescrCampaignDb;
import tm.mtwModPatcher.lib.data.exportDescrBuilding.ExportDescrBuilding;
import tm.mtwModPatcher.lib.data.exportDescrUnit.ExportDescrUnitTyped;
import tm.mtwModPatcher.lib.managers.UnitsManager;
import tm.mtwModPatcher.sship.lib.UnitRecruitmentSshipQueries;

import javax.xml.xpath.XPathExpressionException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Gives benefits to muslim factions
 */
public class MuslimFactionsBoost extends Feature {

	@Getter @Setter
	private boolean ahdathMilitiaBoost = false;
	@Getter @Setter
	private double muslimReplenishMult = 1.2;

	@Override
	public void executeUpdates() throws Exception {
		exportDescrBuilding = getFileRegisterForUpdated(ExportDescrBuilding.class);
		exportDescrUnit = getFileRegisterForUpdated(ExportDescrUnitTyped.class);
		descrCampaignDb = getFileRegisterForUpdated(DescrCampaignDb.class);

		unitsReplenishRatesBoost();
		religiousConversionTempleBonus();
		jihadRequirement();
	}

	private void unitsReplenishRatesBoost() {

		List<Pattern> unitsToExclude = null; //Arrays.asList(Pattern.compile(".*[Cc]hristian.*"));

		UnitsManager unitsManager = new UnitsManager();

		val queriesServ = new UnitRecruitmentSshipQueries(exportDescrBuilding);
		val muslimUnitNames = UnitRecruitmentQueries.toUnitNames(queriesServ.findMuslim());

		if(muslimReplenishMult > 1.0) {
			val islamFactions = FactionsDefs.islamFactionsSet();
			unitsManager.updateOrAddReplenishBonusEntry(islamFactions, muslimUnitNames, muslimReplenishMult, unitsToExclude, exportDescrBuilding);
		}
		else consoleLogger.writeLine("MuslimReplenishMult="+muslimReplenishMult+" (no bonus, decrease), skipping");

		if (ahdathMilitiaBoost) {
			val ahdathMilitia = exportDescrUnit.loadUnit("Ahdath Militia");
			ahdathMilitia.StatCost.Cost *= 0.90;
			ahdathMilitia.StatCost.RecruitTurns = 1;
		}
	}

	private void religiousConversionTempleBonus() throws PatcherLibBaseEx {

		// ### Religion Conversion bonus Muslims Mosques  ###

		String attribStr = "        religion_level bonus ";

		// # City #
		exportDescrBuilding.insertIntoBuildingCapabilities("temple_muslim", "small_masjid", "city", attribStr + 1);
		exportDescrBuilding.insertIntoBuildingCapabilities("temple_muslim", "masjid", "city", attribStr + 1);
		exportDescrBuilding.insertIntoBuildingCapabilities("temple_muslim", "minareted_masjid", "city", attribStr + 1);
		exportDescrBuilding.insertIntoBuildingCapabilities("temple_muslim", "jama", "city", attribStr + 1);
		exportDescrBuilding.insertIntoBuildingCapabilities("temple_muslim", "great_jama", "city", attribStr + 1);
		// # Castle #
		exportDescrBuilding.insertIntoBuildingCapabilities("temple_muslim_castle", "c_small_masjid", "castle", attribStr + 1);
		exportDescrBuilding.insertIntoBuildingCapabilities("temple_muslim_castle", "c_masjid", "castle", attribStr + 1);
	}

	private void jihadRequirement() throws XPathExpressionException {
		descrCampaignDb.setAttribute("/root//crusades/required_jihad_piety", "int", 4);
	}

	@Override
	public ListUnique<ParamId> defineParamsIds() {
		val pars = new ArrayUniqueList<ParamId>();

		pars.add(new ParamIdBoolean("AhdathMilitiaBoost", "Ahdath Militia Boost",
				feature -> ((MuslimFactionsBoost) feature).isAhdathMilitiaBoost(),
				(feature, value) -> ((MuslimFactionsBoost) feature).setAhdathMilitiaBoost(value)));

		pars.add(new ParamIdDouble("MuslimReplenishMultiplier", "Muslim Replenish Multiplier",
				feature -> ((MuslimFactionsBoost) feature).getMuslimReplenishMult(),
				(feature, value) -> ((MuslimFactionsBoost) feature).setMuslimReplenishMult(value)));

		return pars;
	}

	protected ExportDescrBuilding exportDescrBuilding;
	protected ExportDescrUnitTyped exportDescrUnit;
	protected DescrCampaignDb descrCampaignDb;

	@Override
	public UUID getId() {
		return Id;
	}
	public static UUID Id = UUID.fromString("a0c884ca-2151-4a33-ae07-86c09c57b213");

	public MuslimFactionsBoost() {
		setName("Muslim Factions Boost");

		addCategory("Campaign");

		setDescriptionShort("Gives various benefits to muslim factions");
		setDescriptionUrl("http://tmsship.wikidot.com/muslim-factions-boost");
	}
}
