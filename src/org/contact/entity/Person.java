/*
 * This project is created by ShodowBoy all copyright reserved.
 * No distribute is approved unless authorized.
 * Any problem please contact youli9056@126.com
 */

package org.contact.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 * 人员类
 * 存在和部门Department的多对一的关系，和Incumbency的双向一对多关系
 * @author youli
 */
@Entity
@Table(name="PERSON")
public class Person {

    @Id
    @Column(name="PERSONID")//4位年份+两位数共6位数
    private Integer personId;
    //姓名
    @Column(name="NAME")
    private String name;
    //出生年月限定格式为yyyymmdd
    @Column(name="BIRTHDAY")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date birthday;
    //和department的多对一的关系
    @ManyToOne
    private Department depart;
    //职称
    @Column(name="JOBTITLE")
    private String jobTitle;
    //学历
    @Column(name="EDUBACKGROUND")
    private String eduBackground;
    //和任职Incumbency的双向一对多关系
    @OneToMany(mappedBy = "person")
    private List<Incumbency> incumbencys;

    public Person() {
    }

    public Person(Integer personId, String name, Date birthday, Department depart, String jobTitle, String eduBackground) {
        this.personId = personId;
        this.name = name;
        this.birthday = birthday;
        this.depart = depart;
        this.jobTitle = jobTitle;
        this.eduBackground = eduBackground;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Department getDepart() {
        return depart;
    }

    public void setDepart(Department depart) {
        this.depart = depart;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getEduBackground() {
        return eduBackground;
    }

    public void setEduBackground(String eduBackground) {
        this.eduBackground = eduBackground;
    }
    
    
}
