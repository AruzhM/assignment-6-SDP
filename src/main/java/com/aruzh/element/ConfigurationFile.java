package com.aruzh.element;
import com.aruzh.visitor.ProjectVisitor;

public class ConfigurationFile implements ProjectElement {

    private String name;
    private boolean secure;
    private boolean hasExposedCredentials;

    public ConfigurationFile(String name, boolean secure,
                             boolean hasExposedCredentials) {
        this.name = name;
        this.secure = secure;
        this.hasExposedCredentials = hasExposedCredentials;
    }

    public String getName() {
        return name;
    }

    public boolean isSecure() {
        return secure;
    }

    public boolean hasExposedCredentials() {
        return hasExposedCredentials;
    }

    @Override
    public void accept(ProjectVisitor visitor) {
        visitor.visit(this);
    }
}
