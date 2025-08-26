package com.hiberus.enviadorprendas;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.google.gson.Gson;
import com.hiberus.enviadorprendas.domain.model.Prenda;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final Gson gson = new Gson();

    static {
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    }


    public static String getPrendaAsString(String file) throws IOException {
        return gson.toJson(getRecord(Map.class, file));
    }

    public static Prenda getPrenda(String file) throws IOException {
        return getRecord(Prenda.class, file);
    }

    //-------------------------- private
    private static <T> T getRecord(final Class<T> clazz, String... paths) throws IOException {
        return objectMapper.readValue(getJsonResource(paths), clazz);
    }

    private static InputStream getJsonResource(String... paths) {
        String path = "json" + Arrays.stream(paths).map(it -> "/" + it).collect(Collectors.joining())+ ".json";
        return TestUtils.class.getClassLoader().getResourceAsStream(path);
    }
}
