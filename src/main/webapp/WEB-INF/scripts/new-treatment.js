var i = 0;
function addRow() {
    i++;

    document.getElementById("drugCount").value = i;
    var table = document.getElementById("prescriptionTable");

    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);
    
    var cell0 = row.insertCell(0);
    cell0.innerHTML = i + 1;

    var cell1 = row.insertCell(1);
    var selectClone = document.getElementById("select").cloneNode(true);
    selectClone.setAttribute("name", "drug" + i);
    cell1.appendChild(selectClone);

    var cell2 = row.insertCell(2);
    var dosage = document.createElement("input");
    dosage.type = "text";
    dosage.name = "dosage" + i;
    cell2.appendChild(dosage);

    var cell3 = row.insertCell(3);
    var count = document.createElement("input");
    count.type = "text";
    count.name = "count" + i;
    cell3.appendChild(count);

    var cell4 = row.insertCell(4);
    var comment = document.createElement("textarea");
    comment.rows = "2";
    comment.cols = "30";
    comment.name = "comment" + i;
    cell4.appendChild(comment);
}
function formCheck(form) {
    if (form.complaint.value.length === 0) {
        alert('field "Complaint" is not filled!');
        return false;
    }
    if (form.diagnosis.value.length === 0) {
        alert('field "Diagnosis" is not filled!');
        return false;
    }
}