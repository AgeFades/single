package com.agefades.single.common.util.dto;

import lombok.Data;

import java.util.List;

@Data
public class TxDistrict {

    /**
     * status : 0
     * message : query ok
     * data_version : 20200814
     * result : [[{"id":"110000","name":"北京","fullname":"北京市","pinyin":["bei","jing"],"level":1,"location":{"lat":39.90469,"lng":116.40717},"address":"北京"},{"id":"230225580","fullname":"北京市双河农场","level":4,"location":{"lat":47.866631,"lng":123.753351},"address":"黑龙江,齐齐哈尔,甘南县,北京市双河农场"}]]
     */

    private Integer status;
    private String message;
    private String dataVersion;
    private List<List<ResultDTO>> result;

    @Data
    public static class ResultDTO {
        /**
         * id : 110000
         * name : 北京
         * fullname : 北京市
         * pinyin : ["bei","jing"]
         * level : 1
         * location : {"lat":39.90469,"lng":116.40717}
         * address : 北京
         */

        private String id;
        private String name;
        private String fullname;
        private Integer level;
        private LocationDTO location;
        private String address;
        private List<String> pinyin;

        @Data
        public static class LocationDTO {
            /**
             * lat : 39.90469
             * lng : 116.40717
             */

            private Double lat;
            private Double lng;
        }
    }
}
