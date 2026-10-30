package com.aruzh.visitor;
import com.aruzh.element.*;
import java.util.ArrayList;
import java.util.List;

public class ComplianceVisitor implements ProjectVisitor {

    private final List<String> violations = new ArrayList<>();
    private int sourceFiles = 0;
    private int testFiles = 0;

    @Override
    public void visit(SourceCodeFile file) {
        sourceFiles++;

        if (file.hasHardcodedCredentials()) {
            violations.add("Exposed credentials in " + file.getName());
        }
    }

    @Override
    public void visit(TestFile file) {
        testFiles++;
    }

    @Override
    public void visit(ConfigurationFile file) {
        if (file.hasExposedCredentials()) {
            violations.add("Exposed credentials in " + file.getName());
        }
    }

    @Override
    public void visit(DependencyFile file) {
        if (!file.isVersionsSpecified()) {
            violations.add("Missing dependency versions in " + file.getName());
        }

        if (file.hasProhibitedDependencies()) {
            violations.add("Prohibited dependencies in " + file.getName());
        }
    }

    @Override
    public void visit(Directory directory) {
    }

    public List<String> getViolations() {
        return violations;
    }

    public boolean isCompliant() {
        return violations.isEmpty() && (sourceFiles == 0 || testFiles > 0);
    }

    public void printReport() {
        System.out.println("=== COMPLIANCE REPORT ===");

        for (String violation : violations) {
            System.out.println("- " + violation);
        }

        if (sourceFiles > 0 && testFiles == 0) {
            System.out.println("- Production code has no tests");
        }

        System.out.println("FINAL RESULT: " +
                (isCompliant() ? "COMPLIANT" : "NON-COMPLIANT"));
    }
}
