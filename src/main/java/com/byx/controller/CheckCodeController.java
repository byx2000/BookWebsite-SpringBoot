package com.byx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 验证码生成Controller
 */
@Controller
@RequestMapping("/check_code")
public class CheckCodeController extends BaseController {
    @RequestMapping("/generate")
    public void generate(Integer width, Integer height, HttpServletResponse response) {
        try {
            if (width == null) width = 100;
            if (height == null) height = 35;

            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            Graphics g = image.getGraphics();
            g.setColor(Color.PINK);
            g.fillRect(0, 0, width, height);

            g.setColor(Color.BLUE);

            Random rand = new Random();
            String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            StringBuilder answer = new StringBuilder();
            for (int i = 0; i < 4; ++i) {
                int index = rand.nextInt(str.length());
                char ch = str.charAt(index);
                answer.append(ch);
                int x = (width / 5) * (i + 1) + rand.nextInt(10) - 5;
                int y = (height / 5 * 3) + rand.nextInt(10) - 5;
                int fontSize = height / 5 * 3 + rand.nextInt(6) - 3;
                Font font = new Font("Consolas", Font.BOLD, fontSize);
                g.setFont(font);
                g.drawString(ch + "", x, y);
            }

            for (int i = 0; i < 15; ++i) {
                g.setColor(Color.GREEN);
                int x1 = rand.nextInt(width);
                int y1 = rand.nextInt(height);
                int x2 = rand.nextInt(width);
                int y2 = rand.nextInt(height);
                Color color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
                g.setColor(color);
                g.drawLine(x1, y1, x2, y2);
            }

            setCheckCode(answer.toString());
            ImageIO.write(image, "jpg", response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
