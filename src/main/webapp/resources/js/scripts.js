function checkPossibleOptions(id, forbiddenOptions) {
    let optionsArea = document.getElementById('options-area');
    let optionsInputs = optionsArea.getElementsByTagName('input');
    console.log(optionsInputs);
    for (let i = 0; i < optionsInputs.length; i++) {
        if(forbiddenOptions.indexOf(optionsInputs[i].value)) {
            optionsInputs[i].setAttribute("disabled", true);
        } else {
            optionsInputs[i].setAttribute("disabled", false);
        }
    }
}