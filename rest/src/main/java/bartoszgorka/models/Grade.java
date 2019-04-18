package bartoszgorka.models;

import bartoszgorka.rest.Course;
import bartoszgorka.rest.Grades;
import bartoszgorka.rest.Student;
import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;
import java.util.List;

@XmlRootElement
public class Grade {
    @InjectLinks({
        @InjectLink(
            style = InjectLink.Style.ABSOLUTE,
            resource = Grades.class,
            bindings = {
                @Binding(name="index", value="${instance.studentIndex}"),
                @Binding(name="ID", value="${instance.ID}")
            },
            rel = "self"),
        @InjectLink(
            style = InjectLink.Style.ABSOLUTE,
            resource = Student.class,
            bindings = {@Binding(name="index", value="${instance.studentIndex}")},
            rel = "student"),
        @InjectLink(
            style = InjectLink.Style.ABSOLUTE,
            resource = bartoszgorka.rest.Grade.class,
            bindings = {@Binding(name="index", value="${instance.studentIndex}")},
            rel = "parent"),
        @InjectLink(
            style = InjectLink.Style.ABSOLUTE,
            resource = Course.class,
            bindings = {@Binding(name="ID", value="${instance.courseID}")},
            rel = "course")

    })
    @XmlElement(name="link")
    @XmlElementWrapper(name = "links")
    @XmlJavaTypeAdapter(Link.JaxbAdapter.class)
    List<Link> links;
    private int ID;
    @XmlTransient
    private int studentIndex;
    private int courseID;
    private Date createdAt;
    private GradeValue grade;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @XmlTransient
    public int getStudentIndex() {
        return studentIndex;
    }

    public void setStudentIndex(int studentIndex) {
        this.studentIndex = studentIndex;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public GradeValue getGrade() {
        return grade;
    }

    public void setGrade(GradeValue grade) {
        this.grade = grade;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public enum GradeValue {
        NIEDOSTATECZNY,
        DOSTATECZNY,
        DOSTATECZNY_PLUS,
        DOBRY,
        DOBRY_PLUS,
        BARDZO_DOBRY
    }
}
