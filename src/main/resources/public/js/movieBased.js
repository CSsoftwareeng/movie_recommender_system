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
      $card_img =
        data[i].poster == "-"
          ? $('<p class="card-text">' + data[i].title + "</p>")
          : $('<img class="card-img" src="' + data[i].poster + '"/>');
      $card.append($card_img);
      $link.append($card);
      $result.append($link);
    }
    console.log(data);
    $(".contents").append($result);
  });
}
