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

    @TableId(value = "volunteerWorkId", type = IdType.AUTO)
    private Integer volunteerWorkId;

    private String volunteerWorkName;

    private Object workHours;

    private Integer recruitmentNumbers;

    private String time;

    private Integer applicants;

    private String content;

    private String requirement;

    private String location;

    public Integer getVolunteerWorkId() {
        return volunteerWorkId;
    }

    public void setVolunteerWorkId(Integer id) {
        this.volunteerWorkId = id;
    }

    public String getVolunteerWorkName() {
        return volunteerWorkName;
    }

    public void setVolunteerWorkName(String name) {
        this.volunteerWorkName = name;
    }

    public Object getWorkHours() {
        return workHours;
    }

    public void setWorkHours(Object workHours) {
        this.workHours = workHours;
    }

    public Integer getRecruitmentNumbers() { return recruitmentNumbers;}

    public void setRecruitmentNumbers(Integer recruitmentNumbers) {
        this.recruitmentNumbers = recruitmentNumbers;
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
            "volunteerWorkId = " + volunteerWorkId +
            ", volunteerWorkName = " + volunteerWorkName +
            ", workHours = " + workHours +
            ", recruitmentNumbers = " + recruitmentNumbers +
            ", time = " + time +
            ", applicants = " + applicants +
            ", content = " + content +
            ", requirement = " + requirement +
            ", location = " + location +
        "}";
    }
}
