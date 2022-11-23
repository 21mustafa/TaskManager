let deadline = document.querySelectorAll("#taskDeadlineInCard");
deadline.forEach(item => {
    localdate = new Date(item.innerText);
    date = new Date(localdate.getUTCFullYear(), localdate.getMonth(), localdate.getUTCDate());
    today = new Date();
    three_days = new Date().setDate(today.getDate() + 3);
    if (date <= today) {
        item.classList.replace("text-bg-success", "text-bg-danger");
    }else if (date <= three_days) {
        item.classList.replace("text-bg-success", "text-bg-warning");
    }
})