package com.aruzh.visitor;
import com.aruzh.element.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QualityGateVisitorTest {

    @Test
    void shouldPassQualityGate() {
        Directory project = new Directory("safe-project");

        project.addElement(new SourceCodeFile("PaymentService.java", 150, 8, false));
        project.addElement(new SourceCodeFile("UserService.java", 100, 10, false));
        project.addElement(new TestFile("PaymentServiceTest.java", 80, 5, true));

        QualityGateVisitor visitor = new QualityGateVisitor();
        project.accept(visitor);

        assertEquals(9.0, visitor.getAverageComplexity());
        assertEquals(0.5, visitor.getTestSourceRatio());
        assertEquals(0, visitor.getCriticalIssues());
        assertTrue(visitor.isReleaseReady());
    }

    @Test
    void shouldFailQualityGate() {
        Directory project = new Directory("risky-project");

        project.addElement(new SourceCodeFile("PaymentService.java", 150, 18, true));
        project.addElement(new SourceCodeFile("UserService.java", 100, 14, false));

        QualityGateVisitor visitor = new QualityGateVisitor();
        project.accept(visitor);

        assertFalse(visitor.isSecurityPassed());
        assertFalse(visitor.isComplexityPassed());
        assertFalse(visitor.isTestsPassed());
        assertFalse(visitor.isReleaseReady());
    }

    @Test
    void shouldFailWithoutSourceFiles() {
        Directory project = new Directory("empty-project");

        QualityGateVisitor visitor = new QualityGateVisitor();
        project.accept(visitor);

        assertFalse(visitor.isTestsPassed());
        assertFalse(visitor.isReleaseReady());
    }
}
