
var vm = new Vue({
    el: '#rrapp',
    data: {
        q: {
            v: ''
        },
        showAdd: false,
        showInfo: false,
        showList: true,
        title: null,
        data: {},
    },
    created: function () {

    },
    methods: {
        query: function (s) {
            $.get("../data/control/"+s , function (r) {
                 layer.msg("发送指令成功")
            });
        },


    }
});