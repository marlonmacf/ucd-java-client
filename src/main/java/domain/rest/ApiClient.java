package domain.rest;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ApiClient {

    private RestTemplate restTemplate = new RestTemplate();
    private Gson gson = new Gson();
    private String body;

    public static String URL_SERVER;
    public static String URL_CLIENT;
    public static String URL_IMGUR;

    @Autowired
    public ApiClient(Environment env) {
        this.restTemplate = new RestTemplate();
        this.gson = new Gson();
        this.body = null;

        URL_CLIENT = env.getProperty("client.url");
        URL_SERVER = env.getProperty("server.url");
        URL_IMGUR = env.getProperty("server.img");
    }

    public ApiClient get(URI request) {
        body = restTemplate.getForEntity(request, String.class).getBody();
        return this;
    }

    public ApiClient post(URI request) {
        body = restTemplate.postForEntity(request.toString(), request, String.class).getBody();
        return this;
    }

    public <T> List<T> all(Class<T> clazz) {
        Type type = new TypeToken<List<T>>() {}.getType();
        List<LinkedTreeMap<?, ?>> mapList = gson.fromJson(body, type);
        List<T> response = new ArrayList<T>();
        for (LinkedTreeMap<?, ?> map : mapList) {
            response.add(gson.fromJson(gson.toJson(map), clazz));
        }
        return response;
    }

    public <T> T one(Class<T> clazz) {
        return gson.fromJson(body, clazz);
    }
}
