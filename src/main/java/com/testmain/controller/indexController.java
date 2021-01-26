package com.testmain.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.util.ArrayList;

@Controller
@EnableAutoConfiguration
public class indexController {
    @RequestMapping("/")
    public String all_video(ModelMap modelMap){
        ArrayList<File> files = new ArrayList<File>();//文件地址列表
        File file = new File("D:\\下载\\人人视频下载");//视频存放位置
        files=func(file,files);
        modelMap.addAttribute("files",files);//插入
        return "index";
    }
    //遍历文件夹所有文件和文件夹下文件
    private ArrayList<File>  func(File file,ArrayList<File> temple){
        String[] allowTypes={".mp4"};
        File[] fs = file.listFiles();
        for(File f:fs){
            if(f.isDirectory())	//若是目录，则递归打印该目录下的文件
                func(f,temple);
            if(f.isFile())
                //若是文件，直接打印
                for (String type : allowTypes) {
                    if (f.getName().indexOf(type) > -1) {
                        temple.add(f);
                    }
                }
        }
        return temple;
    }

}
