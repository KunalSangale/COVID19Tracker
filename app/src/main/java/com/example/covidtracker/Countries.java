package com.example.covidtracker;

public class Countries {
    private String flag,country,totalcases,recovered,deaths,tcases,tdeaths,active;

    public Countries() {
    }

    public Countries(String flag, String country, String totalcases, String recovered, String deaths, String tcases, String tdeaths, String active) {
        this.flag = flag;
        this.country = country;
        this.totalcases = totalcases;
        this.recovered = recovered;
        this.deaths = deaths;
        this.tcases = tcases;
        this.tdeaths = tdeaths;
        this.active = active;
    }
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTotalcases() {
        return totalcases;
    }

    public void setTotalcases(String totalcases) {
        this.totalcases = totalcases;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getTcases() {
        return tcases;
    }

    public void setTcases(String tcases) {
        this.tcases = tcases;
    }

    public String getTdeaths() {
        return tdeaths;
    }

    public void setTdeaths(String tdeaths) {
        this.tdeaths = tdeaths;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

}
