/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.peacocks;

import org.junit.*;
import static org.junit.Assert.*;

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
}
