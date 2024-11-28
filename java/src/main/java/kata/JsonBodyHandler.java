package kata;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class JsonBodyHandler<T> implements HttpResponse.BodyHandler<T> {
    private final Class<T> targetType;
    private final ObjectMapper objectMapper = new ObjectMapper();


    public JsonBodyHandler(Class<T> targetType) {
        this.targetType = targetType;
    }

    @Override
    public HttpResponse.BodySubscriber<T> apply(HttpResponse.ResponseInfo responseInfo) {
        HttpResponse.BodySubscriber<String> upstream = HttpResponse.BodySubscribers.ofString(StandardCharsets.UTF_8);
        return HttpResponse.BodySubscribers.mapping(upstream, this::convert);
    }

    private T convert(String body) {
        try {
            return objectMapper.readValue(body, targetType);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
