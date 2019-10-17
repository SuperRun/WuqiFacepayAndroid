/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.wuqi.facepay.util;

import com.wuqi.facepay.data.model.AccessToken;
import com.wuqi.facepay.exception.FaceError;

import org.json.JSONException;
import org.json.JSONObject;

public class AccessTokenParser implements Parser<AccessToken> {
    @Override
    public AccessToken parse(String json) throws FaceError {
        try {
            AccessToken accessToken = new AccessToken();
            accessToken.setJson(json);
            JSONObject jsonObject = new JSONObject(json);
            LogUtil.i("ORC", "json token->" + json);

            if (jsonObject != null) {

                accessToken.setAccessToken(jsonObject.optString("access_token"));
                accessToken.setExpiresIn(jsonObject.optInt("expires_in"));
                return accessToken;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            FaceError error = new FaceError(FaceError.ErrorCode.JSON_PARSE_ERROR, "Json parse error", e);
            throw error;
        }
        return null;
    }
}
