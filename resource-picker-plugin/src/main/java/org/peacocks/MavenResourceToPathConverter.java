package org.peacocks;

import java.nio.file.Path;
import java.util.List;
import org.apache.maven.model.Build;
import org.apache.maven.model.Resource;

/**
 *
 * @author edvicif
 */
public interface MavenResourceToPathConverter {

    List<Path> getResourceDirectories(Build mavenBuild, List<Resource> mavenResourceDirs);

}
