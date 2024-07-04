package com.xxn.crawler.crawlerUtiles;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.xxn.crawler.pojo.News;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;



public class PdfUtil {
    public static void newsTopdf(String title,String time,String content,String path){
        PdfWriter writer;

        try{
            writer = new PdfWriter(new FileOutputStream(path));
            PdfFont font = PdfFontFactory.createFont("C:\\Windows\\Fonts\\simhei.ttf", PdfEncodings.IDENTITY_H,false);
            //方法中设置中文字体
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);
            Text TITLE= new Text(title)  //新闻标题
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
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public static void downloadNews(String title,String time,String content, HttpServletResponse response){
        try{
            // 设置响应头，告诉浏览器这是一个文件下载请求
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=news.pdf");

            PdfWriter writer = new PdfWriter(response.getOutputStream());
            PdfFont font = PdfFontFactory.createFont("C:\\Windows\\Fonts\\simhei.ttf", PdfEncodings.IDENTITY_H,false);
            //方法中设置中文字体
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);
            Text TITLE= new Text(title)  //新闻标题
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
        }catch (IOException e){
            e.printStackTrace();
        }
    }





}
