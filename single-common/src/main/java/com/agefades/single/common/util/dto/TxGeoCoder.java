package com.agefades.single.common.util.dto;

import lombok.Data;

@Data
public class TxGeoCoder {

    /**
     * status : 0
     * message : query ok
     * request_id : 2244ae2a-a24f-11eb-b076-52540079c2d4
     * result : {"location":{"lat":39.915119,"lng":116.403963},"address":"北京市东城区东华门大街62号","formatted_addresses":{"recommend":"东华门小区西北(东华门大街)","rough":"东华门小区西北(东华门大街)"},"address_component":{"nation":"中国","province":"北京市","city":"北京市","district":"东城区","street":"东华门大街","street_number":"东华门大街62号"},"ad_info":{"nation_code":"156","adcode":"110101","city_code":"156110000","name":"中国,北京市,北京市,东城区","location":{"lat":39.916668,"lng":116.434578},"nation":"中国","province":"北京市","city":"北京市","district":"东城区"},"address_reference":{"crossroad":{"id":"659076","title":"东华门大街/智德前巷(路口)","location":{"lat":39.915169,"lng":116.405052},"_distance":87.8,"_dir_desc":"西"},"town":{"id":"110101001","title":"东华门街道","location":{"lat":39.923271,"lng":116.396828},"_distance":0,"_dir_desc":"内"},"street_number":{"id":"5956636539586275260","title":"东华门大街62号","location":{"lat":39.915131,"lng":116.4039},"_distance":5.4,"_dir_desc":""},"street":{"id":"4382178191545952722","title":"东华门大街","location":{"lat":39.915161,"lng":116.404167},"_distance":0,"_dir_desc":""},"landmark_l2":{"id":"3504460654985366161","title":"东华门小区","location":{"lat":39.914692,"lng":116.404831},"_distance":50.8,"_dir_desc":"西北"}}}
     */

    private Integer status;
    private String message;
    private String requestId;
    private ResultDTO result;

    @Data
    public static class ResultDTO {
        /**
         * location : {"lat":39.915119,"lng":116.403963}
         * address : 北京市东城区东华门大街62号
         * formatted_addresses : {"recommend":"东华门小区西北(东华门大街)","rough":"东华门小区西北(东华门大街)"}
         * address_component : {"nation":"中国","province":"北京市","city":"北京市","district":"东城区","street":"东华门大街","street_number":"东华门大街62号"}
         * ad_info : {"nation_code":"156","adcode":"110101","city_code":"156110000","name":"中国,北京市,北京市,东城区","location":{"lat":39.916668,"lng":116.434578},"nation":"中国","province":"北京市","city":"北京市","district":"东城区"}
         * address_reference : {"crossroad":{"id":"659076","title":"东华门大街/智德前巷(路口)","location":{"lat":39.915169,"lng":116.405052},"_distance":87.8,"_dir_desc":"西"},"town":{"id":"110101001","title":"东华门街道","location":{"lat":39.923271,"lng":116.396828},"_distance":0,"_dir_desc":"内"},"street_number":{"id":"5956636539586275260","title":"东华门大街62号","location":{"lat":39.915131,"lng":116.4039},"_distance":5.4,"_dir_desc":""},"street":{"id":"4382178191545952722","title":"东华门大街","location":{"lat":39.915161,"lng":116.404167},"_distance":0,"_dir_desc":""},"landmark_l2":{"id":"3504460654985366161","title":"东华门小区","location":{"lat":39.914692,"lng":116.404831},"_distance":50.8,"_dir_desc":"西北"}}
         */

        private LocationDTO location;
        private String address;
        private FormattedAddressesDTO formattedAddresses;
        private AddressComponentDTO addressComponent;
        private AdInfoDTO adInfo;
        private AddressReferenceDTO addressReference;

        @Data
        public static class LocationDTO {
            /**
             * lat : 39.915119
             * lng : 116.403963
             */

            private Double lat;
            private Double lng;
        }

        @Data
        public static class FormattedAddressesDTO {
            /**
             * recommend : 东华门小区西北(东华门大街)
             * rough : 东华门小区西北(东华门大街)
             */

            private String recommend;
            private String rough;
        }

        @Data
        public static class AddressComponentDTO {
            /**
             * nation : 中国
             * province : 北京市
             * city : 北京市
             * district : 东城区
             * street : 东华门大街
             * street_number : 东华门大街62号
             */

            private String nation;
            private String province;
            private String city;
            private String district;
            private String street;
            private String streetNumber;
        }

        @Data
        public static class AdInfoDTO {
            /**
             * nation_code : 156
             * adcode : 110101
             * city_code : 156110000
             * name : 中国,北京市,北京市,东城区
             * location : {"lat":39.916668,"lng":116.434578}
             * nation : 中国
             * province : 北京市
             * city : 北京市
             * district : 东城区
             */

            private String nationCode;
            private String adcode;
            private String cityCode;
            private String name;
            private LocationDTOx location;
            private String nation;
            private String province;
            private String city;
            private String district;

            @Data
            public static class LocationDTOx {
                /**
                 * lat : 39.916668
                 * lng : 116.434578
                 */

                private Double lat;
                private Double lng;
            }
        }

        @Data
        public static class AddressReferenceDTO {
            /**
             * crossroad : {"id":"659076","title":"东华门大街/智德前巷(路口)","location":{"lat":39.915169,"lng":116.405052},"_distance":87.8,"_dir_desc":"西"}
             * town : {"id":"110101001","title":"东华门街道","location":{"lat":39.923271,"lng":116.396828},"_distance":0,"_dir_desc":"内"}
             * street_number : {"id":"5956636539586275260","title":"东华门大街62号","location":{"lat":39.915131,"lng":116.4039},"_distance":5.4,"_dir_desc":""}
             * street : {"id":"4382178191545952722","title":"东华门大街","location":{"lat":39.915161,"lng":116.404167},"_distance":0,"_dir_desc":""}
             * landmark_l2 : {"id":"3504460654985366161","title":"东华门小区","location":{"lat":39.914692,"lng":116.404831},"_distance":50.8,"_dir_desc":"西北"}
             */

            private CrossroadDTO crossroad;
            private TownDTO town;
            private StreetNumberDTO streetNumber;
            private StreetDTO street;
            private LandmarkL2DTO landmarkL2;

            @Data
            public static class CrossroadDTO {
                /**
                 * id : 659076
                 * title : 东华门大街/智德前巷(路口)
                 * location : {"lat":39.915169,"lng":116.405052}
                 * _distance : 87.8
                 * _dir_desc : 西
                 */

                private String id;
                private String title;
                private LocationDTO location;
                private Double distance;
                private String dirDesc;

                @Data
                public static class LocationDTO {
                    /**
                     * lat : 39.915169
                     * lng : 116.405052
                     */

                    private Double lat;
                    private Double lng;
                }
            }

            @Data
            public static class TownDTO {
                /**
                 * id : 110101001
                 * title : 东华门街道
                 * location : {"lat":39.923271,"lng":116.396828}
                 * _distance : 0
                 * _dir_desc : 内
                 */

                private String id;
                private String title;
                private LocationDTO location;
                private Integer distance;
                private String dirDesc;

                @Data
                public static class LocationDTO {
                    /**
                     * lat : 39.923271
                     * lng : 116.396828
                     */

                    private Double lat;
                    private Double lng;
                }
            }

            @Data
            public static class StreetNumberDTO {
                /**
                 * id : 5956636539586275260
                 * title : 东华门大街62号
                 * location : {"lat":39.915131,"lng":116.4039}
                 * _distance : 5.4
                 * _dir_desc :
                 */

                private String id;
                private String title;
                private LocationDTO location;
                private Double distance;
                private String dirDesc;

                @Data
                public static class LocationDTO {
                    /**
                     * lat : 39.915131
                     * lng : 116.4039
                     */

                    private Double lat;
                    private Double lng;
                }
            }

            @Data
            public static class StreetDTO {
                /**
                 * id : 4382178191545952722
                 * title : 东华门大街
                 * location : {"lat":39.915161,"lng":116.404167}
                 * _distance : 0
                 * _dir_desc :
                 */

                private String id;
                private String title;
                private LocationDTOx location;
                private Integer distance;
                private String dirDesc;

                @Data
                public static class LocationDTOx {
                    /**
                     * lat : 39.915161
                     * lng : 116.404167
                     */

                    private Double lat;
                    private Double lng;
                }
            }

            @Data
            public static class LandmarkL2DTO {
                /**
                 * id : 3504460654985366161
                 * title : 东华门小区
                 * location : {"lat":39.914692,"lng":116.404831}
                 * _distance : 50.8
                 * _dir_desc : 西北
                 */

                private String id;
                private String title;
                private LocationDTO location;
                private Double distance;
                private String dirDesc;

                @Data
                public static class LocationDTO {
                    /**
                     * lat : 39.914692
                     * lng : 116.404831
                     */

                    private Double lat;
                    private Double lng;
                }
            }
        }
    }
}
