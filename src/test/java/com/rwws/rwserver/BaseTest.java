package com.rwws.rwserver;

import com.rwws.rwserver.RwServerApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = RwServerApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseTest {
}
