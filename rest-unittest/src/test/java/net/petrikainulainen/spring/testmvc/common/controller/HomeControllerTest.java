package net.petrikainulainen.spring.testmvc.common.controller;

import net.petrikainulainen.spring.testmvc.config.TestContextRestConfig;
import net.petrikainulainen.spring.testmvc.config.WebAppContextRestConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * @author Petri Kainulainen
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContextRestConfig.class, WebAppContextRestConfig.class})
//@ContextConfiguration(locations = {"classpath:testContext.xml", "classpath:exampleApplicationContext-web.xml"})
@WebAppConfiguration
public class HomeControllerTest {

  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  public void showHomePage_ShouldRenderHomePage() throws Exception {
    mockMvc.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(view().name("index"))
        .andExpect(forwardedUrl("/WEB-INF/jsp/index.jsp"));
  }

  @Test
  public void showSessionPage1_ShouldRenderHomePage() throws Exception {
    User user = new UserBuilderBad().id(5L).build();

    MockHttpSession session = new MockHttpSession();
    session.setAttribute("user", user);

    mockMvc.perform(get("/")
        .session(session)
    )
        .andExpect(status().isOk())
        .andExpect(view().name("session"))
        .andExpect(forwardedUrl("/WEB-INF/jsp/session.jsp"));
  }

  @Test
  public void showSessionPage2_ShouldRenderHomePage() throws Exception {
    User user = new UserBuilderBad().id(22L).build();
    mockMvc.perform(get("/")
        .sessionAttr("user", user)
    )
        .andExpect(status().isOk())
        .andExpect(view().name("session"))
        .andExpect(forwardedUrl("/WEB-INF/jsp/session.jsp"));
  }
}
