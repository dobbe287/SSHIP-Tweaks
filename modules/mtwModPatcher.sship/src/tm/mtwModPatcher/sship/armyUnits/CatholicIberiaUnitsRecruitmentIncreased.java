package tm.mtwModPatcher.sship.armyUnits;

import lombok.val;
import tm.mtwModPatcher.lib.common.core.features.PatcherLibBaseEx;
import tm.mtwModPatcher.lib.common.core.features.Feature;
import tm.mtwModPatcher.lib.common.core.features.OverrideCopyTask;
import tm.mtwModPatcher.lib.common.core.features.fileEntities.LinesProcessor;
import tm.mtwModPatcher.lib.data.exportDescrBuilding.ExportDescrBuilding;
import tm.mtwModPatcher.lib.common.entities.UnitReplenishRate;
import tm.mtwModPatcher.lib.data.exportDescrUnit.ExportDescrUnitTyped;
import tm.mtwModPatcher.lib.data.unitModels.BattleModels;
import tm.mtwModPatcher.lib.data.world.maps.campaign.DescrMercenaries;
import tm.mtwModPatcher.lib.data.world.maps.campaign.DescrStratSectioned;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**   */
public class CatholicIberiaUnitsRecruitmentIncreased extends Feature {

	protected ExportDescrBuilding _ExportDescrBuilding;
	protected ExportDescrUnitTyped _ExportDescrUnit;
	protected DescrMercenaries _DescrMercenaries;
	protected DescrStratSectioned _DescrStrat;
	protected BattleModels _BattleModels;

	@Override
	public void executeUpdates() throws Exception {
		_ExportDescrBuilding = fileEntityFactory.getFile(ExportDescrBuilding.class);
		registerUpdatedFile(_ExportDescrBuilding);
		_ExportDescrUnit = fileEntityFactory.getFile(ExportDescrUnitTyped.class);
		registerUpdatedFile(_ExportDescrUnit);
		_DescrMercenaries = getFileRegisterForUpdated(DescrMercenaries.class);
		_DescrStrat = getFileRegisterForUpdated(DescrStratSectioned.class);
		_BattleModels = getFileRegisterForUpdated(BattleModels.class);

		// #### Add Urban Spear Militia - with -2 replenish rate
		//addUrbanMilitia();

		val factions = Arrays.asList("spain", "aragon", "portugal");

		// ### Add Sergeant Spearmen - with minus -2 replenish rates  ###
		addSergentSpearmen();

		addSergeantSpearmanModels(factions, "france");	// "portugal"
		addArmoredSpearmanModels(factions, "jerusalem");

		addArmoredSpearmen();

		// ##### Add Light Swordsman & Light Man at Arms - CASTLE LEVEL ####
		addlightSwordsmen();

		// ## Aragon , Span, Portugal : Archer + Crossbowmen + Crusader Sergeant -> replaced on 3 units
		giveInitialCrusaderTroops();

		addCaballerosVillanosToAragon();
	}

	protected void giveInitialCrusaderTroops() throws PatcherLibBaseEx {
		LinesProcessor lines = _DescrStrat.Factions.getContent().getLines();

		int aragonIndex = lines.findExpFirstRegexLine("^;## ARAGON ##\\s*");
		replaceInitialUnit("Peasant Archers","Prussian Archers" , aragonIndex);
		replaceInitialUnit("Spear Militia","Crossbow Militia" , aragonIndex);
		replaceInitialUnit("Spear Militia","Crusader Sergeants" , aragonIndex , 1);
		replaceInitialUnit("Alforrats","Sergeant Spearmen" , aragonIndex);

		int spainIndex = lines.findExpFirstRegexLine(";## CASTILE & LEON ##\\s*");
		replaceInitialUnit("Javelinmen","Prussian Archers" , spainIndex);
		replaceInitialUnit("Javelinmen","Crossbow Militia" , spainIndex);
		replaceInitialUnit("Spear Militia","Crusader Sergeants" , spainIndex , 1);
		replaceInitialUnit("Spear Militia","Crusader Sergeants" , spainIndex , 1);
		replaceInitialUnit("Spear Militia","Sergeant Spearmen" , spainIndex , 1);

		int portugalIndex = lines.findExpFirstRegexLine(";## PORTUGAL ##\\s*");
		replaceInitialUnit("Peasant Archers","Prussian Archers" , portugalIndex);
		replaceInitialUnit("Lusitanian Javelinmen","Crossbow Militia" , portugalIndex);
		replaceInitialUnit("Spear Militia","Crusader Sergeants" , portugalIndex , 1);

	}

	protected void replaceInitialUnit(String oldUnitName , String newUnitName, int factionStartIndex) throws PatcherLibBaseEx {
		replaceInitialUnit(oldUnitName, newUnitName, factionStartIndex, 0);
	}
	protected void replaceInitialUnit(String oldUnitName , String newUnitName, int factionStartIndex , int experienceLevel) throws PatcherLibBaseEx {

		LinesProcessor lines = _DescrStrat.Factions.getContent().getLines();

		factionStartIndex = lines.findExpFirstRegexLine("^unit\\s+"+ oldUnitName + ".+" , factionStartIndex+1);
		lines.replaceLine(factionStartIndex , "unit\t\t" + newUnitName + "\t\t\t\texp " + experienceLevel + " armour 0 weapon_lvl 0");
	}

	protected void addlightSwordsmen() throws PatcherLibBaseEx {
		// ##### Add Light Swordsman & Light Man at Arms - CASTLE LEVEL ####

		// org commented - add uncomented :
//		;recruit_pool    "Light Swordsmen"  1   0.13   1  0  requires factions { hre, spain, hungary, teutonic_order, portugal, } and not event_counter first_watch 1 and hidden_resource poland or hidden_resource hre or hidden_resource spain or hidden_resource pisa or hidden_resource venice or hidden_resource papal_states or hidden_resource hungary or hidden_resource portugal
//		;recruit_pool    "Light Swordsmen"  0   0.085   1  0  requires factions { hre, spain, hungary, teutonic_order, portugal, } and not event_counter first_watch 1 and not hidden_resource poland and not hidden_resource hre and not hidden_resource spain and not hidden_resource pisa and not hidden_resource venice and not hidden_resource papal_states and not hidden_resource hungary and not hidden_resource portugal
//		;recruit_pool    "Light Swordsmen"  1   0.085   1  0  requires factions { poland, pisa, venice, papal_states, } and not event_counter first_watch 1 and hidden_resource poland or hidden_resource hre or hidden_resource spain or hidden_resource pisa or hidden_resource venice or hidden_resource papal_states or hidden_resource hungary or hidden_resource portugal
//		;recruit_pool    "Light Swordsmen"  0   0.065   1  0  requires factions { poland, pisa, venice, papal_states, } and not event_counter first_watch 1 and not hidden_resource poland and not hidden_resource hre and not hidden_resource spain and not hidden_resource pisa and not hidden_resource venice and not hidden_resource papal_states and not hidden_resource hungary and not hidden_resource portugal
//
//		;recruit_pool    "Light Men at Arms"  1   0.13   1  0  requires factions { england, france, aragon, } and not event_counter first_watch 1 and hidden_resource england or hidden_resource france or hidden_resource aragon
//		;recruit_pool    "Light Men at Arms"  0   0.085   1  0  requires factions { england, france, aragon, } and not event_counter first_watch 1 and not hidden_resource england and not hidden_resource france and not hidden_resource aragon


		// ### Light Swordsmen & Light Men at Arms ###
		_ExportDescrBuilding.insertIntoBuildingCapabilities("castle_barracks", "drill_square", "castle",
				"recruit_pool    \"Light Swordsmen\"  1   0.13   1  0  requires factions { hre, spain, hungary, teutonic_order, portugal, } and not event_counter first_watch 1 and hidden_resource poland or hidden_resource hre or hidden_resource spain or hidden_resource pisa or hidden_resource venice or hidden_resource papal_states or hidden_resource hungary or hidden_resource portugal");
		_ExportDescrBuilding.insertIntoBuildingCapabilities("castle_barracks", "drill_square", "castle",
				"recruit_pool    \"Light Swordsmen\"  0   0.085   1  0  requires factions { hre, spain, hungary, teutonic_order, portugal, } and not event_counter first_watch 1 and not hidden_resource poland and not hidden_resource hre and not hidden_resource spain and not hidden_resource pisa and not hidden_resource venice and not hidden_resource papal_states and not hidden_resource hungary and not hidden_resource portugal");
		_ExportDescrBuilding.insertIntoBuildingCapabilities("castle_barracks", "drill_square", "castle",
				"recruit_pool    \"Light Swordsmen\"  1   0.085   1  0  requires factions { poland, pisa, venice, papal_states, } and not event_counter first_watch 1 and hidden_resource poland or hidden_resource hre or hidden_resource spain or hidden_resource pisa or hidden_resource venice or hidden_resource papal_states or hidden_resource hungary or hidden_resource portugal");
		_ExportDescrBuilding.insertIntoBuildingCapabilities("castle_barracks", "drill_square", "castle",
				"recruit_pool    \"Light Swordsmen\"  0   0.065   1  0  requires factions { poland, pisa, venice, papal_states, } and not event_counter first_watch 1 and not hidden_resource poland and not hidden_resource hre and not hidden_resource spain and not hidden_resource pisa and not hidden_resource venice and not hidden_resource papal_states and not hidden_resource hungary and not hidden_resource portugal");

		_ExportDescrBuilding.insertIntoBuildingCapabilities("castle_barracks", "drill_square", "castle",
				"recruit_pool    \"Light Men at Arms\"  1   0.13   1  0  requires factions { england, france, aragon, } and not event_counter first_watch 1 and hidden_resource england or hidden_resource france or hidden_resource aragon");
		_ExportDescrBuilding.insertIntoBuildingCapabilities("castle_barracks", "drill_square", "castle",
				"recruit_pool    \"Light Men at Arms\"  0   0.085   1  0  requires factions { england, france, aragon, } and not event_counter first_watch 1 and not hidden_resource england and not hidden_resource france and not hidden_resource aragon");
	}

	protected void addSergentSpearmen() throws PatcherLibBaseEx {
		// ### Add Sergeant Spearmen - with minus -2 replenish rates  ###
		// ## Castle ## levels mustering_hall garrison_quarters drill_square barracks armoury

		_ExportDescrUnit.addOwnership("Sergeant Spearmen", "aragon");
		_ExportDescrUnit.addOwnership("Sergeant Spearmen", "aragon", 0);
		_ExportDescrUnit.addOwnership("Sergeant Spearmen", "aragon", 1);

		_ExportDescrUnit.addOwnership("Sergeant Spearmen", "spain");
		_ExportDescrUnit.addOwnership("Sergeant Spearmen", "spain", 0);
		_ExportDescrUnit.addOwnership("Sergeant Spearmen", "spain", 1);

		_ExportDescrUnit.addOwnership("Sergeant Spearmen", "portugal");
		_ExportDescrUnit.addOwnership("Sergeant Spearmen", "portugal", 0);
		_ExportDescrUnit.addOwnership("Sergeant Spearmen", "portugal", 1);

		String orderHousesRequirement = "";
		orderHousesRequirement += "hidden_resource aragon or hidden_resource spain or hidden_resource portugal or hidden_resource france";

		// ## Castle ## levels mustering_hall garrison_quarters drill_square barracks armoury
		String sergSpear = "Sergeant Spearmen";
		String factionsRequirement = "factions { spain, aragon, portugal, }";

		_ExportDescrBuilding.insertRecruitmentBuildingCapabilities( "castle_barracks", "garrison_quarters", "castle", sergSpear,
				1, UnitReplenishRate.R13, 1, 0, factionsRequirement); //and " + orderHousesRequirement

		_ExportDescrBuilding.insertRecruitmentBuildingCapabilities("castle_barracks", "drill_square", "castle", sergSpear,
				1, UnitReplenishRate.R9, 2, 0, factionsRequirement); //and " + orderHousesRequirement

		// #####  C I T Y  Order Houses #####
//		exportDescrBuilding.insertRecruitmentBuildingCapabilities("templars_chapter_house", "templars_minor_ch", "city", sergSpear,
//				1, UnitReplenishRate.R13, 1, 0, factionsRequirement + " and " + orderHousesRequirement);
//		exportDescrBuilding.insertRecruitmentBuildingCapabilities("templars_chapter_house", "templars_major_ch", "city", sergSpear,
//				1, UnitReplenishRate.R10, 2, 0, factionsRequirement + " and " + orderHousesRequirement);
//
//		exportDescrBuilding.insertRecruitmentBuildingCapabilities("st_johns_chapter_house", "st_johns_minor_ch", "city", sergSpear,
//				1, UnitReplenishRate.R13, 1, 0, factionsRequirement + " and " + orderHousesRequirement);
//		exportDescrBuilding.insertRecruitmentBuildingCapabilities("st_johns_chapter_house", "st_johns_major_ch", "city", sergSpear,
//				1, UnitReplenishRate.R10, 2, 0, factionsRequirement + " and " + orderHousesRequirement);
//
//
//		// # Santiago - one for City & Castle
//		exportDescrBuilding.insertRecruitmentBuildingCapabilities("knights_of_santiago_chapter_house", "knights_of_santiago_minor_ch", null, sergSpear,
//				1, UnitReplenishRate.R10, 1, 0, factionsRequirement + " and " + orderHousesRequirement);
//		exportDescrBuilding.insertRecruitmentBuildingCapabilities("knights_of_santiago_chapter_house", "knights_of_santiago_major_ch", null, sergSpear,
//				1, UnitReplenishRate.R8, 2, 0, factionsRequirement + " and " + orderHousesRequirement);
//
//
//		// #####  C A S T L E   Order Houses #####
//		exportDescrBuilding.insertRecruitmentBuildingCapabilities("templars_chapter_house_castle", "templars_minor_ch", "castle", sergSpear,
//				1, UnitReplenishRate.R10, 1, 0, factionsRequirement + " and " + orderHousesRequirement);
//		exportDescrBuilding.insertRecruitmentBuildingCapabilities("templars_chapter_house_castle", "templars_major_ch", "castle", sergSpear,
//				1, UnitReplenishRate.R8, 2, 0, factionsRequirement + " and " + orderHousesRequirement);
//
//		exportDescrBuilding.insertRecruitmentBuildingCapabilities("st_johns_chapter_house_castle", "st_johns_minor_ch", "castle", sergSpear,
//				1, UnitReplenishRate.R10, 1, 0, factionsRequirement + " and " + orderHousesRequirement);
//		exportDescrBuilding.insertRecruitmentBuildingCapabilities("st_johns_chapter_house_castle", "st_johns_major_ch", "castle", sergSpear,
//				1, UnitReplenishRate.R8, 2, 0, factionsRequirement + " and " + orderHousesRequirement);


//		// ## Castle Barracks #2 : org : R6 , R8 , R12 , MAX : 1
//		exportDescrBuilding.insertRecruitmentBuildingCapabilities("castle_barracks", "garrison_quarters", "castle", sergSpear,
//				0, UnitReplenishRate.R13, 1, 0, factionsRequirement + " and not event_counter FULL_PLATE_ARMOR 1 and " + orderHousesRequirement); //and " + orderHousesRequirement
//
//		// ## City Barracks #3 : org Replenish : R4 , R6 , R8  , MAX : 2,1
//		exportDescrBuilding.insertRecruitmentBuildingCapabilities("castle_barracks", "drill_square", "castle", sergSpear,
//				1, UnitReplenishRate.R10, 1, 0, factionsRequirement + " and not event_counter FULL_PLATE_ARMOR 1 and " + orderHousesRequirement);
//
//		exportDescrBuilding.insertRecruitmentBuildingCapabilities("castle_barracks", "drill_square", "castle", sergSpear,
//				1, UnitReplenishRate.R8, 1, 0, factionsRequirement + " and event_counter FULL_PLATE_ARMOR 1 and not event_counter first_watch 1 and " + orderHousesRequirement);
//
////		// ## Castle Barracks #4 : org : Replenish : R3, R4, R6 , R8   , Max : 3,2,1
//		exportDescrBuilding.insertRecruitmentBuildingCapabilities("castle_barracks", "barracks", "castle", sergSpear,
//				1, UnitReplenishRate.R9, 1, 0, factionsRequirement + " and not event_counter FULL_PLATE_ARMOR 1 and " + orderHousesRequirement);
//
//		exportDescrBuilding.insertRecruitmentBuildingCapabilities("castle_barracks", "barracks", "castle", sergSpear,
//				1, UnitReplenishRate.R7, 2, 0, factionsRequirement + " and event_counter FULL_PLATE_ARMOR 1 and not event_counter first_watch 1 and " + orderHousesRequirement);
//
//		exportDescrBuilding.insertRecruitmentBuildingCapabilities("castle_barracks", "barracks", "castle", sergSpear,
//				1, UnitReplenishRate.R5, 2, 0, factionsRequirement + " and event_counter first_watch 1 and " + orderHousesRequirement);
//
////		// ## Castle Barracks #5 : org Replenish : R2 , R3 , R4 , R6 , MAX : 4,3,2,1
//		exportDescrBuilding.insertRecruitmentBuildingCapabilities("castle_barracks", "armoury", "castle", sergSpear,
//				1, UnitReplenishRate.R8, 1, 0, factionsRequirement + " and not event_counter FULL_PLATE_ARMOR 1 and " + orderHousesRequirement);
//
//		exportDescrBuilding.insertRecruitmentBuildingCapabilities("castle_barracks", "armoury", "castle", sergSpear,
//				1, UnitReplenishRate.R6, 2, 0, factionsRequirement + " and event_counter FULL_PLATE_ARMOR 1 and not event_counter first_watch 1 and " + orderHousesRequirement);
//
//		exportDescrBuilding.insertRecruitmentBuildingCapabilities("castle_barracks", "armoury", "castle", sergSpear,
//				1, UnitReplenishRate.R4, 2, 0, factionsRequirement + " and event_counter first_watch 1 and " + orderHousesRequirement);


//		orderHousesRequirement += " and ";
//
//		orderHousesRequirement += "building_present_min_level templars_chapter_house templars_minor_ch";
//		orderHousesRequirement += " or ";
//		orderHousesRequirement += "building_present_min_level templars_chapter_house_castle templars_minor_ch";
//		orderHousesRequirement += " or ";
//		orderHousesRequirement += "building_present_min_level st_johns_chapter_house st_johns_minor_ch";
//		orderHousesRequirement += " or ";
//		orderHousesRequirement += "building_present_min_level st_johns_chapter_house_castle st_johns_minor_ch";
//		orderHousesRequirement += " or ";
//		orderHousesRequirement += "building_present_min_level teutonic_knights_chapter_house teutonic_knights_minor_ch";
//		orderHousesRequirement += " or ";
//		orderHousesRequirement += "building_present_min_level knights_of_santiago_chapter_house knights_of_santiago_minor_ch";
	}

	private void addSergeantSpearmanModels(List<String> factions, String copyFromFaction) throws PatcherLibBaseEx {

		for(val factionName : factions) {
			_BattleModels.copyModelBlocksData("sergeant_spearmen", factionName , copyFromFaction );
			_BattleModels.copyModelBlocksData("sergeant_spearmen_ug1", factionName , copyFromFaction );
		}
	}
	private void addArmoredSpearmanModels(List<String> factions, String copyFromFaction) throws PatcherLibBaseEx {

		for(val factionName : factions) {
			_BattleModels.copyModelBlocksData("armored_sergeants", factionName , copyFromFaction );
			_BattleModels.copyModelBlocksData("armored_sergeants_ug1", factionName , copyFromFaction );
		}
	}

	protected void addArmoredSpearmen() throws PatcherLibBaseEx {

		// ### Add Sergeant Spearmen - with minus -2 replenish rates  ###
		// ## Castle ## levels mustering_hall garrison_quarters drill_square barracks armoury

		String sergSpear = "Armored Sergeants";

		_ExportDescrUnit.addOwnership(sergSpear, "aragon");
		_ExportDescrUnit.addOwnership(sergSpear, "aragon", 0);
		_ExportDescrUnit.addOwnership(sergSpear, "aragon", 1);
		_ExportDescrUnit.addOwnership(sergSpear, "aragon", 2);

		_ExportDescrUnit.addOwnership(sergSpear, "spain");
		_ExportDescrUnit.addOwnership(sergSpear, "spain", 0);
		_ExportDescrUnit.addOwnership(sergSpear, "spain", 1);
		_ExportDescrUnit.addOwnership(sergSpear, "spain", 2);

		_ExportDescrUnit.addOwnership(sergSpear, "portugal");
		_ExportDescrUnit.addOwnership(sergSpear, "portugal", 0);
		_ExportDescrUnit.addOwnership(sergSpear, "portugal", 1);
		_ExportDescrUnit.addOwnership(sergSpear, "portugal", 2);

		String orderHousesRequirement = "";
		orderHousesRequirement += "hidden_resource aragon or hidden_resource spain or hidden_resource portugal or hidden_resource france";

		// ## Castle ## levels mustering_hall garrison_quarters drill_square barracks armoury
		String factionsRequirement = "factions { spain, aragon, portugal, }";


		// # Santiago - one for City & Castle
		_ExportDescrBuilding.insertRecruitmentBuildingCapabilities("knights_of_santiago_chapter_house", "knights_of_santiago_minor_ch", null, sergSpear,
				1, UnitReplenishRate.R15, 1, 0, factionsRequirement + " and " + orderHousesRequirement);
		_ExportDescrBuilding.insertRecruitmentBuildingCapabilities("knights_of_santiago_chapter_house", "knights_of_santiago_major_ch", null, sergSpear,
				1, UnitReplenishRate.R13, 2, 0, factionsRequirement + " and " + orderHousesRequirement);


		// #####  C A S T L E   Order Houses #####
		_ExportDescrBuilding.insertRecruitmentBuildingCapabilities("templars_chapter_house_castle", "templars_minor_ch", "castle", sergSpear,
				1, UnitReplenishRate.R12, 1, 0, factionsRequirement + " and " + orderHousesRequirement);
		_ExportDescrBuilding.insertRecruitmentBuildingCapabilities("templars_chapter_house_castle", "templars_major_ch", "castle", sergSpear,
				1, UnitReplenishRate.R10, 2, 0, factionsRequirement + " and " + orderHousesRequirement);

		_ExportDescrBuilding.insertRecruitmentBuildingCapabilities("st_johns_chapter_house_castle", "st_johns_minor_ch", "castle", sergSpear,
				1, UnitReplenishRate.R12, 1, 0, factionsRequirement + " and " + orderHousesRequirement);
		_ExportDescrBuilding.insertRecruitmentBuildingCapabilities("st_johns_chapter_house_castle", "st_johns_major_ch", "castle", sergSpear,
				1, UnitReplenishRate.R10, 2, 0, factionsRequirement + " and " + orderHousesRequirement);
	}

	protected void addCaballerosVillanosToAragon() throws PatcherLibBaseEx {
		String caballerosVillanos = "Caballeros Villanos";

		_ExportDescrUnit.addOwnership(caballerosVillanos, "aragon");
		_ExportDescrUnit.addOwnership(caballerosVillanos, "aragon", 0);
		_ExportDescrUnit.addOwnership(caballerosVillanos, "aragon", 1);

		LinesProcessor lines = _DescrStrat.Factions.getContent().getLines();
		int aragonIndex = lines.findExpFirstRegexLine("^;## ARAGON ##\\s*");
		replaceInitialUnit("Alforrats","Caballeros Villanos" , aragonIndex);


		// levels stables knights_stables barons_stables earls_stables kings_stables
		String reqs = "factions { aragon, } and not event_counter ENGLISH_ARCHERS 1";

		_ExportDescrBuilding.insertRecruitmentBuildingCapabilities("equestrian" , "stables" , "castle" , caballerosVillanos , 0, UnitReplenishRate.R10, 1, 0, reqs);
		_ExportDescrBuilding.insertRecruitmentBuildingCapabilities("equestrian" , "knights_stables" , "castle" , caballerosVillanos , 0, UnitReplenishRate.R9, 1, 0, reqs);
		_ExportDescrBuilding.insertRecruitmentBuildingCapabilities("equestrian" , "barons_stables" , "castle" , caballerosVillanos , 0, UnitReplenishRate.R8, 1, 0, reqs);
		_ExportDescrBuilding.insertRecruitmentBuildingCapabilities("equestrian" , "earls_stables" , "castle" , caballerosVillanos , 0, UnitReplenishRate.R7, 1, 0, reqs);
		_ExportDescrBuilding.insertRecruitmentBuildingCapabilities("equestrian" , "kings_stables" , "castle" , caballerosVillanos , 0, UnitReplenishRate.R6, 1, 0, reqs);
	}

//	protected void addCaballerosVillanosToAragon() throws PatcherLibBaseEx {
//
//		String requirements = "factions { aragon, } and not event_counter ENGLISH_ARCHERS 1";
//
//		// stables knights_stables barons_stables earls_stables kings_stables
//		exportDescrBuilding.insertRecruitmentBuildingCapabilities("equestrian" , "stables" , "castle", "Caballeros Villanos", 0 , UnitReplenishRate.R10, 1, 0, requirements);
//		exportDescrBuilding.insertRecruitmentBuildingCapabilities("equestrian" , "knights_stables" , "castle", "Caballeros Villanos", 0 , UnitReplenishRate.R9, 1, 0, requirements);
//		exportDescrBuilding.insertRecruitmentBuildingCapabilities("equestrian" , "barons_stables" , "castle", "Caballeros Villanos", 0 , UnitReplenishRate.R8, 1, 0, requirements);
//		exportDescrBuilding.insertRecruitmentBuildingCapabilities("equestrian" , "earls_stables" , "castle", "Caballeros Villanos", 0 , UnitReplenishRate.R7, 1, 0, requirements);
//		exportDescrBuilding.insertRecruitmentBuildingCapabilities("equestrian" , "kings_stables" , "castle", "Caballeros Villanos", 0 , UnitReplenishRate.R6, 1, 0, requirements);
//
//	}

	protected void addUrbanMilitia() throws PatcherLibBaseEx {
		// #### Add Urban Spear Militia - with -2 replenish rate ####
		// ## City ## : levels town_watch town_guard city_watch militia_drill_square militia_barracks army_barracks royal_armoury

		_ExportDescrBuilding.insertIntoBuildingCapabilities("barracks", "city_watch", "city",	// old : town_watch
				"\t\t\trecruit_pool  \"Urban Spear Militia\"  1   0.13   2  0  requires factions { spain, aragon, portugal, } ; Patcher Added");

		_ExportDescrBuilding.insertIntoBuildingCapabilities("barracks", "militia_drill_square", "city",	// old : town_guard
				"\t\t\trecruit_pool  \"Urban Spear Militia\"  1   0.17   3  0  requires factions { spain, aragon, portugal, } ; Patcher Added");

		// ## Castle ## levels mustering_hall garrison_quarters drill_square barracks armoury

//		exportDescrBuilding.insertIntoBuildingCapabilities("castle_barracks", "garrison_quarters", "castle",
//				"\t\t\trecruit_pool  \"Urban Spear Militia\"  1   0.13   1  1  requires factions { spain, aragon, portugal, } ; Patcher Added");

		_ExportDescrBuilding.insertIntoBuildingCapabilities("castle_barracks", "drill_square", "castle",
				"\t\t\trecruit_pool  \"Urban Spear Militia\"  1   0.13   1  1  requires factions { spain, aragon, portugal, } ; Patcher Added");

		_ExportDescrBuilding.insertIntoBuildingCapabilities("castle_barracks", "barracks", "castle",
				"\t\t\trecruit_pool  \"Urban Spear Militia\"  1   0.13   1  1  requires factions { spain, aragon, portugal, } ; Patcher Added");

		_ExportDescrBuilding.insertIntoBuildingCapabilities("castle_barracks", "armoury", "castle",
				"\t\t\trecruit_pool  \"Urban Spear Militia\"  1   0.13   1  1  requires factions { spain, aragon, portugal, } ; Patcher Added");
	}

	@Override
	public UUID getId() {
		return Id;
	}
	public static UUID Id = UUID.randomUUID();

	public CatholicIberiaUnitsRecruitmentIncreased() {

		super("Catholic Iberia unit recruitment increased");

		addCategory("Campaign");
		addCategory("Units");

		setDescriptionShort("Spain & Aragon &, Portugal have better recruitment options");
		setDescriptionUrl("http://tmsship.wikidot.com/catholic-iberia-units-recruitment-increased");
		addOverrideTask(new OverrideCopyTask("UnitInfos"));
	}
}
