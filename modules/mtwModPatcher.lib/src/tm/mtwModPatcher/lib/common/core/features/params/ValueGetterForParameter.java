package tm.mtwModPatcher.lib.common.core.features.params;

import tm.mtwModPatcher.lib.common.core.features.Feature;

/**
 * Created by tomek on 19.04.2017.
 */
public interface ValueGetterForParameter<T> {
	T getValueForParam(Feature feature);
}