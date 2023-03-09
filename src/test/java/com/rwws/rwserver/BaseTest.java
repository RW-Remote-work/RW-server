package com.rwws.rwserver;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = RwServerApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("dev")
public class BaseTest {
}
