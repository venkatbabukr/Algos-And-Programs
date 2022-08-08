package com.venkat.utils;

import java.util.Arrays;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.venkat.utils.Constants.BitConstants;

public class NetworkUtils {

	// Ref: https://mkyong.com/regular-expressions/how-to-validate-ip-address-with-regular-expression/
    public static enum IPV4PartsRange {
        R1(0, 9, "[0-9]"),
        R2(10, 99, "[1-9][0-9]"),
        R3(100, 199, "1[0-9][0-9]"),
        R4(200, 249, "2[0-4][0-9]"),
        R5(250, 255, "25[0-5]");

        private int lower, upper;
        private String vptr;
        private IPV4PartsRange(int l, int u, String validationPattern) {
            this.lower = l;
            this.upper = u;
            this.vptr = validationPattern;
        }

        public int getLower() {
            return lower;
        }

        public int getUpper() {
            return upper;
        }

        public String getVptr() {
            return vptr;
        }

    }

    public static final String IPV4_VALIDATION_PATTERN_FROM_RANGES = Arrays.stream(IPV4PartsRange.values())
                                                                           .map(IPV4PartsRange::getVptr)
                                                                           .collect(Collectors.joining("|", "^((", ")(\\.(?!$)|$)){4}$"));

    public static final String IPV4_VALIDATION_PATTERN_STR = "^(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(\\.(?!$)|$)){4}$";

    public static final int STANDARD_PORT_NUMBERS_MAX = 1 << 10;

    private NetworkUtils() {
    }

    public static boolean isValidIp(String ip) {
        return Pattern.matches(IPV4_VALIDATION_PATTERN_STR, ip);
    }

    public static long ip2num(String ip) {
        if (!isValidIp(ip)) {
            throw new IllegalArgumentException("Invalid IPv4 address!");
        }
        String[] ipParts = ip.split("\\.");
        long ipNum = 0L;
        for (String part : ipParts) {
           ipNum = (ipNum << 8) + Integer.valueOf(part);
        }
        return ipNum;
    }

    public static String num2ip(long ipNum) {
        String[] ipParts = new String[4];
        for (int i = ipParts.length - 1; i >= 0; i--, ipNum >>= 8) {
            ipParts[i] = String.valueOf(ipNum & BitConstants.FF);
        }
        return Arrays.stream(ipParts).collect(Collectors.joining("."));
    }

    public static String randomIpAddr() {
    	Random rgen = new Random();
        IPV4PartsRange[] allRanges = IPV4PartsRange.values();
        return IntStream.range(0, 4)
                        .boxed()
                        .map(pos -> {
                            IPV4PartsRange rfp = allRanges[rgen.nextInt(allRanges.length)];
                            int posNum = rfp.lower + rgen.nextInt(rfp.upper - rfp.lower);
                            return String.valueOf(posNum);
                        })
                        .collect(Collectors.joining("."));
    }

    public static void main(String[] args) {
        System.out.println(ip2num("61.232.0.0"));
        System.out.println(num2ip(1038614528));
        System.out.println(randomIpAddr());
    }

}
