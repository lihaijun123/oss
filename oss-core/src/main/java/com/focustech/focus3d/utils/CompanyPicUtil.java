package com.focustech.focus3d.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.focustech.oss2008.utils.ColorUtils;

/**
 * 公司图片生成工具类 *
 *
 * @author lihaijun
 */
public class CompanyPicUtil {
	/** 图片高度 */
	private static final int HEIGHT = 256;
	/** 图片宽度 */
	private static final int WIDTH = 512;

    /**
     * 获取公司图片文件 *
     *
     * @param companyName
     * @param resource
     * @return
     * @throws IOException
     */
	public static File getPicFile(String companyName, String path)
			throws IOException {
        File file = new File(path);
        Font font = new Font("幼圆", Font.BOLD, 40);
        // 创建一个画 布
        BufferedImage bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        // 获取画布的 画笔
        Graphics2D g2 = (Graphics2D) bi.getGraphics();
        // 开始绘图
        // g2.setBackground(Color.WHITE);
        g2.setBackground(ColorUtils.getColorFromHex("#18A2FD"));
        g2.clearRect(0, 0, WIDTH, HEIGHT);
        g2.setPaint(new Color(0, 0, 255));
        g2.setFont(font);
        g2.setColor(ColorUtils.getColorFromHex("#FFFFFF"));
        FontMetrics fm = g2.getFontMetrics(font);
        /*
         * g2.fillRect(0, 0, 100, 10); g2.setPaint(new Color(253, 2, 0)); g2.fillRect(0, 10, 100, 10);
         * g2.setPaint(Color.red);
         */
        FontRenderContext context = g2.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(companyName, context);
        // String[] wrapedStr = wrapText(companyName, WIDTH, fm);
        // liushuyan 修改门楣中的公司名称折行显示
        String[] wrapedStr = splitToColsArray(fm, companyName, WIDTH);
        // double x =
        // wrapedStr.length > 0 ? (WIDTH - fm.stringWidth(wrapedStr[0])) / 2 : (WIDTH - fm
        // .stringWidth(companyName)) / 2;
        double y =
                wrapedStr.length > 0 ? (HEIGHT - (bounds.getHeight() * wrapedStr.length)) / 2 : (HEIGHT - bounds
                        .getHeight()) / 2;
        // 绘制字符串
        for (int i = wrapedStr.length - 1; i >= 0; i--) {
            double x = (WIDTH - fm.stringWidth(wrapedStr[i])) / 2;
            g2.drawString(wrapedStr[i], (int) x, (int) y + 40 * i + fm.getAscent());
        }
        try {
            // 将 生成的图片保存为jpg格式的文件。ImageIO支持jpg、png、gif等格式
            ImageIO.write(bi, "jpg", file);
        }
        catch (IOException e) {
            System.out.println("生成图片出错........");
            e.printStackTrace();
        }
        return file;
	}

    /**
     * 将某个字符串切割成适合显示长度的数组
     *
     * @param font 显示字体
     * @param strSrc 待切割的文字
     * @param width 每行的最大宽度
     * @return 处理后的结果字符串数组
     * @author liushuyan
     */
    public static String[] splitToColsArray(FontMetrics font, String strSrc, int width) {
        int totalWidth = font.stringWidth(strSrc);
        int totalLines = totalWidth / width;
        if (totalWidth % width != 0) {
            totalLines++;
        }
        String lines[] = new String[totalLines];
        int lineIndex = 0;
        int startIndex = 0;
        int length = strSrc.length();
        int lineWidth = 0;
        int charWidth = 0;
        for (int i = 0; i < length; i++) {
            charWidth = (font).charWidth(strSrc.charAt(i));
            if (lineWidth + charWidth > width) {
                lines[lineIndex++] = strSrc.substring(startIndex, i);
                startIndex = i;
                lineWidth = 0;
                if (i == length - 1) {
                    lines[lineIndex] = strSrc.substring(startIndex, i + 1);
                }
            }
            else if (i == length - 1) {
                lines[lineIndex] = strSrc.substring(startIndex, i + 1);
            }
            lineWidth += charWidth;
        }
        return lines;
    }

	public static void main(String[] args) {
		try {
            CompanyPicUtil.getPicFile("the People's republic of China 中华人民共和国", "D:/test.jpg");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    public static void createMM(String comName, String filePath) {
        // 1.读取公司名称信息excel
        // 2.查找文件服务器上的公司门楣
        // 3.生成图片，替换原有的文件路径
        try {
            CompanyPicUtil.getPicFile(comName, filePath);
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

	}
}
