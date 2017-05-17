package tm.mtwModPatcher.sship.agentsCharacters;

import tm.mtwModPatcher.lib.common.core.features.Feature;
import tm.mtwModPatcher.lib.common.core.features.OverrideCopyTask;
import tm.mtwModPatcher.lib.common.core.features.OverrideDeleteFilesTask;

import java.util.UUID;

/**
 * Created by Tomek on 2016-11-11.
 */
public class ProvincialTitlesFixJoC extends Feature {

	@Override
	public UUID getId() {
		return Id;
	}

	@Override
	public void executeUpdates() throws Exception {	}

	public static UUID Id = UUID.randomUUID();

	public ProvincialTitlesFixJoC() {

		super("Provincial Titles v2 [JoC]");

		setDescriptionShort("PTF: provincial titles’ fix for SSHIP 0.9.2 by Jurand of Cracov");
		setDescriptionUrl("http://www.twcenter.net/forums/showthread.php?741452-PTF-provincial-titles%92-fix-for-SSHIP-0-9-2");

		addOverrideTask(new OverrideCopyTask("JoC-Provincial-Titles"));
		addOverrideTask(new OverrideDeleteFilesTask("data\\text\\export_ancillaries.txt.strings.bin"));
	}
}