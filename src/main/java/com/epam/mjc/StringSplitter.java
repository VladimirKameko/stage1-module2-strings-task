package com.epam.mjc;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source     source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {

        StringBuilder sb = new StringBuilder("[");
        for (String str : delimiters) {
            sb.append(str.trim());
        }
        sb.append("]");

        return Arrays.stream(source.split(sb.toString())).filter(s -> !s.equals("")).collect(Collectors.toList());

    }
}
