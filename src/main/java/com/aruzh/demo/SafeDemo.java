package com.aruzh.demo;
import com.aruzh.element.*;
import com.aruzh.visitor.*;

public class SafeDemo {

    public static void main(String[] args) {

        Directory project = new Directory("safe-fintech-project");

        Directory src = new Directory("src");
        Directory tests = new Directory("tests");
        Directory config = new Directory("config");

        src.addElement(new SourceCodeFile("PaymentService.java", 180, 7, false));
        src.addElement(new SourceCodeFile("UserService.java", 120, 5, false));

        tests.addElement(new TestFile("PaymentServiceTest.java", 90, 5, true));

        config.addElement(new ConfigurationFile("application.properties", true, false));

        project.addElement(src);
        project.addElement(tests);
        project.addElement(config);
        project.addElement(new DependencyFile("pom.xml", 3, false, true, false));

        SecurityAuditVisitor security = new SecurityAuditVisitor();
        ProjectStatisticsVisitor statistics = new ProjectStatisticsVisitor();
        QualityGateVisitor quality = new QualityGateVisitor();
        ComplianceVisitor compliance = new ComplianceVisitor();
        TechnicalDebtVisitor debt = new TechnicalDebtVisitor();

        project.accept(security);
        project.accept(statistics);
        project.accept(quality);
        project.accept(compliance);
        project.accept(debt);

        security.printReport();
        System.out.println();

        statistics.printReport();
        System.out.println();

        quality.printReport();
        System.out.println();

        compliance.printReport();
        System.out.println();

        debt.printReport();
    }
}
