package com.person.modules.person.constant;

public enum ApplyStatusEnum {

    WAIT(0,"审核中"),
    FAIL(1,"审核未通过"),
    ADOPT(2,"审核通过")
    ;
    private int code;
    private String name;

    ApplyStatusEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
