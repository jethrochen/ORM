/*
 * Copyright (c) 2011, Oracle and/or its affiliates. All rights reserved.
 */
package login.entity;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="USER")
public class User {

    private static final Map<String, User> USERS = new HashMap<String, User>();

    public User() {
    }

    public static User of(String id) {
        User user = USERS.get(id);
        if (user == null) {
            user = new User(id);
            USERS.put(id, user);
        }
        return user;
    }

    private User(String id) {
        this.id = id;
    }
    @Id
    @Column(name="id")
    private String id;

    public String getId() {
        return id;
    }
    @Column(name="email")
    private String email = "";
    @Column(name="phone")
    private String phone = "";
    @Column(name="subscribed")
    private boolean subscribed;
    @Column(name="address")
    private String address = "";

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the subscribed
     */
    public boolean isSubscribed() {
        return subscribed;
    }

    /**
     * @param subscribed the subscribed to set
     */
    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }
}
