package com.person.modules.person.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.person.common.validator.group.AddGroup;
import com.person.common.validator.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;

@TableName("b_user_doc")
public class UserDocEntity {
    @TableId
    private Long id;

    private Long userId;

    @TableField(exist = false)
    private String userName;
    @TableField(exist = false)
    private int age;
    private String mobile;
    @TableField(exist = false)
    private String deptName;
    @NotNull(message = "部门不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Long deptId;
    private String education;

    private String userNo;

    @NotNull(message = "生日不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String birth;
    @NotNull(message = "入职日期不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String entryDate;

    private String userType;

    private String createTime;

    private String updateTime;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }
}