package tm.mtwModPatcher.sship.features.buildings;

import tm.mtwModPatcher.lib.common.core.features.Feature;
import tm.mtwModPatcher.lib.data._root.DescrSettlementMechanics;

import java.util.UUID;

/**
 * Created by Tomek on 2016-04-15.
 */
public class CastleEasierGrowth extends Feature {

	protected DescrSettlementMechanics _DescrSettlementMechanics;

	@Override
	public void executeUpdates() throws Exception {

		_DescrSettlementMechanics = fileEntityFactory.getFile(DescrSettlementMechanics.class);

		//_DescrSettlementMechanics.UpdateAttribute("/root/population_levels/level[@name='castle']", "upgrade", 100);

		_DescrSettlementMechanics.SetCityPopulationCastleData(1, 	300, 	500, 	1000, 	5000);	// motto & bailey	-> upgrade org 1500
		_DescrSettlementMechanics.SetCityPopulationCastleData(2, 	500, 	800, 	4000, 	12000);	// wooden castle, old 4250	-> upgrade org 5000
		_DescrSettlementMechanics.SetCityPopulationCastleData(3, 	500, 	1000, 	9000, 	25000);	// castle 			-> upgrade org 12000


		registerUpdatedFile(_DescrSettlementMechanics);
	}

	@Override
	public UUID getId() {
		return Id;
	}
	public static UUID Id = UUID.fromString("");

	public CastleEasierGrowth() {
		super("Castle easier growth");

		addCategory("Campaign");

		setDescriptionShort("Easier Castle upgrades ,lower population requirements for the castle first 3 levels");
		setDescriptionUrl("http://tmsship.wikidot.com/castle-easier-growth");
	}
}
