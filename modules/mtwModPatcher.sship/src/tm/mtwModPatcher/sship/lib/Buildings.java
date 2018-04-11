package tm.mtwModPatcher.sship.lib;

import java.util.Arrays;
import java.util.List;

/**
 * Created by tomek on 14.10.2017.
 */
public class Buildings {

	// ### Guilds ###
	public static final String MerchantsGuild = "guild_merchants_guild";
	public static final List<String> MerchantsGuildLevels = Arrays.asList("merchants_guild", "m_merchants_guild", "gm_merchants_guild");

	public static final String ExplorersGuild = "guild_explorers_guild";
	public static final List<String> ExplorersLevels = Arrays.asList("explorers_guild", "m_explorers_guild", "gm_explorers_guild");

	// #### Markets ####
	public static final String MarketCity = "market";
	public static final String MarketCastle = "market_castle";
	public static final List<String> MarketCityLevels = Arrays.asList("corn_exchange", "market", "fairground", "great_market", "merchants_quarter");
	public static final List<String> MarketCastleLevels = Arrays.asList("corn_exchange", "market", "fairground");

	public  static final String RoadCity = "hinterland_roads";
	public  static final String RoadCastle = "hinterland_castle_roads";
	public static final List<String> RoadCityLevels = Arrays.asList("roads", "paved_roads", "highways");
	public static final List<String> RoadCastleLevels = Arrays.asList("c_roads", "c_paved_roads", "c_highways");

	public static final String RiverAccess = "river_access";
	public static final String RiverAccessLevel = "river_port";

	public  static final String PortCity = "port";
	public static final List<String> PortCityLevels = Arrays.asList("port", "shipwright" ,"dockyard" ,"naval_drydock");
	public  static final String PortCastle = "castle_port";
	public static final List<String> PortCastleLevels = Arrays.asList("c_port", "c_shipwright", "c_dockyard", "c_naval_drydock");

	public  static final String SeaTradeCity = "sea_trade";
	public  static final String SeaTradeCastle = "castle_sea_trade";
	public static final List<String> SeaTradeCityLevels = Arrays.asList("merchants_wharf", "warehouse", "docklands");
	public static final List<String> SeaTradeCastleLevels = Arrays.asList("merchants_wharf");

	public static final String TavernCity = "taverns";
	public static final String TavernCastle = "castle_taverns";
	public static final List<String> TavernCityLevels = Arrays.asList("brothel", "inn", "tavern", "coaching_house", "pleasure_palace");
	public static final List<String> TavernCastleLevels = Arrays.asList("c_brothel");

	public static final String StoneMason = "stonemason";
	public static final List<String> StoneMasonLevels = Arrays.asList("sitehut", "stonemason");

	public static final String LoggingCamp = "logging_camp";
	public static final List<String> LoggingCampLevels = Arrays.asList("logging_camp", "carpenter");

	public static final String SmithCity = "smith";
	public static final List<String> SmithCityLevels = Arrays.asList("leather_tanner", "blacksmith", "armourer", "heavy_armourer", "plate_armourer", "gothic_armourer");
	public static final String SmithCastle = "castle_smith";
	public static final List<String> SmithCastleLevels = Arrays.asList("c_leather_tanner", "c_blacksmith", "c_armourer", "c_heavy_armourer", "c_plate_armourer", "c_gothic_armourer");

	public static final String BakeryCity = "bakery";
	public static final List<String> BakeryCityLevels = Arrays.asList("bakery", "pastry_shop");
	public static final String BakeryCastle = "castle_bakery";
	public static final String BakeryCastleLevel = "c_bakery";

	public static final String TempleMuslimCity = "temple_muslim";
	public static final String TempleMuslimCastle = "temple_muslim_castle";

	public static final String TempleCatholicCity = "temple_catholic";
	public static final List<String> TempleCatholicCityLevels = Arrays.asList("small_church", "church", "abbey", "cathedral", "huge_cathedral");
	public static final String TempleCatholicCastle = "temple_catholic_castle";
	public static final List<String> TempleCatholicCastleLevels = Arrays.asList("small_chapel", "chapel");


	public static final String MonasteryCatholicCity = "friar";
	public static final List<String> MonasteryCatholicCityLevels = Arrays.asList("monastery", "medical_care", "hospital");
	public static final String MonasteryCatholicCastle = "monastery_castle";
	public static final List<String> MonasteryCatholicCastleLevels = Arrays.asList("castle_monastery");

	public static final String Farms = "farms";
	public static final List<String> FarmsLevels = Arrays.asList("farms","farms+1","farms+2","farms+3","farms+4");

	public static final String WaterSupply = "water_supply";
	public static final List<String> WaterSupplyLevels = Arrays.asList("well", "water_conductor", "aqueduct");

	public static final String Health = "health";
	public static final List<String> HealthLevels = Arrays.asList("bath_house", "public_baths");

	public static final String MinesCastle = "castle_mines";
	public static final List<String> MinesCastleLevels = Arrays.asList("c_mines", "c_mines+1");

	public static final String BarracksCastle = "castle_barracks";
	public static final List<String> BarracksCastleLevels = Arrays.asList("mustering_hall", "garrison_quarters", "drill_square", "barracks", "armoury");

	public static final String MerchantGuild = "guild_merchants_guild";
	public static final List<String> MerchantGuildLevels = Arrays.asList("merchants_guild", "m_merchants_guild", "gm_merchants_guild");





	public final static tm.mtwModPatcher.lib.data.exportDescrBuilding.buildings.WallsCity WallsCity =
			tm.mtwModPatcher.lib.data.exportDescrBuilding.buildings.Buildings.WallsCity;

	public final static tm.mtwModPatcher.lib.data.exportDescrBuilding.buildings.WallsCastle WallsCastle =
			tm.mtwModPatcher.lib.data.exportDescrBuilding.buildings.Buildings.WallsCastle;

	public static final List<String> WallsCityLevels = Arrays.asList(WallsCity.L1_WoodenPalisade, WallsCity.L2_WoodenWall, WallsCity.L3_StoneWall, WallsCity.L4_LargeStoneWall, WallsCity.L5_HugeStoneWall);
	public static final List<String> WallsCastleLevels = Arrays.asList(WallsCastle.L1_MotteAndBailey, WallsCastle.L2_WoodenCastle, WallsCastle.L3_Castle, WallsCastle.L4_Fortress, WallsCastle.L5_Citadel);

	public final static String CityType = tm.mtwModPatcher.lib.data.exportDescrBuilding.buildings.Buildings.CityType;
	public final static String CastleType = tm.mtwModPatcher.lib.data.exportDescrBuilding.buildings.Buildings.CastleType;
}
