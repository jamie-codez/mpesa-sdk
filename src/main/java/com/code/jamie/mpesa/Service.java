package com.code.jamie.mpesa;

import java.io.IOException;

public interface Service {
    String authenticate(String url)throws IOException;
    String C2B(String shortCode,String commandId,String amount,String phoneNumber,String billRefNum) throws IOException;
    String B2C(String initiatorName,String securityCreds,String commandId,String amount,String senderShortCode,String recipientPhone,String remarks,String queueTimeOutUrl,String resultUrl,String occasion) throws IOException;
    String B2B(String initiatorName,String accRef,String secCreds,String commandId,String senderIdType,String receiverIdType,Double amount,String sender,String recipient,String remarks,String queueTimeOutUrl,String resultUrl,String occasion)throws IOException;
    String STKPushSimulation(String businessShortCode, String password, String timestamp,String transactionType, String amount, String phoneNumber, String sender, String recipient, String callBackURL, String queueTimeOutUrl,String accountReference, String transactionDesc) throws IOException;
    String STKPushTransactionStatus( String businessShortCode, String password, String timestamp, String checkoutRequestId) throws IOException;
    String reversal(String initiator, String securityCredential, String commandId, String transactionId, String amount, String receiverParty, String receiverIdType, String resultURL,String queueTimeOutUrl, String remarks, String occasion) throws IOException;
    String balanceInquiry(String initiator, String commandID, String securityCredential, String partyA, String identifierType, String remarks, String queueTimeOutURL, String resultURL) throws IOException;
    String registerURL(String shortCode, String responseType, String confirmationUrl, String validationUrl) throws IOException;
}
