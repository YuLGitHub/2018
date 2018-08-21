package cn.company.project.common.utils;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式匹配IP工具
 * @author yule
 * @date 2018/08/21 14:20
 */
public class RegIPUtil {
    /**
     * 通过正则表达式截取字符串中的IP地址
     *
     * @param str
     * @return
     */
    public static String regIP(String str) {
        String ip = "";
        if (!StringUtils.isEmpty(str)) {
            /*
            默认先匹配IPv4
            */
            String regIPv4 = "(?:(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?))";
            Boolean flag = false;
            Pattern pattern = Pattern.compile(regIPv4, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(str);
            while (matcher.find()) {
                flag = true;
                ip = matcher.group();
            }
            /*
            IPv4未匹配成功则匹配IPv6
            */
            if (!flag) {
                String regIPv6 = "(?:([\\da-fA-F]{1,4}:){6}((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)|::([\\da-fA-F]{1,4}:){0,4}((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)|([\\da-fA-F]{1,4}:):([\\da-fA-F]{1,4}:){0,3}((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)|([\\da-fA-F]{1,4}:){2}:([\\da-fA-F]{1,4}:){0,2}((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)|([\\da-fA-F]{1,4}:){3}:([\\da-fA-F]{1,4}:){0,1}((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)|([\\da-fA-F]{1,4}:){4}:((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)|([\\da-fA-F]{1,4}:){7}[\\da-fA-F]{1,4}|:((:[\\da-fA-F]{1,4}){1,6}|:)|[\\da-fA-F]{1,4}:((:[\\da-fA-F]{1,4}){1,5}|:)|([\\da-fA-F]{1,4}:){2}((:[\\da-fA-F]{1,4}){1,4}|:)|([\\da-fA-F]{1,4}:){3}((:[\\da-fA-F]{1,4}){1,3}|:)|([\\da-fA-F]{1,4}:){4}((:[\\da-fA-F]{1,4}){1,2}|:)|([\\da-fA-F]{1,4}:){5}:([\\da-fA-F]{1,4})?|([\\da-fA-F]{1,4}:){6}:)";
                Pattern pattern1 = Pattern.compile(regIPv6, Pattern.CASE_INSENSITIVE);
                Matcher matcher1 = pattern1.matcher(str);
                while (matcher1.find()) {
                    ip = matcher1.group();
                }
            }
        }
        return ip;
    }
}
