package com.raoni.legalrag.embeddings.application;

import java.util.List;

public interface EmbeddingService {
    List<Double> embed(String text);
    String serialize(List<Double> vector);
}
