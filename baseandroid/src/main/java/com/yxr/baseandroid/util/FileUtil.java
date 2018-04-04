package com.yxr.baseandroid.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.request.GetRequest;

/**
 * 文件操作工具包
 *
 * @author liux (http://my.oschina.net/liux)
 * @version 1.0
 * @created 2012-3-21
 */
public class FileUtil {
    /**
     * 写文本文件 在Android系统中，文件保存在 /data/data/PACKAGE_NAME/files 目录下
     *
     * @param context
     */
    public static void write(Context context, String fileName, String content) {
        if (content == null)
            content = "";

        try {
            FileOutputStream fos = context.openFileOutput(fileName,
                    Context.MODE_PRIVATE);
            fos.write(content.getBytes());

            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取文本文件
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String read(Context context, String fileName) {
        try {
            FileInputStream in = context.openFileInput(fileName);
            return readInStream(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String readInStream(InputStream inStream) {
        try {
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[512];
            int length = -1;
            while ((length = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, length);
            }

            outStream.close();
            inStream.close();
            return outStream.toString();
        } catch (IOException e) {
            Log.i("FileTest", e.getMessage());
        }
        return null;
    }

    public static File createFile(String folderPath, String fileName) {
        File destDir = new File(folderPath);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
        return new File(folderPath, fileName + fileName);
    }

    /**
     * 根据文件绝对路径获取文件名
     *
     * @param filePath
     * @return
     */
    public static String getFileName(String filePath) {
        if (TextUtils.isEmpty(filePath))
            return "";
        return filePath.substring(filePath.lastIndexOf("/") + 1);
    }

    /**
     * 根据文件的绝对路径获取文件名但不包含扩展名
     *
     * @param filePath
     * @return
     */
    public static String getFileNameNoFormat(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return "";
        }
        int point = filePath.lastIndexOf('.');
        return filePath.substring(filePath.lastIndexOf("/") + 1,
                point);
    }

    /**
     * 获取文件扩展名
     *
     * @param fileName
     * @return
     */
    public static String getFileFormat(String fileName) {
        if (TextUtils.isEmpty(fileName))
            return "";

        int point = fileName.lastIndexOf('.');
        return fileName.substring(point + 1);
    }

    /**
     * 转换文件大小
     *
     * @return B/KB/MB/GB
     */
    public static String formatFileSize(Context context, long fileSize) {
        return Formatter.formatFileSize(context, fileSize);
    }

    public static long getFileSize(String path) {
        long startSize = 0;
        getFileSize(path, startSize);
        return startSize;
    }

    /**
     * 获取文件指定文件的指定单位的大小
     *
     * @param filePath 文件路径
     */
    private static void getFileSize(String filePath, long startSize) {
        File file = new File(filePath);
        try {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                if (files != null && files.length > 0) {
                    for (int i = 0; i < files.length; i++) {
                        getFileSize(files[i].getPath(), startSize);
                    }
                }
            } else {
                startSize = startSize + getFileSize(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取指定文件大小
     *
     * @param file
     * @return
     * @throws Exception
     */
    private static long getFileSize(File file) {
        long size = 0;
        if (file.exists()) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                size = fis.available();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return size;
    }


    /**
     * 检查文件是否存在
     *
     * @return
     */
    public static boolean checkFileExists(String path) {
        boolean status;
        if (!TextUtils.isEmpty(path)) {
            File newPath = new File(path);
            status = newPath.exists();
        } else {
            status = false;
        }
        return status;
    }

    /**
     * 计算SD卡的剩余空间
     *
     * @return 返回-1，说明没有安装sd卡
     */
    public static long getFreeDiskSpace() {
        long freeSpace = 0;
        if (checkExternalSDExists()) {
            try {
                File path = Environment.getExternalStorageDirectory();
                StatFs stat = new StatFs(path.getPath());
                long blockSize = stat.getBlockSize();
                long availableBlocks = stat.getAvailableBlocks();
                freeSpace = availableBlocks * blockSize / 1024;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return -1;
        }
        return (freeSpace);
    }

    /**
     * 检查是否安装SD卡
     *
     * @return
     */
    public static boolean checkExternalSDExists() {
        String sDCardStatus = Environment.getExternalStorageState();
        boolean status;
        if (Environment.MEDIA_MOUNTED.equals(sDCardStatus)) {
            status = true;
        } else
            status = false;
        return status;
    }

    /**
     * 删除目录(包括：目录里的所有文件)
     *
     * @param path
     * @return
     */
    public static void deleteDirectory(String path) {
        if (TextUtils.isEmpty(path)) {
            File file = new File(path);
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                if (files != null && files.length > 0) {
                    for (int i = 0; i < files.length; i++) {
                        deleteDirectory(files[i].getPath());
                    }
                }
            } else if (file.exists()) {
                file.delete();
            }
        }
    }


    /**
     * 获取SD卡的根目录
     *
     * @return
     */
    public static String getSDRoot() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    /**
     * @return ：获取系统相册路径
     */
    public static String getPhotoRoot() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath();
    }

    public static void simpleDownloadFile(String url, FileCallback callback) {
        GetRequest<File> fileGetRequest = OkGo.get(url);
        fileGetRequest.execute(callback);
    }
}