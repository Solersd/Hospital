function formCheck(name, patronymic, surname, dateOfBirth) {
    var form = document.getElementById("form");
    if (form.name.value.length === 0) {
        alert(name);
        return false;
    }
    if (form.patronymic.value.length === 0) {
        alert(patronymic);
        return false;
    }
    if (form.surname.value.length === 0) {
        alert(surname);
        return false;
    }
    if (form.dateOfBirth.value.length === 0) {
        alert(dateOfBirth);
        return false;
    }
}


