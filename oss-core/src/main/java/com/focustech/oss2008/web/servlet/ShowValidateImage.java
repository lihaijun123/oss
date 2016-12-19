package com.focustech.oss2008.web.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 提供增強版的驗證碼圖片生成程序 Create Time: 2005-11-23 19:56:09
 *
 * @author JimCheng/成俊杰
 */
public class ShowValidateImage extends HttpServlet {
    private final static String[] VALIDATE_CHAR_LIB =
            {"A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "R", "S", "T", "U", "V", "W", "X",
                    "Y", "Z", "2", "3", "4", "5", "6", "7", "8", "9"};
    // private final static String[] VALIDATE_CHAR_LIB=
    // {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
    // "w",
    // "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private static int width = 100;
    private static int height = 40;
    private static int numDigital = 4;
    private static int numLine = 50;
    public static String[] fontTypes = { // "Courier New",
            "arial",// "Times New Roman" // "Viner Hand ITC",
            // "Papyrus",
            // "Courier New",
            // "Viner Hand ITC",
            // "Papyrus",
            // "Courier New",
            // "Viner Hand ITC",
            // "Papyrus"
            };
    // public static int[] fontStyles= { Font.BOLD, Font.BOLD, Font.BOLD, Font.BOLD,Font.PLAIN, Font.ITALIC, Font.PLAIN,
    // Font.ITALIC, Font.PLAIN };
    public static int[] fontStyles =
            {Font.PLAIN, Font.ITALIC, Font.BOLD, Font.BOLD, Font.BOLD, Font.BOLD, Font.ITALIC, Font.BOLD, Font.PLAIN,
                    Font.ITALIC, Font.PLAIN, Font.BOLD};

    //
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("image/jpeg");
        res.setHeader("Pragma", "No-cache");
        // res.setHeader("Cache-Control", "no-cache");
        res.setDateHeader("Expires", 0);
        // 生成隨機類
        Random random = new Random(System.currentTimeMillis());
        // 隨機生成圖片的長寬
        int curWidth = width + random.nextInt(5);
        int curHeight = height + random.nextInt(5);
        HttpSession session = req.getSession();
        // 在內存中創建圖象
        BufferedImage image = new BufferedImage(curWidth, curHeight, BufferedImage.TYPE_INT_RGB);
        // 獲取圖形上下文
        Graphics g = image.getGraphics();
        // 設定背景色
        g.setColor(getRandColor(random, 200, 250));
        g.fillRect(0, 0, curWidth, curHeight);
        // 畫邊框
        // g.setColor(new Color());
        // g.drawRect(0,0,width-1,height-1);
        // 隨機產生300條干擾線，使圖象中的認證碼不易被其它程序探測到
        for (int i = 0; i < numLine; i++) {
            Color c1 = getRandColor(random, 160, 200);
            g.setXORMode(c1);
            // g.setColor(c1);
            int x = random.nextInt(curWidth);
            int y = random.nextInt(curHeight);
            int xl = random.nextInt(curWidth / 4);
            int yl = random.nextInt(curHeight / 4);
            g.drawLine(x, y, x + xl, y + yl);
        }
        // 根據當前圖片大小，隨機生成每個數字位置
        int[][] posDigital = new int[numDigital][2];
        for (int i = 0; i < numDigital; i++) {
            if ((i % 2) == 1)
                posDigital[i][0] = posDigital[i - 1][0] + 25 - 2;
            // i * curWidth / numDigital + Math.abs(random.nextInt(10));
            else {
                if (i == 0)
                    posDigital[i][0] = i * curWidth / numDigital + Math.abs(random.nextInt(4));
                else
                    posDigital[i][0] = posDigital[i - 1][0] + 25;
            }
            posDigital[i][1] = 25 + Math.abs(random.nextInt(curHeight - 30));
        }
        // 取隨機產生的認證碼
        String sRand = "";
        for (int i = 0; i < numDigital; i++) {
            String rand = VALIDATE_CHAR_LIB[random.nextInt(VALIDATE_CHAR_LIB.length)];
            sRand += rand;
            // 設定字體
            g.setFont(new Font(fontTypes[random.nextInt(fontTypes.length)], fontStyles[random
                    .nextInt(fontStyles.length)], 28));
            // 將認證碼顯示到圖象中
            // Color c1= new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110));
            Color c1 = getRandColor(random, 20, 130);
            // if (i % 2 == 1)
            // g.setColor(c1);
            // else
            g.setXORMode(c1);
            // 調用函數出來的顏色相同，可能是因為種子太接近，所以只能直接生成
            // g.drawString(rand, 13 * i + 6, 16);
            g.drawString(rand, posDigital[i][0], posDigital[i][1]);
        }
        // 隨機產生300條干擾線，使圖象中的認證碼不易被其它程序探測到
        for (int i = 0; i < numLine; i++) {
            Color c1 = getRandColor(random, 160, 200);
            g.setXORMode(c1);
            // g.setColor(c1);
            int x = random.nextInt(curWidth);
            int y = random.nextInt(curHeight);
            int xl = random.nextInt(curWidth / 3);
            int yl = random.nextInt(curHeight / 3);
            g.drawLine(x, y, x + xl, y + yl);
        }
        // 畫中間的橫線
        int totalLineNum = 3 + random.nextInt(2);
        for (int i = 0; i < totalLineNum; i++) {
            Color c1 = new Color(0, 0, 0);
            g.setXORMode(c1);
            int x = i * (curWidth / totalLineNum) + random.nextInt(5);
            int y = 15 + random.nextInt(20);
            int xl = x + (curWidth / totalLineNum) + random.nextInt(5);
            int yl = 15 + random.nextInt(20);
            g.drawLine(x, y, xl, yl);
        }
        // 將認證碼存入SESSION
        // session.setAttribute("post_validate_code", sRand);
        session.setAttribute("strRandom", sRand);
        // 圖象生效
        g.dispose();
        // 扭曲圖片
        // long begin= System.currentTimeMillis();
        // try
        // {
        // //image to byte[]
        // ByteArrayOutputStream baout= new ByteArrayOutputStream();
        // JPEGImageEncoder encoder= JPEGCodec.createJPEGEncoder(baout);
        // encoder.encode(image);
        // byte[] data= baout.toByteArray();
        // //swirl image
        // ImageInfo iInfo= new ImageInfo();
        // MagickImage mImage= new MagickImage(iInfo, data);
        // mImage= mImage.swirlImage(30);
        // data= mImage.imageToBlob(iInfo);
        // //byte[] to image
        // InputStream in= new ByteArrayInputStream(data);
        // image= ImageIO.read(in);
        // }
        // catch (Exception e)
        // {
        // e.printStackTrace();
        // }
        // System.out.println(System.currentTimeMillis() - begin);
        // 輸出圖象到頁面
        // ImageIO.write(image, "JPEG", res.getOutputStream());:此語句效率太低，廢棄之
        ServletOutputStream out1 = res.getOutputStream();
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out1);
        encoder.encode(image);
        out1.close();
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }

    // 給定範圍獲得隨機顏色
    private Color getRandColor(Random myRandom, int fc, int bc) {
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + myRandom.nextInt(bc - fc);
        int g = fc + myRandom.nextInt(bc - fc);
        int b = fc + myRandom.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
