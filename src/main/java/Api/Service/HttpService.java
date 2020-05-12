package Api.Service;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

@Service
public class HttpService {
    private final ObjectMapper jsonSerializer;
    private final HttpClient client;

    public HttpService(ObjectMapper jsonSerializer) {
        this.jsonSerializer = jsonSerializer;
        this.client = HttpClient.newBuilder().build();
    }

    public <T> CompletableFuture<HttpResponse<T>> requestAsync(String urlString, String requestType) throws IOException, URISyntaxException {
        return this.requestAsync(urlString, requestType, null, null);
    }

    public <T> CompletableFuture<HttpResponse<T>> requestAsync(String urlString, String requestType, @Nullable Object requestBody) throws IOException, URISyntaxException {
        return this.requestAsync(urlString, requestType, null, requestBody);
    }

    public <T> CompletableFuture<HttpResponse<T>> requestAsync(String urlString, String requestType, @Nullable Class<T> clazz) throws IOException, URISyntaxException {
        return this.requestAsync(urlString, requestType, clazz, null);
    }

    public <T> CompletableFuture<HttpResponse<T>> requestAsync(String urlString, String requestType, @Nullable Class<T> clazz, @Nullable Object requestBody, String... headers) throws IOException, URISyntaxException {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(new URI(urlString))
                .version(HttpClient.Version.HTTP_1_1);

        if (headers.length > 0) {
            requestBuilder.headers(headers);
        }

        if (requestBody != null) {
            String jsonString = this.jsonSerializer.writeValueAsString(requestBody);
            requestBuilder.method(requestType, HttpRequest.BodyPublishers.ofString(jsonString));
            requestBuilder.header("content-type", "application/json");
        } else {
            requestBuilder.method(requestType, HttpRequest.BodyPublishers.noBody());
        }

        HttpRequest request = requestBuilder.build();
        return this.client.sendAsync(request, new JsonBodyHandler<>(clazz, this.jsonSerializer));
    }

    public static class JsonBodyHandler<T> implements HttpResponse.BodyHandler<T> {

        private final Class<T> clazz;
        private final ObjectMapper objectMapper;

        public JsonBodyHandler(Class<T> clazz, ObjectMapper objectMapper) {
            this.clazz = clazz;
            this.objectMapper = objectMapper;
        }

        @Override
        public HttpResponse.BodySubscriber<T> apply(HttpResponse.ResponseInfo responseInfo) {
            return HttpResponse.BodySubscribers.mapping(HttpResponse.BodySubscribers.ofString(StandardCharsets.UTF_8), resp ->
            {
                if(responseInfo.statusCode() == 200) {
                    try {
                        return this.objectMapper.readValue(resp, clazz);
                    } catch (IOException ex) {
                        throw new UncheckedIOException(ex);
                    }
                }
                return null;
            });
        }
    }
}
