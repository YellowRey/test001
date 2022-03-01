package com.leyou.upload.service;

import org.springframework.web.multipart.MultipartFile; /**
 * 上传文件业务层接口
 */

public interface UploadService {

    /**
     * 图片上传
     * @param file 文件流
     * @return
     */
    String upload(MultipartFile file);
}
