//vue
var vm = new Vue({

    el: '#rrapp',
    //data
    data: {
        data1:'',name1:'',
        data2:'',name2:'',
        data3:'',name3:'',
        data4:'',name4:'',
        data5:'',name5:'',
        data6:'',name6:'',
        warn:{},minwarn:{}
    },
    created: function () {

        this.query()
        setInterval(function () {
            vm.query()
        },1000)
    },
    //
    methods: {
        query: function (s) {

//data come from service interface
            $.getJSON("../data/now", function (r) {
                vm.warn = r.warn;
                vm.minwarn = r.minwarn;

                if (vm.warn[vm.type]) {
                    //service provide API
                    document.getElementById("mp3").play();
                }

                if (vm.minwarn[vm.type]) {
                    document.getElementById("mp3").play();
                }
            })

            $.getJSON("../data/last?type=1" ,function (r) {
                vm.data1  = r.data.v;
                vm.name1 = fmt(1).type;

            })
            $.getJSON("../data/last?type=2" ,function (r) {
                var v = r.data.v;
                vm.data2  = r.data.v;
                vm.name2 = fmt(2).type;
            })
            $.getJSON("../data/last?type=3" ,function (r) {
                var v = r.data.v;
                var name = fmt(3);
                vm.data3 = r.data.v;
                vm.name3 = fmt(3).type;
            })
            $.getJSON("../data/last?type=4" ,function (r) {
                var v = r.data.v;
                var name = fmt(4);
                vm.data4  = r.data.v;
                vm.name4 = fmt(4).type;
            })
            $.getJSON("../data/last?type=5" ,function (r) {
                var v = r.data.v;
                var name = fmt(5);
                vm.data5 = r.data.v;
                vm.name5 = fmt(5).type;
            })
            $.getJSON("../data/last?type=6" ,function (r) {
                var v = r.data.v;
                var name = fmt(6).type;
                vm.data6  = r.data.v;
                vm.name6 = fmt(6).type;
            })

        },


    }
});