package com.aruzh.element;
import com.aruzh.visitor.ProjectVisitor;
import java.util.ArrayList;
import java.util.List;

public class Directory implements ProjectElement {

    private String name;
    private List<ProjectElement> elements = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addElement(ProjectElement element) {
        elements.add(element);
    }

    public List<ProjectElement> getElements() {
        return elements;
    }

    @Override
    public void accept(ProjectVisitor visitor) {
        visitor.visit(this);

        for (ProjectElement element : elements) {
            element.accept(visitor);
        }
    }
}
