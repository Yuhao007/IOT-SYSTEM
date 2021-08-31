package com.learn.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * monitoring data
 *
 *
 *
 *
 */
public class DataEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Long id;

    //
    private String v;

    //
    private String type;



    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


    //
    private Date time = new Date();

    /**
     * set；
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * get:
     */
    public Long getId() {
        return id;
    }

    /**
     * set:value
     */
    public void setV(String v) {
        this.v = v;
    }

    /**
     * get:value
     */
    public String getV() {
        return v;
    }

    /**
     * set:type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * get:value
     */
    public String getType() {
        return type;
    }

    /**
     * set:time
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * get:time
     */
    public Date getTime() {
        return time;
    }
}
