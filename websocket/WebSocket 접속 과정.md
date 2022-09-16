# WebSocket 접속 과정

<img width="728" alt="스크린샷 2022-09-16 오후 3 17 00" src="https://user-images.githubusercontent.com/83891837/190569702-04488f36-1f96-45d3-8030-b496ded4156f.png">
웹소켓을 이용하여 서버와 클라이언트가 통신을 하려면 먼저 웹소켓 접속 과정을 거쳐야 한다. "웹소켓 접속 과정은 TCP/IP 접속 그리고 웹소켓 열기 HandShake 과정으로 나눌수 있다."
웹소켓도 TCP/IP 위에서 동작하므로, 서버와 클라이언트는 웹소켓을 사용하기 전에 서로 TCP/IP 접속이 돼있어야 한다. TCP/IP 접속이 완료된 후 서버와 클라이언트는 웹소켓 열기 HandShake 과정을 시작한다.

* 웹소켓 열기 HandShake
웹소켓 열기 HandShake는 "클라이언트가 먼저 HandShake 요청을 보내고 이에 대한 응답을 서버가 클라이언트로 보내는 구조"이다. 서버와 클라이언트는 HTTP 1.1 프로토콜을 사용하여 요청와 응답을 보낸다.

- HandShake Request
<img width="811" alt="스크린샷 2022-09-16 오후 3 22 09" src="https://user-images.githubusercontent.com/83891837/190570427-6994e463-115b-4081-aaa1-023a0891a27f.png">
- HandShake Response
<img width="808" alt="스크린샷 2022-09-16 오후 3 22 29" src="https://user-images.githubusercontent.com/83891837/190570465-2d4fb09e-c478-419f-941f-f9a1096ecadb.png">

위 테이블에 명시된 헤더 중 필수는 반드시 사용해야 하며, 특정한 값이 명시된 헤더는 그 값만 사용해야 한다.
