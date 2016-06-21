package com.mengy.netroiddemo.util;

import android.content.Context;
import android.os.Environment;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.internal.Supplier;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

/**
 * Created by Mengy on 2016/6/8.
 */
public class ImgHelper {
    private MemoryCacheParams memoryCacheParams;
    private static final int MAX_MEMORY = (int) Runtime.getRuntime().maxMemory();
    private static ImagePipelineConfig imagePipelineConfig;

    private static ImagePipelineConfig init(Context context) {
        Supplier<MemoryCacheParams> supplier = new SupplierImpl(new MemoryCacheParams(
                MAX_MEMORY / 4,
                Integer.MAX_VALUE,
                MAX_MEMORY,
                Integer.MAX_VALUE,
                Integer.MAX_VALUE));
        DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder(context)
                .setBaseDirectoryPath(Environment.getExternalStorageDirectory().getAbsoluteFile())
                .setBaseDirectoryName("BEAUTY/imageCaches")
                .setMaxCacheSize(52428800L).setMaxCacheSizeOnLowDiskSpace(31457280L)
                .setMaxCacheSizeOnVeryLowDiskSpace(10485760L)
                .build();
        return ImagePipelineConfig.newBuilder(context)
                .setBitmapMemoryCacheParamsSupplier(supplier)
                .setMainDiskCacheConfig(diskCacheConfig)
                .build();
    }

    public static ImagePipelineConfig getImagePipelineConfig(Context context) {
        if (imagePipelineConfig == null) {
            imagePipelineConfig = init(context);
        }
        return imagePipelineConfig;
    }


}
