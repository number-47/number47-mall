package com.number47.mall;

import com.number47.mall.model.entity.UmsAdmin;
import com.number47.mall.model.service.UmsAdminService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class MallApplicationTests {

    @Autowired
    private UmsAdminService umsAdminService;


    @Test
    void contextLoads() {
        UmsAdmin admin = umsAdminService.getUmsAdmin(1L);
        log.info("admin={}:", admin);
    }

}
