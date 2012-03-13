/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.peacocks.resourcepicker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author edvicif
 */
public class ResourceEnumerationGeneratorTest {
    
    private final ResourceEnumerationGenerator resourceEnumerationGenerator = ResourceEnumerationGenerator.INSTANCE;
    
    @Test
    public void testPackageGrouping() {
        List<ResourceSubject> testResourceSubjects = new ArrayList<>();
        testResourceSubjects.add(ResourceSubject.Builder.instance().packageName("pkg1.pkg1").resourceName("res1").resourceType("tst").build());
        testResourceSubjects.add(ResourceSubject.Builder.instance().packageName("pkg2.pkg2").resourceName("res2").resourceType("tst").build());
        testResourceSubjects.add(ResourceSubject.Builder.instance().packageName("pkg2.pkg2").resourceName("res3").resourceType("tst").build());
        
        Map<String, List<ResourceSubject>> result = resourceEnumerationGenerator.groupResouceByPackage(testResourceSubjects);
        assertEquals(2, result.size());
        assertEquals(1, result.get("pkg1.pkg1").size());
        assertEquals(2, result.get("pkg2.pkg2").size());
    }
    
    @Test
    public void testEnumGeneration() {
        List<ResourceSubject> testResourceSubjects = new ArrayList<>();
        testResourceSubjects.add(ResourceSubject.Builder.instance().packageName("").resourceName("res1").resourceType("tst").build());
        testResourceSubjects.add(ResourceSubject.Builder.instance().packageName("pkg1.pkg1").resourceName("res1").resourceType("tst").build());
        testResourceSubjects.add(ResourceSubject.Builder.instance().packageName("pkg2.pkg2").resourceName("res2").resourceType("tst").build());
        testResourceSubjects.add(ResourceSubject.Builder.instance().packageName("pkg2.pkg2").resourceName("res3").resourceType("tst").build());
        
        Map<String, String> result = resourceEnumerationGenerator.generateEnumerations(testResourceSubjects);
        String resourceDefinition = result.get("pkg2.pkg2");
        assertTrue(resourceDefinition.contains("package pkg2.pkg2;"));
        
        String resourceInDefaultPackage = result.get("");
        assertFalse(resourceInDefaultPackage.contains("package"));
        assertFalse(resourceInDefaultPackage.contains("//res1"));
    }
}
