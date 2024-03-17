package org.example;

import static com.github.dreamhead.moco.Moco.and;
import static com.github.dreamhead.moco.Moco.by;
import static com.github.dreamhead.moco.Moco.conditional;
import static com.github.dreamhead.moco.Moco.header;
import static com.github.dreamhead.moco.Moco.json;
import static com.github.dreamhead.moco.Moco.status;
import static com.github.dreamhead.moco.Moco.uri;

import static com.github.dreamhead.moco.Moco.httpServer;
import static com.github.dreamhead.moco.Runner.runner;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.dreamhead.moco.HttpServer;
import com.github.dreamhead.moco.Runner;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * com.github.dreamhead.moco mockserver 示例
 *
 * @author yinxing
 * @since 2024/2/16
 **/
public class MockServerExample {

  HttpServer server;
  Runner runner;

  @BeforeEach
  public void setup() {
    server = httpServer(12306);
    runner = runner(server);
    runner.start();
  }

  @Test
  public void testMockServer() throws Exception {
    server.request(by(uri("/api/data"))).response("Mocked data response");
    server.request(by(uri("/api/error"))).response(status(500));

    // Send request to the mock server
    HttpUrl url = HttpUrl.get("http://127.0.0.1:12306/api/data");
    Request request =
        new Request.Builder()
            .url(url)
            .get()
            .build();
    OkHttpClient client = new OkHttpClient();

    Response response = client.newCall(request).execute();
    System.out.println(response);
    assertEquals("Mocked data response", response.body().string());
  }

  @Test
  public void test2() throws Exception {
    String attributes = "[{\"key\":\"method\",\"value\":\"POST\",\"type\":\"String\"},{\"key\":\"url\",\"value\":\"https://ae-openapi.feishu.cn/api/data/v1/namespaces/package_d22e20__c/objects/mock_insert/records\",\"type\":\"String\"},{\"key\":\"requestHeaders\",\"value\":\"{authorization:[T:the_access_token],x-tt-logid:[20240221172459010254137044613BD1],content-type:[application/json]}\",\"type\":\"Json\"},{\"key\":\"requestBody\",\"value\":\"{\\\"filter\\\":[{\\\"leftValue\\\":\\\"name\\\",\\\"operator\\\":\\\"in\\\",\\\"rightValue\\\":\\\"张三,李四,王五\\\"}],\\\"limit\\\":200,\\\"offset\\\":0,\\\"count\\\":false,\\\"fields\\\":[\\\"name\\\"]}\",\"type\":\"String\"},{\"key\":\"responseHeaders\",\"value\":\"{content-length:[60]}\",\"type\":\"Json\"},{\"key\":\"responseBody\",\"value\":\"{\\\"code\\\":\\\"0\\\",\\\"data\\\":{\\\"records\\\":[],\\\"total\\\":0},\\\"msg\\\":\\\"success\\\"}\",\"type\":\"String\"},{\"key\":\"code\",\"value\":\"null\",\"type\":\"Unknown\"},{\"key\":\"success\",\"value\":\"true\",\"type\":\"Boolean\"},{\"key\":\"error\",\"value\":\"null\",\"type\":\"Unknown\"}]";
    attributes = attributes.replaceAll(",x-tt-logid:\\[[^\\]]*\\]", "");
    System.out.println(attributes);
  }

  @AfterEach
  public void teardown() {
    runner.stop();
  }
}
