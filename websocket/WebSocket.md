# WebSocket
기존의 단방향 HTTP 프로토콜과 호환되어 양방향 통신을 제공하기 위해 개발된 프로토콜이다. 일반 Socket 통신과 달리 HTTP 80 Port를 사용하므로 방화벽에 제약이 없다.
접속까지는 HTTP 프로토콜을 이용하고, 그 이후 통신은 자체적인 WebSocket 프로토콜로 통신하게 된다.

## 그럼 왜 WebSocker을 사용하는가 ?
사실 HTTP만으로도 원하는 정보를 송수신 할 수 있다. 모든 HTTP를 사용한 통신은 클라이언트가 먼저 요청을 보내고, 그 요청에 따라 웹 서버가 응답하는 형태이며 웹 서버는 응답을 보낸 후 웹 브라이저와의 연결을 끊는다.

WebSocket이 존재하기 이전에는 Polling이나 Long Polling, Streaming 등의 방식으로 해결하였다.

* polling
<img width="389" alt="스크린샷 2022-09-16 오후 2 43 22" src="https://user-images.githubusercontent.com/83891837/190565063-cbb77fb0-1a19-4348-8822-258cba729239.png">
클라이언트가 평범한 HTTP Request를 서버로 계속 요청해 이벤트 내용을 전달받는 방식이다. 가장 쉬운 방법이지만 클라이언트가 지속적으로 Request를 요청하기 때문에 클라이언트의 수가 많아지면 서버의 부담이 급증한다. HTTP Request Connection을 맺고 끊는 것 자체가 부담이 많은 방식이고, 클라이언트에서 실시간 정도의 빠른 응답을 기대하기 어렵다.

* Long polling
<img width="382" alt="스크린샷 2022-09-16 오후 2 45 11" src="https://user-images.githubusercontent.com/83891837/190565347-b30d09d3-0cb3-4394-b9bc-d0d23f7e1f98.png">
클라이언트에서 서버로 일단 HTTP Request를 요청한다. 이 상태로 계속 기다리다가 서버에서 해당 클라이언트로 전달할 이벤트가 있다면 그 순간 Response 메세지를 전달하며 연결이 종료된다. 곧이어 클라이언트가 다시 HTTP Request를 요청해 서버의 다음 이벤트를 기다리는 방식이다. polling보다 서버의 부담은 줄었으나, 클라이언트로 보내는 이벤트들의 시간간격이 좁다면 polling과 별차이가 없게 되며, 다수의 클라이언트에게 동시에 이벤트가 발생될 경우 서버의 부담이 증가한다.

* Streaming
Long polling과 마찬가지로 클라이언트 -> 서버로 HTTP Request를 요청한다. 서버 -> 클라이언트로 이벤트를 전달할 때 해당 요청을 해제하지 않고 필요한 메세지만 보내기(Flush)를 반복하는 방식이다. Long Polling과 비교하여 서버에 메세지를 보내지 않고도 다시 HTTP Request 연결을 하지 않아도 돼 부담이 경감된다.

## WebSocket
<img width="487" alt="스크린샷 2022-09-16 오후 2 50 13" src="https://user-images.githubusercontent.com/83891837/190565928-19acb11c-f06d-401c-ae0d-dcced30f9618.png">

이처럼 HTTP 통신의 특징인(연결 -> 연결 해제) 때문에 효율이 많이 떨어지게 되고, 웹 브라우저 말고 외부 플러그인이 항상 필요하게 되었다. 이런 상황을 극복하고자 2014년 HTML5에 웹소켓을 포함하게 되었다.
웹소켓은 "클라이언트가 접속 요청을 하고 웹 서버가 응답한 후 연결을 끊는것이 아닌 Connection을 그대로 유지하고 클라이언트의 요청 없이도 데이터를 전송할 수 있는 프로토콜"이다. 프로토콜의 요청은 [ws://~]로 시작한다.

웹소켓은 HTTP 환경에서 전이중 통신(Full Duplex, 2-way communication)을 지원하기 위한 프로토콜이다. HTTP 프로토콜에서 HandShaking을 완료한 후, HTTP로 동작하지만, HTTP와는 다른 방식으로 통신한다.

- WebSocket이 기존의 TCP Socket과 다른 점은 최초 접속이 일반 HTTP Request를 통해 HandShaking 과정을 통해 이루어진다는 점이다.
- HTTP Request를 그대로 사용하기 때문에 기존의 80, 443 포트로 접속을 하므로 추가 방화벽을 열지 않고도 양방향 통신이 가능하고, HTTP 규격인 CORS 적용이나 인증 등 과정을 기존과 동일하게 가져갈 수 있는것이 장점이다.

웹 소켓은 서비스를 동적으로 만들어 주지만, Ajax Streaming Long polling이 더 효과적인 경우도 있다. 변경사항의 빈도가 자주 일어나지 않고, 데이터의 크기가 작은 경우가 이에 해당한다. 즉, "실시간 성을 보장해야 하고, 변경사항의 빈도가 잦고, 짧은 대기 시간, 고주파수, 대용량의 조합" 인경우에 WebSocket이 좋은 해결책이 될 수 있다.

뉴스나 메일, SNS 피드는 동적으로 업데이트 하는 것은 맞지만 몇 분마다 업데이트 하는 것이 좋다. 반면 협업, 게임, 금융 앱은 훨씬 더 실시간에 근접해야한다.

* Browser 별 지원 현황
<img width="797" alt="스크린샷 2022-09-16 오후 2 56 58" src="https://user-images.githubusercontent.com/83891837/190566805-3f683a21-76f4-4f3f-b706-ba12e807ebb5.png">


