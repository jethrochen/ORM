/*
 * This project is created by ShodowBoy all copyright reserved.
 * No distribute is approved unless authorized.
 * Any problem please contact youli9056@126.com
 */

package org.contact.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 任职类
 * 存在和Project的多对一的关系、和Person的双向多对一的关系
 * @author youli
 */
@Entity
@Table(name="INCUMBENCY")
public class Incumbency {
    @Id
    @GeneratedValue
    @Column(name="INCUMBENCYID")
    private Long id;
    //任职的项目，存在和Project的多对一关系
    @ManyToOne
    private Project project;
    //任职的专业组
    @Column(name="SECTION")
    private String section;
    //任职的人员
    @ManyToOne
    private Person person;
    //任职名称
    @Column(name="INCUMBENCYNAME")
    private String incumbencyName;
    //此任职是否计算
    @Column(name="ISCOUNT")
    private Boolean isCount;

    public Incumbency() {
    }

    public Incumbency(Long id, Project project, String section, Person person, String incumbencyName, Boolean isCount) {
        this.id = id;
        this.project = project;
        this.section = section;
        this.person = person;
        this.incumbencyName = incumbencyName;
        this.isCount = isCount;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getIncumbencyName() {
        return incumbencyName;
    }

    public void setIncumbencyName(String incumbencyName) {
        this.incumbencyName = incumbencyName;
    }

    public Boolean isIsCount() {
        return isCount;
    }

    public void setIsCount(Boolean isCount) {
        this.isCount = isCount;
    }
    
}
