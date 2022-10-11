package com.atguigu.eduservice.enums;

/**
 * @创建人 ruansl
 * @创建时间 2021/6/10 0010
 * @描述
 **/
public enum CourseEnum {

    /**
     * 已发布
     */
    COURSE_NORMAL("Normal", "已发布"),
    /**
     * 未发布
     */
    CROUSE_DRAFT("Draft","未发布");

    private String code;
    private String value;

    public String getCode() {
        return code;
    }
    CourseEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }
    public static CourseEnum getValue(String code) {
        switch (code) {
            case "Normal":
                return COURSE_NORMAL;
            case "Draft":
                return CROUSE_DRAFT;
            default:
                return null;
        }
    }

}
