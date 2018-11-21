package com.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 *
 * 规则：租BUC时计算取ceil最大值，还BUC时取floor最小值
 * Created by Administrator on 2018/4/11.
 */

public class NumberCovertUtils {

    public static final int Money_Precise = 2;            //金额换算保留的默认精度

    /**
     * 获取乘法运算结果
     **/
    public static String getMultiplyResult(String v1, String v2) {
        return getMultiplyResult(v1, v2, Money_Precise);
    }

    public static String getMultiplyResult(String v1, String v2, int precise) {
        return getMultiplyResult(v1, v2, precise, BigDecimal.ROUND_HALF_UP);
    }

    public static String getMultiplyResult(String v1, String v2, int precise,int round) {
        String monney = BigDecimalUtils.multiply(v1, v2, precise,round);
        if ("0E-8".equals(monney)) monney = "0";
        return subZeroAndDot(monney);
    }

    /**
     * 获取除法运算结果
     **/
    public static String getDivideResult(String v1, String v2) {
        return getDivideResult(v1, v2, Money_Precise);
    }

    public static String getDivideResult(String v1, String v2, int precise) {
        String monney = BigDecimalUtils.divide(v1, v2, precise);
        if ("0E-8".equals(monney)) monney = "0";
        return subZeroAndDot(monney);
    }

    public static String getDivideResult(double v1, double v2) {
        return getDivideResult(v1, v2, Money_Precise);
    }

    public static String getDivideResult(double v1, double v2, int precise) {
        double monney = BigDecimalUtils.divide(v1, v2, precise);
        if (0E-8==monney) monney = 0;
        return subZeroAndDot(String.valueOf(monney));
    }

    /**
     * 获取加法运算结果
     **/
    public static String getAddResult(String v1, String v2) {
        return getAddResult(v1, v2, Money_Precise);
    }

    public static String getAddResult(String v1, String v2, int precise) {
        return getAddResult(v1,v2,precise,BigDecimal.ROUND_HALF_UP);
    }

    public static String getAddResult(String v1, String v2, int precise,int round) {
        String monney = BigDecimalUtils.add(v1, v2, precise,round);
        if ("0E-8".equals(monney)) monney = "0";
        return subZeroAndDot(monney);
    }

    /**
     * 提供精确的小数位四舍五入处理
     *
     * @param v 需要四舍五入的数字
     * @return 四舍五入后的结果
     */
    public static String getRoundResult(String v) {
        String monney = BigDecimalUtils.round(v, Money_Precise);
        if ("0E-8".equals(monney)) monney = "0";
        return subZeroAndDot(monney);
    }

    public static String getRoundResult(String v,int precise) {
        String monney = BigDecimalUtils.round(v, precise);
        if ("0E-8".equals(monney)) monney = "0";
        return subZeroAndDot(monney);
    }

    public static String getRoundResult(double v) {
        double monney = BigDecimalUtils.round(v, Money_Precise);
        if ("0E-8".equals(monney)) monney = 0;
        return subZeroAndDot(String.valueOf(monney));
    }

    public static String getRoundResult(double v,int precise) {
        double monney = BigDecimalUtils.round(v, precise);
        if ("0E-8".equals(monney)) monney = 0;
        return subZeroAndDot(String.valueOf(monney));
    }


    /**
     * 使用java正则表达式去掉多余的.与0
     *
     * @param s
     * @return
     */
    public static String subZeroAndDot(String s) {
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }

    /**
     * 将每三个数字加上逗号处理（通常使用金额方面的编辑）
     *
     * @param str 需要处理的字符串
     * @return 处理完之后的字符串
     */
    public static String addComma(String str) {
        DecimalFormat decimalFormat = new DecimalFormat(",###");
        return decimalFormat.format(Double.parseDouble(str));
    }

    /**
     * 格式化数字，用逗号分割
     *
     * @param number 1000000.7569 to 1,000,000.76 or
     * @return
     */
    public static String formatNumberWithCommaSplit(double number) {
        String firstStr = "";//第一个字符
        String middleStr = "";//中间字符
        String endStr = "";//小数后两位
        if (number < 0) {
            firstStr = "-";
        } else if (number != 0 && number < 0.1) {
            return number + "";
        }

        String tempNumberStr = number + "00";
        int endIndex = tempNumberStr.lastIndexOf(".");
        endStr = tempNumberStr.substring(endIndex+1, endIndex + 3);

        String numberStr = Math.abs((long) number) + "";//取正

        int firstIndex = numberStr.length() % 3;
        int bitCount = numberStr.length() / 3;

        if (firstIndex > 0) {
            middleStr += numberStr.substring(0, firstIndex) + ",";
        }
        for (int i = 0; i < bitCount; i++) {
            middleStr += numberStr.substring(firstIndex + i * 3, firstIndex + i * 3 + 3) + ",";
        }
        if (middleStr.length() > 1) {
            middleStr = middleStr.substring(0, middleStr.length() - 1);
        }
        return firstStr + middleStr + "." + endStr;
    }

    /**
     * 手续费计算规则（1：手续费最低是0.01，如果最终手续费金额低于0.01，则默认0.01，2：手续费保留小数点后两位，如果小数位数超过两位，则进0.01位（eg: 0.151=0.16））
     *
     * @author lzy
     * create at 2018/5/21 10:45
     **/
    public static String getPoundageResult(String v1, String v2) {
        return getPoundageResult(v1,v2,8);
    }

    public static String getPoundageResult(String v1, String v2, int precise) {
        String a = getMultiplyResult(v1, v2, precise);
        double a1 = Double.parseDouble(a) * 100;
        double a2=Math.ceil(a1)/100.00;
        if (a1>0&&a2<0.01) a2=0.01;
        return String.valueOf(a2);
    }

    public static String three2Str(int src){
        if (src>=1000&&src<=99000){
            return getDivideResult(src,1000,3)+"K";
        }else if (src>99000){
            return "99k+";
        }
        return String.valueOf(src);
    }

    public static void main(String[] args) {
//        /**
//         * ceil天花板的意思，就是返回大的值
//         */
////        System.out.println(Math.ceil(10.7));    //11.0
////        System.out.println(Math.ceil(10.11));
//
//        String a = 0.1*0.005+"";
//        double a1 = Double.parseDouble(a) * 100;
//        int a2 = (int) a1;
//        double a3;
//        if (a1 > a2) {
//            a3 = (a2 + 1) / 100.00;
//        } else {
//            a3 = a2/100.00;
//        }
//        double a4=Math.ceil(a1)/100.00;
//
//        System.out.println(a);
//        System.out.println(a1);
//        System.out.println(a2);
//        System.out.println(a3);
//        System.out.println("==========================");
//        System.out.println(a4);

        int[] srcs={0,64,999,1000,1001,1026,1500,1701,1210,9999,99999};
        for (int src : srcs) {
            System.out.printf("\n"+src+"=====>"+three2Str(src));
        }
    }

}
