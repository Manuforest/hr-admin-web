package com.edu.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author cwq
 * @since 2023-12-14
 */
@TableName("x_volunteer_work")
public class VolunteerWork implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private Object workhours;

    private Integer recruitmentnumbers;

    private String time;

    private Integer applicants;

    private String content;

    private String requirement;

    private String location;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getWorkhours() {
        return workhours;
    }

    public void setWorkhours(Object workhours) {
        this.workhours = workhours;
    }

    public Integer getRecruitmentnumbers() {
        return recruitmentnumbers;
    }

    public void setRecruitmentnumbers(Integer recruitmentnumbers) {
        this.recruitmentnumbers = recruitmentnumbers;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getApplicants() {
        return applicants;
    }

    public void setApplicants(Integer applicants) {
        this.applicants = applicants;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "VolunteerWork{" +
            "id = " + id +
            ", name = " + name +
            ", workhours = " + workhours +
            ", recruitmentnumbers = " + recruitmentnumbers +
            ", time = " + time +
            ", applicants = " + applicants +
            ", content = " + content +
            ", requirement = " + requirement +
            ", location = " + location +
        "}";
    }
}
