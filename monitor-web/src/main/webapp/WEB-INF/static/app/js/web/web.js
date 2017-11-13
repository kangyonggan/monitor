$(function () {
    $(".sim-page").simPage();

    $(window).scroll(function () {
        if ($(window).scrollTop() >= 300) { //向下滚动像素大于这个值时，即出现浮窗~
            $('.scroll-top').fadeIn(1000);
        } else {
            $('.scroll-top').fadeOut(1000);
        }
    });
});

