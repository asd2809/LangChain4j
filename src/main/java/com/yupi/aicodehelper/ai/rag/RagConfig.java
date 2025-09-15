//package com.yupi.aicodehelper.ai.rag;
//
//import dev.langchain4j.data.document.Document;
//import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
//import dev.langchain4j.data.document.splitter.DocumentByParagraphSplitter;
//import dev.langchain4j.data.segment.TextSegment;
//import dev.langchain4j.model.embedding.EmbeddingModel;
//import dev.langchain4j.rag.content.retriever.ContentRetriever;
//import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
//import dev.langchain4j.store.embedding.EmbeddingStore;
//import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
//import jakarta.annotation.Resource;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import java.util.List;
//
///**
// * 加载Rag
// */
//@Configuration
//
//public class RagConfig {
//
//    @Resource
//    private EmbeddingModel qwenEmbeddingModel;
//
//    @Resource
//    private EmbeddingStore<TextSegment> qwenEmbeddingStore;
//
//    @Bean
//    public ContentRetriever contentRetriever() {
//        /// ----RAG-----
//        /// 1.加载文档
//        List<Document> document = FileSystemDocumentLoader.loadDocuments("src/main/resources/docs");
//        /// 2.加载Embedding 模型
//        DocumentByParagraphSplitter DocumentByParagraphSplitter =
//                new DocumentByParagraphSplitter(1000,200);
//        /// 3.自定义文档加载器，把文档转换成向量并保存到向量数据库中
//        EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
//                .documentSplitter(DocumentByParagraphSplitter)
//                //为了提高文档质量，为每个切割后的文档碎片 TextSegment 添加文档名称作为元信息
//                .textSegmentTransformer(textSegment ->
//                        TextSegment.from(textSegment.metadata()
//                                .getString("file_name") + "\n" + textSegment.text(), textSegment.metadata()))
//                //使用的向量模型
//                .embeddingModel(qwenEmbeddingModel)
//                .embeddingStore(qwenEmbeddingStore)
//                .build();
//        /// 加载文档
//        ingestor.ingest(document);
//        /// 4.自定义内容加载器
//        EmbeddingStoreContentRetriever embeddingStoreContentRetriever = EmbeddingStoreContentRetriever.builder()
//                .embeddingStore(qwenEmbeddingStore)
//                .embeddingModel(qwenEmbeddingModel)
//                .maxResults(5) /// 输出的内容是5条
//                .minScore(0.75)/// 过滤条件，最低是0.75分
//                .build();
//        return embeddingStoreContentRetriever;
//    }
//}
