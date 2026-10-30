package com.aruzh.visitor;
import com.aruzh.element.SourceCodeFile;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SecurityAuditVisitorTest {

    @Test
    void shouldDetectHardcodedCredentials() {
        SourceCodeFile file =
                new SourceCodeFile("PaymentService.java", 150, 8, true);

        SecurityAuditVisitor visitor = new SecurityAuditVisitor();

        file.accept(visitor);

        assertEquals(1, visitor.getCriticalIssues());
        assertEquals(90, visitor.getSecurityScore());
        assertEquals(1, visitor.getIssues().size());
    }

    @Test
    void shouldPassSafeSourceFile() {
        SourceCodeFile file =
                new SourceCodeFile("UserService.java", 100, 5, false);

        SecurityAuditVisitor visitor = new SecurityAuditVisitor();

        file.accept(visitor);

        assertEquals(0, visitor.getCriticalIssues());
        assertEquals(100, visitor.getSecurityScore());
        assertTrue(visitor.getIssues().isEmpty());
    }
}
