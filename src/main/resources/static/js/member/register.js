Vue.http.options.emulateJSON = true;

// Vue实例
new Vue({
    el: '#app',
    data() {
        return {

            // sendVerificationCodeButton:{
            //     buttonName:"发送验证码",
            //     isDisabled:false,
            //     time:20
            // },

            content: '发送验证码',
            totalTime: 30,
            isAbandon: false,

            verificationCode:'',

            repeatPassword:'',

            register: {
                account:'',
                password: '',
                nickName:'',
                phone:'',
                mail:''
            },

        }
    },
    methods: {

        sendVerificationCode(){
            if(this.register.mail===''){
                this.$emit(
                    'submit-form',
                    this.$message({
                        message: '邮箱为空',
                        type: 'warning',
                        duration: 6000
                    }),
                );
            }
            else{
                //发送验证码
                this.$http.post('/member/sendVerificationCode', {
                    mail:this.register.mail
                }).then(result => {
                    console.log(result);
                    if (result.body.success) {
                        console.log(result);
                    } else {
                        // 弹出错误信息框
                        this.$emit(
                            'submit-form',
                            this.$message({
                                message: result.body.message,
                                type: 'warning',
                                duration: 6000
                            }),
                        );
                    }
                });
            }

        },

        countDown () {
            if (this.isAbandon) return ; //改动的是这两行代码

            this.sendVerificationCode();
            this.isAbandon = true;
            this.content = this.totalTime + 's后重新发送';
            let clock = window.setInterval(() => {
                this.totalTime--;
                this.content = this.totalTime + 's后重新发送';
                if (this.totalTime < 0) {
                    window.clearInterval(clock);
                    this.content = '重新发送验证码';
                    this.totalTime = 30;
                    this.isAbandon = false  //这里重新开启
                }
            },1000)
        },

        //注册会员(验证验证码并提交注册表单)
        registerMember(){
            if(this.verificationCode===''){
                this.$emit(
                    'submit-form',
                    this.$message({
                        message: '验证码为空',
                        type: 'warning',
                        duration: 6000
                    }),
                );
            }
            else{
                //发送验证码
                let _this = this;
                this.$http.post('/member/validate', {
                    verificationCode:this.verificationCode
                }).then(result => {
                    console.log(result);
                    if (result.body.success) {
                        //验证码正确后再提交注册表单
                        _this.submitForm(_this.register);
                    } else {
                        // 弹出错误信息框
                        this.$emit(
                            'submit-form',
                            this.$message({
                                message: result.body.message,
                                type: 'warning',
                                duration: 6000
                            }),
                        );
                    }
                });
            }

        },


        submitForm(register) {
            if(this.repeatPassword !== this.register.password){
                // 弹出错误信息框
                this.$emit(
                    'submit-form',
                    this.$message({
                        message: '两次输入的密码不相同',
                        type: 'warning',
                        duration: 6000
                    }),
                );
            }
            else{
                //提交表单
                this.$http.post('/member/register', JSON.stringify(this.register)
                ).then(result => {
                    console.log(result);
                    if (result.body.success) {
                        sessionStorage.setItem("account",this.register.account);
                        window.location.href = "/memberMainPage";
                    } else {
                        // 弹出错误信息框
                        this.$emit(
                            'submit-form',
                            this.$message({
                                message: result.body.message,
                                type: 'warning',
                                duration: 6000
                            }),
                            this.register.account=''
                        );


                    }
                });
            }
        },



    }
});
