function btn_movie_click() {
  var title = $("#title-text-box").get(0).value;
  var limit = $("#limit-text-box").get(0).value;
  var param = {
    title: title ? title : "",
    limit: limit ? limit : 10,
  };
  $(".result").remove();
  $result = $('<div class="result"></div>');
  $.ajax({
    contentType: "application/json; charset=utf-8",
    type: "GET",
    data: param,
    url: "/movies/recommendations",
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
