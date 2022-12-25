package com.code.jamie.mpesa;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        new Implementation("Azs2KejU1ARvIL5JdJsARbV2gDrWmpOB","hipGvFJbOxri330c").getService().authenticate("https://sandbox.safaricom.co.ke/");
    }
}