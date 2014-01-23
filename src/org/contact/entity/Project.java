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
import javax.persistence.Table;

/**
 *
 * @author youli
 */
@Entity
@Table(name="PROJECT")
public class Project {
    @Id
    @GeneratedValue
    @Column(name="ID")
    private Integer id;
    //项目编号，可能会用到
    @Column(name="CODE")
    private String code;
    //项目名称
    @Column(name="NAME")
    private String name;
    //项目类型
    @Column(name="TYPE")
    private String type;
    //此项目的任职是否需要计算，默认应该是true
    @Column(name="ISCOUNT")
    private Boolean isCount;

    public Project() {
    }

    public Project(Integer id, String code, String name, String type, Boolean isCount) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.type = type;
        this.isCount = isCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean isIsCount() {
        return isCount;
    }

    public void setIsCount(Boolean isCount) {
        this.isCount = isCount;
    }
    
}
