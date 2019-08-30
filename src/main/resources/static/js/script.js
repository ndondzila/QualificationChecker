function getWeightclassesByGender(s1, s2, womens, mens){
    var s1 = document.getElementById(s1);
    var s2 = document.getElementById(s2);
    s2.innerHTML = "";
    if(s1.value == "women") {
        var optionArray = womens;
    } else if(s1.value == "men") {
        var optionArray = mens;
    }
    for (var option in optionArray) {
        var pair = optionArray[option].split("|");
        var newOption = document.createElement("option");
        newOption.value = option
        newOption.innerHTML = option
        weightclass.options.add(newOption);
    }
}

