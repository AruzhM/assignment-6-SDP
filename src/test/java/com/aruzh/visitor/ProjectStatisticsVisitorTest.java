package com.aruzh.visitor;
import com.aruzh.element.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProjectStatisticsVisitorTest {

    @Test
    void shouldCalculateProjectStatistics() {
        Directory project = new Directory("fintech-project");

        project.addElement(new SourceCodeFile("PaymentService.java", 150, 8, false));
        project.addElement(new SourceCodeFile("UserService.java", 100, 12, false));
        project.addElement(new TestFile("PaymentServiceTest.java", 80, 5, true));
        project.addElement(new DependencyFile("pom.xml", 4, false, true, false));

        ProjectStatisticsVisitor visitor = new ProjectStatisticsVisitor();
        project.accept(visitor);

        assertEquals(4, visitor.getTotalFiles());
        assertEquals(2, visitor.getSourceFiles());
        assertEquals(1, visitor.getTestFiles());
        assertEquals(330, visitor.getLinesOfCode());
        assertEquals(4, visitor.getDependencies());
        assertEquals(10.0, visitor.getAverageComplexity());
        assertEquals(0.5, visitor.getTestSourceRatio());
    }

    @Test
    void shouldHandleEmptyDirectory() {
        Directory project = new Directory("empty-project");

        ProjectStatisticsVisitor visitor = new ProjectStatisticsVisitor();
        project.accept(visitor);

        assertEquals(0, visitor.getTotalFiles());
        assertEquals(0, visitor.getLinesOfCode());
        assertEquals(0.0, visitor.getAverageComplexity());
        assertEquals(0.0, visitor.getTestSourceRatio());
    }

    @Test
    void shouldCountNestedDirectories() {
        Directory project = new Directory("fintech-project");
        Directory src = new Directory("src");
        Directory services = new Directory("services");

        services.addElement(new SourceCodeFile("PaymentService.java", 120, 6, false));
        src.addElement(services);
        project.addElement(src);

        ProjectStatisticsVisitor visitor = new ProjectStatisticsVisitor();
        project.accept(visitor);

        assertEquals(1, visitor.getTotalFiles());
        assertEquals(120, visitor.getLinesOfCode());
        assertEquals(1, visitor.getSourceFiles());
    }
}
