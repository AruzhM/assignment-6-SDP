package com.aruzh.visitor;
import com.aruzh.element.*;

public class QualityGateVisitor implements ProjectVisitor {

    private int sourceFiles = 0;
    private int testFiles = 0;
    private int totalComplexity = 0;
    private int criticalIssues = 0;

    @Override
    public void visit(SourceCodeFile file) {
        sourceFiles++;
        totalComplexity += file.getComplexity();

        if (file.hasHardcodedCredentials()) {
            criticalIssues++;
        }
    }

    @Override
    public void visit(TestFile file) {
        testFiles++;
    }

    @Override
    public void visit(ConfigurationFile file) {
        if (file.hasExposedCredentials()) {
            criticalIssues++;
        }
    }

    @Override
    public void visit(DependencyFile file) {
        if (file.hasVulnerabilities()) {
            criticalIssues++;
        }
    }

    @Override
    public void visit(Directory directory) {
    }

    public double getAverageComplexity() {
        return sourceFiles == 0 ? 0 :
                (double) totalComplexity / sourceFiles;
    }

    public double getTestSourceRatio() {
        return sourceFiles == 0 ? 0 :
                (double) testFiles / sourceFiles;
    }

    public int getCriticalIssues() {
        return criticalIssues;
    }

    public boolean isSecurityPassed() {
        return criticalIssues == 0;
    }

    public boolean isComplexityPassed() {
        return getAverageComplexity() <= 10;
    }

    public boolean isTestsPassed() {
        return sourceFiles > 0 && getTestSourceRatio() >= 0.40;
    }

    public boolean isReleaseReady() {
        return isSecurityPassed()
                && isComplexityPassed()
                && isTestsPassed();
    }

    public void printReport() {
        System.out.println("=== QUALITY GATE ===");
        System.out.println("Security: " + (isSecurityPassed() ? "PASS" : "FAIL"));
        System.out.println("Complexity: " + (isComplexityPassed() ? "PASS" : "FAIL"));
        System.out.println("Tests: " + (isTestsPassed() ? "PASS" : "FAIL"));
        System.out.println("FINAL RESULT: " + (isReleaseReady() ? "PASSED" : "FAILED"));
    }
}
