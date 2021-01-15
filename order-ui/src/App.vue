<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
//import Cookies from "js-cookie";
let socket = null;
export default {
  name: "App",
  created() {
    //this.timer = setInterval(this.get, 5000);
    // this.timer = setInterval(this.websocketonmessage, 5000);
    //this.initWebSocket();
	 let username = localStorage.getItem("user");
    if (username != "" && username != null && username != undefined) {
      this.userName = username;
     // this.initWebSocket();
    }
  },
  data() {
    return {
      userName: null,
    };
  },
  methods: {
    get() {
      //右上角
      // this.$notify.error({
      //   title: "错误",
      //   message: "这是一条错误的提示消息",
      // });
      //中间
      //this.msgError("这是一条错误的提示消息");
    },
    initWebSocket() {
      this.websock = null;
      //判断当前浏览器是否支持WebSocket
      if ("WebSocket" in window) {
        //初始化weosocket(必须)
        this.websock = new WebSocket(
          "ws://192.168.1.107:80/websocket/" + this.userName
        ); //新建一个webstock对象
        this.websock.onopen = this.websocketonopen;
        this.websock.onmessage = this.websocketonmessage;
        this.websock.onerror = this.websocketonerror;
        this.websock.onclose = this.websocketclose;
      } else {
        alert("当前浏览器 Not support websocket");
      }
    },
    websocketonopen() {
      console.log("---连接建立成功---");
    },
    websocketonerror() {
      //连接建立失败重连
      this.initWebSocket();
    },
    websocketonmessage(e) {
      //数据接收
      this.redata = e.data;
      console.log("-----redata-----");
      console.log(this.redata);
      this.msgError(this.redata);
    },
    websocketsend(Data) {
      //数据发送
      this.websock.send(Data);
    },
    websocketclose(e) {
      //关闭
      console.log("断开连接", e);
    },
  },
};
</script>
