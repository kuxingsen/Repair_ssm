package cn.repair.utils;

public class PhoneUtil{
    public static boolean isPhone(String phone) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (phone == null || phone.length() != 11) {
//            MToast.showToast("手机号应为11位数");
            return false;
        }
        return phone.matches(regex);
    }
    public static String get6Code(){
        String code = System.currentTimeMillis()+"";
        return code.substring(code.length()-6);
    }
    public static boolean isCode(String code){
        String regex = "\\d{6}$";
        if (code.length() != 6) {
            return false;
        }
        return code.matches(regex);
    }

    public static boolean isAdmin(String phone) {
        return "13556507839".equals(phone);
    }
}
