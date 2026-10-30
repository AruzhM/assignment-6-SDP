package com.aruzh.demo;
import com.aruzh.element.*;
import com.aruzh.visitor.*;

public class Main {

    public static void main(String[] args) {

        Directory project = new Directory("fintech-project");

        Directory src = new Directory("src");
        Directory tests = new Directory("tests");
        Directory config = new Directory("config");

        src.addElement(new SourceCodeFile("PaymentService.java", 350, 16, true));
        src.addElement(new SourceCodeFile("UserService.java", 180, 8, false));

        tests.addElement(new TestFile("PaymentServiceTest.java", 120, 6, true));

        config.addElement(new ConfigurationFile("application.properties", false, true));

        project.addElement(src);
        project.addElement(tests);
        project.addElement(config);
        project.addElement(new DependencyFile("pom.xml", 5, true, false, true));

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
