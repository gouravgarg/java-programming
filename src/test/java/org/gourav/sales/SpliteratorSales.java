package org.gourav.sales;

import org.gourav.domain.Sales;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Spliterator;
import java.util.function.Consumer;

public class SpliteratorSales implements Spliterator<Sales> {

    Spliterator<String> stringSpliterator;

    private  boolean skipHeaders=true;

    public SpliteratorSales(Spliterator<String> stringSpliterator) {
        this.stringSpliterator = stringSpliterator;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Sales> action) {
        final Sales sales = new Sales();

        if(skipHeaders){
            for (int i = 0; i < 14; i++) {
                this.stringSpliterator.tryAdvance(line -> System.out.println(line));
            }
            skipHeaders=false;
        }


        final java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("MM/dd/yyyy");

       if( this.stringSpliterator.tryAdvance(sales:: setRegion)  &&
        this.stringSpliterator.tryAdvance(sales::setCountry) &&
        this.stringSpliterator.tryAdvance(sales::setItemType) &&
        this.stringSpliterator.tryAdvance(sales::setSalesChannel) &&
        this.stringSpliterator.tryAdvance(sales::setOrderPriority) &&
        this.stringSpliterator.tryAdvance(line -> {
            try {
                sales.setOrderDate(LocalDate.ofInstant(simpleDateFormat.parse(line).toInstant(), ZoneId.systemDefault()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }) &&
        this.stringSpliterator.tryAdvance(line -> sales.setOrderID(Long.parseLong(line))) &&
        this.stringSpliterator.tryAdvance(line -> {
                try {
                    sales.setOrderDate(LocalDate.ofInstant(simpleDateFormat.parse(line).toInstant(), ZoneId.systemDefault()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            })&&

        this.stringSpliterator.tryAdvance(line -> sales.setUnitsSold(Integer.parseInt(line)))&&
            this.stringSpliterator.tryAdvance(line -> sales.setPrice(Double.parseDouble(line)))&&
            this.stringSpliterator.tryAdvance(line -> sales.setCost(Double.parseDouble(line)))&&
            this.stringSpliterator.tryAdvance(line -> sales.setTotalRevenue(Double.parseDouble(line)))&&
            this.stringSpliterator.tryAdvance(line -> sales.setTotalCost(Double.parseDouble(line))) &&

            this.stringSpliterator.tryAdvance(line -> sales.setTotalProfit(Double.parseDouble(line)))){
                action.accept(sales);
                return true;

    }else{
           return false;
       }
    }

    @Override
    public Spliterator<Sales> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return this.stringSpliterator.estimateSize()/14;
    }

    @Override
    public int characteristics() {
        return this.stringSpliterator.characteristics();
    }
}
