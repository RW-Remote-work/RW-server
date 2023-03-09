package com.rwws.rwserver.common.constant;

import java.util.regex.Pattern;

public class RegexPatternFactory {

    private final static Pattern emailPattern = Pattern.compile(RegexConstant.EMAIL);

    public static Pattern emailPattern() {
        return emailPattern;
    }
}
