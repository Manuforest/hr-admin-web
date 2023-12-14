package com.edu.sys.entity;

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
@TableName("x_application_info")
public class ApplicationInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer uid;

    private Integer vid;

    private String state;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "ApplicationInfo{" +
            "uid = " + uid +
            ", vid = " + vid +
            ", state = " + state +
        "}";
    }
}
