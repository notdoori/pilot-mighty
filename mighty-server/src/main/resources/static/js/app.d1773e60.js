(function(t){function e(e){for(var a,s,r=e[0],c=e[1],u=e[2],d=0,m=[];d<r.length;d++)s=r[d],Object.prototype.hasOwnProperty.call(o,s)&&o[s]&&m.push(o[s][0]),o[s]=0;for(a in c)Object.prototype.hasOwnProperty.call(c,a)&&(t[a]=c[a]);l&&l(e);while(m.length)m.shift()();return i.push.apply(i,u||[]),n()}function n(){for(var t,e=0;e<i.length;e++){for(var n=i[e],a=!0,r=1;r<n.length;r++){var c=n[r];0!==o[c]&&(a=!1)}a&&(i.splice(e--,1),t=s(s.s=n[0]))}return t}var a={},o={app:0},i=[];function s(e){if(a[e])return a[e].exports;var n=a[e]={i:e,l:!1,exports:{}};return t[e].call(n.exports,n,n.exports,s),n.l=!0,n.exports}s.m=t,s.c=a,s.d=function(t,e,n){s.o(t,e)||Object.defineProperty(t,e,{enumerable:!0,get:n})},s.r=function(t){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(t,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(t,"__esModule",{value:!0})},s.t=function(t,e){if(1&e&&(t=s(t)),8&e)return t;if(4&e&&"object"===typeof t&&t&&t.__esModule)return t;var n=Object.create(null);if(s.r(n),Object.defineProperty(n,"default",{enumerable:!0,value:t}),2&e&&"string"!=typeof t)for(var a in t)s.d(n,a,function(e){return t[e]}.bind(null,a));return n},s.n=function(t){var e=t&&t.__esModule?function(){return t["default"]}:function(){return t};return s.d(e,"a",e),e},s.o=function(t,e){return Object.prototype.hasOwnProperty.call(t,e)},s.p="/";var r=window["webpackJsonp"]=window["webpackJsonp"]||[],c=r.push.bind(r);r.push=e,r=r.slice();for(var u=0;u<r.length;u++)e(r[u]);var l=c;i.push([0,"chunk-vendors"]),n()})({0:function(t,e,n){t.exports=n("56d7")},1029:function(t,e,n){"use strict";n("d94b")},"56d7":function(t,e,n){"use strict";n.r(e);n("e260"),n("e6cf"),n("cca6"),n("a79d");var a=n("a026"),o=(n("d3b7"),n("bc3a")),i=n.n(o),s={},r=i.a.create(s);r.interceptors.request.use((function(t){return t}),(function(t){return Promise.reject(t)})),r.interceptors.response.use((function(t){return t}),(function(t){return Promise.reject(t)})),Plugin.install=function(t,e){t.axios=r,window.axios=r,Object.defineProperties(t.prototype,{axios:{get:function(){return r}},$axios:{get:function(){return r}}})},a["a"].use(Plugin);Plugin;var c=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("v-app",[n("v-main",[!1===t.isLogIn?n("Login"):t._e(),!0===t.isLogIn?n("router-view"):t._e()],1)],1)},u=[],l=n("5530"),d=n("2f62"),m=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"login"},[n("v-app",{attrs:{id:"inspire"}},[n("v-main",[n("v-container",{staticClass:"fill-height",attrs:{fluid:""}},[n("v-row",{attrs:{align:"center",justify:"center"}},[n("v-col",{attrs:{cols:"12",sm:"8",md:"4"}},[n("v-card",{staticClass:"elevation-12"},[n("v-toolbar",{attrs:{color:"primary",dark:"",flat:""}},[n("v-toolbar-title",[t._v("Mighty Login ")]),n("v-spacer")],1),n("v-card-text",[n("v-form",[n("v-text-field",{attrs:{label:"Login",name:"login",clearable:"true","prepend-icon":"mdi-account",type:"text"},model:{value:t.userId,callback:function(e){t.userId=e},expression:"userId"}}),n("v-text-field",{attrs:{label:"Password",name:"password",clearable:"true","prepend-icon":"mdi-lock",type:"password"},model:{value:t.password,callback:function(e){t.password=e},expression:"password"}})],1)],1),n("v-card-actions",[n("v-spacer"),n("v-btn",{attrs:{color:"primary"},on:{click:t.Login}},[t._v("Login")])],1)],1)],1)],1)],1)],1)],1)],1)},v=[],f={name:"Login",components:{},computed:Object(l["a"])({},Object(d["c"])(["isLogIn"])),methods:Object(l["a"])(Object(l["a"])({},Object(d["b"])(["actLogin"])),{},{Login:function(){this.$store.dispatch("actLogin",{userId:this.userId,password:this.password}),this.userId=null,this.password=null}}),data:function(){return{userId:null,password:null}}},p=f,b=n("2877"),g=n("6544"),h=n.n(g),I=n("7496"),_=n("8336"),L=n("b0af"),S=n("99d9"),y=n("62ad"),T=n("a523"),w=n("4bd4"),k=n("f6c4"),O=n("0fd9"),j=n("2fa4"),V=n("8654"),x=n("71d9"),M=n("2a7f"),C=Object(b["a"])(p,m,v,!1,null,null,null),E=C.exports;h()(C,{VApp:I["a"],VBtn:_["a"],VCard:L["a"],VCardActions:S["a"],VCardText:S["b"],VCol:y["a"],VContainer:T["a"],VForm:w["a"],VMain:k["a"],VRow:O["a"],VSpacer:j["a"],VTextField:V["a"],VToolbar:x["a"],VToolbarTitle:M["a"]});var $={computed:Object(l["a"])({},Object(d["c"])(["isLogIn"])),methods:Object(l["a"])({},Object(d["b"])(["actLogout"])),components:{Login:E}},q=$,R=Object(b["a"])(q,c,u,!1,null,null,null),P=R.exports;h()(R,{VApp:I["a"],VMain:k["a"]});var N=n("8c4f"),A=(n("b0c0"),{namespaced:!0,state:{mTab:"Hi"},mutations:{},actions:{actTest:function(){console.log("MainStore: actTest")}}}),F=A;a["a"].use(d["a"]);var U=new d["a"].Store({state:{user:null,accessToken:null,isLogIn:!1},mutations:{LoginSuccess:function(t,e){t.user=e.USERID,t.accessToken=e.ACCESSTOKEN,t.isLogIn=!0},LoginFailure:function(t){t.user=null,t.accessToken=null,t.isLogIn=!1}},actions:{actRouteMove:function(t,e){Qt.currentRoute.name!==e&&(console.log("cur: ",Qt.currentRoute.name,", target: ",e),Qt.push({name:e}))},actLogin:function(t,e){var n=t.dispatch,a=t.commit;i.a.post("http://127.0.0.1:8080/api/users/login",e).then((function(t){var e=t.data.USERID,o=t.data.ACCESSTOKEN;null!==e&&null!==o&&(n("addLocalStorage",{Id:e,Token:o}),a("LoginSuccess",t.data),n("actRouteMove","Main"))})).catch((function(t){console.log(t),alert("아이디와 비밀번호를 확인 하세요."),a("LoginFailure")}))},actLogout:function(t){console.log("Logout");var e=localStorage.getItem("user_id"),n=localStorage.getItem("access_token");i.a.post("http://127.0.0.1:8080/api/users/logout",{userId:e,token:n}).then((function(e){t.dispatch("clearLocalStorage"),t.commit("LoginFailure"),t.dispatch("actRouteMove","Login")})).catch((function(e){t.dispatch("clearLocalStorage"),t.commit("LoginFailure"),t.dispatch("actRouteMove","Login")}))},actLogoutYN:function(t){if(console.log("LogoutYN"),!1!==confirm("로그아웃 하시겠습니까?")){var e=localStorage.getItem("user_id"),n=localStorage.getItem("access_token");i.a.post("http://127.0.0.1:8080/api/users/logout",{userId:e,token:n}).then((function(e){t.dispatch("clearLocalStorage"),t.commit("LoginFailure"),t.dispatch("actRouteMove","Login")})).catch((function(e){t.dispatch("clearLocalStorage"),t.commit("LoginFailure"),t.dispatch("actRouteMove","Login")}))}},actCheckLogin:function(t){var e=localStorage.getItem("access_token"),n=localStorage.getItem("user_id");null!==e?i.a.post("http://127.0.0.1:8080/api/users/check",{userId:n,token:e}).then((function(e){if(200===e.status){console.log("Success 200");var n=e.data.USERID,a=e.data.ACCESSTOKEN;console.log("New Token: ",a),t.dispatch("addLocalStorage",{Id:n,Token:a}),t.commit("LoginSuccess",e.data),t.dispatch("actRouteMove","Main")}})).catch((function(e){e.response&&(console.log(e.response),403===e.response.status?t.dispatch("actLogout"):t.dispatch("actRouteMove","Login"))})):t.commit("LoginFailure")},addSessionStorage:function(t,e){console.log("addSessionStorage info: ",e),sessionStorage.setItem("user_id",e.Id),sessionStorage.setItem("access_token",e.Token)},clearSessionStorage:function(){console.log("clearSessionStorage"),sessionStorage.removeItem("user_id"),sessionStorage.removeItem("access_token")},addLocalStorage:function(t,e){console.log("addlocalStorage info: ",e),localStorage.setItem("user_id",e.Id),localStorage.setItem("access_token",e.Token)},clearLocalStorage:function(){console.log("clearlocalStorage"),localStorage.removeItem("user_id"),localStorage.removeItem("access_token")}},modules:{MainStore:F}}),B=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",[n("v-app-bar",{attrs:{tile:"",color:"transparent",height:"40"}},[n("v-row",{attrs:{align:"stretch",justify:"start"}},[n("SysMenu",{on:{selectSysItem:t.setSysItem}}),n("InquiryMenu",{on:{selectInquiryItem:t.setInquiryItem}})],1)],1),n("div",{staticClass:"d1"},[n("v-tabs",{attrs:{"background-color":"transparent",color:"primary",height:"38"},model:{value:t.activeTab,callback:function(e){t.activeTab=e},expression:"activeTab"}},t._l(t.tabs,(function(e){return n("v-tab",{key:e.id},[t._v(" "+t._s(e.title)+" "),n("v-icon",{attrs:{small:"",left:""},on:{click:function(n){return t.removeTab(e)}}},[t._v("mdi-minus-circle")])],1)})),1),n("v-tabs-items",{model:{value:t.activeTab,callback:function(e){t.activeTab=e},expression:"activeTab"}},t._l(t.tabs,(function(t){return n("v-tab-item",{key:t.id},[n("div",[n("keep-alive",[n("Authority",{directives:[{name:"show",rawName:"v-show",value:0===t.id,expression:"tab.id === 0"}]})],1),n("keep-alive",[n("Groups",{directives:[{name:"show",rawName:"v-show",value:1===t.id,expression:"tab.id === 1"}]})],1),n("keep-alive",[n("users",{directives:[{name:"show",rawName:"v-show",value:2===t.id,expression:"tab.id === 2"}]})],1),n("keep-alive",[n("Inquiry1",{directives:[{name:"show",rawName:"v-show",value:10===t.id,expression:"tab.id === 10"}]})],1),n("keep-alive",[n("Inquiry2",{directives:[{name:"show",rawName:"v-show",value:11===t.id,expression:"tab.id === 11"}]})],1)],1)])})),1)],1)],1)},D=[],G=n("ade3"),Y=(n("4de4"),n("c740"),n("dd1e")),K=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("v-menu",{attrs:{bottom:"","offset-y":""},scopedSlots:t._u([{key:"activator",fn:function(e){var a=e.on,o=e.attrs;return[n("v-btn",t._g(t._b({attrs:{tile:"",depressed:""}},"v-btn",o,!1),a),[t._v(" System ")])]}}])},[n("v-list",t._l(this.items,(function(e){return n("v-list-item",{key:e.menu[0],on:{click:function(n){return t.itemClicked({id:e.menu[0],value:e.menu[1],content:e.menu[2]})}}},[n("v-list-item-title",[t._v(t._s(e.menu[1]))])],1)})),1)],1)},J=[],H={data:function(){return{items:[{menu:[0,"권한관리","Auth"]},{menu:[1,"그룹관리","Groups"]},{menu:[2,"사용자관리","Users"]},{menu:[100,"로그아웃","Logout"]}],activeItems:null}},methods:{itemClicked:function(t){this.$emit("selectSysItem",t)}}},z=H,Q=n("8860"),W=n("da13"),X=n("5d23"),Z=n("e449"),tt=Object(b["a"])(z,K,J,!1,null,null,null),et=tt.exports;h()(tt,{VBtn:_["a"],VList:Q["a"],VListItem:W["a"],VListItemTitle:X["a"],VMenu:Z["a"]});var nt=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("v-menu",{attrs:{bottom:"","offset-y":""},scopedSlots:t._u([{key:"activator",fn:function(e){var a=e.on,o=e.attrs;return[n("v-btn",t._g(t._b({attrs:{tile:"",depressed:""}},"v-btn",o,!1),a),[t._v(" Inquiry ")])]}}])},[n("v-list",t._l(this.items,(function(e){return n("v-list-item",{key:e.menu[0],on:{click:function(n){return t.itemClicked({id:e.menu[0],value:e.menu[1],content:e.menu[2]})}}},[n("v-list-item-title",[t._v(t._s(e.menu[1]))])],1)})),1)],1)},at=[],ot={data:function(){return{items:[{menu:[10,"조회1","Inquiry1"]},{menu:[11,"조회2","Inquiry2"]}],activeItems:"Piano"}},methods:{itemClicked:function(t){this.$emit("selectInquiryItem",t)}}},it=ot,st=Object(b["a"])(it,nt,at,!1,null,null,null),rt=st.exports;h()(st,{VBtn:_["a"],VList:Q["a"],VListItem:W["a"],VListItemTitle:X["a"],VMenu:Z["a"]});var ct=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",[t._v(" This is Users Vue !!! ")])},ut=[],lt={data:function(){return{}},methods:{}},dt=lt,mt=Object(b["a"])(dt,ct,ut,!1,null,null,null),vt=mt.exports,ft=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",[t._v(" This is Groups Vue !!! ")])},pt=[],bt={data:function(){return{}}},gt=bt,ht=Object(b["a"])(gt,ft,pt,!1,null,null,null),It=ht.exports,_t=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",[t._v(" This is Authority Vue !!! ")])},Lt=[],St={data:function(){return{}}},yt=St,Tt=Object(b["a"])(yt,_t,Lt,!1,null,null,null),wt=Tt.exports,kt=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",[t._v(" This is Inquiry1 Vue !!! ")])},Ot=[],jt={data:function(){return{}}},Vt=jt,xt=Object(b["a"])(Vt,kt,Ot,!1,null,null,null),Mt=xt.exports,Ct=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",[t._v(" This is Inquiry2 Vue !!! ")])},Et=[],$t={data:function(){return{}}},qt=$t,Rt=Object(b["a"])(qt,Ct,Et,!1,null,null,null),Pt=Rt.exports,Nt=Object(G["a"])({components:{SysMenu:et,InquiryMenu:rt,users:vt,Groups:It,Authority:wt,Inquiry1:Mt,Inquiry2:Pt,VRuntimeTemplate:Y["a"]},computed:Object(l["a"])(Object(l["a"])({},Object(d["c"])(["isLogIn"])),Object(d["c"])("MainStore",{mTab:function(t){return t.mTab}})),methods:Object(l["a"])(Object(l["a"])(Object(l["a"])({},Object(d["b"])(["actLogout","actLogoutYN"])),Object(d["b"])("MainStore",["actTest"])),{},{setSysItem:function(t){"Logout"===t.content?this.$store.dispatch("actLogoutYN"):this.addTab(t)},setInquiryItem:function(t){this.addTab(t)},activateTab:function(t){this.activeTab=t},removeTab:function(t){this.tabs=this.tabs.filter((function(e){return e.id!==t.id})),console.log("tabs: ",this.tabs),console.log("activeTab: ",this.activeTab)},addTab:function(t){console.log("item: ",t),this.tabs.some((function(e){return e.id===t.id}))?console.log("already exist: ",t.value):this.tabs.push({id:t.id,title:t.value,content:t.content}),this.activeTab=this.tabs.findIndex((function(e){return e.id===t.id})),console.log("activeTab: ",this.activeTab),console.log("tabs: ",this.tabs)}}),data:function(){return{activeTab:0,tabs:[]}},created:function(){this.activeTab=0}},"created",(function(){window.addEventListener("beforeunload",(function(t){t.preventDefault(),t.returnValue=""})),window.addEventListener("unload",(function(t){this.$store.dispatch("actLogout")}))})),At=Nt,Ft=(n("1029"),n("40dc")),Ut=n("132d"),Bt=n("71a3"),Dt=n("c671"),Gt=n("fe57"),Yt=n("aac8"),Kt=Object(b["a"])(At,B,D,!1,null,"132c0af9",null),Jt=Kt.exports;h()(Kt,{VAppBar:Ft["a"],VIcon:Ut["a"],VRow:O["a"],VTab:Bt["a"],VTabItem:Dt["a"],VTabs:Gt["a"],VTabsItems:Yt["a"]}),a["a"].use(N["a"]);var Ht=[{path:"/",name:"Login",component:E},{path:"/main",name:"Main",component:Jt}],zt=new N["a"]({mode:"history",base:"/",routes:Ht}),Qt=zt,Wt=n("f309");a["a"].use(Wt["a"]);var Xt=new Wt["a"]({});a["a"].config.productionTip=!1,new a["a"]({router:Qt,store:U,vuetify:Xt,beforeCreate:function(){this.$store.dispatch("actCheckLogin")},render:function(t){return t(P)}}).$mount("#app")},d94b:function(t,e,n){}});
//# sourceMappingURL=app.d1773e60.js.map