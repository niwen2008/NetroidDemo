package com.mengy.netroiddemo.util;

import com.facebook.common.internal.Supplier;
import com.facebook.imagepipeline.cache.MemoryCacheParams;

/**
 * Created by Mengy on 2016/6/8.
 */
public class SupplierImpl implements Supplier<MemoryCacheParams> {
    private MemoryCacheParams memoryCacheParams;

    public SupplierImpl(MemoryCacheParams memoryCacheParams) {
        this.memoryCacheParams = memoryCacheParams;
    }

    @Override
    public MemoryCacheParams get() {
        return memoryCacheParams;
    }
}
