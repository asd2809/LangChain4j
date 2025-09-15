package com.yupi.aicodehelper.ai;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AiCodeHelperTest {


    @Resource
    private AiCodeHelper helper;
    @Test
    void chat() {
        helper.chat("你好");
    }
}