package com.aruzh.visitor;
import com.aruzh.element.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TechnicalDebtVisitorTest {

    @Test
    void shouldDetectHighTechnicalDebt() {
        Directory project = new Directory("risky-project");

        project.addElement(new SourceCodeFile("PaymentService.java", 400, 18, false));
        project.addElement(new SourceCodeFile("UserService.java", 150, 12, false));
        project.addElement(new TestFile("PaymentServiceTest.java", 80, 5, false));

        TechnicalDebtVisitor visitor = new TechnicalDebtVisitor();
        project.accept(visitor);

        assertEquals(35, visitor.getDebtPoints());
        assertEquals(4, visitor.getIssues().size());
        assertEquals(0.5, visitor.getTestSourceRatio());
    }

    @Test
    void shouldDetectLowTestCoverage() {
        Directory project = new Directory("low-coverage-project");

        project.addElement(new SourceCodeFile("File1.java", 100, 5, false));
        project.addElement(new SourceCodeFile("File2.java", 100, 5, false));
        project.addElement(new SourceCodeFile("File3.java", 100, 5, false));

        TechnicalDebtVisitor visitor = new TechnicalDebtVisitor();
        project.accept(visitor);

        assertTrue(visitor.hasLowTestCoverage());
        assertEquals(15, visitor.getDebtPoints());
    }

    @Test
    void shouldNotChangeDebtAfterPrintingReport() {
        SourceCodeFile file = new SourceCodeFile("LargeFile.java", 400, 15, false);

        TechnicalDebtVisitor visitor = new TechnicalDebtVisitor();
        file.accept(visitor);

        int before = visitor.getDebtPoints();

        visitor.printReport();
        visitor.printReport();

        assertEquals(before, visitor.getDebtPoints());
        assertEquals(30, visitor.getDebtPoints());
    }
}
