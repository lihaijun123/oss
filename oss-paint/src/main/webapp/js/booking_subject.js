function initSubject() {
    var v = document.getElementById("content").value;
    if (v != "" && !isNaN(v)) {
        ajaxGetSubject(v);
    }
}

function ajaxGetSubject(id) {
    ajaxRequest("/resourceBook.do?method=showSubject", "rec_id=" + id, subjectShow);
}
