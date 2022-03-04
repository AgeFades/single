package com.agefades.single.admin.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * 摸鱼办文化输出
 *
 * @author AgeFades
 * @date 2021/12/21 11:42 AM
 */
public class MoYuBan {

    /**
     * 当前时间
     */
    private static final LocalDateTime now = LocalDateTime.now();
    /**
     * 当前日期
     */
    private static final LocalDate nowDate = now.toLocalDate();
    /**
     * 当前时分秒
     */
    private static final LocalTime nowTime = now.toLocalTime();
    /**
     * 每日下班时间
     */
    private static final LocalTime offDuty = LocalTime.parse("18:00:00");

    public static void main(String[] args) {
        System.out.println("【摸鱼办】提醒您: " +
                (now.getHour() < 12 ? "上午好" : "下午好") +
                ", 摸鱼人！当前时间为: " + now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        System.out.println("工作再累，一定不要忘记摸鱼哦！有事没事起身去茶水间、去厕所、去廊道走走，别老在工位上坐着。钱是老板的，命是自己的！");

        // 今日下班时间
        print("今日下班", nowDate, offDuty);

        // 周五下班时间
        print("周五下班", nowDate.plusDays(5 - now.getDayOfWeek().getValue()), offDuty);

        // 清明节
        print("离清明节", LocalDate.parse("2022-04-05"));

        // 劳动节
        print("离劳动节", LocalDate.parse("2022-05-01"));

        // 端午节
        print("离端午节", LocalDate.parse("2022-06-03"));

        // 中秋节
        print("离中秋节", LocalDate.parse("2022-09-10"));

        // 国庆节
        print("离国庆节", LocalDate.parse("2022-10-01"));

        // 元旦
        print("离元旦节", LocalDate.parse("2023-01-01"));

        // 春节
        print("离过春节", LocalDate.parse("2023-01-22"));
    }

    /**
     * 输出节日、时间、间距，指定目标时间
     */
    public static void print(String targetName, LocalDate targetDate, LocalTime targetTime) {
        System.out.println("距" + targetName + " " + targetDate + " 时间还有: \t"
                + diffDays(targetDate)
                + diffHours(targetTime)
                + diffMinutes(targetTime)
                + diffSeconds(targetTime));
    }

    /**
     * 输出节日、时间、间距，目标时间默认为一天的结束时间
     */
    public static void print(String targetName, LocalDate targetDate) {
        System.out.println("距" + targetName + " " + targetDate + " 时间还有: \t"
                + diffDays(targetDate.minusDays(1))
                + diffHours(LocalTime.MAX)
                + diffMinutes(LocalTime.MAX)
                + diffSeconds(LocalTime.MAX));
    }

    /**
     * 时间差(单位: 秒)
     */
    private static String diffSeconds(LocalTime target) {
        return ChronoUnit.SECONDS.between(nowTime, target) % 60 + "秒";
    }

    /**
     * 时间差(单位: 分)
     */
    private static String diffMinutes(LocalTime target) {
        return ChronoUnit.MINUTES.between(nowTime, target) % 60 + "分";
    }

    /**
     * 时间差(单位: 小时)
     */
    private static String diffHours(LocalTime target) {
        return ChronoUnit.HOURS.between(nowTime, target) + "小时";
    }

    /**
     * 时间差(单位: 天)
     */
    private static String diffDays(LocalDate target) {
        long days = ChronoUnit.DAYS.between(nowDate, target);
        if (days >= 1) {
            return days + "天";
        }
        return "";
    }

}
