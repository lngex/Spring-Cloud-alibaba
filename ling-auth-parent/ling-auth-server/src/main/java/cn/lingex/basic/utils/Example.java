package cn.lingex.basic.utils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;

public class Example {

    public static String getHdSerialInfo() {

        String line = "";
        //定义变量 硬盘序列号
        String hdSerial = "";
        try {
            //获取命令行参数
            Process proces = Runtime.getRuntime().exec("cmd wmic diskdrive get serialnumber");
            BufferedReader buffreader = new BufferedReader(new InputStreamReader(proces.getInputStream(), "gbk"));
            while ((line = buffreader.readLine()) != null) {
                System.out.println(line);
                //读取参数并获取硬盘序列号
                if (line.contains("卷的序列号是 ")) {
                    hdSerial = line.substring(line.indexOf("卷的序列号是 ") + "卷的序列号是 ".length(), line.length());
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return hdSerial;
    }

//    public static void main(String[] args) throws IOException {
//        Process process = Runtime.getRuntime().exec(
//                new String[]{"wmic", "diskdrive", "get", "serialnumber"});
//        process.getOutputStream().close();
//        Scanner sc = new Scanner(process.getInputStream());
//        String property = sc.next();
//        String serial = sc.next();
//        System.out.println(property + ": " + serial);
//
//    }

    /**
     * 获取硬盘序列号
     *
     * @return 硬盘序列号
     * @throws IOException IO流异常
     */
    @SuppressWarnings("all")
    public static List<String> diskSerialnumber() throws IOException {
        Process process = Runtime.getRuntime().exec(
                new String[]{"wmic", "diskdrive", "get", "serialnumber"});
        process.getOutputStream().close();
        ArrayList<String> strings = new ArrayList<>();
        try (InputStream inputStream = process.getInputStream();) {
            Scanner scanner = new Scanner(inputStream);
            while (true){
                strings.add(scanner.next().trim());
            }
        }catch (Exception e){

        }
        return strings;
    }

    public static List<String> getMACAddress() throws SocketException {
        Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
        List<String> list = new ArrayList<>();
        while (nis.hasMoreElements()) {
            NetworkInterface ni = nis.nextElement();
            if (ni != null && ni.isUp()) {
                byte[] bytes = ni.getHardwareAddress();
                if (bytes != null && bytes.length == 6) {
                    StringBuilder sb = new StringBuilder();
                    for (byte b : bytes) {
                        //与11110000作按位与运算以便读取当前字节高4位
                        sb.append(Integer.toHexString((b & 240) >> 4));
                        //与00001111作按位与运算以便读取当前字节低4位
                        sb.append(Integer.toHexString(b & 15));
                        sb.append("-");
                    }
                    sb.deleteCharAt(sb.length() - 1);
                    list.add(sb.toString().toUpperCase());
                }
            }
        }
        return list;
    }

    /**
     * 获取请求中的Body参数
     * @param request 请求对象
     * @return 字节数
     */
    public static byte[] readAsBytes(HttpServletRequest request) {

        int len = request.getContentLength();
        byte[] buffer = new byte[len];

        try (ServletInputStream in = request.getInputStream()) {
            in.read(buffer, 0, len);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    public static void main(String[] args) throws IOException {

    }
}