function btn_user_click() {
  var gender = $("#gender-selection").get(0).value;
  var age = $("#age-text-box").get(0).value;
  var occupation = $("#occupation-selection").get(0).value;
  var genres = "";
  var $genres = $(".genre-selection");
  for (var i = 0; i < $genres.length; i++) {
    var new_each = $genres.get(i).value;
    i == 0 ? (genres = new_each) : (genres = genres.concat("|", new_each));
  }
  var param = {
    gender: gender ? gender : "",
    age: age ? age : "",
    occupation: occupation ? occupation : "",
    genres: genres ? genres : "",
  };
  $(".result").remove();
  $result = $('<div class="result"></div>');
  $.ajax({
    contentType: "application/json; charset=utf-8",
    type: "GET",
    data: param,
    url: "/users/recommendations",
  }).then(function (data) {
    for (var i = 0; i < Object.keys(data).length; i++) {
      $card = $('<div class="card"></div>');
      $card_body = $('<div class="card-body"></div>');
      $card_text = $('<p class="card-text"></div>');
      $card_text.html(data[i].title);
      $card_body.append($card_text);
      $card.append($card_body);
      $result.append($card);
    }
    $(".contents").append($result);
  });
}

function add_genre_selection() {
  var genre_options = [
    "Genre",
    "Action",
    "Adventure",
    "Animation",
    "Children's",
    "Comedy",
    "Crime",
    "Documentary",
    "Drama",
    "Fantasy",
    "Film-Noir",
    "Horror",
    "Musical",
    "Mystery",
    "Romance",
    "Sci-Fi",
    "Thriller",
    "War",
    "Western",
  ];

  var $new_select = $('<select class="genre-selection"></select>');
  $new_select.append($('<option value="">Genre</option>'));
  for (var i = 1; i < genre_options.length; i++) {
    var value = genre_options[i];
    var $option = $('<option value="' + value + '">' + value + "</option>");
    $new_select.append($option);
  }
  $(".genre-selection-holder").append($new_select);
}
