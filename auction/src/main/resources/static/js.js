$(window).scroll( function() {
    if($(window).scrollTop() >= 100)
    {
        $('.navbar').addClass("scroll-nav");
    }
    else
    {
        $('.navbar').removeClass("scroll-nav");
    }

});
 