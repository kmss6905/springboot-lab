
### "어떻게 무엇을 테스트할 것인가"에 대한 고민거리 
---
### 목표 :rocket:
---
* [x] 테스트컨테이너를 활용하여 local db에 종속적인 테스트가 아닌 실제 prod db과 비슷한 환경을 구축하여 테스트를 진행할 수 있다.
* [X] 구현테스트가 아닌 비지니스 로직테스트를 한다.
* [ ] 테스트 할 수 없는 것들을 구분하고 어떻게 테스트를 해야할 지 머리속으로 그릴 수 있다.
* [X] 테스트하기에 쉬운 코드와 그렇지 못한 코드를 구분할 수 있고, 왜 그런지 설명할 수 있다.
* [X] 테스트 커버리지 99 를 달성할 수 있다. :rocket:

<img width="952" alt="스크린샷 2022-05-18 오전 1 01 54" src="https://user-images.githubusercontent.com/43781484/168856758-9f42679a-a72c-43ac-ae8a-44ee1afc6148.png">

### How to Test
```shell
./gradlew test
```

### How to See Test Coverage Report
```shell
$ ./gradlew build

$ cd $buildDir/jacocoHtml & cat index.html
```