/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.wuqi.facepay.util;

import com.wuqi.facepay.exception.FaceError;

public interface OnResultListener<T> {
    void onResult(T result);

    void onError(FaceError error);
}
