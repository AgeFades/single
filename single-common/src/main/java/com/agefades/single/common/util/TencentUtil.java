package com.agefades.single.common.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.agefades.single.common.enums.HttpEnum;
import com.agefades.single.common.util.dto.TxDistrict;
import com.agefades.single.common.util.dto.TxGeoCoder;
import com.agefades.single.common.util.dto.TxLocation;

import java.util.TreeMap;

/**
 * 腾讯地图工具类
 * https://lbs.qq.com/service/webService/webServiceGuide/webServiceOverview
 *
 * @author DuChao
 * @date 2021/1/29 4:42 下午
 */
public class TencentUtil {

    /** 开发者Key */
    private static final String APP_KEY = "*";

    /** 开发者Secret */
    private static final String APP_SECRET = "*";

    /** 腾讯地图接口请求基础地址 */
    private static final String BASE_URL = "https://apis.map.qq.com";

    /** IP定位请求路径 */
    private static final String URI_IP = "/ws/location/v1/ip";

    /** 根据关键词或行政区划代码搜索请求路径 */
    private static final String URI_DISTRICT_SEARCH = "/ws/district/v1/search";

    /** 逆地址解析 */
    private static final String URI_GEO_CODER = "/ws/geocoder/v1/";

    /**
     * 逆地址解析，通过经纬度获取文字地址及相关位置信息
     */
    public static TxGeoCoder getGeoCoder(Double lat, Double lng) {
        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.put("location", lat + "," + lng);
        return BeanUtil.fillBeanWithMap(doGet(URI_GEO_CODER, treeMap), new TxGeoCoder(), true, true);

    }

    /**
     * 腾讯地图通过 ip 获取位置信息
     */
    public static TxLocation getLocationByIp(String ip) {
        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.put("ip", ip);
        return BeanUtil.fillBeanWithMap(doGet(URI_IP, treeMap), new TxLocation(), true, true);
    }

    /**
     * 根据关键词或行政区划代码搜索请求路径
     */
    public static TxDistrict getGpsByCity(String city) {
        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.put("keyword", city);
        return BeanUtil.fillBeanWithMap(doGet(URI_DISTRICT_SEARCH, treeMap), new TxDistrict(), true, true);
    }

    private static JSONObject doGet(String uri, TreeMap<String, Object> treeMap) {
        return JSONUtil.parseObj(HttpUtil.doGet(HttpEnum.TENCENT_MAP, BASE_URL + uri, doSign(uri, treeMap)));
    }

    /**
     * 腾讯地图 Http 请求加签
     */
    private static TreeMap<String, Object> doSign(String uri, TreeMap<String, Object> treeMap) {
        treeMap.put("key", APP_KEY);
        String queryString = URLUtil.buildQuery(treeMap, null);
        treeMap.put("sig", SecureUtil.md5(uri + "?" + queryString + APP_SECRET));
        return treeMap;
    }

}
