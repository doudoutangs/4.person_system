package com.person.modules.person.constant;
/**
 * @author: QQ:553039957
 * @Date: 2023/9/25 15:37
 * @Description:
 * 1. gitcode主页： https://gitcode.net/tbb414 （推荐）
 * 2. github主页：https://github.com/doudoutangs
 * 3. gitee(码云)主页：https://gitee.com/spdoudoutang
 */
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
