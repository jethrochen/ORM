/*
 * This project is created by ShodowBoy all copyright reserved.
 * No distribute is approved unless authorized.
 * Any problem please contact youli9056@126.com
 */

package org.contact.entity;

import person.entity.Person;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 部门类
 * @author youli
 */
@Entity
@Table(name="DEPARTMENT")
public class Department{
    @Id
    @GeneratedValue
    @Column(name="ID")
    private int id;
    //部门名称
    @Column(name="name")
    private String name;
    //部门里的人员，和人员Person存在一对多的关系
    @OneToMany(mappedBy = "depart")
    private List<Person> persons;

    public Department(){
        super();
    }
    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }
    @Override public String toString(){
        return name;
    }
    public void addPerson(Person p){
        persons.add(p);
    }
    public void removePerson(Person p){
        persons.remove(p);
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
    
    
}
