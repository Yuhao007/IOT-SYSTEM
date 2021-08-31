function getUrlPara(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return (r[2]);
    return null;
}

var vm = new Vue({
    el: '#rrapp',
    data: {
        show: false,
        warn: {},
        minwarn: {},
        name: '',
        now: '',
        max: '',
        min: '',
        house: '',
        type: getUrlPara('type'),
        list: []
    },
    created: function () {
        this.query();
    }, methods: {
        query: function () {
            this.$nextTick(function () {
                init();
            })
        }
    }
});

function init() {
    var type = getUrlPara('type');
    var dw = fmt(type).dw;
    var t = fmt(type).type;
    vm.name = t;
    var myChart = echarts.init(document.getElementById('main'));
    var vs = [];
    var names = []
    $.getJSON("../data/list2?limit=10&offset=0&type=" + getUrlPara("type")  , function (r) {
        vm.now = r.list[0].v;
        r.list = r.list.reverse();
        for (var i = 0; i < r.list.length; i++) {
            names.push(r.list[i].time.substring(11, r.list[i].time.length))
            vs.push(r.list[i].v)
        }


        var option = { title: {
                text: 'Realtime' + t + 'Data Monitoring ',
                subtext: ''
            },
            toolbox: {
                show: true,
                feature: {
                    mark: {show: true, title: 'Remark'},
                    magicType: {show: true, type: ['line', 'bar', 'scatter'],
                        title:{line: 'line', bar: 'bar'}},
                    restore: {show: true, title: 'restore'},
                    saveAsImage: {show: true, title: 'save'}
                }
            },
            tooltip: {
                trigger: 'axis',
                formatter: "{b}  <br/>{c}"
            },
            legend: {
                data: [t]
            },
            xAxis: [
                {
                    type: 'category',
                    data: names
                }
            ],
            yAxis: [
                {
                    type: 'value', axisLabel: {
                        formatter: '{value} '
                    }
                }
            ],
            series: [
                {
                    "name": 'Realtime' + t + 'Data Monitoring',
                    "type": "line",
                    "data": vs
                }
            ]
        };


        // echarts load
        myChart.setOption(option);
        window.onresize = myChart.resize;

        $.getJSON("../data/now", function (r) {
            vm.warn = r.warn;
            vm.minwarn = r.minwarn;

            if (vm.warn[vm.type]) {
                document.getElementById("mp3").play();
            }

            if (vm.minwarn[vm.type]) {
                document.getElementById("mp3").play();
            }
        })
        $.getJSON("../sys/config/info/"+vm.type,function (r) {
            vm.max = r.config.value;
            vm.min = r.config.min;
        })
    })
    //SetInterval has three requests to call the background data interface.
    // Timeout is how often it is called in milliseconds
    // // Rerender is automatically updated every time the data is retrieved
    setInterval(function () {

        $.getJSON("../data/now", function (r) {
            vm.warn = r.warn;
            vm.minwarn = r.minwarn;

            if (vm.warn[vm.type]) {
                document.getElementById("mp3").play();
            }

            if (vm.minwarn[vm.type]) {
                document.getElementById("mp3").play();
            }

        })

        $.getJSON("../sys/config/info/"+vm.type,function (r) {
            console.log(r)
            vm.max = r.config.value;
            vm.min = r.config.min;
        })

        // gets the data every two seconds and re-renders an Echarts
        $.getJSON("../data/last?type=" + getUrlPara("type"), function (r) {
            vm.now = r.data.v;
            r.data.time = r.data.time.substring(11, r.data.time.length);
            if (r.data.time == names[names.length - 1]) {
                return;
            }
            names.shift();
            vs.shift();
            names.push(r.data.time);
            vs.push(r.data.v)


            var option = {
                title: {
                    text: 'Realtime' + t + 'Data Monitoring',
                    subtext: ''
                },
                toolbox: {
                    show: true,
                    feature: {
                        mark: {show: true, title: 'Remark'},
                        magicType: {show: true, type: ['line', 'bar'],
                            title:{line: 'line', bar: 'bar'}},
                        restore: {show: true, title: 'restore'},
                        saveAsImage: {show: true, title: 'save'}
                    }
                },
                tooltip: {
                    trigger: 'axis',
                    formatter: "Present Data : <br/>{b}  : {c}" + dw
                },
                legend: {
                    data: [t]
                },
                xAxis: [
                    {
                        type: 'category',
                        data: names
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    }
                ],
                series: [
                    {
                        "name": "Real-time data Monitoring",
                        "type": "line",
                        "data": vs
                    }
                ]
            };


            // Load the data for the object echarts
            myChart.setOption(option);


        })
    }, 2 * 1000)

}