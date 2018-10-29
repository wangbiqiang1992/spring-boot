package cn.com.study.boot.employee.enums;

public enum Status {
    DI_MISSION("0","离职"),
    ON_MISSION("1","在岗");

    private String value;
    private String name;

    Status(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
