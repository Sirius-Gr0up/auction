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
let timer= document.getElementsByClassName("card-timer");

for (let i = 0 ; i<timer.length ;  i++){

    let time = timer[i].textContent;
    let countDownDate = new Date(time).getTime();

// Update the count down every 1 second
    let x = setInterval(function() {

        // Get today's date and time
        let now = new Date().getTime();

        // Find the distance between now and the count down date
        let distance = countDownDate - now;
if(distance>=0) {
    // Time calculations for days, hours, minutes and seconds
    let days = Math.floor(distance / (1000 * 60 * 60 * 24));
    let hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
    let minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
    let seconds = Math.floor((distance % (1000 * 60)) / 1000);

    // Output the result in an element with id="demo"
    document.getElementsByClassName("demo")[i].innerHTML = days + "d " + hours + "h "
        + minutes + "m " + seconds + "s ";
}
        // If the count down is over, write some text
        else {
            clearInterval(x);
            document.getElementsByClassName("demo")[i].innerHTML = "EXPIRED";
        }
    }, 1000);
}
// let product = new SiriusGroup.auction.Products();
console.log(time);
// Set the date we're counting down to

