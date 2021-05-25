package com.recommend.app;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class UsersBasedRecommControllerTest {

  @Autowired
  private MockMvc mvc;

  @Test
  public void testValidRequest() throws Exception {
    String json =
      "{\"gender\" : \"\", \"age\" : \"\", \"occupation\" : \"\", \"genres\" : \"Romance|comedy\"}";

    mvc
      .perform(
        get("/users/recommendations")
          .content(json)
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.length()").value(10));
  }

  @Test
  public void testValidRequestWithFullConditions() throws Exception {
    String json =
      "{\"gender\" : \"F\", \"age\" : \"30\", \"occupation\" : \"artist\", \"genres\" : \"Romance|comedy\"}";

    mvc
      .perform(
        get("/users/recommendations")
          .content(json)
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.length()").value(10));
  }

  @Test
  public void testValidRequestWoGenres() throws Exception {
    String json = "{\"gender\" : \"\", \"age\" : \"\", \"occupation\" : \"\"}";

    mvc
      .perform(
        get("/users/recommendations")
          .content(json)
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.length()").value(10));
  }

  @Test
  public void testTooFewArguments() throws Exception {
    String json = "{\"occupation\" : \"\", \"genres\" : \"Romance|comedy\"}";

    mvc
      .perform(
        get("/users/recommendations")
          .content(json)
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isBadRequest())
      .andExpect(status().reason(containsString("ArgCntError")));
  }

  @Test
  public void testTooManyArguments() throws Exception {
    String json =
      "{ \"gender\" : \"\", \"age\" : \"\", \"occupation\" : \"\", \"genres\" : \"Romance|comedy\", \"DUMMY_FILED\" : \"DUMMY_STRING\"}";

    mvc
      .perform(
        get("/users/recommendations")
          .content(json)
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isBadRequest())
      .andExpect(status().reason(containsString("ArgCntError")));
  }

  @Test
  public void testWrongArguments() throws Exception {
    String json =
      "{ \"gender\" : \"\", \"age\" : \"\", \"occupation\" : \"\", \"INVALID_FILED\" : \"INVALID_STRING\"}";

    mvc
      .perform(
        get("/users/recommendations")
          .content(json)
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isBadRequest())
      .andExpect(status().reason(containsString("WrongArgError")))
      .andExpect(status().reason(containsString("gender, age, occupation, genres")));;
  }

  @Test
  public void testInvalidAge() throws Exception {
    String json =
      "{\"gender\" : \"\", \"age\" : \"-1\", \"occupation\" : \"\", \"genres\" : \"Romance|comedy\"}";

    mvc
      .perform(
        get("/users/recommendations")
          .content(json)
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isBadRequest())
      .andExpect(status().reason(containsString("age")));
  }

  @Test
  public void testInvalidGender() throws Exception {
    String json =
      "{\"gender\" : \"Q\", \"age\" : \"\", \"occupation\" : \"\", \"genres\" : \"Romance|comedy\"}";

    mvc
      .perform(
        get("/users/recommendations")
          .content(json)
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isBadRequest())
      .andExpect(status().reason(containsString("gender")));
  }

  @Test
  public void testInvalidOccupation() throws Exception {
    String json =
      "{\"gender\" : \"\", \"age\" : \"\", \"occupation\" : \"Q\", \"genres\" : \"Romance|comedy\"}";

    mvc
      .perform(
        get("/users/recommendations")
          .content(json)
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isBadRequest())
      .andExpect(status().reason(containsString("occupation")));
  }

  @Test
  public void testMissingArgument() throws Exception {
    String jsonMissingAge =
      "{\"gender\" : \"\", \"occupation\" : \"\", \"genres\" : \"Romance|comedy\"}";
    String jsonMissingGender =
      "{\"age\" : \"\", \"occupation\" : \"\", \"genres\" : \"Romance|comedy\"}";
    String jsonMissingOccupation =
      "{\"gender\" : \"\", \"age\" : \"\", \"genres\" : \"Romance|comedy\"}";

    mvc
      .perform(
        get("/users/recommendations")
          .content(jsonMissingAge)
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isBadRequest())
      .andExpect(status().reason(containsString("'age' is missing")));

    mvc
      .perform(
        get("/users/recommendations")
          .content(jsonMissingGender)
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isBadRequest())
      .andExpect(status().reason(containsString("'gender' is missing")));

    mvc
      .perform(
        get("/users/recommendations")
          .content(jsonMissingOccupation)
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isBadRequest())
      .andExpect(status().reason(containsString("'occupation' is missing")));
  }
}
