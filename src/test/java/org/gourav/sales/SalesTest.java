package org.gourav.sales;

import org.gourav.domain.Sales;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class SalesTest {

    java.nio.file.Path path = java.nio.file.Paths.get("/home/gourav/Downloads/sales.csv");
    private List<Sales> salesList = new ArrayList<>();

    @Test
    void loadSalesData() throws IOException {
        java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("MM/dd/yyyy");
        try (java.util.stream.Stream<String> stream = java.nio.file.Files.lines(path)) {
            stream.skip(1).forEach(input -> {
                Sales sales = null;
                try {
                    sales = new Sales(input.split(",")[0],
                            input.split(",")[1],
                            input.split(",")[2],
                            input.split(",")[3],
                            input.split(",")[4],
                            LocalDate.ofInstant(simpleDateFormat.parse(input.split(",")[5]).toInstant(), ZoneId.systemDefault()),
                            Long.parseLong(input.split(",")[6]),
                            LocalDate.ofInstant(simpleDateFormat.parse(input.split(",")[7]).toInstant(), ZoneId.systemDefault()),
                            Integer.parseInt(input.split(",")[8]),
                            Double.parseDouble(input.split(",")[9]),
                            Double.parseDouble(input.split(",")[10]),
                            Double.parseDouble(input.split(",")[11]),
                            Double.parseDouble(input.split(",")[12]),
                            Double.parseDouble(input.split(",")[13])
                    );
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                salesList.add(sales);
            });

            assertEquals(500000, salesList.size());

            //get max revenue

            Optional<Sales> sales = salesList.stream().collect(Collectors.maxBy(Comparator.comparing(a -> a.getTotalRevenue())));
            //System.out.println(sales.isPresent()?sales.get():null);
            assertEquals(6682700.00, sales.get().getTotalRevenue());

            sales = salesList.stream().collect(Collectors.minBy(Comparator.comparing(a -> a.getTotalRevenue())));
            //System.out.println(sales.isPresent()?sales.get():null);
            assertEquals(9.33, sales.get().getTotalRevenue());

        }
    }

    void get1000Rows() throws IOException {
        java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("MM/dd/yyyy");
        try (java.util.stream.Stream<String> stream = java.nio.file.Files.lines(path)) {
            stream.skip(1).limit(1000).forEach(input -> {
                Sales sales = null;
                try {
                    sales = new Sales(input.split(",")[0],
                            input.split(",")[1],
                            input.split(",")[2],
                            input.split(",")[3],
                            input.split(",")[4],
                            LocalDate.ofInstant(simpleDateFormat.parse(input.split(",")[5]).toInstant(), ZoneId.systemDefault()),
                            Long.parseLong(input.split(",")[6]),
                            LocalDate.ofInstant(simpleDateFormat.parse(input.split(",")[7]).toInstant(), ZoneId.systemDefault()),
                            Integer.parseInt(input.split(",")[8]),
                            Double.parseDouble(input.split(",")[9]),
                            Double.parseDouble(input.split(",")[10]),
                            Double.parseDouble(input.split(",")[11]),
                            Double.parseDouble(input.split(",")[12]),
                            Double.parseDouble(input.split(",")[13])
                    );
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                salesList.add(sales);
            });
    }
    }


    @Test
    public void loadTopNRows() throws IOException {

            get1000Rows();
            assertEquals(1000, salesList.size());
            //salesList.stream().forEach(sales -> System.out.println(sales));
        }


    /**
     * Its called Histogram from Stream
     * Full Playlist of Collectors
     * Downstream collector
     * Play with streams to map
     *
     * @throws IOException
     */
    @Test
    public void playingWithHistogram() throws IOException {

        get1000Rows();
        //sales total revenue more than 100000 in arraylist
        List<Double> salesTotalProfile1000 = salesList.stream()
                .map(sales -> sales.getTotalProfit())   // only to check Function interface
                .filter(d -> d>100000)
                .collect(Collectors.toList());
        assertEquals(737, salesTotalProfile1000.size());

        //sales total revenue more than 100000 in set
        Set<Sales> salesTotalProfile1000Set = salesList.stream()
                .filter(d -> d.getTotalProfit() > 100000)
                .collect(Collectors.toSet());
        assertEquals(737, salesTotalProfile1000Set.size());

        //sales total revenue more than 100000 in Linked list (We can create any custom data structure also)
        LinkedList<Sales> salesTotalProfile1000LL = salesList.stream()
                .filter(d -> d.getTotalProfit() > 100000)
                .collect(Collectors.toCollection(LinkedList::new));
        assertEquals(737,  salesTotalProfile1000LL.size());

        /**
         * Convert stream into map by Region wise
         */
        Map<String, List<Sales>> salesMapRegionWise = salesList.stream()
                // .map(s -> s.getTotalProfit())
                .filter(d -> d.getTotalProfit() > 100000)
                .collect(groupingBy(Sales::getRegion));

        assertEquals(7, salesMapRegionWise.keySet().stream().count());

        /**
         * Convert stream into map by Country Wise
         */
        Map<String, List<Sales>> salesMapCountryWise = salesList.stream()
                .filter(d -> d.getTotalProfit() > 100000)
                .collect(groupingBy(Sales::getCountry));

        assertEquals(180, salesMapCountryWise.keySet().stream().count());


        /**
         * Convert stream into map
         *  and Downstream collector
         */
        Map<String, Long> salesMapCountCountryWise = salesList.stream()
                .filter(d -> d.getTotalProfit() > 100000)
                .collect(Collectors.groupingBy(Sales::getRegion,Collectors.counting()));

        assertEquals(7, salesMapCountCountryWise.keySet().stream().count());

        /**
         * Maximum number of sales in country
          */
        System.out.println("Maximum number of sales in country="+salesMapCountCountryWise.entrySet().stream().max(Comparator.comparing(entry -> entry.getValue())).orElseThrow());

        /**
         * Minimum number of sales in country
         */
        System.out.println("Minimum number of sales in country="+salesMapCountCountryWise.entrySet().stream().min(Comparator.comparing(entry -> entry.getValue())).orElseThrow());
        /**
         *      OR
         */
        //System.out.println(salesMapCountCountryWise.entrySet().stream().min(Map.Entry.comparingByValue()).orElseThrow());


        /**
         * DoubleSummaryStatistics by Region Wise
         */
        Map<String, DoubleSummaryStatistics> doubleSummaryStatisticsSalesRegionWiseMap = salesList.stream()
                .filter(d -> d.getTotalProfit() > 100000)
                .collect(Collectors.groupingBy(Sales::getRegion,Collectors.summarizingDouble(value -> value.getTotalProfit())));

        assertEquals(7, doubleSummaryStatisticsSalesRegionWiseMap.keySet().stream().count());
        doubleSummaryStatisticsSalesRegionWiseMap.entrySet().stream().forEach(System.out::println);

        /**
         * SummingDouble by Region Wise
         */
        Map<String, Double> totalSalesRegionWiseMap = salesList.stream()
                .filter(d -> d.getTotalProfit() > 100000)
                .collect(groupingBy(Sales::getRegion,Collectors.summingDouble(value -> value.getTotalProfit())));

        assertEquals(7, totalSalesRegionWiseMap.keySet().stream().count());
        Consumer<String> stringConsumer =System.out::println;
        totalSalesRegionWiseMap.entrySet().stream().forEach(d -> stringConsumer.accept(d.toString()));

    }
}

