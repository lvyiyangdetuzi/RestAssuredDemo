import org.junit.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class XueqiuMyTest {
    @Test
    public void testSearch(){
        //信任https的任何证书
        useRelaxedHTTPSValidation();

        //given开发表示输入数据
        given().log().all()
                //query请求
                .queryParam("code","bushui")
                .header("Cookie","device_id=0f4500e86bbb637412edf4e90a9871bb; xq_a_token=229a3a53d49b5d0078125899e528279b0e54b5fe; xq_a_token.sig=oI-FfEMvVYbAuj7Ho7Z9mPjGjjI; xq_r_token=8a43eb9046efe1c0a8437476082dc9aac6db2626; xq_r_token.sig=Efl_JMfn071_BmxcpNvmjMmUP40; Hm_lvt_1db88642e346389874251b5a1eded6e3=1523758979; u=361523758979344; __utma=1.2026637841.1523758979.1523758979.1523758979.1; __utmc=1; __utmz=1.1523758979.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __utmt=1; __utmb=1.2.10.1523758979; Hm_lpvt_1db88642e346389874251b5a1eded6e3=1523759089" )
                .when()
                .get("https://xueqiu.com/stock/search.json")
                //对结果断言
                .then()
                .log().all()
                //状态码断言
                .statusCode(200)
                //字段断言
                .body("stocks.name", hasItems("西部水泥"))
                .body("stocks.code",hasItems("02233"));
    }

}


