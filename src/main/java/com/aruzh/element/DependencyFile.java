package com.aruzh.element;
import com.aruzh.visitor.ProjectVisitor;

public class DependencyFile implements ProjectElement {

    private String name;
    private int dependencyCount;
    private boolean hasVulnerabilities;
    private boolean versionsSpecified;
    private boolean hasProhibitedDependencies;

    public DependencyFile(String name, int dependencyCount,
                          boolean hasVulnerabilities,
                          boolean versionsSpecified,
                          boolean hasProhibitedDependencies) {
        this.name = name;
        this.dependencyCount = dependencyCount;
        this.hasVulnerabilities = hasVulnerabilities;
        this.versionsSpecified = versionsSpecified;
        this.hasProhibitedDependencies = hasProhibitedDependencies;
    }

    public String getName() {
        return name;
    }

    public int getDependencyCount() {
        return dependencyCount;
    }

    public boolean hasVulnerabilities() {
        return hasVulnerabilities;
    }

    public boolean isVersionsSpecified() {
        return versionsSpecified;
    }

    public boolean hasProhibitedDependencies() {
        return hasProhibitedDependencies;
    }

    @Override
    public void accept(ProjectVisitor visitor) {
        visitor.visit(this);
    }
}
