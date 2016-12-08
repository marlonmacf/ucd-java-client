package domain;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Development.class)
public class DevelopmentTest {

    public DevelopmentTest() {
        System.setProperty("spring.profiles.active", "development");
    }
}