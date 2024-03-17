package org.example;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class MockServerExampleTest {

  private MockWebServer mockWebServer;

  public static void main(String[] args) throws IOException {
    MockWebServer mockWebServer = new MockWebServer();

    // 设置 Dispatcher
    mockWebServer.setDispatcher(new Dispatcher() {
      @Override
      public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
        if (request.getPath().equals("/api/data")) {
          return new MockResponse().setResponseCode(200).setBody("Mocked data response");
        } else if (request.getPath().equals("/api/error")) {
          return new MockResponse().setResponseCode(500);
        } else {
          return new MockResponse().setResponseCode(404);
        }
      }
    });

    // 启动 mockWebServer
    mockWebServer.start();

    // 获取 mockWebServer 的 URL
    String baseUrl = mockWebServer.url("").toString();
    System.out.println("Mock Server URL: " + baseUrl);

    // 在这里进行网络请求，使用 baseUrl 作为请求的地址

    // 关闭 mockWebServer
    mockWebServer.shutdown();
  }

  @Before
  public void setUp() throws IOException {
    mockWebServer = new MockWebServer();

    mockWebServer.setDispatcher(new Dispatcher() {
      @Override
      public MockResponse dispatch(RecordedRequest request) {
        if (request.getPath().equals("/api/data")) {
          return new MockResponse().setResponseCode(200).setBody("Mocked data response");
        } else if (request.getPath().equals("/api/error")) {
          return new MockResponse().setResponseCode(500);
        } else {
          return new MockResponse().setResponseCode(404);
        }
      }
    });

    mockWebServer.start();
  }

  @After
  public void tearDown() throws IOException {
    mockWebServer.shutdown();
  }

  @Test
  public void testApiData() throws IOException {
    OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder()
        .url(mockWebServer.url("/api/data"))
        .build();

    Response response = client.newCall(request).execute();
    assertEquals(200, response.code());
    assertEquals("Mocked data response", response.body().string());
    response.close();
  }

  @Test
  public void testApiError() throws IOException {
    OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder()
        .url(mockWebServer.url("/api/error"))
        .build();

    Response response = client.newCall(request).execute();
    assertEquals(500, response.code());
    response.close();
  }
}