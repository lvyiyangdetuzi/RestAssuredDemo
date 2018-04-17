import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class DemoTest {
    @Test
    public void testJson(){
        when().get("http://192.168.1.11:8000/testdemo.json")
        .then()
                .body("store.book.category",hasItems("reference"))
                .body("store.book[1].author",equalTo("Evelyn Waugh"))
                .body("store.book.find { book -> book.price == 8.95f }.price",equalTo(8.95f))
                .body("store.book.findAll { book -> book.price >= 10 && book.price <= 25 }.price",hasItems(12.99f,22.99f))

        ;

    }

    @Test
    public void testXml(){
        when().get("http://192.168.1.11:8000/testdemo.xml")
        .then()
            .body("shopping.category.item[1].name",equalTo("Coffee"))
            .body("shopping.category[0].item.size()",equalTo(2))
            .body("shopping.category.findAll { it.@type == 'groceries' }.size()",equalTo(1))
            .body("shopping.category.item.findAll { item -> def price = item.price.toFloat(); price >= 10 && price <= 20 }.name",hasItems("Chocolate","Coffee","Pens"))
            //.body("**.find { it.name == 'Chocolate' }.price",equalTo(10))
            .body("**.find { it.price == 10 }.name",equalTo("Chocolate"))
        ;

    }
}
