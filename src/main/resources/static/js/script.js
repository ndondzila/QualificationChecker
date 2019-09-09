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

function convert() {
var lbs = Number(document.getElementById("imperial").value);
var kgs = Math.round(lbs * 0.453592);
document.getElementById("metric").setAttribute("value", kgs);
}

function populateProgressBar(){



    var userTotal = document.getElementById("userTotal").value;
    var eventQTs = document.querySelectorAll("[id='eventQT']");
    var labels = document.querySelectorAll("[id='label']");
    var hello = 0;

    for(var i = 0; i < eventQTs.length; i++) {
        var eventQT = eventQTs[i].getAttribute("value");
        var ratio = Math.round((userTotal*100)/eventQT);
        var event = labels[i].getAttribute("value");
        labels[i].innerHTML = event + '          ' + "<small>" + ratio + '% of qualifying total' + "</small>";
        if(ratio>=100){
          eventQTs[i].setAttribute("class", "progress-bar progress-bar-success");
          eventQTs[i].setAttribute("style", "width:" + ratio + "%");
          eventQTs[i].setAttribute("aria-valuenow", userTotal);
          eventQTs[i].setAttribute("aria-valuemax", userTotal);
          eventQTs[i].innerHTML = userTotal + "kg out of " + eventQT + "kg";

      } else {
          eventQTs[i].setAttribute("style", "width:" + ratio + "%");
          eventQTs[i].setAttribute("aria-valuenow", userTotal);
          eventQTs[i].setAttribute("aria-valuemax", eventQT);
          eventQTs[i].innerHTML = userTotal + "kg out of " + eventQT + "kg";
      }
}
}