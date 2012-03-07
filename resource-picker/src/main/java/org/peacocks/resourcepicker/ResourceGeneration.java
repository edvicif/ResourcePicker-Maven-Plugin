/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.peacocks.resourcepicker;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 *
 * @author edvicif
 */
public class ResourceGeneration {

    private final List<Path> sourcePaths;
    private final Path targetPath;
    
    public ResourceGeneration(List<Path> sourcePaths, Path targetPath) {
        this.sourcePaths = sourcePaths;
        this.targetPath = targetPath;
    }
    
    public void generateResourceFiles() throws IOException{
        List<ResourceSubject> resourceSubjects = new ArrayList<>();
        for(Path sourcePath : sourcePaths){
            resourceSubjects.addAll(ResourceScanner.INSTANCE.findResources(sourcePath));
        }
        Map<String,String> resourceEnums = ResourceEnumerationGenerator.INSTANCE.generateEnumerations(resourceSubjects);

        ResourceWriter.INSTANCE.writeResource(targetPath, resourceEnums);
    }
}
