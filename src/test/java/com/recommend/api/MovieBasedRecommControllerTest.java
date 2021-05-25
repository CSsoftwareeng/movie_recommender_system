package com.recommend.app;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieBasedRecommControllerTest {

  @Autowired
  private MockMvc mvc;

  @Test
  public void testValidRequest() throws Exception {
    String json = "{\"title\" : \"Toy Story (1995)\", \"limit\" : 20}";

    mvc
      .perform(
        get("/movies/recommendations")
          .content(json)
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.length()").value(20));
  }

  @Test
  public void testValidRequestWoYear() throws Exception {
    String jsonWithYear = "{\"title\" : \"Toy Story (1995)\", \"limit\" : 20}";
    String jsonWoYear = "{\"title\" : \"Toy Story\", \"limit\" : 20}";

    MvcResult resultWithYear = mvc
      .perform(
        get("/movies/recommendations")
          .content(jsonWithYear)
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andReturn();

    String contentWithYear = resultWithYear.getResponse().getContentAsString();

    MvcResult resultWoYear = mvc
      .perform(
        get("/movies/recommendations")
          .content(jsonWoYear)
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andReturn();

    String contentWoYear = resultWoYear.getResponse().getContentAsString();

    Assert.assertEquals(contentWithYear, contentWoYear);
  }

  @Test
  public void testValidRequestWithExtremeLimit() throws Exception {
    String json = "{\"title\" : \"Toy Story (1995)\", \"limit\" : 4000}";

    mvc
      .perform(
        get("/movies/recommendations")
          .content(json)
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isOk());
  }

  @Test
  public void testValidRequestWoLimit() throws Exception {
    String json = "{\"title\" : \"Toy Story (1995)\"}";

    mvc
      .perform(
        get("/movies/recommendations")
          .content(json)
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.length()").value(10));
  }

  @Test
  public void testTooFewArguments() throws Exception {
    String json = "{}";

    mvc
      .perform(
        get("/movies/recommendations")
          .content(json)
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isBadRequest())
      .andExpect(status().reason(containsString("ArgCntError")));
  }

  @Test
  public void testInvalidTitle() throws Exception {
    String json = "{\"title\" : \"INVALID_STRING\", \"limit\" : 20}";

    mvc
      .perform(
        get("/movies/recommendations")
          .content(json)
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isNotFound())
      .andExpect(status().reason(containsString("MovieNotExistError")));
  }

  @Test
  public void testWrongArguments() throws Exception {
    String json = "{\"title\" : \"INVALID_STRING\", \"INVALID_FILED\" : \"INVALID_STRING\"}";

    mvc
      .perform(
        get("/movies/recommendations")
          .content(json)
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isBadRequest())
      .andExpect(status().reason(containsString("WrongArgError")))
      .andExpect(status().reason(containsString("title, limit")));
  }

  @Test
  public void testMissingTitle() throws Exception {
    String json = "{\"limit\" : 20}";

    mvc
      .perform(
        get("/movies/recommendations")
          .content(json)
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isBadRequest())
      .andExpect(status().reason(containsString("ArgMissingError")));
  }
}
