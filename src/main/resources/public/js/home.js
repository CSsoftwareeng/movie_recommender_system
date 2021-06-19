$(document).ready(function () {
  function config_select_options() {
    var occupation_options = [
      "Occupation",
      "academic/educator",
      "artist",
      "clerical/admin",
      "college/grad student",
      "customer service",
      "doctor/health care",
      "executive/managerial",
      "farmer",
      "homemaker",
      "K-12 student",
      "lawyer",
      "programmer",
      "retired",
      "sales/marketing",
      "scientist",
      "self-employed",
      "technician/engineer",
      "tradesman/craftsman",
      "unemployed",
      "writer",
      "other",
    ];
    $("#occupation-selection").append(
      $('<option value="">Occupation</option>')
    );
    for (var i = 1; i < occupation_options.length; i++) {
      var value = occupation_options[i];
      var option = $('<option value="' + value + '">' + value + "</option>");
      $("#occupation-selection").append(option);
    }

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

    $(".genre-selection").append($('<option value="">Genre</option>'));
    for (var i = 1; i < genre_options.length; i++) {
      var value = genre_options[i];
      var option = $('<option value="' + value + '">' + value + "</option>");
      $(".genre-selection").append(option);
    }
  }
  config_select_options();

  // var default_data = { gender: "", age: "", occupation: "", genres: "" };
  $result_top_ten = $('<div class="result-top-ten"></div>');
  $title_top_ten = $('<div class="title-top-ten">Top 10 Recommendation</div>');
  $.ajax({
    contentType: "application/json; charset=utf-8",
    type: "GET",
    data: { type: "top" },
    url: "/home",
    // url: "/users/recommendations",
  })
    .then(function (data) {
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
        $result_top_ten.append($link);
      }
      console.log(data);
      $(".contents").append($title_top_ten);
      $(".contents").append($result_top_ten);
    })
    .then(function () {
      $result_action = $('<div class="result-action"></div>');
      $title_action = $('<div class="title-action">Popular in Action</div>');
      $.ajax({
        contentType: "application/json; charset=utf-8",
        type: "GET",
        data: { type: "action" },
        url: "/home",
      })
        .then(function (data) {
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
            $result_action.append($link);
          }
          console.log(data);
          $(".contents").append($title_action);
          $(".contents").append($result_action);
        })
        .then(function () {
          $result_drama = $('<div class="result-drama"></div>');
          $title_drama = $('<div class="title-drama">Popular in Drama</div>');
          $.ajax({
            contentType: "application/json; charset=utf-8",
            type: "GET",
            data: { type: "drama" },
            url: "/home",
          })
            .then(function (data) {
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
                $result_drama.append($link);
              }
              console.log(data);
              $(".contents").append($title_drama);
              $(".contents").append($result_drama);
            })
            .then(function () {
              $result_animation = $('<div class="result-animation"></div>');
              $title_animation = $(
                '<div class="title-animation">Popular in Animation</div>'
              );
              $.ajax({
                contentType: "application/json; charset=utf-8",
                type: "GET",
                data: { type: "animation" },
                url: "/home",
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
                      : $(
                          '<img class="card-img" src="' + data[i].poster + '"/>'
                        );
                  $card.append($card_img);
                  $link.append($card);
                  $result_animation.append($link);
                }
                console.log(data);
                $(".contents").append($title_animation);
                $(".contents").append($result_animation);
              });
            });
        });
    });
});
