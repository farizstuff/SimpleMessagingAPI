package com.fariz.rest.service;

import com.fariz.rest.model.ResponseModel;
import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

@Service
public class SMSService {

    Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    // Change SID, TOKEN and SENDER NUMBER with your own number at your Twilio Account
    public static final String ACCOUNT_SID = "##########";
    public static final String AUTH_TOKEN = "##########";
    public static final String SENDER = "+12512728408";
    public static final String DESTINATION = "+628111719692";

    //method for sending sms
    public ResponseModel sendSMS (String message){
        logger.info("**************************************************************************");
        logger.info("message: "+message);
        logger.info("**************************************************************************");

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message messageContent = Message
                .creator(new PhoneNumber(DESTINATION),
                        new PhoneNumber(SENDER),
                        message).create();

        ResponseModel responseModel = new ResponseModel();

        responseModel.setCode(200);
        responseModel.setMessageStatus("Success");
        responseModel.setContent(messageContent);

        return responseModel;
    }

    //method for receive sms history
    public ResponseModel getHistorySMS (int limit){
        logger.info("**************************************************************************");
        logger.info("limit: "+limit);
        logger.info("**************************************************************************");

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        ResourceSet<Message> messages = Message.reader().limit(limit).read();
        List<Object> records = new ArrayList<>();

        for(Message record : messages) {
            JSONObject obj = new JSONObject();
            obj.put("id", record.getSid());
            obj.put("sender", record.getFrom());
            obj.put("destination", record.getTo());
            obj.put("messageContent", record.getBody());
            obj.put("sendDate", record.getDateSent());

            records.add(obj.toMap());
        }

        ResponseModel responseModel = new ResponseModel();

        responseModel.setCode(200);
        responseModel.setMessageStatus("Success");
        responseModel.setContent(records);

        return responseModel;

    }

}
