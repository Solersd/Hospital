var i = 0;
var j = 0;
var k = 0;
function rowNumber() {
    i++;
    return i;
}
function setElementsName(){
    var table = document.getElementById("prescriptionTable");
    table.getElementsByTagName("select")[j].setAttribute("name", "drug" + j);
    table.getElementsByTagName("input")[k].setAttribute("name", "prescriptionId" + j);
    k++;
    table.getElementsByTagName("input")[k].setAttribute("name", "dosage" + j);
    k++;
    table.getElementsByTagName("input")[k].setAttribute("name", "count" + j);
    k++;
    table.getElementsByTagName("textarea")[j].setAttribute("name", "comment" + j);
    document.getElementById("drugCount").setAttribute("value", j);
    j++;
}
function addRow() {
    var table = document.getElementById("prescriptionTable");

    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);

    var cell0 = row.insertCell(0);
    cell0.innerHTML = ++i;

    var cell1 = row.insertCell(1);
    var selectClone = document.getElementById("select").cloneNode(true);
    selectClone.setAttribute("name", "drug" + j);
    cell1.appendChild(selectClone);

    var cell2 = row.insertCell(2);
    var dosage = document.createElement("input");
    dosage.type = "text";
    dosage.name = "dosage" + j;
    cell2.appendChild(dosage);

    var cell3 = row.insertCell(3);
    var count = document.createElement("input");
    count.type = "text";
    count.name = "count" + j;
    cell3.appendChild(count);

    var cell4 = row.insertCell(4);
    var comment = document.createElement("textarea");
    comment.rows = "2";
    comment.cols = "30";
    comment.name = "comment" + j;
    cell4.appendChild(comment);

    document.getElementById("drugCount").value = j;
    j++;
}
function confirmWindow(userConfirm){
    var window = confirm(userConfirm);
    if (!window) {
        return false;
    }
}
function closedTreatment(reopen){
    document.getElementById("addPrescription").setAttribute("type", "hidden");
    document.getElementById("submitOk").setAttribute("type", "hidden");
    document.getElementById("submitEndTreatment").setAttribute("value", reopen);
    document.getElementById("submitEndTreatment").setAttribute("form", "Reopen");
    document.getElementById("submitEndTreatment").setAttribute("style", "");
    var select = document.getElementsByClassName("select");
    for (var i = 0; i < select.length; i++) {
        select[i].setAttribute("disabled", "");
    }
    var textarea = document.getElementsByTagName("textarea");
    for (var i = 0; i < textarea.length; i++) {
        textarea[i].setAttribute("disabled", "");
    }
    var input = document.getElementsByClassName("input");
    for (var i = 0; i < input.length; i++) {
        input[i].setAttribute("disabled", "");
    }
}

