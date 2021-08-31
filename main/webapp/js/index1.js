//Generated menu
var menuItem = Vue.extend({
    name: 'menu-item',
    props: {item: {}},
    template: [
        '<li class="     ">',
        '<a class="dropdown-toggle" v-if="item.type === 0" href="javascript:;">',
        '<i class="icon-double-angle-right"></i>',
        '<span class="menu-text">{{item.name}}</span>',
        '<i class="fa fa-angle-left pull-right"></i>',
        '</a>',
        '<ul  v-if="item.type === 0" class="submenu">',
        '<menu-item :item="item" v-for="item in item.list"></menu-item>',
        '</ul>',
        '<a v-if="item.type === 1" :href="\'#\'+item.url"><i  class="icon-double-angle-right"></i><i v-else class="fa fa-circle-o"></i> {{item.name}}</a>',
        '</li>'
    ].join('')
});
 function height(hei) {
        //document.body.offsetHeight
     var min = document.documentElement.clientHeight - 180;
     if(min > hei){
         hei = min;
     }

    $("body").find('iframe').each(function () {
        $(this).height(hei +20);
    });
    var $content = $('.content');
    $content.height(hei  +20);

}


//Register menu component
Vue.component('menuItem', menuItem);

var vm = new Vue({
    el: '#rrapp',
    data: {
        user: {},
        menuList: {},
        main: "sys/main.html",
        password: '',
        msg: '',
        newPassword: '',
        navTitle: "Dashboard"
    },
    methods: {
        getMenuList: function (event) {
            $.getJSON("sys/menu/user?_" + $.now(), function (r) {
                vm.menuList = r.menuList;
            });
        },
        getUser: function () {
            $.getJSON("sys/user/info?_" + $.now(), function (r) {
                vm.user = r.user;
            });
        },
        showInfo(){
            $("#infoModal").modal('show')
        },
        showPwd(){
            vm.password = ''
            vm.newPassword = ''
            $("#changePasswordModal").modal('show')
        },
        //  The functions are in Vue instance Methods
        updatePassword: function () {

            //This VM is an instance of Vue that represents the current page
            var data = "password=" + vm.password + "&newPassword=" + vm.newPassword;
            // communication protocol asynchronous communication that allows  to communicate with the back end
            $.ajax({
                type: "POST", //the method of communication
                url: "sys/user/password", //This is the back-end address which is the address in the controller
                                           // Find sys in controller

                data: data,// the parameter passed from front end to back end
                dataType: "json", //How does this transmit parameters
                //this is called a callback function which is
                // what does the back end do when it's done and it returns something
                success: function (result) {
                    //
                    // console.log(result);
                    //Specific business logic
                    if (result.code == 0) {
                        layer.alert('Modify successful', function (index) {
                            location.reload();
                        });
                    } else {
                        layer.msg(result.msg);
                    }
                }
            });

        }, uploadInfo: function () {
            $.ajax({
                type: "POST",
                url: "sys/user/updateInfo",
                data: JSON.stringify(vm.user),
                contentType: "application/json",
                dataType: "json",
                success: function (result) {
                    if (result.code == 0) {
                        layer.msg('Modify successful' );
                        $("#infoModal").modal('hide')
                    } else {
                        layer.msg(result.msg);
                    }
                }
            });
        }
    },
    created: function () {
        this.getMenuList();
        this.getUser();

        $.getJSON("../data/warn",function (r) {
            if(r.data){
                vm.msg = r.data
            }else{
                vm.msg = '';
            }
        })
        setInterval(function () {
            $.getJSON("../data/warn",function (r) {
                if(r.data){
                    vm.msg = r.data
                }else{
                    vm.msg = '';
                }
            })
        },2000)
    },
    updated: function () {
        //router
        var router = new Router();
        routerList(router, vm.menuList);
        router.start();
    }
});


function routerList(router, menuList) {
    for (var key in menuList) {
        var menu = menuList[key];
        if (menu.type == 0) {
            routerList(router, menu.list);
        } else if (menu.type == 1) {
            router.add('#' + menu.url, function () {
                var url = window.location.hash;

                //Replace the URL of iframe
                vm.main = url.replace('#', '');

                //Navigation menu expansion
                $(".nav-list li").removeClass("active");
                $("a[href='" + url + "']").parents("li").addClass("active");
	 $("#sidebar").removeClass("display");	
                $("body").removeClass("sidebar-open");
                vm.navTitle = $("a[href='" + url + "']").text();
            });
        }
    }
}

