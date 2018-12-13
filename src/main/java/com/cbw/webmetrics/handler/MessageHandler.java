package com.cbw.webmetrics.handler;

import com.cbw.webmetrics.config.Config;
import com.cbw.webmetrics.utils.HttpUtil;
import com.cbw.webmetrics.utils.JsonUtil;

import java.net.URLEncoder;
import java.util.logging.Logger;

/**
 * @ClassName: MessageHandler
 * @Description: Verification Code Notification Short Message Interface
 */
public class MessageHandler {

    private static String operation = Config.OPERATION;
    private static String accountSid = Config.ACCOUNT_SID;
    private static String smsContent = Config.MESSAGE_PREFIX;

    /**
     * Verification Code Notification SMS
     */
    public static void sendMessage(String phone, String realContent) {
        for (int i = 0; i < 3; i++) {
            String tmpSmsContent = null;
            try {
                tmpSmsContent = URLEncoder.encode(smsContent, "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
                Logger.getLogger("MessageHandler").warning("send message error, phone is: " + phone + ", realContent is: " + realContent + e);
            }
            String url = Config.BASE_URL + operation;
            String body = "accountSid=" + accountSid + "&to=" + phone + "&smsContent=" + tmpSmsContent + realContent
                    + HttpUtil.createCommonParam();
            // Submit request
            String result = HttpUtil.post(url, body);
            String respCode = JsonUtil.getCodeFromJson(result);
            if (respCode.equals("00000")) break;
        }
    }
}