package com.example.eoe;

import java.util.Date;

public class DayStats {
    private int dailyDoses1;
    private int dailyDoses2;
    private int totalDailyDoses;
    private int totalDoses;
    private Date date;

    public DayStats(int dailyDoses1, int dailyDoses2, int totalDailyDoses, int totalDoses, Date date) {
        this.dailyDoses1 = dailyDoses1;
        this.dailyDoses2 = dailyDoses2;
        this.totalDailyDoses = totalDailyDoses;
        this.totalDoses = totalDoses;
        this.date = date;
    }

    public int getDailyDoses1() {
        return dailyDoses1;
    }

    public int getDailyDoses2() {
        return dailyDoses2;
    }

    public int getTotalDailyDoses() {
        return totalDailyDoses;
    }

    public int getTotalDoses() {
        return totalDoses;
    }

    public Date getDate() {
        return date;
    }
}
