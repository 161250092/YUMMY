//设置全局表单提交格式
// Vue.http.options.emulateJSON = true;

//Vue实例
new Vue({
    el: '#app',
    data() {
        return {
            account: '',

            activeIndex: '1',

            url:"/image/public/merchant.jpg",
            card:[]
        }
    },
    methods: {

        getName(){
            this.account = sessionStorage.getItem("account");
        }
    },
    //声明周期钩子函数-->在data和methods渲染结束后执行
    created() {
        this.url="/image/public/merchant.jpg";
        this.card = [
            {url:"/image/public/noddle.jpg",id:1},
            {url:"/image/public/merchant.jpg",id:2},
            {url:"/image/public/sweet.png",id:3}];
    }

});