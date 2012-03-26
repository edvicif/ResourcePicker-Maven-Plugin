/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.peacocks.resourcepicker;

import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author edvicif
 */
public class ResourceSubjectTest {

    @Test(expected=IllegalArgumentException.class)
    public void testNullPackageNameNotAllowed(){
        new ResourceSubject(null, "", "");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testNullResourceNameNotAllowed(){
        new ResourceSubject("", null, "");
    }
    @Test(expected=IllegalArgumentException.class)
    public void testNullResourceTypeNotAllowed(){
        new ResourceSubject("", "", null);
    }

    @Test
    public void testEnumNameGeneration() {
        ResourceSubject resourceSubject = ResourceSubject.Builder.instance().resourceName("name.this").resourceType("type").build();
        assertEquals("NAME.THIS_TYPE", resourceSubject.getEnumName());
        
        ResourceSubject resourceSubjectNoType = ResourceSubject.Builder.instance().resourceName("name.this").build();
        assertEquals("NAME.THIS", resourceSubjectNoType.getEnumName());
    }
}
