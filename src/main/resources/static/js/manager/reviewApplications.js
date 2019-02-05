Vue.http.options.emulateJSON = true;

// Vue实例
new Vue({
    el: '#app',
    data() {
        return {
            activeIndex:'1',
            applications:[],
        };
    },
    methods: {
        getApplications(){
            this.$http.post('/manager/getApplications', {
                account: sessionStorage.getItem("account"),
            }).then(result => {
                console.log(result);
                   this.applications = result.body;
                });

        },


        passApplications(applicationId){
            this.$confirm('确定通过', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
                center: true
            }).then(() => {
                this.$http.post('/manager/passApplication', {
                        applicationId: applicationId,
                    }
                ).then(result => {
                    if (result.body.success) {
                        this.$message({
                            type: 'success',
                            message: result.body.message,
                            duration: 6000
                        });
                    }
                });

            });
            this.getApplications();
        },


        refuseApplications(applicationId){
            this.$confirm('确定驳回', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
                center: true
            }).then(() => {
                this.$http.post('/manager/refuseApplication', {
                        applicationId: applicationId,
                    }
                ).then(result => {
                    if (result.body.success) {
                        this.$message({
                            type: 'success',
                            message: result.body.message,
                            duration: 6000
                        });
                    }
                });

            });
            this.getApplications();

        }
    },


    created(){
        this.getApplications();





    }


});

