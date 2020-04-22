package com.up.jdk.feature.jdk8;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

/**
 * @author yxy
 * @date 2020/4/22 14:02
 * @description
 */
public class DateApi {

    public static void main(String[] args) {

        System.out.println(LocalTime.now());
        System.out.println(LocalDateTime.now());
        System.out.println(ZonedDateTime.now());
    }
}
