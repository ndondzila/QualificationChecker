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