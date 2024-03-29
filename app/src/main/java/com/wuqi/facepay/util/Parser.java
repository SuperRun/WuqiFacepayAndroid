/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.wuqi.facepay.util;


import com.wuqi.facepay.exception.FaceError;

/**
 * JSON解析
 * @param <T>
 */
public interface Parser<T> {
    T parse(String json) throws FaceError;
}
