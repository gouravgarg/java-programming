package org.gourav.stocks;

import org.gourav.domain.SP500;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.file.Files.newBufferedReader;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for SP500.
 */

public class TestSP500 {
    List<SP500> sp500s = new ArrayList<>();
    private long numberOfRows = 23267;
    private String sourceFile = "GSPC.csv";
    private LocalDate closeLowestDate = LocalDate.parse("1932-06-01");
    private LocalDate closeHighestDate = LocalDate.parse("2020-02-19");
    private java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");

    @Test
    void playWithDates() throws IOException, ParseException {
        csvReadStream2Pojo();
        assertEquals(numberOfRows - 1, sp500s.size());
        //Validate Lowest/Highest Close
//        SP500 sp500closeLowest = sp500s.stream().sorted(java.util.Comparator.comparing(SP500::getClose)).findFirst().get();
//        SP500 sp500closeHighest = sp500s.stream().sorted(java.util.Comparator.comparing(SP500::getClose).reversed()).findFirst().get();
        //or
        //get lowest close date
        SP500 sp500closeLowest = sp500s.stream().parallel().min(java.util.Comparator.comparing(SP500::getClose)).get();
        //get highest close date
        SP500 sp500closeHighest = sp500s.stream().parallel().max(java.util.Comparator.comparing(SP500::getClose)).get();

        //Java 9
        assertEquals(closeLowestDate, LocalDate.ofInstant(sp500closeLowest.getDate().toInstant(), ZoneId.systemDefault()));
        assertEquals(closeHighestDate, LocalDate.ofInstant(sp500closeHighest.getDate().toInstant(), ZoneId.systemDefault()));

        //Type 1
        DoubleSummaryStatistics doubleSummaryStatistics = sp500s.stream().collect(Collectors.summarizingDouble(value -> value.getCloseDouble()));
        assertEquals(sp500closeLowest.getCloseDouble(), doubleSummaryStatistics.getMin());
        assertEquals(sp500closeHighest.getCloseDouble(), doubleSummaryStatistics.getMax());
        //Type 2
        assertEquals(sp500closeLowest.getCloseDouble(), sp500s.stream().mapToDouble(SP500::getCloseDouble).min().getAsDouble());
        assertEquals(sp500closeHighest.getCloseDouble(), sp500s.stream().mapToDouble(SP500::getCloseDouble).max().getAsDouble());

        //
        SP500 marketStart = sp500s.stream().sorted(java.util.Comparator.comparing(SP500::getDate)).findFirst().get();
        SP500 marketEnd = sp500s.stream().sorted(java.util.Comparator.comparing(SP500::getDate).reversed()).findFirst().get();

        LocalDate startDate = LocalDate.ofInstant(marketStart.getDate().toInstant(), ZoneId.systemDefault());//Java9
        LocalDate endDate = marketEnd.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();    //Java8

        for (LocalDate localDate = startDate; localDate.isBefore(endDate); localDate = localDate.plusYears(1)) {
            //   System.out.println(localDate);+
        }
        java.util.Map<Integer, List<SP500>> sp500YearlyListMap = sp500s.stream().collect(Collectors.groupingByConcurrent(SP500::getYear));
        System.out.println(sp500YearlyListMap.get(1927).stream().anyMatch(sp500 -> sp500.getVolume() >= 0));

        sp500YearlyListMap.get(2020).stream().filter(sp500 -> sp500.getCloseDouble() > 3360 && sp500.getOpenDouble() > 3380).forEach(System.out::println);
        //or
        sp500YearlyListMap.get(2020).stream().filter(SP500::isOpenClose).forEach(System.out::println);



        java.util.Map<Integer, Long> spYearlyCount = sp500s.stream().collect(Collectors.groupingByConcurrent(SP500::getYear, Collectors.counting()));
        assertEquals(spYearlyCount.get(1928), 250L);

        java.util.Map<Integer, DoubleSummaryStatistics> sp500YearlyDoubleSummaryStatisticsMap = sp500s.stream().
                collect(Collectors.groupingBy(SP500::getYear, Collectors.summarizingDouble(SP500::getCloseDouble)));
        System.out.println(sp500YearlyDoubleSummaryStatisticsMap.get(2020));

        //Pending
//        try (java.util.stream.Stream<String> stream = java.nio.file.Files.lines(path)) {
//            assertEquals(numberOfRows,stream.collect(java.util.stream.Collectors.toMap()).size());
//        }
    }

    @Test
    @Disabled
    public void csvReadForLoop2Pojo() throws IOException, ParseException {
        java.nio.file.Path path =  Paths.get(sourceFile);
        List<String> list = Files.readAllLines(path);
        for (String line : list) {
            System.out.println(line);
            SP500 sp500 = new SP500(
                    simpleDateFormat.parse(line.split(",")[0]),
                    new BigDecimal(line.split(",")[1]),
                    new BigDecimal(line.split(",")[2]),
                    new BigDecimal(line.split(",")[3]),
                    new BigDecimal(line.split(",")[4]),
                    new BigDecimal(line.split(",")[5]),
                    Long.parseLong(line.split(",")[6]));
            sp500s.add(sp500);
        }
        assertEquals(numberOfRows - 1, sp500s.size());
    }


    public void csvReadStream2Pojo() throws IOException {
        java.nio.file.Path path = Paths.get(sourceFile);
        try(java.util.stream.Stream<String> stringStream = Files.lines(path)){
            stringStream.skip(1).forEach(line -> {
                SP500 sp500 = null;
                try {
                    sp500 = new SP500(
                            simpleDateFormat.parse(line.split(",")[0]),
                            //new java.util.Date(java.sql.Date.valueOf(LocalDate.parse(line.split(",")[0])).getTime()),   //java 8/9
                            new BigDecimal(line.split(",")[1]),
                            new BigDecimal(line.split(",")[2]),
                            new BigDecimal(line.split(",")[3]),
                            new BigDecimal(line.split(",")[4]),
                            new BigDecimal(line.split(",")[5]),
                            Long.parseLong(line.split(",")[6]));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                sp500s.add(sp500);
            });
        }
        assertEquals(numberOfRows - 1, sp500s.size());
    }

    @Test
    @RepeatedTest(5)
    public void csvSet() throws IOException {
        try (java.util.stream.Stream<String> stream = Files.lines(Paths.get(sourceFile))) {
            assertEquals(numberOfRows, stream.collect(Collectors.toSet()).size());
        }
    }

    @Test
    public void csv2List() throws IOException {
        try (java.util.stream.Stream<String> stream = Files.lines(Paths.get(sourceFile))) {
            assertEquals(numberOfRows, stream.collect(Collectors.toList()).size());
        }

    }

    @Test
    public void csvJoining() throws IOException {
        try (java.util.stream.Stream<String> stream = Files.lines(Paths.get(sourceFile))) {
            String joined = stream.collect(Collectors.joining("|"));
            System.out.println(""+joined.length());
            assertEquals(1676975,joined.length());
        }
    }

    @Test
    public void testFile() throws IOException {
        List<String> result = new ArrayList<>();
        try (BufferedReader reader = newBufferedReader(Paths.get(sourceFile))) {
            for (;;) {
                String line = reader.readLine();
                if (line == null)
                    break;
                result.add(line);
            }
            assertEquals(numberOfRows, result.size());
        }

    }

    @Test
    public void testWriteList() throws IOException {
        List<String> stringList =new ArrayList<>();
        try(BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(sourceFile));){
            for(;;){
                String line = bufferedReader.readLine();
                if(null==line)
                    break;
                stringList.add(line);
            }
            assertEquals(numberOfRows,stringList.size());
        }

        Files.write(Paths.get("GSPC2.csv"),stringList, StandardCharsets.UTF_8);

        Files.writeString(Paths.get("GSPC3.csv"),"Gourav", StandardCharsets.UTF_8);

    }
}
