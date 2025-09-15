package com.yupi.aicodehelper.controller;

import com.yupi.aicodehelper.ai.AiCodeHelperService;
import jakarta.annotation.Resource;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/ai")
public class Aicontroller {
    @Resource
    private AiCodeHelperService aiCodeHelperService;


    @GetMapping("/chat")
    public Flux<ServerSentEvent<String>> chat(int memoryId,String message) {
        /// 样板代码
        return aiCodeHelperService.chatStream(memoryId,message)
                .map(chunk -> ServerSentEvent.builder(chunk)
                        .data(chunk)
                        .build());
    }
}
