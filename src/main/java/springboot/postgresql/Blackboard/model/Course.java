package springboot.postgresql.Blackboard.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.List;
import java.util.Objects;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"label"})})
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "label")
    private String label;

    @ManyToOne
    @JsonIgnore
    private Teacher teacher;

    @OneToMany(mappedBy = "course", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Section> sections;

    @OneToMany(mappedBy = "course", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Module> modules;

    public Course() {
    }

    public Course(String label, Teacher teacher, List<Section> sections, List<Module> modules) {
        this.label = label;
        this.teacher = teacher;
        this.sections = sections;
        this.modules = modules;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(label, course.label) &&
                Objects.equals(teacher, course.teacher) &&
                Objects.equals(sections, course.sections) &&
                Objects.equals(modules, course.modules);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, teacher, sections, modules);
    }

}
