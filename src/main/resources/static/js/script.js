function getLifter(s1, s2){
    var s1 = document.getElementById(s1);
    var s2 = document.getElementById(s2);
    s2.innerHTML = "";
    if(s1.value == "women") {
        var optionArray = ["|", "45kg|45kg"];
    } else if(s1.value == "men") {
        var optionArray = ["|", "55kg|55kg", "61kg|61kg"];
    }
    for (var option in optionArray) {
        var pair = optionArray[option].split("|");
        var newOption = document.createElement("option");
        newOption.value = pair[0];
        newOption.innerHTML = pair[1];
        weightclass.options.add(newOption);
    }
}

