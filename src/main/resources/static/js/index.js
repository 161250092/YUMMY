//设置全局表单提交格式
// Vue.http.options.emulateJSON = true;

//Vue实例
new Vue({
    el: '#app',
    data() {
        return {
            account: '',

            activeIndex: '1'
        }
    },
    methods: {

        getName(){
            this.account = sessionStorage.getItem("account");
        }
    },
    //声明周期钩子函数-->在data和methods渲染结束后执行
    created() {
        this.getName(); //获取用户登录名
    }
});