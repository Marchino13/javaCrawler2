package com.xxn.crawler.crawlerUtiles;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.xxn.crawler.pojo.News;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class PdfUtil {
    //将新闻内容转换成pdf， 保存到本地指定路径
    public static void newsTopdf(String title, String time, String content, String path) {
        PdfWriter writer;

        try {
            writer = new PdfWriter(new FileOutputStream(path));
            PdfFont font = PdfFontFactory.createFont("C:\\Windows\\Fonts\\simhei.ttf", PdfEncodings.IDENTITY_H, false);
            //方法中设置中文字体
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);
            Text TITLE = new Text(title)  //新闻标题
                    .setFont(font)
                    .setFontSize(30)//字体大小
                    .setFontColor(ColorConstants.MAGENTA)  //字体颜色品红
                    .setBackgroundColor(ColorConstants.YELLOW);//背景颜色
            //把文本加入到文档中
            document.add(new Paragraph(TITLE));

            Text TIME = new Text(time)//新闻时间
                    .setFont(font)
                    .setFontSize(15)
                    .setFontColor(ColorConstants.GRAY)//灰色
                    .setBackgroundColor(ColorConstants.WHITE);
            document.add(new Paragraph(TIME));

            Text CONTENT = new Text(content)//新闻时间
                    .setFont(font)
                    .setFontSize(15)
                    .setFontColor(ColorConstants.BLACK)
                    .setBackgroundColor(ColorConstants.WHITE);
            document.add(new Paragraph(CONTENT));

            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    //将包含一系列图片类型的list中图片一一取出并且转换成pdf
    public static void imageToPDF(List<String> a, String path){
        PdfWriter writer;
        try {
            writer = new PdfWriter(new FileOutputStream(path));
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            for (int i = 0; !(i >= a.size()); i++) {
                String b = a.get(i);
                Image image = new Image(ImageDataFactory.create(b));
                //图片路径
                document.add(image);
                // 加入图片
            }
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void downloadsImages(ArrayList<String> urls, HttpServletResponse response) {
        try {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=images.pdf");

            PdfWriter writer = new PdfWriter(response.getOutputStream());
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            for (String url : urls) {
                Image image = new Image(ImageDataFactory.create(url));
                document.add(image);
            }

            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void downloadNews(String title, String time, String content, HttpServletResponse response) {
        try {
            // 设置响应头，告诉浏览器这是一个文件下载请求
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=news.pdf");

            PdfWriter writer = new PdfWriter(response.getOutputStream());
            PdfFont font = PdfFontFactory.createFont("C:\\Windows\\Fonts\\simhei.ttf", PdfEncodings.IDENTITY_H, false);
            //方法中设置中文字体
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);
            Text TITLE = new Text(title)  //新闻标题
                    .setFont(font)
                    .setFontSize(30)//字体大小
                    .setFontColor(ColorConstants.MAGENTA)  //字体颜色品红
                    .setBackgroundColor(ColorConstants.YELLOW);//背景颜色
            //把文本加入到文档中
            document.add(new Paragraph(TITLE));

            Text TIME = new Text(time)//新闻时间
                    .setFont(font)
                    .setFontSize(15)
                    .setFontColor(ColorConstants.GRAY)//灰色
                    .setBackgroundColor(ColorConstants.WHITE);
            document.add(new Paragraph(TIME));

            Text CONTENT = new Text(content)//新闻时间
                    .setFont(font)
                    .setFontSize(15)
                    .setFontColor(ColorConstants.BLACK)
                    .setBackgroundColor(ColorConstants.WHITE);
            document.add(new Paragraph(CONTENT));

            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
