package com.fariz.rest.controller;

import com.fariz.rest.model.ResponseModel;
import com.fariz.rest.service.SMSService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.invoke.MethodHandles;

@RestController
@RequestMapping("/api")
public class APIController {

    Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private SMSService smsService;

    @PostMapping(value = "/sms")
    public ResponseModel smsAPI(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody String body) {

        JSONObject jsonBody = new JSONObject(body);
        logger.info("***************************************");
        logger.info(body);
        logger.info("***************************************");

        ResponseModel responseModel = new ResponseModel();

        String action = jsonBody.getString("action");
        String subAction = jsonBody.getString("subAction");

        if (action.equalsIgnoreCase("SMS")
                && subAction.equalsIgnoreCase("sendSMS")) {
            String message = jsonBody.getString("message");
            responseModel = smsService.sendSMS(message);
        }

        if (action.equalsIgnoreCase("SMS")
                && subAction.equalsIgnoreCase("getHistorySMS")) {
            int limit = jsonBody.getInt("limit");
            responseModel = smsService.getHistorySMS(limit);
        }

        return responseModel;

    }

}

