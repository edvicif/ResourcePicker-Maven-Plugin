package org.peacocks.resourcepicker;

import java.util.Objects;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

/**
 *
 * @author edvicif
 */
public class ResourceSubject {

    private final String packageName;
    private final String resourceName;
    private final String resourceType;

    public ResourceSubject(String packageName, String resourceName, String resourceType) {
        Validate.notNull(packageName);
        Validate.notNull(resourceName);
        Validate.notNull(resourceType);

        this.packageName = packageName;
        this.resourceName = resourceName;
        this.resourceType = resourceType;
    }
    
    public String getEnumName(){
        StringBuilder enumName = new StringBuilder(resourceName.toUpperCase());
        if(StringUtils.isNotEmpty(resourceType)){
            enumName.append("_").append(resourceType.toUpperCase());
        }
        return enumName.toString();
                
    }

    public String getPackageName() {
        return packageName;
    }
    
    public String getClassPath(){
        return packageName.replace(".", "/") ;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getResourceType() {
        return resourceType;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ResourceSubject other = (ResourceSubject) obj;
        if (!Objects.equals(this.packageName, other.packageName)) {
            return false;
        }
        if (!Objects.equals(this.resourceName, other.resourceName)) {
            return false;
        }
        if (!Objects.equals(this.resourceType, other.resourceType)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.packageName);
        hash = 29 * hash + Objects.hashCode(this.resourceName);
        hash = 29 * hash + Objects.hashCode(this.resourceType);
        return hash;
    }
    public static class Builder {

        private String packageName = "";
        private String resourceName = "";
        private String resourceType = "";

        public Builder packageName(String packageName) {
            this.packageName = packageName;
            return this;
        }

        public Builder resourceName(String resourceName) {
            this.resourceName = resourceName;
            return this;
        }

        public Builder resourceType(String resourceType) {
            this.resourceType = resourceType;
            return this;
        }

        public static Builder instance() {
            return new Builder();
        }

        public ResourceSubject build() {
            return new ResourceSubject(packageName, resourceName, resourceType);
        }
    }
}
