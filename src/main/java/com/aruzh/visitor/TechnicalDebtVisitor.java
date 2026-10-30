package com.aruzh.visitor;
import com.aruzh.element.*;
import java.util.ArrayList;
import java.util.List;

public class TechnicalDebtVisitor implements ProjectVisitor {

    private final List<String> issues = new ArrayList<>();
    private int sourceFiles = 0;
    private int testFiles = 0;
    private int debtPoints = 0;

    @Override
    public void visit(SourceCodeFile file) {
        sourceFiles++;

        if (file.getComplexity() > 10) {
            issues.add("High complexity: " + file.getName());
            debtPoints += 10;
        }

        if (file.getLinesOfCode() > 300) {
            issues.add("Large source file: " + file.getName());
            debtPoints += 5;
        }
    }

    @Override
    public void visit(TestFile file) {
        testFiles++;

        if (!file.isPassing()) {
            issues.add("Failing tests: " + file.getName());
            debtPoints += 10;
        }
    }

    @Override
    public void visit(ConfigurationFile file) {
    }

    @Override
    public void visit(DependencyFile file) {
    }

    @Override
    public void visit(Directory directory) {
    }

    public int getDebtPoints() {
        return getTotalDebtPoints();
    }


    public List<String> getIssues() {
        return issues;
    }

    public double getTestSourceRatio() {
        return sourceFiles == 0 ? 0 :
                (double) testFiles / sourceFiles;
    }

    public boolean hasLowTestCoverage() {
        return sourceFiles > 0 && getTestSourceRatio() < 0.40;
    }

    public int getTotalDebtPoints() {
        return debtPoints + (hasLowTestCoverage() ? 15 : 0);
    }


    public void printReport() {
        System.out.println("=== TECHNICAL DEBT REPORT ===");

        for (String issue : issues) {
            System.out.println("- " + issue);
        }

        if (hasLowTestCoverage()) {
            System.out.println("- Low test coverage");
        }

        System.out.println("Technical Debt Points: " + getTotalDebtPoints());
    }

}
