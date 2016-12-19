package com.focustech.oss2008.utils;

import org.springframework.web.context.ContextLoader;

import com.focustech.common.utils.StringUtils;
import com.focustech.oss2008.service.EnvironmentParameter;
import com.focustech.utils.encrypt.EncryptHandler;

/**
 * 類 Encode 的兩個方法分別對字符串進行加密與解密。
 */
public class Encode {
    // 加密Handler實例
    private static EncryptHandler EncryptHandler = null;
    private static EncryptHandler EncryptHandler4Image = null;
    private static Object EncryptHandlerLock = new Object();

    /**
     * 方法 encoder 可把明文字符串編碼成 base 64 字符串。
     *
     * @param plain 待加密的明文字符串
     * @return 如果明文的字符數超過76位則此方法返回空(null)(參見 RFC2045)。
     * @return 返回加密後的字符串（或者當字符數超過76位時返回 null ）。
     */
    public static String encoder(String plain) {
        if (plain.equals(""))
            return "";
        if (plain.length() > 76)
            return "";
        StringBuffer sb = new StringBuffer();
        // the encode buffer
        byte[] enc = new byte[3];
        boolean end = false;
        for (int i = 0, j = 0; !end; i++) {
            if (i == plain.length() - 1)
                end = true;
            enc[j++] = (byte) plain.charAt(i);
            if (j == 3 || end) {
                int res;
                // this is a bit inefficient at the end point
                // worth it for the small decrease in code size?
                res = (enc[0] << 16) + (enc[1] << 8) + enc[2];
                int b;
                int lowestbit = 18 - (j * 6);
                for (int toshift = 18; toshift >= lowestbit; toshift -= 6) {
                    b = res >>> toshift;
                    b &= 63;
                    if (b >= 0 && b < 26)
                        sb.append((char) (b + 65));
                    if (b >= 26 && b < 52)
                        sb.append((char) (b + 71));
                    if (b >= 52 && b < 62)
                        sb.append((char) (b - 4));
                    if (b == 62)
                        sb.append('+');
                    if (b == 63)
                        sb.append('/');
                    if (sb.length() % 76 == 0)
                        sb.append('\n');
                }
                // now set the end chars to be pad character if there
                // was less than integral input (ie: less than 24 bits)
                if (end) {
                    if (j == 1)
                        sb.append("==");
                    if (j == 2)
                        sb.append('=');
                }
                enc[0] = 0;
                enc[1] = 0;
                enc[2] = 0;
                j = 0;
            }
        }
        String sbTemp = sb.toString();
        // 去掉“=”號
        if (sbTemp.indexOf("=") != -1)
            sbTemp = sbTemp.substring(0, sbTemp.indexOf("="));
        // 把第一個字符放到最後
        sbTemp = sbTemp.substring(1) + sbTemp.substring(0, 1);
        return sbTemp;
    }

    /**
     * 方法 decoder 可把加密後的文字符串進行解碼處理成明文。
     *
     * @param encoded 待解密的加密字符串
     * @return 解密後的字符串
     */
    public static String decoder(String encoded) {
        if (encoded.equals(""))
            return "";
        encoded = encoded.substring(encoded.length() - 1) + encoded.substring(0, encoded.length() - 1);
        while (encoded.length() % 4 != 0) {
            encoded += "=";
        }
        StringBuffer sb = new StringBuffer();
        int maxturns;
        // work out how long to loop for.
        if (encoded.length() % 3 == 0)
            maxturns = encoded.length();
        else
            maxturns = encoded.length() + (3 - (encoded.length() % 3));
        // tells us whether to include the char in the unencode
        boolean skip;
        // the unencode buffer
        byte[] unenc = new byte[4];
        byte b;
        for (int i = 0, j = 0; i < maxturns; i++) {
            skip = false;
            // get the byte to convert or 0
            if (i < encoded.length())
                b = (byte) encoded.charAt(i);
            else
                b = 0;
            // test and convert first capital letters, lowercase, digits then '+' and '/'
            if (b >= 65 && b < 91)
                unenc[j] = (byte) (b - 65);
            else if (b >= 97 && b < 123)
                unenc[j] = (byte) (b - 71);
            else if (b >= 48 && b < 58)
                unenc[j] = (byte) (b + 4);
            else if (b == '+')
                unenc[j] = 62;
            else if (b == '/')
                unenc[j] = 63;
            // if we find "=" then data has finished, we're not really dealing with this now
            else if (b == '=')
                unenc[j] = 0;
            else {
                char c = (char) b;
                if (c == '\n' || c == '\r' || c == ' ' || c == '\t')
                    skip = true;
                else
                    // could throw an exception here? it's input we don't understand.
                    ;
            }
            // once the array has boiled convert the bytes back into chars
            if (!skip && ++j == 4) {
                // shift the 6 bit bytes into a single 4 octet word
                int res = (unenc[0] << 18) + (unenc[1] << 12) + (unenc[2] << 6) + unenc[3];
                byte c;
                int k = 16;
                // shift each octet down to read it as char and add to StringBuffer
                while (k >= 0) {
                    c = (byte) (res >> k);
                    if (c > 0)
                        sb.append((char) c);
                    k -= 8;
                }
                // reset j and the unencode buffer
                j = 0;
                unenc[0] = 0;
                unenc[1] = 0;
                unenc[2] = 0;
                unenc[3] = 0;
            }
        }
        return sb.toString();
    }

    /**
     * 對長整型的數值進行加密，加密數值的長度不能超過9位，與MIC一致 返回為統一長度字符串
     *
     * @param string_id 欲加密字符串
     * @return
     */
    public static String encode(String string_id) {
        String s = "";
        try {
            if (string_id != null && !"".equals(string_id)) {
                if (EncryptHandler == null) {
                    synchronized (EncryptHandlerLock) {
                        if (EncryptHandler == null) {
                            InitEncryptHandler();
                        }
                    }
                }
                s = EncryptHandler.encode(Long.parseLong(string_id));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * 對EncryptHandler進行初始化
     */
    @SuppressWarnings("unchecked")
    private static void InitEncryptHandler() throws Exception {
        EnvironmentParameter envParameter =
                (EnvironmentParameter) ContextLoader.getCurrentWebApplicationContext().getBean(
                        "environmentParameterImpl", EnvironmentParameter.class);
        Class clsHandler = Class.forName(envParameter.getStringValue("EncryptHandler"));
        EncryptHandler = (EncryptHandler) clsHandler.newInstance();
        EncryptHandler4Image = (EncryptHandler) clsHandler.newInstance();
        //
        String str1 = envParameter.getStringValue("UnEncryptMaxLen");
        String str2 = envParameter.getStringValue("EncryptLen");
        EncryptHandler.setPlainTextAndCipherTextLength(Integer.parseInt(str1), Integer.parseInt(str2));
        EncryptHandler4Image.setPlainTextAndCipherTextLength(Integer.parseInt(str1), Integer.parseInt(str2));
        //
        str1 = envParameter.getStringValue("EncryptPosMap");
        EncryptHandler.setPositionMangleTable(StringUtils.explodetoArray(str1, ","));
        //
        str1 = envParameter.getStringValue("EncryptWheelMap");
        EncryptHandler.setEncryptionTable(str1);
        // ///////////////////////////////////////////////////
        // 初始化圖片ID加密處理器
        str2 = envParameter.getStringValue("EncryptPosMap4Image");
        EncryptHandler4Image.setPositionMangleTable(StringUtils.explodetoArray(str2, ","));
        //
        str2 = envParameter.getStringValue("EncryptWheelMap4Image");
        EncryptHandler4Image.setEncryptionTable(str2);
    }
}
