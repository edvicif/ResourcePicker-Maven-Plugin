package org.peacocks.resourcepicker;

import java.io.StringWriter;
import java.util.*;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.context.Context;

/**
 *
 * @author edvicif
 */
public enum ResourceEnumerationGenerator {
	
    INSTANCE;

    public Map<String, String> generateEnumerations(List<ResourceSubject> resourceSubjects) {
        Map<String, List<ResourceSubject>> resourceSubjectsByPackage = groupResouceByPackage(resourceSubjects);
        Velocity.setProperty(Velocity.RESOURCE_LOADER, "class");
        Velocity.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

        Velocity.init();
        Template enumTemplate = Velocity.getTemplate("/EnumTemplate.vm");
        Map<String, String> resourceEnums = new HashMap<>();
        
        for (String packageName : resourceSubjectsByPackage.keySet()) {
            Context context = new VelocityContext();
            context.put("packageName", packageName);
            context.put("resources", resourceSubjectsByPackage.get(packageName));
            context.put("properties", filterProperties(resourceSubjectsByPackage.get(packageName)));
            StringWriter w = new StringWriter();
            enumTemplate.merge(context, w);
            resourceEnums.put(packageName, w.toString());
        }
        
        return resourceEnums;
    }

    private List<ResourceSubject> filterProperties(List<ResourceSubject> resourceSubjects) {
        List<ResourceSubject> properties = new ArrayList<>();
        for (ResourceSubject resourceSubject : resourceSubjects) {
            if ("properties".equals(resourceSubject.getResourceType()))
                properties.add(resourceSubject);
        }
        return  properties;
    }

    Map<String, List<ResourceSubject>> groupResouceByPackage(List<ResourceSubject> resourceSubjects) {
        Map<String, List<ResourceSubject>> resourcesBySubjects = new HashMap<>();
        for (ResourceSubject resourceSubject : resourceSubjects) {
            if (!resourcesBySubjects.containsKey(resourceSubject.getPackageName())) {
                resourcesBySubjects.put(resourceSubject.getPackageName(), new LinkedList<ResourceSubject>());
            }

            resourcesBySubjects.get(resourceSubject.getPackageName()).add(resourceSubject);
        }
        return resourcesBySubjects;
    }
}
