package com.guangzhou.t.baymax.artseed.networkapi;

import com.guangzhou.t.baymax.artseed.bean.UserBean;
import com.guangzhou.t.baymax.artseed.core.data.entity.CoreDataResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


/**
 * 请求网络接口
 * Created by T-BayMax on 2016/11/22.
 */

public interface UserNetworkApi {
    @FormUrlEncoded
    @POST("login")
    Observable<CoreDataResponse<UserBean>> getUsersHot(@FieldMap Map<String, String> fieldMap);

}
