package com.person.modules.person.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
/**
 * @author: QQ:553039957
 * @Date: 2023/9/25 16:21
 * @Description:
 * 1. gitcode主页： https://gitcode.net/tbb414 （推荐）
 * 2. github主页：https://github.com/doudoutangs
 * 
 */

@TableName("b_merit_set")
public class MeritEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId
    private Long id;

    private String setDate;

    private String monthQuota;

    private String weekQuota;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSetDate() {
        return setDate;
    }

    public void setSetDate(String setDate) {
        this.setDate = setDate;
    }

    public String getMonthQuota() {
        return monthQuota;
    }

    public void setMonthQuota(String monthQuota) {
        this.monthQuota = monthQuota;
    }

    public String getWeekQuota() {
        return weekQuota;
    }

    public void setWeekQuota(String weekQuota) {
        this.weekQuota = weekQuota;
    }
}