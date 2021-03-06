package tm.mtwModPatcher.sship.features.layout;

import tm.mtwModPatcher.lib.common.core.features.Feature;
import tm.mtwModPatcher.lib.common.core.features.OverrideCopyTask;

import java.util.UUID;

/** Created by Tomek on 2016-07-18. */
public class MenuScreenSS extends Feature {

	@Override
	public void setParamsCustomValues() {

	}

	@Override
	public void executeUpdates() throws Exception {	}

	@Override
	public UUID getId() {
		return Id;
	}
	public static UUID Id = UUID.fromString("043634b6-cdd0-4bff-b7c9-b81d57199110");

	public MenuScreenSS() {
		super("Stainless Steel menu picture");

		addCategory("Various");

		setDescriptionShort("Restores beautiful Stainless Steel menu picture");
		setDescriptionUrl("http://tmsship.wikidot.com/stainless-steel-menu-picture");

		addOverrideTask(new OverrideCopyTask("MenuScreenSS"));
	}
}
