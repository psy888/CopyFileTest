package com.psy888;

import sun.reflect.misc.FieldUtil;

import java.io.*;
import java.nio.channels.FileChannel;

public class FS {
    private static final String COPY_PREFIX = "_copy";

    private static final int BUF_SIZE = 1024;



    //NIO
    public boolean copyFileNIO(String path){
        File src = new File(path);
        if (src.exists()) {
            File dst = new File(getDstFileName(src));
            //если файла нет создаем
            if(!dst.exists()) {
                try {
                    dst.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //копируем
            try( FileChannel srcFC = new FileInputStream(src).getChannel();
                 FileChannel dstFC = new FileOutputStream(dst).getChannel()) {

                dstFC.transferFrom(srcFC,0,srcFC.size());

                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    //IO
    public boolean copyFileIO(String path) {
        File src = new File(path);
        if (src.exists()) {
            byte[] buf = new byte[BUF_SIZE];
            try (FileInputStream fis = new FileInputStream(src);
                 FileOutputStream fos = new FileOutputStream(new File(getDstFileName(src)))) {
                int count;
                while ((count = fis.read(buf, 0, buf.length)) != -1){
                    fos.write(buf, 0, count);
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    private String getDstFileName(File srcFile){
        String srcFileName = srcFile.getName();
        String dstFileName = srcFileName.substring(0,srcFileName.lastIndexOf("."));
        String extension = srcFileName.substring(srcFileName.lastIndexOf("."));
        return dstFileName+COPY_PREFIX+extension;
    }
}
