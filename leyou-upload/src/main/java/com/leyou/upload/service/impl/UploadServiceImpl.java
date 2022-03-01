package com.leyou.upload.service.impl;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.leyou.upload.service.UploadService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 业务层接口实现类
 */
@Service
public class UploadServiceImpl implements UploadService {

    //使用fastDFS客户端上传
    @Autowired
    private FastFileStorageClient storageClient;


    //定义一个标准格式(使用常量)
    private static final List<String> CONTENT_TYPES = Arrays.asList("image/jpeg", "image/gif");
    //日志创建方式
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadServiceImpl.class);

    @Override
    public String upload(MultipartFile file) {
        //验证文件格式类型
            //1.将file转为字符串
        String originalFilename = file.getOriginalFilename();
            //2.获取文件类型
        String contentType = file.getContentType();
        if (!CONTENT_TYPES.contains(contentType)){
            // 文件类型不合法，直接返回null
            LOGGER.info("文件类型不合法：{}", originalFilename);
            return null;
        }

        //验证文件的内容
        try {
            // 校验文件的内容
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if (bufferedImage == null){
                LOGGER.info("文件内容不合法：{}", originalFilename);
                return null;
            }

            // 保存到服务器
          //  file.transferTo(new File("C:\\images\\" + originalFilename));
            //获取文件的类型
            String ext = StringUtils.substringAfterLast(originalFilename, ".");
            //上传文件（文件字节流，文件长度，文件后缀，null）
            StorePath storePath = this.storageClient.uploadFile(file.getInputStream(), file.getSize(), ext, null);
            // 生成url地址，返回
            return "http://image.leyou.com/" + storePath.getFullPath();
        } catch (IOException e) {
            LOGGER.info("服务器内部错误：{}", originalFilename);
            e.printStackTrace();
        }
        return null;
    }
}
