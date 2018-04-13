package com.example.iyunxiao.myrontest.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 通用的工具方法类
 */
public class CommonUtils {

    /**
     * 获取手机IMEI(串号)
     *
     * @param context
     * @return 手机串号
     */
    public static String getIMEI(Context context) {
        String imei = "";
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager != null) {
            try {
                imei = telephonyManager.getDeviceId();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (TextUtils.isEmpty(imei)) {
                imei = "";
            }
        }
        return imei;
    }

    /**
     * 检查sim卡状态
     *
     * @param context
     * @return sim卡是否有效
     */
    public static boolean checkSimState(Context context) {
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        if (tm.getSimState() == TelephonyManager.SIM_STATE_ABSENT
                || tm.getSimState() == TelephonyManager.SIM_STATE_UNKNOWN) {
            return false;
        }
        return true;
    }

    /**
     * 获取AndroidId
     *
     * @param context
     * @return
     */
    public static String getAndroidId(Context context) {
        String id = "";
        id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        if (TextUtils.isEmpty(id)) {
            id = "";
        }
        return id;
    }

    /**
     * 获取屏幕高度
     *
     * @param context
     * @return 屏幕高度(px)
     */
    public static int getScreenHeight(Activity context) {
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    /**
     * 获取屏幕高度
     *
     * @param context
     * @return 屏幕高度(px)
     */
    public static int getScreenHeight(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.heightPixels;
    }

    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return 屏幕宽度(px)
     */
    public static int getScreenWidth(Activity context) {
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return 屏幕宽度(px)
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.widthPixels;
    }

    /**
     * 获取屏幕密度
     *
     * @param context
     * @return
     */
    public static double getScreenDensity(Activity context) {
        double screenDensity = 1;
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenDensity = dm.density;
        return screenDensity;
    }

    /**
     * 获取屏幕密度
     *
     * @param context
     * @return
     */
    public static double getScreenDensity(Context context) {
        double screenDensity = 1;
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        screenDensity = dm.density;
        return screenDensity;
    }

    /**
     * 获取版本号
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取版本名称
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 用来判断服务是否运行.
     *
     * @param context
     * @param serviceName service的名字 是包名+服务的类名
     * @return true 在运行;false 不在运行
     */
    public static boolean isServiceRunning(Context context, String serviceName) {
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager)
                context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList
                = activityManager.getRunningServices(500);
        if (!(serviceList.size() > 0)) {
            return false;
        }
        for (int i = 0; i < serviceList.size(); i++) {
            if (serviceList.get(i).service.getClassName().equals(serviceName)) {
                isRunning = true;
                break;
            }
        }
        return isRunning;
    }

    /**
     * 将dp转换成px
     *
     * @param context
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        float m = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * m + 0.5f);
    }

    /**
     * 将dp转换成px
     *
     * @param dipValue
     * @return
     */
    public static int dip2px(float dipValue) {
        float m = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dipValue * m + 0.5f);
    }

    /**
     * 将px转换成dp
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        float m = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / m + 0.5f);
    }

    /**
     * 将px转换成dp
     *
     * @param pxValue
     * @return
     */
    public static int px2dip(float pxValue) {
        float m = Resources.getSystem().getDisplayMetrics().density;
        return (int) (pxValue / m + 0.5f);
    }
    public static int sp2px(float spValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, Resources.getSystem().getDisplayMetrics());
    }

    /**
     * 把float转换为显示的String，如果小数点后面是0，则显示整数部分；
     *
     * @param xx float数字
     * @return 显示的String
     */
    public static String getDisplayStringPercent(String xx) {
        try {
            Float.parseFloat(xx);
            return xx + "%";
        } catch (NumberFormatException e) {
//            e.printStackTrace();
            return xx;
        }

    }

    /**
     * 把float转换为显示的String，如果小数点后面是0，则显示整数部分；
     * 默认保留小数点后2位
     *
     * @param xx float数字
     * @return 显示的String
     */
    public static String getDisplayString(float xx) {
        return getDisplayString(xx, 2);
    }


    /**
     * 把float转换为显示的String，如果小数点后面是0，则显示整数部分；
     *
     * @param xx float数字
     * @return 显示的String
     */
    public static String getDisplayString(float xx, int decimalCount) {
        float newXx = getFloatText(xx, decimalCount);
        int yy = (int) newXx;
        if (0 == (newXx - (float) yy)) {
            return String.valueOf(yy);
        } else {
            return String.valueOf(newXx);
        }
    }

    /**
     * 把float精确到小数点后两位，
     *
     * @param data float数字
     * @return 显示的String
     */
    private static float getFloatText(float data, int decimalCount) {
        BigDecimal b = new BigDecimal(data);
        return b.setScale(decimalCount, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    /**
     * 判断apk是否安装
     *
     * @param context
     * @param packageName：包名
     * @return
     */
    public static boolean isApkInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    /**
     * 根据包名启动app
     *
     * @param mContext
     * @param appPackageName
     */
    public static Intent startAPP(Context mContext, String appPackageName) {
        Intent intent;
        try {
            intent = mContext.getPackageManager().getLaunchIntentForPackage(appPackageName);
        } catch (Exception e) {
            intent = null;
        }
        return intent;
    }

    /**
     * 将字符串数组转换成字符串，并带分隔符","
     *
     * @param array
     * @return
     */
    public static String array2String(String[] array) {
        if (array != null) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; i++) {
                if (i == array.length - 1) {
                    sb.append(array[i]);
                } else {
                    sb.append(array[i] + ",");
                }
            }
            return sb.toString();
        }
        return null;
    }

    /**
     * 把list转换为string，并添加分隔符
     *
     * @param list
     * @param separator
     * @return
     */
    public static String list2String(List<String> list, char separator) {
        StringBuilder sb = new StringBuilder();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                sb.append(list.get(i));
                if (i < list.size() - 1) {
                    sb.append(separator);
                }
            }
        }
        return sb.toString();
    }

    /**
     * 判断list是否为空
     *
     * @param list
     * @return
     */
    public static boolean listIsEmpty(List list) {
        return list == null || list.size() == 0;
    }

    /**
     * 去掉非字母的字符，然后剩余字母大写，并升序组成字符串返回
     *
     * @param src
     * @return
     */
    public static String getSortedUpperString(String src) {
        if (TextUtils.isEmpty(src)) {
            return src;
        }

        StringBuffer buffer = new StringBuffer();
        char[] chars = src.toCharArray();
        for (char c : chars) {
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                buffer.append(c);
            }
        }

        char[] list = buffer.toString().toCharArray();
        Arrays.sort(list);
        return String.valueOf(list).toUpperCase();
    }

    /**
     * 获取本地IP
     *
     * @return
     */
    public static String getLocalIpAddress() {

        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface netIf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = netIf
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address)) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            // TODO: handle exception
        }
        return null;
    }

    /**
     * 获取题目中的题号 如：第1题=>1，第12-15题=>12-15。
     *
     * @param questionName 题目名字
     * @return
     */
    public static String getQuestionNumber(String questionName) {
        if (TextUtils.isEmpty(questionName)) {
            return "";
        }

        Pattern p = Pattern.compile("[^a-zA-Z（）\\d.-]");
        Matcher m = p.matcher(questionName);
        String newName = questionName;
        while (m.find()) {
            newName = newName.replace(m.group(), "");
        }
        if (TextUtils.isEmpty(newName)) {
            return questionName;
        }
        return newName;
    }

    /**
     * 验证用户名是否合法
     * 用户名长度扩展到6-30位，首位仍然是字母开头，后面支持数字，减号，字母。
     *
     * @param account
     * @return
     */
    public static boolean isAccount(String account) {
        if (TextUtils.isEmpty(account)) {
            return false;
        }
        String regexAccount = "^[a-zA-Z][\\da-zA-Z\\-]{5,29}$";
        return Pattern.matches(regexAccount, account);
    }

    /**
     * 下发账号检测是否为家长账号
     *
     * @param account
     * @return
     */
    public static boolean isPreParentAccount(String account) {
        return account.toLowerCase().startsWith("p") && account.toLowerCase().contains("-");
    }

    /**
     * 判断是否是数理化生
     *
     * @param subject
     * @return
     */
    public static boolean isScience(String subject) {
        if (subject.contains("数学") ||
                subject.contains("物理") ||
                subject.contains("化学") ||
                subject.contains("生物") ||
                subject.contains("政治") ||
                subject.contains("历史") ||
                subject.contains("地理")) {
            return true;
        }
        return false;
    }

    /**
     * 获得困难程度
     *
     * @param diff
     * @return
     */
    public static String getDifficulty(float diff) {
        if (diff == 1.0) {
            return "容易";
        } else if (diff == 2.0) {
            return "较易";
        } else if (diff == 3.0) {
            return "中等";
        } else if (diff == 4.0) {
            return "较难";
        } else if (diff == 5.0) {
            return "困难";
        }
        return "";
    }

    /**
     * 将string转换成int并捕获异常
     *
     * @param s
     * @return
     */
    public static int parseString2Int(String s, int defaultValue) {
        if (TextUtils.isEmpty(s)) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    /**
     * 将string转换成float并捕获异常
     *
     * @param s
     * @return
     */
    public static float parseString2Float(String s, float defaultValue) {
        if (TextUtils.isEmpty(s)) {
            return defaultValue;
        }
        try {
            return Float.valueOf(s);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    /**
     * 是否是主进程
     *
     * @param application application
     * @return
     */
    public static boolean isMainProcess(Application application) {
        int pid = android.os.Process.myPid();
        String processName = getProcessName(application, pid);
        //判断进程名，保证只有主进程运行
        return !TextUtils.isEmpty(processName) && processName.equals(application.getPackageName());


    }

    /**
     * 获取进程名称
     *
     * @param cxt application
     * @param pid process id
     * @return
     */
    public static String getProcessName(Application cxt, int pid) {
        ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return null;
    }

    /**
     * 解决InputMethodManager内存泄漏问题
     *
     * @param destContext context
     */
    public static void fixInputMethodManagerLeak(Context destContext) {
        if (destContext == null) {
            return;
        }

        InputMethodManager imm = (InputMethodManager) destContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) {
            return;
        }

        String[] arr = new String[]{"mCurRootView", "mServedView", "mNextServedView"};
        Field f = null;
        Object objGet = null;
        for (int i = 0; i < arr.length; i++) {
            String param = arr[i];
            try {
                f = imm.getClass().getDeclaredField(param);
                if (f.isAccessible() == false) {
                    f.setAccessible(true);
                } // author: sodino mail:sodino@qq.com
                objGet = f.get(imm);
                if (objGet != null && objGet instanceof View) {
                    View vGet = (View) objGet;
                    if (vGet.getContext() == destContext) { // 被InputMethodManager持有引用的context是想要目标销毁的
                        f.set(imm, null); // 置空，破坏掉path to gc节点
                    } else {
                        // 不是想要目标销毁的，即为又进了另一层界面了，不要处理，避免影响原逻辑,也就不用继续for循环了
                        break;
                    }
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }


        fixLeakCanary696(destContext);
    }

    static void fixLeakCanary696(Context context) {
        if (!isEmui()) {
            Log.w("fix", "not emui");
            return;
        }
        try {
            Class clazz = Class.forName("android.gestureboost.GestureBoostManager");
            Log.w("fix", "clazz " + clazz);

            Field gestureBoostManager = clazz.getDeclaredField("sGestureBoostManager");
            gestureBoostManager.setAccessible(true);
            Field mContext = clazz.getDeclaredField("mContext");
            mContext.setAccessible(true);

            Object sGestureBoostManager = gestureBoostManager.get(null);
            if (sGestureBoostManager != null) {
                mContext.set(sGestureBoostManager, context);
            }
        } catch (Exception ignored) {
        }
    }

    static boolean isEmui() {
        return !TextUtils.isEmpty(getSystemProperty("ro.build.version.emui"));
    }

    static String getSystemProperty(String propName) {
        String line;
        BufferedReader input = null;
        try {
            Process p = Runtime.getRuntime().exec("getprop " + propName);
            input = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"), 1024);
            line = input.readLine();
            input.close();
        } catch (IOException ex) {
            return null;
        } finally {
            try {
                input.close();
            } catch (IOException e) {
            }
        }
        return line;
    }

    /**
     * 判断当前设备是手机还是平板，代码来自 Google I/O App for Android
     *
     * @param context
     * @return 平板返回 True，手机返回 False
     */
    public static boolean isPad(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    private static String sDeviceId;



    /**
     * 验证手机号是否合法
     *
     * @param phoneNum
     * @return
     */
    public static boolean isPhoneNum(String phoneNum) {
        if (TextUtils.isEmpty(phoneNum)) {
            return false;
        }
        String regexMobile;
        // regex_mobile = "^1(3[0-9]|4[57]|5[^4,\\D]|8[0-9]|7[06-8])\\d{8}$";
        regexMobile = "^1\\d{10}$";

        //  移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
        //  联通：130、131、132、152、155、156、185、186
        //  电信：133、153、180、189、（1349卫通）
        //String regex_mobile = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
        //  电信号段:133/153/180/181/189/177
        //  联通号段:130/131/132/155/156/185/186/145/176
        //  移动号段:134/135/136/137/138/139/150/151/152/157/158/159/182/183/184/187/188/147/178
        //  虚拟运营商:170
        return Pattern.matches(regexMobile, phoneNum);
    }
}
