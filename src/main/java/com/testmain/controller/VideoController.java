package com.testmain.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

@Controller
@EnableAutoConfiguration
public class VideoController {

    @RequestMapping("/video")
    public String video(ModelMap modelMap, @RequestParam(value = "file") File file){
        modelMap.addAttribute("title",file.getName());
        modelMap.addAttribute("path",file.toString());
        return "video";
    }

    @RequestMapping("/getvideo")
    public void getVideo(@RequestParam(value = "file") String file, HttpServletRequest request, HttpServletResponse response) {
        String[] allowTypes={".mp4"};
        //判断是否是.mp4属性的文件
        for (String type : allowTypes) {
            if (file.indexOf(type) <= -1) {
                return;
            }
        }
        //判断是否来自指定路径，防止错误访问
        if(file.indexOf("D:\\下载\\人人视频下载")<=-1){
            return;
        }
        //播放视频
        try {
            FileInputStream inputStream = new FileInputStream(file);
            byte[] data = new byte[inputStream.available()];
            inputStream.read(data);
            String diskfilename = "final.mp4";
            response.setContentType("video/mp4");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + diskfilename + "\"");
            System.out.println("data.length " + data.length);
            response.setContentLength(data.length);
            response.setHeader("Content-Range", "" + Integer.valueOf(data.length - 1));
            response.setHeader("Accept-Ranges", "bytes");
            response.setHeader("Etag", "W/\"9767057-1323779115364\"");
            OutputStream os = response.getOutputStream();
            os.write(data);
            //先声明的流后关掉！
            os.flush();
            os.close();
            inputStream.close();
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

}
