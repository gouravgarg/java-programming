package org.gourav.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class SP500 {

    private Date date;
    private BigDecimal open, high, low, close, adjClose;
    private double  openDouble, highDouble, lowDouble, closeDouble, adjCloseDouble;
    private long volume;
    private int year;

    public SP500(Date date, BigDecimal open, BigDecimal high, BigDecimal low, BigDecimal close, BigDecimal adjClose, long volume) {
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.adjClose = adjClose;
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "SP500{" +
                "date=" + date +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", adjClose=" + adjClose +
                ", volume=" + volume +
                '}';
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public BigDecimal getAdjClose() {
        return adjClose;
    }

    public void setAdjClose(BigDecimal adjClose) {
        this.adjClose = adjClose;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public int getYear() {
        return LocalDate.ofInstant(getDate().toInstant(), ZoneId.systemDefault()).getYear();
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getCloseDouble() {
        return Double.parseDouble(getClose().toString());
    }

    public double getOpenDouble() {
        return Double.parseDouble(getOpen().toString());
    }

    public boolean isOpenClose(){
        return getCloseDouble()>3360 && getOpenDouble()>3380;
    }
    public boolean isOpenClose(double closeDouble,double openDouble){
        return getCloseDouble()>closeDouble && getOpenDouble()>openDouble;
    }
}
