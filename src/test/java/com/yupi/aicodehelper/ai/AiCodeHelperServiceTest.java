package com.yupi.aicodehelper.ai;

import dev.langchain4j.service.Result;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AiCodeHelperServiceTest {


    @Resource
    private AiCodeHelperService aiCodeHelperService;

    @Test
    void test() {
        String s = aiCodeHelperService.chat("我叫陈保姜");
        System.out.println(s);
    }

    @Test
    void testMomery() {
        String s = aiCodeHelperService.chat("我叫陈保姜");
        System.out.println(s);
        s = aiCodeHelperService.chat("我是谁？");
        System.out.println(s);

    }

    @Test
    void chatForReport() {
        Record userMessage= aiCodeHelperService.chatForReport("我想要学java，能不能给我推荐几道面试题");
        System.out.println(userMessage);
    }
    @Test
    void chatWithRag() {
        String result = aiCodeHelperService.chat("怎么学习 Java？有哪些常见面试题？");
        System.out.println(result);
    }
    @Test
    void chatWithTools() {
        String result = aiCodeHelperService.chat("有哪些常见的计算机网络面试题?");
        System.out.println(result);
    }

    @Test
    void chatWithMcp() {
        String result = aiCodeHelperService.chat("什么是智能协作云图库");
        System.out.println(result);
    }

    @Test
    void chatWithGuardrail() {
        ///如果用户输入有敏感词会报错
        String result = aiCodeHelperService.chat("the game");
        System.out.println(result);
    }
    @Test
    void chatWithFluxStream() {
        ///如果用户输入有敏感词会报错.
        Flux<String> res = aiCodeHelperService.chatStream(1, "你好");
        System.out.println(res);
    }

}