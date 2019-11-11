/*
 * MicroFocus.com Inc.
 * Copyright(c) 2019 All Rights Reserved.
 */
package org.danny.demo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;

/**
 * @author wusui
 * @version $Id: Game.java, 2019-11-11 11:20 AM wusui Exp $
 */
public class Game {

    public String say(int i) {
        String fizz = getFizz(i);
        String buzz = getBuzz(i);
        String result = fizz + buzz;
        return "".equals(result)? Integer.toString(i) : result;
    }

    String getFizz(int i) {
        String itos = Integer.toString(i);
        if (0 == i % 3 || itos.contains("3")) {
            return "Fizz";
        } else {
            return "";
        }
    }

    String getBuzz(int i) {
        String itos = Integer.toString(i);
        if (0 == i % 5 || itos.contains("5")) {
            return "Buzz";
        } else {
            return "";
        }
    }

    String getNow(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date d = new Date();
        return sdf.format(d);
    }

}
