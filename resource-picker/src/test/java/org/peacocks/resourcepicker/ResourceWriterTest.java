/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.peacocks.resourcepicker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author edvicif
 */
public class ResourceWriterTest {
    
    @Rule
    public TemporaryFolder targetFolder = new TemporaryFolder();
    
    @Test
    public void testResourceGeneration() throws IOException {
        Map<String, String> resource = new HashMap<>();
        System.out.println(targetFolder.getRoot().getAbsolutePath());
        resource.put("", "content");
        resource.put("pkg.sub", "sub pkg content");
        
        Path targetPath = targetFolder.getRoot().toPath();
        ResourceWriter.INSTANCE.writeResource(targetPath, resource);
        
        assertTrue(Files.exists(targetPath.resolve("R.java"))) ;
        String content = new String(Files.readAllBytes(targetPath.resolve("R.java")));
        assertEquals("content", content);
        
        assertTrue(Files.exists(targetPath.resolve("pkg/sub/R.java"))) ;
        assertEquals("sub pkg content", new String(Files.readAllBytes(targetPath.resolve("pkg/sub/R.java"))));
        
    }
}
