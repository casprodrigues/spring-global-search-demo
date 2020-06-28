package dev.crodrigues.globalsearch.employee;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "employees")
@NoArgsConstructor
public class Employee {
    
    @Id
    private Integer id;

    private String  lastName;
    private String  firstName;
    private String  email;
    private String  avatar;
    private String  jobTitle;
    private String  department;
    private Integer managerId;
    private String  phone;
    private String  address1;
    private String  address2;
    private String  city;
    private String  state;
    private String  postalCode;
    private String  country;

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }

    public String getJobDetailsAsString() {
        return String.format("%s - %s", jobTitle, department);
    }

}