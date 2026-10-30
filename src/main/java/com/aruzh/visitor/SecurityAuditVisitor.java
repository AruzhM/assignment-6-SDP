package com.aruzh.visitor;
import com.aruzh.element.*;
import java.util.ArrayList;
import java.util.List;

public class SecurityAuditVisitor implements ProjectVisitor {

    private final List<String> issues = new ArrayList<>();

    private int criticalIssues = 0;

    @Override
    public void visit(SourceCodeFile file) {
        if (file.hasHardcodedCredentials()) {
            issues.add("Hardcoded credentials in " + file.getName());
            criticalIssues++;
        }

        if (file.getComplexity() > 15) {
            issues.add("High-risk source file: " + file.getName());
        }
    }

    @Override
    public void visit(TestFile file) {
        if (!file.isPassing()) {
            issues.add("Failing tests in " + file.getName());
        }
    }

    @Override
    public void visit(ConfigurationFile file) {
        if (!file.isSecure()) {
            issues.add("Insecure configuration: " + file.getName());
        }

        if (file.hasExposedCredentials()) {
            issues.add("Exposed credentials in " + file.getName());
            criticalIssues++;
        }
    }

    @Override
    public void visit(DependencyFile file) {
        if (file.hasVulnerabilities()) {
            issues.add("Vulnerable dependencies in " + file.getName());
            criticalIssues++;
        }
    }

    @Override
    public void visit(Directory directory) {

    }

    public List<String> getIssues() {
        return issues;
    }

    public int getSecurityScore() {
        return Math.max(0, 100 - issues.size() * 10);
    }

    public void printReport() {
        System.out.println("=== SECURITY AUDIT ===");
        System.out.println("Security Score: " + getSecurityScore());

        if (issues.isEmpty()) {
            System.out.println("No security issues found.");
        } else {
            for (String issue : issues) {
                System.out.println("- " + issue);
            }
        }
    }

    public int getCriticalIssues() {
        return criticalIssues;
    }

}
