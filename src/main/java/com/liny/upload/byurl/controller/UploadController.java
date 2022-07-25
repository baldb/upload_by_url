package com.liny.upload.byurl.controller;

import com.liny.upload.byurl.pojo.ImgParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

/**
 * @author linyi
 * @date 2022/7/25
 * 1.0
 */
@RestController
@Slf4j
public class UploadController {



    @PostMapping("/upload/url")
    public void test(@RequestBody ImgParam imgParam) throws Exception{
        //https://image-c.weimobwmc.com/openruntime/514279f3784144f3b40ae9704ef29a74.jpg

        String imgUrl = imgParam.getImgUrl();
        // 构造URL
        URL url = new URL(imgUrl);
        // 打开连接
        URLConnection con = url.openConnection();
        // 输入流
        InputStream is = con.getInputStream();
        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 输出的文件流
        String PATH = "/Users/linyi/Desktop/picture/";
        //获取文件后缀名：
        String ext = imgUrl.substring(imgUrl.lastIndexOf(".") + 1).toLowerCase();
        log.info("文件后缀名：{}",ext);
        UUID uuid = UUID.randomUUID();
        String filename = PATH + uuid + ext;  //下载路径及下载图片名称
        log.info("文件名：{}",uuid);
        File file = new File(filename);
        FileOutputStream os = new FileOutputStream(file, true);
        // 开始读取
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        // 完毕，关闭所有链接
        os.close();
        is.close();
        //return null;
    }
}
