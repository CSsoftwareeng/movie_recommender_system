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
      $link = $(
        '<a href="' +
          data[i].imdb.substring(1, data[i].imdb.length - 1) +
          '" target="_blank"></a>'
      );
      $card = $('<div class="card"></div>');
      $card_text = $('<p class="card-text"></div>');
      $card_text.html(data[i].title);
      $card.append($card_text);
      $link.append($card);
      $result.append($link);
    }
    $(".contents").append($result);
  });
}
