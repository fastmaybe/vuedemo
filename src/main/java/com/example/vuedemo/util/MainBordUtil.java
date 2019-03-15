package com.example.vuedemo.util;

import java.io.*;

//取主板序列号  
public class MainBordUtil {
	
	//private static final Logger LOGGER = LoggerFactory.getLogger(MainBordUtil.class);
	
    /**
     * 获取当前操作系统名称. return 操作系统名称 例如:windows xp,linux 等.
     */
    public static String getOSName() {
        return System.getProperty("os.name").toLowerCase();
    }

    private static String getMainBordId_windows() {
        String result = "";
        try {
            File file = File.createTempFile("realhowto", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new FileWriter(file);

            String vbs = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n"
                    + "Set colItems = objWMIService.ExecQuery _ \n"
                    + "   (\"Select * from Win32_BaseBoard\") \n"
                    + "For Each objItem in colItems \n"
                    + "    Wscript.Echo objItem.SerialNumber \n"
                    + "    exit for  ' do the first cpu only! \n" + "Next \n";

            fw.write(vbs);
            fw.close();
            System.out.println(file.getPath());
            Process p = Runtime.getRuntime().exec(
                    "cscript //NoLogo " + file.getPath());
            BufferedReader input = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
        } catch (Exception e) {
        	//LOGGER.error("获取主板信息错误",e);
        	System.out.println("获取主板信息错误");
        	e.printStackTrace();
        }
        return result.trim();
    }

    private static String getMainBordId_linux() {

        String result = "";
        String maniBord_cmd = "dmidecode | grep 'Serial Number' | awk '{print $3}' | head -2 | tail -1";
        Process p;
        try {
            p = Runtime.getRuntime().exec(
                    new String[] { "sh", "-c", maniBord_cmd });// 管道
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                result += line;
                break;
            }
            br.close();
        } catch (IOException e) {
        	//LOGGER.error("获取主板信息错误",e);
        	System.out.println("获取主板信息错误");
        	e.printStackTrace();
        }
        return result;
    }

    public static String getMainBordId(){
        String os = getOSName();
        String mainBordId = "";
        if (os.startsWith("windows")) {
            mainBordId = getMainBordId_windows();
        } else if (os.startsWith("linux")) {
            mainBordId = getMainBordId_linux();
        }
        return mainBordId;
    }
    
    public static void main(String[] args) {
    	System.out.println(getMainBordId());
	}

}