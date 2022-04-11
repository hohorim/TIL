##*vue.js 시작하기 -1*

>props, events 속성

* **뷰 생성** 
  * 전역변수 선언방법
    ```
        Vue.component('app-content', {
            //Vue.component('컴포넌트 이름', 컴포넌트 내용')
            template: '<div>content</div>'
        });
    ```

  * 지역변수 선언방법

    ```
            new Vue({
                el: "#app",
                components: {
                    // '컴포넌트 이름': 컴포넌트 내용
                    'app-footer': {
                        template: '<footer>footer</footer>'
                    }
                }
            });
    ```

    <br>

* **props 및 events 란?**
  
    <img src="/vue/props&event.png" width="300">

    -props: 부모 컴포넌트에서 자식 컴포넌트로 데이터 전달
    -events: 자식에서 부모로 데이터 참조
    <br>

    * props
    ```
    <body>

        <div id="app">
            <app-header v-bind:propsdata= "message"></app-header>
            <!-- v-bind:프롭스 속성 이름= "상위 컴포넌트의 데이터 이름"-->
        </div>

        <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>

        <script>

            var appHeader = {
                template: "<h1>{{propsdata}}</h1>",
                props: ['propsdata']
            }

            new Vue({
                el: "#app",
                components :{
                    'app-header': appHeader
                },
                data: {
                    message: 'hi'   
                }
            })
        </script>
        <!--상위: root-->
        <!--하위: app-header -->
        
    </body>
    ```
    **v-bind:프롭스 속성 이름= "상위 컴포넌트의 데이터 이름"**
    생성된 뷰는 root 인 최상위 컴포넌트인 부모 컴포넌트,
    appHeader 는 자식 컴포넌트

    실행결과 root에서 `message` 값을 변경하면 `appHeader`의 값도 변경된다.
    즉, 부모 컴포넌트의 데이터를 변경하니 자식 컴포넌트 데이터도 변경됨.
    <br>

    <span style="color:blue">
    <details>
        <summary>결과출력확인</summary>
        <div markdown="1">
            <img src="/vue/props1.png" width="500">
            <img src="/vue/props2.png" width="500">
        </div>
    </details>
    </span>
    <br>

    * events

    ```
    <body>
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>

    <div id="app">
        <app-header v-on:pass="logText"></app-header>
        <!--v-on:하위 컴포넌트에서 발생한 이벤트이름="상위 컴포넌트의 메서드 이름"-->
    </div>
    <script>
        var appHeader = {
            template: '<button v-on:click="passEvent">click me</button>',
            methods:{
                passEvent: function(){
                    this.$emit('pass');
                }
            }
        }

        new Vue({
            el:"#app",
            components: {
                'app-header': appHeader

                },
            methods:{
                logText: function(){
                    console.log('Hi');
                }
            }
        });
    </script>

    </body>
    ```
    **v-on:하위 컴포넌트에서 발생한 이벤트이름="상위 컴포넌트의 메서드 이름"**

    `pass` 이벤트가 하위(`<app-header></app-header>`)에서 상위(`root`)로 올라왔을 때, 컴포넌트 태그에서 받아서 `logText`메소드 실행

    <br>

    <span style="color:blue">
    <details>
        <summary>결과출력확인</summary>
        <div markdown="1">
            <img src="/vue/events.png" width="500">
        </div>
    </details>
    </span>
    <br>
