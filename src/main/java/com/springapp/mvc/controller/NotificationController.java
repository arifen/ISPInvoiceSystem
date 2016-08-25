package com.springapp.mvc.controller;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arifen on 8/18/16.
 */
@Controller
public class NotificationController {
    /*
    * my twilio phone number +12015281845
    * */
    public static final String ACCOUNT_SID = "ACe6bdc9851e4220aa989dad7434b8fc12";
    public static final String AUTH_TOKEN = "e507a5707bbff3f26dde1eb946579fa8";
    public static final String TWILIO_NUMBER = "+15005550006";

    @RequestMapping("/greeting")
    public String greeting() {
        sendSMS();
        return "welcome";
    }

    public void sendSMS() {
        try {
            TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

            // Build a filter for the MessageList
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("Body", "Hello, World!"));
            params.add(new BasicNameValuePair("To", "+12015281845")); //Add real number here
            params.add(new BasicNameValuePair("From", TWILIO_NUMBER));

            MessageFactory messageFactory = client.getAccount().getMessageFactory();
            Message message = messageFactory.create(params);
            System.out.println("message sid = " + message.getSid());
        } catch (TwilioRestException e) {
            System.out.println(e.getErrorMessage());
        }
    }
}
