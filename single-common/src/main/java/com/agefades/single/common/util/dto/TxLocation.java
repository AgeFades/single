package com.agefades.single.common.util.dto;

import lombok.Data;

/**
 * 腾讯地图通过ip获取到的位置响应
 *
 * @author DuChao
 * @date 2021/1/29 5:20 下午
 */
@Data
public class TxLocation {

    /**
     * status : 0
     * message : query ok
     * result : {"ip":"119.57.47.18","location":{"lat":39.9219,"lng":116.44355},"ad_info":{"nation":"中国","province":"北京市","city":"北京市","district":"朝阳区","adcode":110105}}
     */

    private Integer status;
    private String message;
    private ResultDTO result;

    @Data
    public static class ResultDTO {
        /**
         * ip : 119.57.47.18
         * location : {"lat":39.9219,"lng":116.44355}
         * ad_info : {"nation":"中国","province":"北京市","city":"北京市","district":"朝阳区","adcode":110105}
         */

        private String ip;
        private LocationDTO location;
        private AdInfoDTO adInfo;

        @Data
        public static class LocationDTO {
            /**
             * lat : 39.9219
             * lng : 116.44355
             */

            private Double lat;
            private Double lng;
        }

        @Data
        public static class AdInfoDTO {
            /**
             * nation : 中国
             * province : 北京市
             * city : 北京市
             * district : 朝阳区
             * adcode : 110105
             */

            private String nation;
            private String province;
            private String city;
            private String district;
            private Integer adcode;
        }
    }
}
