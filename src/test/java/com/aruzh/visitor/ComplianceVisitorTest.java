package com.aruzh.visitor;
import com.aruzh.element.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ComplianceVisitorTest {

    @Test
    void shouldPassCompliantProject() {
        Directory project = new Directory("safe-project");

        project.addElement(new SourceCodeFile("PaymentService.java", 150, 8, false));
        project.addElement(new TestFile("PaymentServiceTest.java", 80, 5, true));
        project.addElement(new DependencyFile("pom.xml", 4, false, true, false));
        project.addElement(new ConfigurationFile("application.properties", true, false));

        ComplianceVisitor visitor = new ComplianceVisitor();
        project.accept(visitor);

        assertTrue(visitor.isCompliant());
        assertTrue(visitor.getViolations().isEmpty());
    }

    @Test
    void shouldDetectComplianceViolations() {
        Directory project = new Directory("risky-project");

        project.addElement(new SourceCodeFile("PaymentService.java", 150, 8, true));
        project.addElement(new DependencyFile("pom.xml", 4, false, false, true));
        project.addElement(new ConfigurationFile("application.properties", false, true));

        ComplianceVisitor visitor = new ComplianceVisitor();
        project.accept(visitor);

        assertFalse(visitor.isCompliant());
        assertEquals(4, visitor.getViolations().size());
    }

    @Test
    void shouldFailWhenProductionHasNoTests() {
        Directory project = new Directory("no-tests-project");

        project.addElement(new SourceCodeFile("PaymentService.java", 150, 8, false));

        ComplianceVisitor visitor = new ComplianceVisitor();
        project.accept(visitor);

        assertFalse(visitor.isCompliant());
    }
}
