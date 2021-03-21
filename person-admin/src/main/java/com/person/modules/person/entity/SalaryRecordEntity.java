package com.person.modules.person.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
@TableName("b_salary_record")
public class SalaryRecordEntity {
    @TableId
    private Long id;

    private Long userId;
    @TableField(exist = false)
    private String userName;
    @TableField(exist = false)
    private String deptName;
    @TableField(exist = false)
    private String deptId;
    private String salaryMonth;
    //基本工资
    private Float mustSalary;
    //全勤工资
    private Float realitySalary;
    //迟到扣款
    private Float fundAmount;
    //请假扣款
    private Float taxAmount;
    //绩效奖金
    private Float medicalAmount;
    //个税
    private Float pensionAmount;
    //五险一金
    private Float injuredAmount;

    private Float birthAmount;

    private Float unemploymentAmount;

    private Float leaveAmount;

    private Float lateAmount;

    private Float baseAmount;

    private Float workAmount;

    private Float overtimeAmount;

    private Float meritsAmount;

    private String createTime;

    private String updateTime;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
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

    public String getSalaryMonth() {
        return salaryMonth;
    }

    public void setSalaryMonth(String salaryMonth) {
        this.salaryMonth = salaryMonth;
    }

    public Float getMustSalary() {
        return mustSalary;
    }

    public void setMustSalary(Float mustSalary) {
        this.mustSalary = mustSalary;
    }

    public Float getRealitySalary() {
        return realitySalary;
    }

    public void setRealitySalary(Float realitySalary) {
        this.realitySalary = realitySalary;
    }

    public Float getFundAmount() {
        return fundAmount;
    }

    public void setFundAmount(Float fundAmount) {
        this.fundAmount = fundAmount;
    }

    public Float getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Float taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Float getMedicalAmount() {
        return medicalAmount;
    }

    public void setMedicalAmount(Float medicalAmount) {
        this.medicalAmount = medicalAmount;
    }

    public Float getPensionAmount() {
        return pensionAmount;
    }

    public void setPensionAmount(Float pensionAmount) {
        this.pensionAmount = pensionAmount;
    }

    public Float getInjuredAmount() {
        return injuredAmount;
    }

    public void setInjuredAmount(Float injuredAmount) {
        this.injuredAmount = injuredAmount;
    }

    public Float getBirthAmount() {
        return birthAmount;
    }

    public void setBirthAmount(Float birthAmount) {
        this.birthAmount = birthAmount;
    }

    public Float getUnemploymentAmount() {
        return unemploymentAmount;
    }

    public void setUnemploymentAmount(Float unemploymentAmount) {
        this.unemploymentAmount = unemploymentAmount;
    }

    public Float getLeaveAmount() {
        return leaveAmount;
    }

    public void setLeaveAmount(Float leaveAmount) {
        this.leaveAmount = leaveAmount;
    }

    public Float getLateAmount() {
        return lateAmount;
    }

    public void setLateAmount(Float lateAmount) {
        this.lateAmount = lateAmount;
    }

    public Float getBaseAmount() {
        return baseAmount;
    }

    public void setBaseAmount(Float baseAmount) {
        this.baseAmount = baseAmount;
    }

    public Float getWorkAmount() {
        return workAmount;
    }

    public void setWorkAmount(Float workAmount) {
        this.workAmount = workAmount;
    }

    public Float getOvertimeAmount() {
        return overtimeAmount;
    }

    public void setOvertimeAmount(Float overtimeAmount) {
        this.overtimeAmount = overtimeAmount;
    }

    public Float getMeritsAmount() {
        return meritsAmount;
    }

    public void setMeritsAmount(Float meritsAmount) {
        this.meritsAmount = meritsAmount;
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
}