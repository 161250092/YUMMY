new Vue({
    el: '#app',
    data() {
        return {
            checked: false,
            repeatPassword:'',

            register: {
                phone:'',
                bankAccount:'',
                restaurantName:'',
                password:'',
            },

            showIdCode:false,
            idCode:''
        }
    },
    methods: {


        submitForm() {
            if(this.register.password !== this.repeatPassword){
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
                console.log(this.register);

                this.$http.post('/merchantRegister', JSON.stringify(this.register)
                ).then(result => {
                    console.log(result);
                    //注册成功，弹出ID CODE
                    if (result.body.success) {
                        this.idCode=result.body.message;
                        sessionStorage.setItem("idCode",result.body.message);
                        this.showIdCode=true;
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
                        // 清空表单状态
                        this.$refs[register].resetFields();
                    }
                });
            }
        },

        confirm(){
            window.location.href = "/merchantEditPage"
        }


    }
});
