package com.agefades.single.admin.test;

import cn.hutool.json.JSONUtil;
import com.agefades.single.admin.SingleAdminApplication;
import com.agefades.single.common.util.TencentUtil;
import com.agefades.single.common.util.dto.TxDistrict;
import com.agefades.single.common.util.dto.TxLocation;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SingleAdminApplication.class})
public class TestTencentUtil {

    @Test
    public void getLocationByIp() {
        TxLocation location = TencentUtil.getLocationByIp("119.57.47.18");
        log.info("腾讯地图位置信息: {}", JSONUtil.toJsonStr(location));
    }

    @Test
    public void getGpsByCity() {
        TxDistrict district = TencentUtil.getGpsByCity("北京市");
        log.info("腾讯地图行政区划信息: {}", JSONUtil.toJsonStr(district));
    }

}
