var weightclasses={
        women:['45kg', '49g', '55kg', '59kg', '64kg', '71kg', '76kg', '81kg', '87kg', '87+kg'],
        men:['55kg', '61kg', '67kg', '73kg', '81kg', '89kg', '96kg', '102kg', '109kg', '109+kg']}

var gender = document.getElementById('gender_menu');
var weightclass = document.getElementById('weightclass_menu');

gender_menu.addEventListener('change', function(){

var selected_option = weightclasses[this.value];

while(weightclass.options.length > 0 ){
weightclass.options.remove(0);
}

Array.from(selected_option).forEach(function(e1){
    let option = new Option(e1, e1);
    weightclass.appendChild(option);
    })
})