//设置全局表单提交格式
Vue.http.options.emulateJSON = true;

var vm = new Vue({

    el:"#app",
    data() {
        return {
            //已有的餐品
            dishes: [{
               dishId:'',
               startTime:'',
               endTime:'',
               type:'',
               name:'',
               price:'',
               quantity:'',
               description:''
            }],

            //编辑表
            editor: {
                startTime:'',
                endTime:'',
                type:'',
                name:'',
                price:'',
                quantity:'',
                description:''
            },

            //更新表
            updateDish:{
                dishId:'',
                startTime:'',
                endTime:'',
                type:'',
                name:'',
                price:'',
                quantity:'',
                description:''
            },


            //新建dialog
            showCreate: false,

            //编辑dialog
            showEditor: false,

            //分页选项
            pageConf: {
                //设置一些初始值(会被覆盖)
                pageCode: 1, //当前页
                pageSize: 6, //每页显示的记录数
                totalPage: 12, //总记录数
                pageOption: [6, 10, 20], //分页选项
            },

            loading: {},

            dialogVisible: false,

            activeIndex: '1', //默认激活
        }

    },


    methods: {
        loadings() {
            this.loading = this.$loading({
                lock: true,
                text: '拼命加载中',
                spinner: 'el-icon-loading',
            });
            setTimeout(() => {
                this.loading.close();
            }, 2000);
        },

        //分页获取菜品信息
        search(pageCode, pageSize) {
            this.loadings();
            this.$http.post('/dishes/findDishedByConPage',{
                idCode:sessionStorage.getItem("account"),
                pageSize:pageSize,
                pageCode:pageCode
            }).then(result => {
                console.log(result);
                this.dishes = result.body.rows,
                this.pageConf.totalPage = result.body.total;
                this.loading.close(); //数据更新成功就手动关闭动画
            });

        },

        reloadList() {
            console.log("totalPage:" + this.pageConf.totalPage + ",pageSize:" + this.pageConf.pageSize + ",:pageCode:" + this.pageConf.pageCode);
            this.search(this.pageConf.pageCode, this.pageConf.pageSize);
        },



        //pageSize改变时触发的函数
        handleSizeChange(val) {
            this.search(this.pageConf.pageCode, val);
        },
        //当前页改变时触发的函数
        handleCurrentChange(val) {
            this.pageConf.pageCode = val; //为了保证刷新列表后页面还是在当前页，而不是跳转到第一页
            this.search(val, this.pageConf.pageSize);
        },

        //删
        deleteDish(dishId) {
            this.$confirm('你确定删除此菜品信息？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
                center: true
            }).then(() => {
                //调用删除的接口(这里必须将数据转换成JSON格式，不然接收不到值，并且后端要用@RequestBody注解标识)
                this.$http.post('/dishes/delete',{
                    dishId:dishId
                }).then(result => {
                    if (result.body.success) {
                        //删除成功
                        this.$message({
                            type: 'success',
                            message: result.body.message,
                            duration: 6000
                        });
                        //刷新列表
                        //为什么要判断并赋值？
                        //答：即使调用reloadList()刷新列表，但是对于删除，在reloadList()中获取到的totalPage总记录和pageCode当前页都是未删除之前的记录，当遇到删除此页的最后一个记录时，页码会自动跳到上一页，但是table中的数据显示"暂无记录"
                        //   所以要判断，如果是删除此页的最后一条记录，删除后自动跳转到前一页，数据也是前一页的数据
                        if ((this.pageConf.totalPage - 1) / this.pageConf.pageSize === (this.pageConf.pageCode - 1)) {
                            this.pageConf.pageCode = this.pageConf.pageCode - 1;
                        }
                        this.reloadList();
                    } else {
                        //删除失败
                        this.$message({
                            type: 'warning',
                            message: result.body.message,
                            duration: 6000
                        });
                        //刷新列表
                        this.reloadList();
                    }
                });
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除',
                    duration: 6000
                });
            });
        },


        openCreate(){
            this.showCreate = true;
            this.editor = {};
        },

        //新建
        save(editor) {
            this.$refs[editor].validate((valid) => {
                if (valid) {
                    //关闭dialog
                    this.showSave = false;
                    //调用保存的接口
                    this.$http.post('/dishes/create', JSON.stringify(this.editor)).then(result => {
                        if (result.body.success) {
                            //保存成功
                            this.$message({
                                type: 'success',
                                message: result.body.message,
                                duration: 6000
                            });
                            //刷新表格
                            this.reloadList();
                            this.editor = {};
                            this.$refs.editor.resetFields();
                        } else {
                            //保存失败
                            this.$emit(
                                'save',
                                this.$message({
                                    type: 'warning',
                                    message: result.body.message,
                                    duration: 6000
                                }),
                            );
                            //刷新表格
                            this.reloadList();
                            this.editor = {};
                            this.$refs.editor.resetFields();
                        }
                    });
                } else {
                    this.$emit(
                        'save',
                        this.$message({
                            message: '输入信息有误！',
                            type: 'warning',
                            duration: 6000
                        }),
                    );
                    return false;
                }
            });
        },


        handleEdit(id){
            this.showEditor = true;
            this.updateDish.dishId = id;
        },

        //修改
        edit() {
           this.showEditor = false;
            //调用更新数据的接口
            this.$http.post('/dishes/update',JSON.stringify(this.updateDish)).then(result => {
                if (result.body.success) {
                    //更新成功
                    this.$message({
                        type: 'success',
                        message: result.body.message,
                        duration: 6000
                    });
                    //刷新列表
                    this.reloadList();
                    this.$refs.editor.resetFields();
                } else {
                    //更新失败
                    this.$message({
                        type: 'warning',
                        message: result.body.message,
                        duration: 6000
                    });
                    //刷新列表
                    this.reloadList();
                    this.$refs.editor.resetFields();
                }
            })
        }


    },

    created(){
        // this.findAll();
        this.search(this.pageConf.pageCode, this.pageConf.pageSize);
        this.loadings(); //加载动画
    }


});