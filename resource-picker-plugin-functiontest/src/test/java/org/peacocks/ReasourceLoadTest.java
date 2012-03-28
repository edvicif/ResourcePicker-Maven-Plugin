/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.peacocks;

import org.junit.Test;
import testRes.R;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 *
 * @author edvicif
 */
public class ReasourceLoadTest {


    @Test
     public void testNormalResourcesGenerated() {
         assertNotNull(this.getClass().getResourceAsStream(sub.R.SAMPLE_XML.getPathToResource()));
     }

     @Test
     public void testTestResourcesGenerated() {
         assertNotNull(this.getClass().getResourceAsStream(testRes.R.TESTRESOURCE_TXT.getPathToResource()));
     }

    @Test
    public void testTestPropertieFileAccesibleAsPropertiesObject() {
        assertEquals("42",R.PROPERTIES.TESTPROPERTIES_PROPERTIES.asProperties().get("test"));
        assertEquals("hello",R.PROPERTIES.TESTPROPERTIES2_PROPERTIES.asProperties().get("test"));
    }
}
