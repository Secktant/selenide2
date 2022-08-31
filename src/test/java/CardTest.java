import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static java.time.Duration.ofSeconds;

public class CardTest {
    @BeforeEach
    void shouldOpenBroswer() {
        open("http://localhost:9999/");
    }


    @Test
    public void happyPathTest() {
        SelenideElement form = $x("//form[contains(@class,form)]");
        SelenideElement notif = $x("//div[@data-test-id=\"notification\"]");
        $x("//input[@placeholder=\"Город\"]").val("Ижевск");
        $x("//span[text()=\"Ижевск\"]").shouldHave(Condition.exactText("Ижевск"));
        form.$x(".//span[@data-test-id=\"name\"]//input").val("Александр Александров");
        form.$x(".//span[@data-test-id=\"phone\"]//input").setValue("+79012345678");
        form.$x(".//span[@class=\"checkbox__box\"]").click();
        form.$x(".//span[text()=\"Забронировать\"]//ancestor::button").click();
        notif.should(visible, ofSeconds(15));
        notif.$x(".//div[@class='notification__title']").should(text("Успешно!"));
        notif.$x(".//div[@class='notification__content']").shouldHave(Condition.exactText("Встреча успешно забронирована на " + form.$x(".//span[@data-test-id='date']//input").getValue()));

    }



}

