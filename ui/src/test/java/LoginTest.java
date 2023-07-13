import com.codeborne.selenide.Selenide;
import org.testng.annotations.Test;

public class LoginTest extends BaseTests {
    @Test
    public void initialTest() {
        Selenide.open("http://localhost:8112/login.html");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
