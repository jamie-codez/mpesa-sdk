package com.code.jamie.mpesa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnit4.class)
public class ImplementationTest {
    private Implementation implementation;

    @Before
    public void setUp() throws Exception {
        implementation = new Implementation("Azs2KejU1ARvIL5JdJsARbV2gDrWmpOB","hipGvFJbOxri330c");
    }

    @After
    public void tearDown() throws Exception {
        implementation = null;
    }

    @Test
    public void getAppKeyTest() {
        assertThat(implementation.getAppKey()).isNotEmpty();
    }

    @Test
    public void getAppSecretTest() {
        assertThat(implementation.getAppSecret()).isNotEmpty();
    }

    @Test
    public void getServiceTest() {
        assertThat(implementation.getService()).isNotNull();
    }

    @Test
    public void authenticateTest() throws IOException {
        assertThat(implementation.authenticate("https://sandbox.safaricom.co.ke/oauth/v1/")).isNotEmpty();
    }

    @Test
    public void c2BTest() {

    }

    @Test
    public void b2CTest() {
    }

    @Test
    public void b2BTest() {
    }

    @Test
    public void STKPushTest() {
    }

    @Test
    public void STKPushTransactionStatusTest() {
    }

    @Test
    public void reversalTest() {
    }

    @Test
    public void balanceInquiryTest() {
    }

    @Test
    public void registerURLTest() {
    }
}