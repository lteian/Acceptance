package com.yanshou.lteian.acceptance.customlibray;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class CustomImage {
    /**
     * Try to return the absolute file path from the given Uri
     * @param context   知道图片路径 Uri 转换为 String 路径            TODO
     * @param uri
     * @return the file path or null
     *
     *Uri获取String类型的绝对路径
     *String path = Uri.getPath();
     */
    public static String getRealFilePath(final Context context, final Uri uri ) {
        if ( null == uri ) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if ( scheme == null )
            data = uri.getPath();
        else if ( ContentResolver.SCHEME_FILE.equals( scheme ) ) {
            data = uri.getPath();
        } else if ( ContentResolver.SCHEME_CONTENT.equals( scheme ) ) {
            Cursor cursor = null;
            String[] projection = {MediaStore.Images.Media.DATA};
            try{
                String authority = uri.getAuthority();
                if(authority.equalsIgnoreCase("media")){
                    // 系统媒体库选的的图片
                    cursor = context.getContentResolver().query( uri, projection, null, null, null );
                }else{
                    //相机拍摄的图片，以FileProvider方式存储
                    return getFPUriToPath(context,uri);
                }

                if ( null != cursor ) {
                    if (cursor.moveToFirst()) {
                        int index = cursor.getColumnIndexOrThrow(projection[0]);
                        data = cursor.getString(index);
                    }
                }
            }catch(Exception e){
                if(cursor != null){
                    cursor.close();
                }
            }
        }
        return data;
    }

    /**
     * 获取FileProvider path
     */
    private static String getFPUriToPath(Context context, Uri uri){
        try{
            List<PackageInfo> packs = context.getPackageManager().getInstalledPackages(PackageManager.GET_PROVIDERS);
            if(packs != null){
                String fileProviderClassName = FileProvider.class.getName();
                for(PackageInfo pack : packs){
                    ProviderInfo[] providers = pack.providers;
                    if(providers != null){
                        for(ProviderInfo provider : providers){
                            if(uri.getAuthority().equals(provider.authority)) {
                                if(provider.name.equalsIgnoreCase(fileProviderClassName)) {
                                    Class<FileProvider> fileProviderClass = FileProvider.class;
                                    try{
                                        Method getPathStrategy = fileProviderClass.getDeclaredMethod("getPathStrategy", Context.class, String.class);
                                        getPathStrategy.setAccessible(true);
                                        Object invoke = getPathStrategy.invoke(null, context, uri.getAuthority());
                                        if(invoke != null){
                                            String PathStrategyStringClass = FileProvider.class.getName() + "$PathStrategy";
                                            Class<?> PathStrategy = Class.forName(PathStrategyStringClass);
                                            Method getFileForUri = PathStrategy.getDeclaredMethod("getFileForUri", Uri.class);
                                            getFileForUri.setAccessible(true);
                                            Object invoke1 = getFileForUri.invoke(invoke, uri);
                                            if(invoke1 instanceof File){
                                                String filePath = ((File) invoke1).getAbsolutePath();
                                                return filePath;
                                            }
                                        }
                                    }catch (NoSuchMethodException e){
                                        e.printStackTrace();
                                    }catch (InvocationTargetException e){
                                        e.printStackTrace();
                                    }catch (IllegalAccessException e){
                                        e.printStackTrace();
                                    }catch (ClassNotFoundException e){
                                        e.printStackTrace();
                                    }
                                    break;
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 图片按比例大小压缩方法
     *
     * @param srcPath （根据路径获取图片并压缩）
     * @return
     */
    public static Bitmap getimage(String srcPath) {

        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 100f;// 这里设置高度为800f
        float ww = 60f;// 这里设置宽度为480f
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;// 设置缩放比例
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
//        return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩
        return bitmap;
    }

    /**
     * 质量压缩方法
     *
     * @param image
     * @return
     */
    public static Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 90;

        while (baos.toByteArray().length / 1024 > 100) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset(); // 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        return bitmap;
    }
}
