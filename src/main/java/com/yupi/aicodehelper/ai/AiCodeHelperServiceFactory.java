package com.yupi.aicodehelper.ai;

import com.yupi.aicodehelper.ai.mcp.McpConfig;
import com.yupi.aicodehelper.ai.tools.InterviewQuestionTool;
import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.service.AiServices;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiCodeHelperServiceFactory {

    /// 为了区分要注入哪一个Bean 是自定义的还是langchain4j的
    @Resource(name = "myQwenChatModel")
    private ChatModel myQwenChatModel;

//    @Resource
//    private ContentRetriever contentRetriever;

    @Resource
    private McpToolProvider mcpToolProvider;

    @Resource
    private StreamingChatModel qwenStreamingChatModel;


    @Bean
    public AiCodeHelperService getAiCodeHelperService() {
        /// 会话记忆
        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);
        /// 构造Ai Service
        AiCodeHelperService aiCodeHelperService =AiServices.builder(AiCodeHelperService.class)
                .chatModel(myQwenChatModel)
                .streamingChatModel(qwenStreamingChatModel)/// 13.流式输出
                .chatMemory(chatMemory) /// 6.会话记忆
                .chatMemoryProvider(memoryId ->MessageWindowChatMemory.withMaxMessages(10))///13.每个会话独立存储
//                .contentRetriever(contentRetriever)
                .tools(new InterviewQuestionTool())/// 8.工具调用
                .toolProvider(mcpToolProvider) /// mcp调用
                .build();
        /// LangChain4j提供的一个方法，用于动态生成AiCodeHelperService的实现类
        return aiCodeHelperService;
    }

}