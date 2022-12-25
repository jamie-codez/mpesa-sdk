package com.code.jamie.mpesa;

import com.google.gson.JsonObject;
import netscape.javascript.JSObject;
import okhttp3.*;
import org.json.JSONObject;

import javax.print.attribute.standard.Media;
import java.io.IOException;
import java.util.Base64;

public class Implementation implements Service{
    private String appKey;
    private String appSecret;

    public Implementation(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public Service getService() {
        return this;
    }

    @Override
    public String authenticate(String url) throws IOException {
        String keySecret = String.format("%s:%s",appKey,appSecret);
        byte[] bytes = keySecret.getBytes("ISO-8859-1");
        String encoded = Base64.getEncoder().encodeToString(bytes);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url+"oauth/v1/generate?grant_type=client_credentials")
                .get()
                .addHeader("authorization","Basic "+encoded)
                .addHeader("cache-control","no-cache")
                .build();
        Response response = client.newCall(request).execute();
        JSONObject jsonObject = new JSONObject(response.body().string());
        System.out.println(jsonObject.getString("access_token"));
        return jsonObject.getString("access_token");
    }

    @Override
    public String C2B(String shortCode, String commandId, String amount, String phoneNumber, String billRefNum,String url) throws IOException {
        JSONObject jsonObject = new JSONObject()
                .put("shortCode",shortCode)
                .put("CommandID",commandId)
                .put("Amount",amount)
                .put("Msisdn",phoneNumber)
                .put("BillRefNumber",billRefNum);
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create(mediaType, String.valueOf(jsonObject));
        Request request = new Request.Builder()
                .url(url+"/c2b/v1/simulate")
                .post(requestBody)
                .addHeader("content-type", mediaType.type())
                .addHeader("authorization","Bearer "+authenticate(url))
                .addHeader("cache-control","no-cache")
                .build();
        Response response = client.newCall(request).execute();
        return response.body().toString();
    }

    @Override
    public String B2C(String initiatorName, String securityCreds, String commandId, String amount, String senderShortCode, String recipientPhone, String remarks, String queueTimeOutUrl, String resultUrl, String occasion,String url) throws IOException {
        JSONObject jsonObject = new JSONObject()
                .put("InitiatorName",initiatorName)
                .put("SecurityCredential",securityCreds)
                .put("CommandID",commandId)
                .put("Amount",amount)
                .put("PartyA",senderShortCode)
                .put("PartyB",recipientPhone)
                .put("Remarks",remarks)
                .put("QueueTimeOutURL",queueTimeOutUrl)
                .put("ResultURL",resultUrl)
                .put("Occassion",occasion);
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create(mediaType,jsonObject.toString());
        Request request = new Request.Builder()
                .url(url+"/b2c/v1/paymentrequest")
                .post(requestBody)
                .addHeader("content-type","application/json")
                .addHeader("cache-control","no-cache")
                .addHeader("authorization","Bearer "+authenticate(url))
                .build();
        Response response = client.newCall(request).execute();
        return response.body().toString();
    }

    @Override
    public String B2B(String initiatorName, String accRef, String secCreds, String commandId, String senderIdType, String receiverIdType, Double amount, String sender, String recipient, String remarks, String queueTimeOutUrl, String resultUrl, String url) throws IOException {
        JSONObject jsonObject = new JSONObject()
                .put("InitiatorName",initiatorName)
                .put("SecurityCredential",secCreds)
                .put("CommandID",commandId)
                .put("SenderIdentificationType",senderIdType)
                .put("RecieverIdentificationType",receiverIdType)
                .put("Amount",amount)
                .put("PartyA",sender)
                .put("PartyB",recipient)
                .put("Remarks",remarks)
                .put("QueueTimeOutURL",queueTimeOutUrl)
                .put("ResultURL",resultUrl);
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create(mediaType,jsonObject.toString());
        Request request = new Request.Builder()
                .url(url+"/b2b/v1/paymentrequest")
                .post(requestBody)
                .addHeader("content-type","application/json")
                .addHeader("authorization","Bearer "+authenticate(url))
                .addHeader("cache-control","no-cache")
                .build();
        Response response = client.newCall(request).execute();
        return response.body().toString();
    }

    @Override
    public String STKPushSimulation(String businessShortCode, String password, String timestamp, String transactionType, String amount, String phoneNumber, String sender, String recipient, String callBackURL, String queueTimeOutUrl, String accountReference, String transactionDesc,String url) throws IOException {
        return null;
    }

    @Override
    public String STKPushTransactionStatus(String businessShortCode, String password, String timestamp, String checkoutRequestId) throws IOException {
        return null;
    }

    @Override
    public String reversal(String initiator, String securityCredential, String commandId, String transactionId, String amount, String receiverParty, String receiverIdType, String resultURL, String queueTimeOutUrl, String remarks, String occasion) throws IOException {
        return null;
    }

    @Override
    public String balanceInquiry(String initiator, String commandID, String securityCredential, String partyA, String identifierType, String remarks, String queueTimeOutURL, String resultURL) throws IOException {
        return null;
    }

    @Override
    public String registerURL(String shortCode, String responseType, String confirmationUrl, String validationUrl) throws IOException {
        return null;
    }
}
