package com.raoni.legalrag.embeddings.application;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HexFormat;
import java.util.List;
import java.util.Locale;

@Service
public class FakeEmbeddingService implements EmbeddingService {
    private static final int DIMENSIONS = 12;

    @Override
    public List<Double> embed(String text) {
        String normalized = text == null ? "" : text.toLowerCase(Locale.ROOT).trim();
        byte[] digest = sha256(normalized);
        List<Double> vector = new ArrayList<>();
        for (int i = 0; i < DIMENSIONS; i++) {
            int unsigned = Byte.toUnsignedInt(digest[i]);
            vector.add(unsigned / 255.0d);
        }
        return vector;
    }

    @Override
    public String serialize(List<Double> vector) {
        return vector.stream().map(v -> String.format(Locale.ROOT, "%.6f", v)).toList().toString();
    }

    private byte[] sha256(String value) {
        try {
            return MessageDigest.getInstance("SHA-256").digest(value.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 is not available", e);
        }
    }

    public String fingerprint(String text) {
        return HexFormat.of().formatHex(sha256(text)).substring(0, 12);
    }
}
