package com.aruzh.visitor;
import com.aruzh.element.*;

public class ProjectStatisticsVisitor implements ProjectVisitor {

    private int totalFiles = 0;
    private int sourceFiles = 0;
    private int testFiles = 0;
    private int linesOfCode = 0;
    private int dependencies = 0;
    private int totalComplexity = 0;

    @Override
    public void visit(SourceCodeFile file) {
        totalFiles++;
        sourceFiles++;
        linesOfCode += file.getLinesOfCode();
        totalComplexity += file.getComplexity();
    }

    @Override
    public void visit(TestFile file) {
        totalFiles++;
        testFiles++;
        linesOfCode += file.getLinesOfCode();
    }

    @Override
    public void visit(ConfigurationFile file) {
        totalFiles++;
    }

    @Override
    public void visit(DependencyFile file) {
        totalFiles++;
        dependencies += file.getDependencyCount();
    }

    @Override
    public void visit(Directory directory) {
    }

    public int getTotalFiles() {
        return totalFiles;
    }

    public int getSourceFiles() {
        return sourceFiles;
    }

    public int getTestFiles() {
        return testFiles;
    }

    public int getLinesOfCode() {
        return linesOfCode;
    }

    public int getDependencies() {
        return dependencies;
    }

    public double getAverageComplexity() {
        return sourceFiles == 0 ? 0 :
                (double) totalComplexity / sourceFiles;
    }

    public double getTestSourceRatio() {
        return sourceFiles == 0 ? 0 :
                (double) testFiles / sourceFiles;
    }

    public void printReport() {
        System.out.println("=== PROJECT STATISTICS ===");
        System.out.println("Total Files: " + totalFiles);
        System.out.println("Source Files: " + sourceFiles);
        System.out.println("Test Files: " + testFiles);
        System.out.println("Lines of Code: " + linesOfCode);
        System.out.println("Dependencies: " + dependencies);
        System.out.println("Average Complexity: " + getAverageComplexity());
        System.out.println("Test/Source Ratio: " + getTestSourceRatio());
    }
}
