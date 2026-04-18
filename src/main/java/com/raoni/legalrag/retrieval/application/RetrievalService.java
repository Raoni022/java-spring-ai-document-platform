package com.raoni.legalrag.retrieval.application;

import com.raoni.legalrag.documents.domain.DocumentChunk;
import com.raoni.legalrag.documents.repository.DocumentChunkRepository;
import com.raoni.legalrag.retrieval.dto.RetrievalResponse;
import com.raoni.legalrag.retrieval.dto.RetrievedChunk;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RetrievalService {
    private final DocumentChunkRepository chunkRepository;

    public RetrievalService(DocumentChunkRepository chunkRepository) {
        this.chunkRepository = chunkRepository;
    }

    @Transactional(readOnly = true)
    public RetrievalResponse search(String query, int topK) {
        Set<String> queryTerms = tokenize(query);
        var results = chunkRepository.findAll().stream()
                .map(chunk -> toResult(chunk, queryTerms))
                .sorted(Comparator.comparingDouble(RetrievedChunk::relevanceScore).reversed())
                .limit(topK)
                .toList();
        return new RetrievalResponse(query, results);
    }

    private RetrievedChunk toResult(DocumentChunk chunk, Set<String> queryTerms) {
        Set<String> chunkTerms = tokenize(chunk.getContent());
        long overlap = queryTerms.stream().filter(chunkTerms::contains).count();
        double score = queryTerms.isEmpty() ? 0.0 : (double) overlap / queryTerms.size();
        return new RetrievedChunk(
                chunk.getDocument().getId(),
                chunk.getId(),
                chunk.getDocument().getTitle(),
                chunk.getContent(),
                chunk.getCitationRef(),
                Math.round(score * 1000.0) / 1000.0
        );
    }

    private Set<String> tokenize(String value) {
        return Arrays.stream(value.toLowerCase(Locale.ROOT).split("[^a-zA-Z0-9À-ÿ]+"))
                .filter(token -> token.length() > 2)
                .collect(Collectors.toSet());
    }
}
