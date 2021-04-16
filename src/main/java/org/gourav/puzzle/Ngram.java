package org.gourav.mix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;


public class Ngram {

    public static void main(String[] args) throws IOException {

        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);
        String input;
        while ((input = in.readLine()) != null) {
            break;
        }

        String text = "Mary had a little lamb its fleece was white as snow;" +
                "And everywhere that Mary went, the lamb was sure to go. " +
                "It followed her to school one day, which was against the rule;" +
                "It made the children laugh and play, to see a lamb at school." +
                "And so the teacher turned it out, but still it lingered near," +
                "And waited patiently about till Mary did appear." +
                "Why does the lamb love Mary so?\" the eager children cry;" +
                "\"Why, Mary loves the lamb, you know\" the teacher did reply.";

        String[] inputParams = input.split(",");
        String word = inputParams[1];
        int n = Integer.parseInt(inputParams[0]);

        String[] sequences = trimFirstWord(findNgramsOfWord(text, word, n), n);

        java.util.Map<String, Integer> counted = sortByValue(getCountOfWords(sequences));

        java.util.Iterator iterator = counted.entrySet().iterator();
        int counter = 0;
        while (iterator.hasNext()) {
            if (counter > 0) {
                System.out.print(";");
            }
            java.util.Map.Entry pair = (java.util.Map.Entry) iterator.next();
            System.out.print(pair.getKey() + "," + getProbability((int) pair.getValue(), sequences.length));
            counter++;
        }
    }

    public static String[] trimFirstWord(String[] ngrams, int n) {
        for (int i = 0; i < ngrams.length; i++) {
            for (int j = 0; j < n; j++) {
                ngrams[i] = ngrams[i].substring(ngrams[i].indexOf(" ") + 1);
            }
        }
        return ngrams;
    }

    public static <K, V extends Comparable<? super V>> java.util.Map<K, V> sortByValue(java.util.Map<K, V> map) {
        java.util.List<java.util.Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
        java.util.Collections.sort(list, new java.util.Comparator<java.util.Map.Entry<K, V>>() {
            @Override
            public int compare(java.util.Map.Entry<K, V> e1, java.util.Map.Entry<K, V> e2) {
                return e2.getValue().compareTo(e1.getValue());
            }
        });

        java.util.Map<K, V> result = new java.util.LinkedHashMap<>();
        for (java.util.Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    public static java.util.Map<String, Integer> getCountOfWords(String[] words) {
        java.util.Map<String, Integer> countedWords = new java.util.TreeMap<>();
        for (int i = 0; i < words.length; i++) {
            if (countedWords.containsKey(words[i]))
                countedWords.put(words[i], countedWords.get(words[i]) + 1);
            else
                countedWords.put(words[i], 1);
        }
        return countedWords;
    }


    public static String[] findAllNgrams(String text, int n) {
        String[] words = findWords(text);
        String[] result = new String[words.length - n + 1];
        for (int i = 0; i < result.length; i++) {
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (j > 0) {
                    builder.append(" ");
                }
                builder.append(words[i + j]);
            }
            result[i] = builder.toString();
        }
        return result;
    }

    public static String[] findNgramsOfWord(String text, String word, int n) {
        String[] words = findWords(text);
        String[] ngrams = findAllNgrams(text, n - 1);

        java.util.List<String> result = new java.util.ArrayList<>();
        for (int i = 0; i < ngrams.length - n + 1; i++) {
            if (ngrams[i].equals(word)) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    if (j > 0) {
                        builder.append(" ");
                    }
                    builder.append(words[i + j]);
                }
                result.add(builder.toString());
            }
        }
        return result.toArray(new String[result.size()]);
    }

    public static String[] findWords(String text) {
        text = text.trim().replaceAll("[^A-Za-z0-9 ]", " ").replaceAll(" +", " ");
        return text.split(" ");
    }

    public static String getProbability(int count, int total) {
        Float result = (float) count / total;
        return String.format("%.3f", result);
    }

}