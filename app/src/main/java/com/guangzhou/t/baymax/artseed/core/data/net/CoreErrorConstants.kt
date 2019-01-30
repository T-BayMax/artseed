package com.bjike.t.baymax.artseed.core.data.net;

object CoreErrorConstants {
    val errors: MutableMap<Int, String> = HashMap()

    init {
        errors[0] = "未找到相关数据"
        errors[102] = "Unknown  parameter"
        errors[401] = "唯一键不能存在重复的值"
        errors[402] = "查询的wher语句长度大于具体多少个字节"
        errors[403] = "登录失效,请重新登录"
        errors[500] = "（服务器内部错误） 服务器遇到错误，无法完成请求."
        errors[501] = "（错误网关） 服务器作为网关或代理，从上游服务器收到无效响应."
        errors[502] = "（网关超时） 服务器作为网关或代理，但是没有及时从上游服务器收到请求."
        errors[503] = "（服务不可用） 服务器目前无法使用（由于超载或停机维护）。 通常，这只是暂时状态."
        errors[504] = "（网关超时） 服务器作为网关或代理，但是没有及时从上游服务器收到请求."
        errors[505] = "（HTTP 版本不受支持） 服务器不支持请求中所用的 HTTP 协议版本."
    }
}