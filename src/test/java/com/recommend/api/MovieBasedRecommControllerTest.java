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

    mvc
      .perform(
        get("/movies/recommendations")
            .param("title", "Toy Story")
            .param("limit", "20")
      )
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.length()").value(20));
  }

  @Test
  public void testValidRequestWithExtremeLimit() throws Exception {

    mvc
      .perform(
        get("/movies/recommendations")
            .param("title", "Toy Story")
            .param("limit", "4000")
      )
      .andExpect(status().isOk());
  }

  @Test
  public void testValidRequestWoLimit() throws Exception {

    mvc
      .perform(
        get("/movies/recommendations")
            .param("title", "Toy Story")
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
      )
      .andExpect(status().isBadRequest())
      .andExpect(status().reason(containsString("ArgCntError")));
  }

  @Test
  public void testInvalidTitle() throws Exception {

    mvc
      .perform(
        get("/movies/recommendations")
            .param("title", "INVALID_STRING")
            .param("limit", "20")
      )
      .andExpect(status().isNotFound())
      .andExpect(status().reason(containsString("MovieNotExistError")));
  }

  @Test
  public void testInvalidLimit() throws Exception {

    mvc
      .perform(
        get("/movies/recommendations")
            .param("title", "Toy Story")
            .param("limit", "-5")
      )
      .andExpect(status().isBadRequest())
      .andExpect(status().reason(containsString("WrongArgError")));
  }

  @Test
  public void testWrongArguments() throws Exception {
      
    mvc
      .perform(
        get("/movies/recommendations")
            .param("title", "INVALID_STRING")
            .param("INVALID_FILED", "INVALID_STRING")
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
            .param("limit", "20")
      )
      .andExpect(status().isBadRequest())
      .andExpect(status().reason(containsString("ArgMissingError")));
  }
}
