package tm.mtwModPatcher.lib.common.core.features.fileEntities;

import java.io.InputStream;

/**
 * Created by tomek on 28.04.2017.
 */
public interface InputStreamProvider {

	InputStream getInputStream(String fullPath);

}
