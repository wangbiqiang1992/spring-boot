package cn.com.study.boot.dto;

public class  PersonDTO {
    private String firstName;

    public PersonDTO(String firstName) {
        super();
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
