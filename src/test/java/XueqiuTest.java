import org.junit.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class XueqiuTest {
    @Test
    public void testSearch(){
        //信任https的任何证书
        useRelaxedHTTPSValidation();

        //given开发表示输入数据
        given().log().all()
                //query请求
                .queryParam("code", "sogo")
                //头信息
                .header("Cookie", "device_id=864d4cb52ace61737d69da102e7e996d; __utmz=1.1516097049.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); s=fv11u1xhjc; xq_a_token=229a3a53d49b5d0078125899e528279b0e54b5fe; xq_a_token.sig=oI-FfEMvVYbAuj7Ho7Z9mPjGjjI; xq_r_token=8a43eb9046efe1c0a8437476082dc9aac6db2626; xq_r_token.sig=Efl_JMfn071_BmxcpNvmjMmUP40; Hm_lvt_1db88642e346389874251b5a1eded6e3=1523358353; u=851523358353984; __utmc=1; __utma=1.1295158553.1516097049.1523673254.1523696123.10; __utmt=1; Hm_lpvt_1db88642e346389874251b5a1eded6e3=1523696133; __utmb=1.3.10.1523696123")
                //表示触发条件
                .when()
                .get("https://xueqiu.com/stock/search.json")
                //对结果断言
                .then()
                .log().all()
                //状态码断言
                .statusCode(200)
                //字段断言
                .body("stocks.name", hasItems("搜狗"))
                .body("stocks.code",hasItems("SOGO"));
    }




}