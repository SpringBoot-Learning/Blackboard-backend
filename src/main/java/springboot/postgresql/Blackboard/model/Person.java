package springboot.postgresql.Blackboard.model;

import org.apache.naming.factory.SendMailFactory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
    private String email;
    private String address;
    private long phoneNo;
    private String password;
    private String username;

    public Person() {
    }

    public Person(String name, int age, String email, String address, long phoneNo, String password, String username) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
        this.phoneNo = phoneNo;
        this.password = password;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(long phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                phoneNo == person.phoneNo &&
                name.equals(person.name) &&
                Objects.equals(email, person.email) &&
                Objects.equals(address, person.address) &&
                password.equals(person.password) &&
                username.equals(person.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, email, address, phoneNo, password, username);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phoneNo=" + phoneNo +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
