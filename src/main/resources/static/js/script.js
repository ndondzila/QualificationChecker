function toggleGender() {

var w = document.querySelectorAll("[id='w']");
var m = document.querySelectorAll("[id='m']");

for(var i = 0; i < w.length; i++)
   if(w[i].style.display === 'block') {
        w[i].style.display = 'none';
   } else {
        w[i].style.display = 'block';
   }

for(var i = 0; i < m.length; i++)
  if(m[i].style.display === 'block') {
          m[i].style.display = 'none';
     } else {
          m[i].style.display = 'block';
     }
     }

function convertImp() {
var lbs = Number(document.getElementById("imperial").value);
var kgs = (lbs * 0.453592).toFixed(2);
document.getElementById("metric").setAttribute("value", kgs);
}
function convertMet() {
var kgs = Number(document.getElementById("metric").value);
var lbs = (kgs * 2.20462).toFixed(2);
document.getElementById("imperial").setAttribute("value", lbs);
}

function populateProgressBar(){

    var eventMessages = document.querySelectorAll("[id='eventMessage']");
    var userTotal = document.getElementById("userTotal").value;
    var eventQTs = document.querySelectorAll("[id='eventQT']");
    var labels = document.querySelectorAll("[id='label']");

    for(var i = 0; i < eventQTs.length; i++) {
        var eventQT = eventQTs[i].getAttribute("value");
        var ratio = Math.round((userTotal*100)/eventQT);
        var event = labels[i].getAttribute("value");
        eventQTs[i].setAttribute("role", "progressbar");
        if(ratio>=100){
          labels[i].innerHTML = ratio + '% of ' + eventQT + 'kg qualifying total';
          labels[i].setAttribute("style", "color:green; font-weight:bold")
          eventQTs[i].setAttribute("class", "progress-bar progress-bar-success");
          eventQTs[i].setAttribute("style", "width:" + ratio + "%");
          eventQTs[i].setAttribute("aria-valuenow", userTotal);
          eventQTs[i].setAttribute("aria-valuemax", userTotal);
          eventQTs[i].innerHTML = (userTotal-eventQT) + "kgs over";
          eventMessages[i].innerHTML = "Qualified!";
          eventMessages[i].setAttribute("style", "font-weight:bold; color:green");


      } else {
          labels[i].innerHTML = ratio + '% of ' + eventQT + 'kg qualifying total';
          labels[i].setAttribute("style", "color:red; font-weight:bold")
          eventQTs[i].setAttribute("class", "progress-bar");
          eventQTs[i].setAttribute("style", "width:" + ratio + "%");
          eventQTs[i].setAttribute("aria-valuenow", userTotal);
          eventQTs[i].setAttribute("aria-valuemax", eventQT);
          eventQTs[i].innerHTML = (eventQT-userTotal) + "kgs to go!";
          eventMessages[i].innerHTML = "Not yet qualified! ";
          eventMessages[i].setAttribute("style", "font-weight:bold; color:red");
      }
}
}