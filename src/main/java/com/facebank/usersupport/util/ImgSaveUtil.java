package com.facebank.usersupport.util;

import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by yaozun on 2017/12/6.
 */
public class ImgSaveUtil {
    //添加文件
    public static String uploadImg(MultipartFile file, String path) {
        File pathtest = null;
        try {
            pathtest = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(!pathtest.exists()) pathtest = new File("");
        System.out.println("path:"+pathtest.getAbsolutePath());

//如果上传目录为/static/images/upload/，则可以如下获取：
        File upload = new File(pathtest.getAbsolutePath(),path);
        if(!upload.exists()) upload.mkdirs();
        System.out.println("upload url:"+upload.getAbsolutePath());


        String fileName = file.getOriginalFilename();//获取文件名

        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".")+1);
        String uploadFileName = UUID.randomUUID().toString()+"."+fileExtensionName;
        System.out.println("开始上传文件，上传文件的文件名："+fileName+"上传的路径："+path+"新文件名："+uploadFileName);
        File targetFile = new File(upload, uploadFileName);
        try {
            file.transferTo(targetFile);
        } catch (IOException e) {
            System.out.println("上传文件异常"+e);
            return null;
        }
        return targetFile.getName();

    }
    //TODO 添加并删除要删除的文件
}
