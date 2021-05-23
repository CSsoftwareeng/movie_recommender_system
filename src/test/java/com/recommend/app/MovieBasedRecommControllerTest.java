package com.recommend.app;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieBasedRecommControllerTest {

  @Autowired
  private MockMvc mvc;

  @Test
  public void validRequest() throws Exception {
    String json = "{\"title\" : \"Toy Story (1995)\", \"limit\" : 20}";

    mvc
      .perform(
        get("/movies/recommendations")
          .content(json)
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isOk());
  }

  @Test
  public void validRequestwoYear() throws Exception {
    String json = "{\"title\" : \"Toy Story\", \"limit\" : 20}";

    mvc
      .perform(
        get("/movies/recommendations")
          .content(json)
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isOk());
  }

  @Test
  public void validRequestwExtremeLimit() throws Exception {
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
  public void validRequestwoLimit() throws Exception {
    String json = "{\"title\" : \"Toy Story (1995)\"}";

    mvc
      .perform(
        get("/movies/recommendations")
          .content(json)
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isOk());
  }

  @Test
  public void TooFewArguments() throws Exception {
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
  public void invalidTitle() throws Exception {
    String json = "{\"title\" : \"To Story (1995)\", \"limit\" : 20}";

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
  public void missingTitle() throws Exception {
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
// 	@Test
// 	public void TooManyArguments() throws Exception {
// 		String json = "{\"gender\" : \"\", \"age\" : \"-1\", \"agasde\" : \"-1\", \"occupation\" : \"\", \"genre\" : \"Romance|comedy\"}";
// 		mvc.perform(get("/users/recommendations")
// 			.content(json)
// 			.contentType(MediaType.APPLICATION_JSON))
// 			.andExpect(status().isBadRequest())
// 			.andExpect(status().reason(containsString("ArgCntError")));
// 	}
// 	@Test
// 	public void invalidAge() throws Exception {
// 		String json = "{\"gender\" : \"\", \"age\" : \"-1\", \"occupation\" : \"\", \"genre\" : \"Romance|comedy\"}";
// 		mvc.perform(get("/users/recommendations")
// 			.content(json)
// 			.contentType(MediaType.APPLICATION_JSON))
// 			.andExpect(status().isBadRequest())
// 			.andExpect(status().reason(containsString("age")));
// 	}
// 	@Test
// 	public void invalidGender() throws Exception {
// 		String json = "{\"gender\" : \"Q\", \"age\" : \"\", \"occupation\" : \"\", \"genre\" : \"Romance|comedy\"}";
// 		mvc.perform(get("/users/recommendations")
// 			.content(json)
// 			.contentType(MediaType.APPLICATION_JSON))
// 			.andExpect(status().isBadRequest())
// 			.andExpect(status().reason(containsString("gender")));
// 	}
// 	@Test
// 	public void invalidOccupation() throws Exception {
// 		String json = "{\"gender\" : \"\", \"age\" : \"\", \"occupation\" : \"Q\", \"genre\" : \"Romance|comedy\"}";
// 		mvc.perform(get("/users/recommendations")
// 			.content(json)
// 			.contentType(MediaType.APPLICATION_JSON))
// 			.andExpect(status().isBadRequest())
// 			.andExpect(status().reason(containsString("occupation")));
// 	}
// 	@Test
// 	public void missingAge() throws Exception {
// 		String json = "{\"gender\" : \"\", \"occupation\" : \"\", \"genre\" : \"Romance|comedy\"}";
// 		mvc.perform(get("/users/recommendations")
// 			.content(json)
// 			.contentType(MediaType.APPLICATION_JSON))
// 			.andExpect(status().isBadRequest())
// 			.andExpect(status().reason(containsString("'age' is missing")));
// 	}
// 	@Test
// 	public void missingGender() throws Exception {
// 		String json = "{\"age\" : \"\", \"occupation\" : \"\", \"genre\" : \"Romance|comedy\"}";
// 		mvc.perform(get("/users/recommendations")
// 			.content(json)
// 			.contentType(MediaType.APPLICATION_JSON))
// 			.andExpect(status().isBadRequest())
// 			.andExpect(status().reason(containsString("'gender' is missing")));
// 	}
// 	@Test
// 	public void missingOccupation() throws Exception {
// 		String json = "{\"gender\" : \"\", \"age\" : \"\", \"genre\" : \"Romance|comedy\"}";
// 		mvc.perform(get("/users/recommendations")
// 			.content(json)
// 			.contentType(MediaType.APPLICATION_JSON))
// 			.andExpect(status().isBadRequest())
// 			.andExpect(status().reason(containsString("'occupation' is missing")));
// 	}
// }
