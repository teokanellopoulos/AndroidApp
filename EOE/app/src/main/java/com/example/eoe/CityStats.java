package com.example.eoe;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class CityStats {
    @SerializedName("area")
    private String area;

    @SerializedName("areaid")
    private String areaId;

    @SerializedName("dailydose1")
    private String dailyDoses1;

    @SerializedName("dailydose2")
    private String dailyDoses2;

    @SerializedName("daydiff")
    private String dayDiff;

    @SerializedName("daytotal")
    private String dayTotal;

    @SerializedName("referencedate")
    private String referenceDate;

    @SerializedName("totaldistinctpersons")
    private String totalDistinctPersons;

    @SerializedName("totaldose1")
    private String getTotalDose1;

    @SerializedName("totaldose2")
    private String totalDose2;

    @SerializedName("totalvaccinations")
    private String totalVaccinations;

    public String getArea() {
        return area;
    }

    public String getAreaId() {
        return areaId;
    }

    public String getDailyDoses1() {
        return dailyDoses1;
    }

    public String getDailyDoses2() {
        return dailyDoses2;
    }

    public String getDayDiff() {
        return dayDiff;
    }

    public String getDayTotal() {
        return dayTotal;
    }

    public String getReferenceDate() {
        return referenceDate;
    }

    public String getTotalDistinctPersons() {
        return totalDistinctPersons;
    }

    public String getGetTotalDose1() {
        return getTotalDose1;
    }

    public String getTotalDose2() {
        return totalDose2;
    }

    public String getTotalVaccinations() {
        return totalVaccinations;
    }
}
