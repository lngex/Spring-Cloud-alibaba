package cn.lingex.basic.utils;

import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yaohuaipeng
 */
public class StrUtils {
    /**
     * 把逗号分隔的字符串转换字符串数组
     *
     * @param str   被分割的字符串
     * @param split 分割标识
     * @return 分割后的数组
     */
    public static String[] splitStrToStrArr(String str, String split) {
        if (str != null && !"".equals(str)) {
            return str.split(split);
        }
        return null;
    }


    /**
     * 把逗号分隔字符串转换List的Long
     *
     * @param str 被分割的字符串
     * @return 分割后转换的集合
     */
    public static List<Long> splitStrToLongArr(String str) {
        String[] strings = splitStrToStrArr(str, ",");
        return getLongs(strings);
    }

    /**
     * 把逗号分隔字符串转换List的Long
     *
     * @param str   被分割的字符串
     * @param split 分割标识
     * @return 结果
     */
    public static List<Long> splitStrToLongArr(String str, String split) {
        String[] strings = splitStrToStrArr(str, split);
        return getLongs(strings);
    }

    private static List<Long> getLongs(String[] strings) {
        if (strings == null) {
            return null;
        }

        List<Long> result = new ArrayList<>();
        for (String string : strings) {
            result.add(Long.parseLong(string));
        }

        return result;
    }

    public static String getRandomString(int length) {
        String str = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(10);
            sb.append(str.charAt(number));
        }
        return sb.toString();

    }

    public static String getComplexRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static String convertPropertiesToHtml(String properties) {
        //1:容量:6:32GB_4:样式:12:塑料壳
        StringBuilder sBuilder = new StringBuilder();
        String[] propArr = properties.split("_");
        for (String props : propArr) {
            String[] valueArr = props.split(":");
            sBuilder.append(valueArr[1]).append(":").append(valueArr[3]).append("<br>");
        }
        return sBuilder.toString();
    }


    /**
     * 生成uuid
     *
     * @return 无符号uuid
     */
    public static String uuid() {
        UUID uuid = UUID.randomUUID();
        String[] split = uuid.toString().split("-");
        StringBuilder stringBuilder = new StringBuilder("");
        for (String s : split) {
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }

    /**
     * 获取系统硬盘序列号
     *
     * @return 硬盘序列号
     * @throws IOException 可能抛出的异常
     */
    public static String getWinHardiskSn() throws IOException {
        Process process = Runtime.getRuntime().exec(
                new String[]{"wmic", "cpu", "get", "ProcessorId"});
        process.getOutputStream().close();
        Scanner sc = new Scanner(process.getInputStream());
        String property = sc.next();
        return sc.next();
    }

    /**
     * 检查是否包含特殊符号
     *
     * @param str 字符串对象
     * @return 是否包含特殊符号
     */
    public static boolean isSpecialChar(String str) {
        String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\]<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }


    public static void main(String[] args) throws InterruptedException {

    }
}
