package com.hc.distributed.utils;

import com.hc.distributed.exceptions.ErrorException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileExistsException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

@Component
public class HdfsUtil {

    @Value("${hdfs.uriPath}")
    private String uriPath;

    @Value("${hdfs.hadoopHomeDir}")
    private String hadoopHomeDir;

    private FileSystem fileSystem;

    @Autowired
    public void getFileSystem() {
        try {
            isNull(uriPath, "uriPath is null", hadoopHomeDir, "hadoopHomeDir is null");

            //TODO windows系统运行所需要的，在Linux系统中可能会有问题
            System.setProperty("hadoop.home.dir", hadoopHomeDir);
            URI uri = new URI(uriPath);
            Configuration configuration = new Configuration();
            this.fileSystem = FileSystem.get(uri, configuration, "root");
        } catch (URISyntaxException | InterruptedException | IOException e) {
            e.printStackTrace();
            throw new ErrorException(e.getLocalizedMessage());
        }
    }

    // 上传文件
    public void uploadFile(MultipartFile file, String filePathStr, String fileName, Boolean checkExist) throws IOException {
        Path filePath = new Path(filePathStr + File.separator + fileName);
        uploadFile(file, filePath, checkExist);

    }

    public void uploadFile(MultipartFile file, String filePath, Boolean checkExist) throws IOException {
        uploadFile(file, filePath, file.getOriginalFilename(), checkExist);
    }

    public void uploadFile(MultipartFile file, Path filePath, Boolean checkExist) throws IOException {
        if (checkExist) {
            if (fileSystem.exists(filePath)) {
                throw new FileExistsException(filePath.getParent() + File.separator + filePath.getName() + "文件已存在：");
            }
        }
        FSDataOutputStream outputStream = fileSystem.create(filePath, true);
        InputStream inputStream = file.getInputStream();
        IOUtils.copyBytes(inputStream, outputStream, file.getSize(), true);
    }


    private void isNull(Object... arg) {
        for (int i = 0; i < arg.length; i += 2) {
            if (null == arg[i])
                throw new NullPointerException((String) arg[i + 1]);
        }
    }

    public FileStatus getFileStatus(Path filePath) throws IOException {
        return fileSystem.listStatus(filePath)[0];
    }

    public FileStatus getFileStatus(String filePathStr, String fileName) throws IOException {
        Path filePath = new Path(filePathStr + File.separator + fileName);
        return getFileStatus(filePath);
    }

}
