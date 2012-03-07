package org.peacocks.resourcepicker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author edvicif
 */
public class ResourceGenerationTest {
    
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();
    
    @Before
    public void initTempFolder() throws IOException {
        
        Path tempDir = tempFolder.getRoot().toPath();
        
        Files.createDirectories(tempDir.resolve("source1/res1"));
        Files.createDirectories(tempDir.resolve("source1/empty"));
        Files.createDirectories(tempDir.resolve("source2/res1"));
        Files.createDirectories(tempDir.resolve("source2/res2"));
        
        Files.createFile(tempDir.resolve("source1/res1/MyResource1.txt"));
        Files.createFile(tempDir.resolve("source2/res1/MyResource2.txt"));
        Files.createFile(tempDir.resolve("source2/res2/MyResource.txt"));
    }

    @Test
    public void testResourceGenerated() throws IOException {
        Path tempDir = tempFolder.getRoot().toPath();
        
        List<Path> sourcePaths = Arrays.asList(tempDir.resolve("source1"), tempDir.resolve("source2"));
        Path targetPath = tempDir.resolve("target");
        
        ResourceGeneration resourceGeneration = new ResourceGeneration(sourcePaths, targetPath);
        resourceGeneration.generateResourceFiles();
        
        assertFalse(Files.exists(targetPath.resolve("empty")));
        assertTrue(Files.exists(targetPath.resolve("res1/R.java")));
        assertTrue(Files.exists(targetPath.resolve("res2/R.java")));
    }
}
