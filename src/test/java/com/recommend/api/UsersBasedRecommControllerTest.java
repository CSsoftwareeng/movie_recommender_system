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

    mvc
      .perform(
        get("/users/recommendations")
            .param("gender", "")
            .param("age", "")
            .param("occupation", "")
            .param("genres", "Romance|comedy")
      )
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.length()").value(10));
  }

  @Test
  public void testValidRequestWithFullConditions() throws Exception {
    // String json =
    //   "{\"gender\" : \"F\", \"age\" : \"30\", \"occupation\" : \"artist\", \"genres\" : \"Romance|comedy\"}";

    mvc
      .perform(
        get("/users/recommendations")
            .param("gender", "F")
            .param("age", "30")
            .param("occupation", "artist")
            .param("genres", "Romance|comedy")
      )
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.length()").value(10));
  }

  @Test
  public void testValidRequestWoGenres() throws Exception {

    mvc
      .perform(
        get("/users/recommendations")
            .param("gender", "")
            .param("age", "")
            .param("occupation", "")
      )
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.length()").value(10));
  }

  @Test
  public void testTooFewArguments() throws Exception {

    mvc
      .perform(
        get("/users/recommendations")
            .param("occupation", "")
            .param("genres", "Romance|comedy")
      )
      .andExpect(status().isBadRequest())
      .andExpect(status().reason(containsString("ArgCntError")));
  }

  @Test
  public void testTooManyArguments() throws Exception {

    mvc
      .perform(
        get("/users/recommendations")
            .param("gender", "")
            .param("age", "")
            .param("occupation", "")
            .param("genres", "Romance|comedy")
            .param("DUMMY_FILED", "DUMMY_STRING")
      )
      .andExpect(status().isBadRequest())
      .andExpect(status().reason(containsString("ArgCntError")));
  }

  @Test
  public void testWrongArguments() throws Exception {

    mvc
      .perform(
        get("/users/recommendations")
            .param("gender", "")
            .param("age", "")
            .param("occupation", "")
            .param("INVALID_FILED", "INVALID_STRING")
      )
      .andExpect(status().isBadRequest())
      .andExpect(status().reason(containsString("WrongArgError")))
      .andExpect(status().reason(containsString("gender, age, occupation, genres")));;
  }

  @Test
  public void testInvalidAge() throws Exception {
  
    mvc
      .perform(
        get("/users/recommendations")
            .param("gender", "")
            .param("age", "-1")
            .param("occupation", "")
            .param("genres", "Romance|comedy")
      )
      .andExpect(status().isBadRequest())
      .andExpect(status().reason(containsString("age")));
  }

  @Test
  public void testInvalidGender() throws Exception {
    
    mvc
      .perform(
        get("/users/recommendations")
            .param("gender", "INVALID_STRING")
            .param("age", "")
            .param("occupation", "")
            .param("genres", "Romance|comedy")
      )
      .andExpect(status().isBadRequest())
      .andExpect(status().reason(containsString("gender")));
  }

  @Test
  public void testInvalidOccupation() throws Exception {

    mvc
      .perform(
        get("/users/recommendations")
            .param("gender", "")
            .param("age", "")
            .param("occupation", "INVALID_STRING")
            .param("genres", "Romance|comedy")
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
            .param("gender", "")
            .param("occupation", "")
            .param("genres", "Romance|comedy")
      )
      .andExpect(status().isBadRequest())
      .andExpect(status().reason(containsString("'age' is missing")));

    mvc
      .perform(
        get("/users/recommendations")
            .param("age", "")
            .param("occupation", "")
            .param("genres", "Romance|comedy")
      )
      .andExpect(status().isBadRequest())
      .andExpect(status().reason(containsString("'gender' is missing")));

    mvc
      .perform(
        get("/users/recommendations")
            .param("gender", "")
            .param("age", "")
            .param("genres", "Romance|comedy")
      )
      .andExpect(status().isBadRequest())
      .andExpect(status().reason(containsString("'occupation' is missing")));
  }
}
