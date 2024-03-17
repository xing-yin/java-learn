package org.example;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.SneakyThrows;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.junit.jupiter.api.Test;

/**
 * <p>FIXME: 简要描述本文件的功能</p>
 *
 * @author yinxing
 * @since 2024/3/7
 **/
public class HttpTest {

  /**
   * 根据模板ID获取模板信息
   */
  private static final String OPENAPI_LIST_DOCUMENT_TEMPLATE_METAINFO =
      "/api/openapi/v2/specifications/byIds/[%s]";

  @SneakyThrows
  @Test
  public void test() {

    OkHttpClient client = new OkHttpClient().newBuilder()
        .build();

    String ids = "ID01xpfSPhb5Zd";
    String domain = "https://app.ekuaibao.com";
    HttpUrl.Builder urlBuilder =
        Objects.requireNonNull(
                HttpUrl.parse(domain + String.format(OPENAPI_LIST_DOCUMENT_TEMPLATE_METAINFO, ids)))
            .newBuilder();
    urlBuilder.addQueryParameter("accessToken", "ID01xoKejIpPAj:AtwcceRJWY2E00");
    String url = urlBuilder.build().toString();
    Request request = new Request.Builder().url(url).get().build();
    Response response = client.newCall(request).execute();
    String responseBody = response.body() == null ? null : response.body().string();
    System.out.println(response);
  }


  @Test
  public void test2() {
    System.out.println(extractDocumentType("/api/openapi/v1.1/docs/getApplyList?accessToken=valid_access_token&type=expense&start=0&count=10"));
  }

  public static String extractDocumentType(String urlString) {
    Pattern pattern = Pattern.compile("type=([^&]*)");
    Matcher matcher = pattern.matcher(urlString);
    if (matcher.find()) {
      return matcher.group(1);
    }
    return null;
  }

}
