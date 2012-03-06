package org.peacocks.resourcepicker;

import java.io.IOException;
import java.nio.file.*;
import java.util.Map;

/**
 * @author edvicif
 */
public enum ResourceWriter {
    INSTANCE;
    
    public void writeResource(Path targetPath, Map<String, String> resourceDefintions) throws IOException{
        writeResource(targetPath, resourceDefintions, "R.java");
    }
    public void writeResource(Path targetPath, Map<String, String> resourceDefintions, String resourceFileName) throws IOException{
        if(!Files.isDirectory(targetPath)){
            throw new IllegalArgumentException("Please specify to target directory");
        }
        
        if(!Files.exists(targetPath)){
            Files.createDirectory(targetPath);
        }
        
        String separator = FileSystems.getDefault().getSeparator();
        for(String resourcePackageName : resourceDefintions.keySet()){
            String packageURL = resourcePackageName.replace(".", separator);
            Path packagePath = targetPath.resolve(packageURL);
            if(!Files.exists(packagePath)){
                Files.createDirectories(packagePath);
            }
            Path resourceFile = packagePath.resolve(resourceFileName);
            Files.write(resourceFile, resourceDefintions.get(resourcePackageName).getBytes(), StandardOpenOption.CREATE );
        }
    }
}
