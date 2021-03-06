package tm.mtwModPatcher.lib.data._root;

import tm.mtwModPatcher.lib.common.core.features.PatcherLibBaseEx;
import tm.mtwModPatcher.lib.common.core.features.fileEntities.LinesProcessorFileEntity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** data / descr_cultures.txt */
public class DescrCultures extends LinesProcessorFileEntity {

	@SuppressWarnings("unused")
	public void setCharacterCost(String characterName, int cost) throws PatcherLibBaseEx {
		setCharacterCost(characterName, cost, null);
	}
	public void setCharacterCost(String characterName, int cost, Integer recruitmentTurns) throws PatcherLibBaseEx {

		// spy				spy.tga				spy_info.tga			spy.tga			350	1	1

		String regexStr = "(^"+characterName +"\\s+\\w+.tga\\s+\\w+.tga\\s+\\w+.tga\\s+)(\\d+)(\\s+)(\\d+)(\\s+\\d+)";

		int index = _lines.findFirstRegexLine(regexStr);
		// should find firts one
		if (index < 0) throw new PatcherLibBaseEx("Character '" + characterName + "' definition add not found!");

		while(index >= 0) {
			String lineOrg = _lines.getLine(index);

			Pattern pattern = Pattern.compile(regexStr);
			Matcher matcher = pattern.matcher(lineOrg);

			if (matcher.find()) {
				String prefixStr, costStr, suffixStr1, suffixStr2, recruitmentTurnsStr;

				prefixStr = matcher.group(1);
				costStr = matcher.group(2);
				suffixStr1 = matcher.group(3);
				recruitmentTurnsStr = matcher.group(4);
				suffixStr2 = matcher.group(5);

				if(recruitmentTurns != null) recruitmentTurnsStr = recruitmentTurns.toString();

				_lines.replaceLine(index, prefixStr + cost + suffixStr1+recruitmentTurnsStr+suffixStr2);
				index = _lines.findFirstRegexLine(regexStr, index+1);
			}
			else throw new PatcherLibBaseEx("Bad msgFormat of character add");
		}
	}

	public DescrCultures() {
		super("data\\descr_cultures.txt");
	}
}
