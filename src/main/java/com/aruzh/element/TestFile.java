package com.aruzh.element;
import com.aruzh.visitor.ProjectVisitor;

public class TestFile implements ProjectElement {

    private String name;
    private int linesOfCode;
    private int testCount;
    private boolean passing;

    public TestFile(String name, int linesOfCode,
                    int testCount, boolean passing) {
        this.name = name;
        this.linesOfCode = linesOfCode;
        this.testCount = testCount;
        this.passing = passing;
    }

    public String getName() {
        return name;
    }

    public int getLinesOfCode() {
        return linesOfCode;
    }

    public int getTestCount() {
        return testCount;
    }

    public boolean isPassing() {
        return passing;
    }

    @Override
    public void accept(ProjectVisitor visitor) {
        visitor.visit(this);
    }
}
