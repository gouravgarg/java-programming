package org.gourav;

import org.gourav.domain.Sales;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Spliterator;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Stream2Words {

    @Test
    public void testStream2Word(){

        java.nio.file.Path path = Paths.get("/home/gourav/Downloads/sales.csv");

        try(Stream<String> stringStream = Files.lines(path);){

            Function<String, Stream<String>> split2WordsStream= line -> Pattern.compile(",").splitAsStream(line);

            Stream<String>  wordsStream = stringStream.flatMap(split2WordsStream);

           // System.out.println(wordsStream.count());

            Files.write(Paths.get("/home/gourav/Downloads/sales2.csv"), (Iterable<String>)wordsStream::iterator);


        }catch(IOException ioException){
            ioException.printStackTrace();
        }



    }

    @Test
    public void  testCustomSplitator(){
        System.out.println( LocalDateTime.now());
        java.nio.file.Path path = Paths.get("/home/gourav/Downloads/sales2.csv");

        try(Stream<String> stringStream = Files.lines(path);){

            Spliterator<String> stringSpliterator   = stringStream.spliterator();

            Spliterator<Sales> salesSpliterator = new SpliteratorSales(stringSpliterator);

            Stream<Sales> salesStream  = StreamSupport.stream(salesSpliterator,false);

            assertEquals(500000,salesStream.count());

            System.out.println( LocalDateTime.now());
        }catch(IOException ioException){
            ioException.printStackTrace();
        }
    }
}
