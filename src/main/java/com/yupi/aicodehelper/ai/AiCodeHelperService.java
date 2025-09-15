package com.yupi.aicodehelper.ai;

import com.yupi.aicodehelper.ai.guardrail.SafeInputGuardrail;
import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.Result;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.guardrail.InputGuardrails;
import jdk.javadoc.doclet.Reporter;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 *
 */
@InputGuardrails({SafeInputGuardrail.class})
public interface AiCodeHelperService {

    @SystemMessage(fromResource = "system-prompt.txt")
    String chat(String userMessage);

    @SystemMessage(fromResource = "system-prompt.txt")
    Report chatForReport(String userMessage);
    /// 自动生成类的语法糖
    /// 7.结构化输出
    record Report(String name, List<String> suggestionList){}

    /// 返回封装后的结果(rag)
//    @SystemMessage(fromResource = "system-prompt.txt")
//    Result<String> chatWithRag(String userMessage);
    /// 流式输出
    @SystemMessage(fromResource = "system-prompt.txt")
    Flux<String> chatStream(@MemoryId int memoryId, @UserMessage String message);
}
