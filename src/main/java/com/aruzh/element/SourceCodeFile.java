package com.aruzh.element;
import com.aruzh.visitor.ProjectVisitor;

public class SourceCodeFile implements ProjectElement {

    private String name;
    private int linesOfCode;
    private int complexity;
    private boolean hasHardcodedCredentials;

    public SourceCodeFile(String name, int linesOfCode,
                          int complexity, boolean hasHardcodedCredentials) {
        this.name = name;
        this.linesOfCode = linesOfCode;
        this.complexity = complexity;
        this.hasHardcodedCredentials = hasHardcodedCredentials;
    }

    public String getName() {
        return name;
    }

    public int getLinesOfCode() {
        return linesOfCode;
    }

    public int getComplexity() {
        return complexity;
    }

    public boolean hasHardcodedCredentials() {
        return hasHardcodedCredentials;
    }

    @Override
    public void accept(ProjectVisitor visitor) {
        visitor.visit(this);
    }
}
