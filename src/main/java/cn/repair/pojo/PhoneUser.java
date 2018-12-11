package cn.repair.pojo;

public class PhoneUser{
    private String phoneNum;
    private String code;

    public PhoneUser() {
    }

    @Override
    public String toString() {
        return "PhoneUser{" +
                "phoneNum='" + phoneNum + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
