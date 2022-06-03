# Game Battle api spring boot
Challenge game battle try to guess the best rated movie

### Flow:
Two users and players are created on the startup: user01 - pwd:123,
user02 - pwd:123.

- [1. Register user - localhost:8080/register](#)
- [2. Authenticate and generate jwt token - localhost:8080/authenticate](#)
- [3. Create a Player - localhost:8080/api/v1/player/create](#)
- [4. Create a Game - localhost:8080/api/v1/game/create](#)
- [5. Get a Game and movies - localhost:8080/api/v1/game/1](#)
- [6. Play the Game  - localhost:8080/api/v1/game/1/play](#)
- [7. Ranking the players - localhost:8080/api/v1/ranking](#)
- [8. Game over the Game whenever  - localhost:8080/api/v1/game/gameOver/1](#)

### API imdb offline
The game consumes information from imdb-rest-api.jar,
This service provides the top 250 movies extracted from omdbapi.
### Swagger API:
http://localhost:8080/swagger-ui/index.html

### Run
sh start.sh
For win: startWin.bat
### Stop Services
stopProcessWin.bat
### Technologies:

- [Java 11](#)
- [Spring boot 2.6.7](#)
- [Spring spring-cloud-openfeign, Data, Security](#)
- [JWT 0.9.1](#)
- [mapstruct](#)
- [lombok](#)
- [zalando - problem-spring-web](#)
- [springdoc](#)
### Tests
- [archunit-junit5](#)
- [mockito](#)
- [rest-assured](#)