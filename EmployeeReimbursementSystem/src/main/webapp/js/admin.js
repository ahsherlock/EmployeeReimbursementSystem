window.onload = function () {
    console.log("setting event listeners to buttons..");
    getAllExpenses();
}

function getAllExpenses() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            let expensesList = JSON.parse(xhttp.responseText);
            console.log(xhttp.responseText);
            DOMManipulation(expensesList);
        }
    }
    let url = "http://localhost:8080/EmployeeReimbursementSystem/api/adminhome/expenses";
    xhttp.open("GET", url, true);
    xhttp.send();
}

function displaySelected() {
    let userSelection = document.getElementById("expStatus").value;
    console.log(userSelection);
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            let expensesList = JSON.parse(xhttp.responseText);
            console.log(xhttp.responseText);
            expenseSorting(expensesList, userSelection);
        }
    }
    let url = "http://localhost:8080/EmployeeReimbursementSystem/api/adminhome/expenses";
    xhttp.open("GET", url, true);
    xhttp.send();
}

function expenseSorting(expensesList, userSelection) {
    document.getElementById("tableBod").innerHTML = "";
    expensesList.forEach(element => {
        if (element.status == userSelection) {
            console.log(element);
            console.log(element.expense_id);
            let table = document.getElementById("expenseTable").getElementsByTagName("tbody")[0];
            let row = document.createElement("tr");
            let number = document.createElement("td");
            let employee = document.createElement("td");
            let type = document.createElement("td");
            let amount = document.createElement("td");
            let submitted = document.createElement("td");
            let resolved = document.createElement("td");
            let status = document.createElement("td");
            let description = document.createElement("td");
            let approveButton = document.createElement("button");
            approveButton.id = "approveButton";
            approveButton.innerHTML = "Approve Expense";
            approveButton.setAttribute("data", element.expense_id);
            approveButton.classList.add("btn");
            approveButton.classList.add("btn-success");
            let denyButton = document.createElement("button");
            denyButton.id = "denyButton";
            denyButton.innerHTML = "Deny Expense";
            denyButton.classList.add("btn");
            denyButton.classList.add("btn-danger");
            denyButton.setAttribute("data", element.expense_id);
            number.innerHTML = element.expense_id;
            employee.innerHTML = element.employee_id;
            switch (element.type) {
                case "l":
                    type.innerHTML = "Lodging"
                    break;
                case "t":
                    type.innerHTML = "Travel";
                    break;
                case "f":
                    type.innerHTML = "Food";
                    break;
                case "o":
                    type.innerHTML = "Other";
                    break;
                default:
                    type.innerHTML = "Other";
                    break;
            }
            amount.innerHTML = "$ " + element.amount.toFixed(2);
            submitted.innerHTML = element.submitted;
            resolved.innerHTML = element.resolved;
            switch (element.status) {
                case "p":
                    status.innerHTML = "PENDING"
                    break;
                case "d":
                    status.innerHTML = "DENIED";
                    break;
                case "c":
                    status.innerHTML = "COMPLETED";
                    break;
                default:
                    status.innerHTML = "PENDING";
                    break;
            }
            description.innerHTML = element.description;
            row.appendChild(number);
            row.appendChild(employee);
            row.appendChild(type);
            row.appendChild(amount);
            row.appendChild(submitted);
            row.appendChild(resolved);
            row.appendChild(status);
            row.appendChild(description);
            row.appendChild(approveButton);
            row.appendChild(denyButton);
            table.appendChild(row);
        } else if (userSelection == "a") {
            console.log(element);
            console.log(element.expense_id);
            let table = document.getElementById("expenseTable").getElementsByTagName("tbody")[0];
            let row = document.createElement("tr");
            let number = document.createElement("td");
            let employee = document.createElement("td");
            let type = document.createElement("td");
            let amount = document.createElement("td");
            let submitted = document.createElement("td");
            let resolved = document.createElement("td");
            let status = document.createElement("td");
            let description = document.createElement("td");
            let approveButton = document.createElement("button");
            approveButton.id = "approveButton";
            approveButton.innerHTML = "Approve Expense";
            approveButton.setAttribute("data", element.expense_id);
            approveButton.classList.add("btn");
            approveButton.classList.add("btn-success");
            let denyButton = document.createElement("button");
            denyButton.id = "denyButton";
            denyButton.innerHTML = "Deny Expense";
            denyButton.classList.add("btn");
            denyButton.classList.add("btn-danger");
            denyButton.setAttribute("data", element.expense_id);
            number.innerHTML = element.expense_id;
            employee.innerHTML = element.employee_id;
            switch (element.type) {
                case "l":
                    type.innerHTML = "Lodging"
                    break;
                case "t":
                    type.innerHTML = "Travel";
                    break;
                case "f":
                    type.innerHTML = "Food";
                    break;
                case "o":
                    type.innerHTML = "Other";
                    break;
                default:
                    type.innerHTML = "Other";
                    break;
            }
            amount.innerHTML = "$ " + element.amount.toFixed(2);
            submitted.innerHTML = element.submitted;
            resolved.innerHTML = element.resolved;
            switch (element.status) {
                case "p":
                    status.innerHTML = "PENDING"
                    break;
                case "d":
                    status.innerHTML = "DENIED";
                    break;
                case "c":
                    status.innerHTML = "COMPLETED";
                    break;
                default:
                    status.innerHTML = "PENDING";
                    break;
            }
            description.innerHTML = element.description;
            row.appendChild(number);
            row.appendChild(employee);
            row.appendChild(type);
            row.appendChild(amount);
            row.appendChild(submitted);
            row.appendChild(resolved);
            row.appendChild(status);
            row.appendChild(description);
            row.appendChild(approveButton);
            row.appendChild(denyButton);
            table.appendChild(row);
        }

    })
}

function DOMManipulation(expensesList) {
    expensesList.forEach(element => {
        console.log(element);
        console.log(element.expense_id);
        let table = document.getElementById("expenseTable").getElementsByTagName("tbody")[0];
        let row = document.createElement("tr");
        let number = document.createElement("td");
        let employee = document.createElement("td");
        let type = document.createElement("td");
        let amount = document.createElement("td");
        let submitted = document.createElement("td");
        let resolved = document.createElement("td");
        let status = document.createElement("td");
        let description = document.createElement("td");
        let approveButton = document.createElement("button");
        approveButton.id = "approveButton";
        approveButton.innerHTML = "Approve Expense";
        approveButton.setAttribute("data", element.expense_id);
        approveButton.classList.add("btn");
        approveButton.classList.add("btn-success");
        let denyButton = document.createElement("button");
        denyButton.id = "denyButton";
        denyButton.innerHTML = "Deny Expense";
        denyButton.classList.add("btn");
        denyButton.classList.add("btn-danger");
        denyButton.setAttribute("data", element.expense_id);
        number.innerHTML = element.expense_id;
        employee.innerHTML = element.employee_id;
        switch (element.type) {
            case "l":
                type.innerHTML = "Lodging"
                break;
            case "t":
                type.innerHTML = "Travel";
                break;
            case "f":
                type.innerHTML = "Food";
                break;
            case "o":
                type.innerHTML = "Other";
                break;
            default:
                type.innerHTML = "Other";
                break;
        }
        amount.innerHTML = "$ " + element.amount.toFixed(2);
        submitted.innerHTML = element.submitted;
        resolved.innerHTML = element.resolved;
        switch (element.status) {
            case "p":
                status.innerHTML = "PENDING"
                break;
            case "d":
                status.innerHTML = "DENIED";
                break;
            case "c":
                status.innerHTML = "COMPLETED";
                break;
            default:
                status.innerHTML = "PENDING";
                break;
        }
        description.innerHTML = element.description;
        row.appendChild(number);
        row.appendChild(employee);
        row.appendChild(type);
        row.appendChild(amount);
        row.appendChild(submitted);
        row.appendChild(resolved);
        row.appendChild(status);
        row.appendChild(description);
        row.appendChild(approveButton);
        row.appendChild(denyButton);
        table.appendChild(row);
    })
}
document.addEventListener('click', function (e) {
    if (e.target && e.target.id == 'approveButton') {
        let xhttp = new XMLHttpRequest();
        let fakeExpense = {
            expense_id: e.target.getAttribute('data'),
            employee_id: 0,
            type: "",
            amount: 20,
            submitted: "",
            resolved: "",
            status: "c",
            description: ""
        }
        let newFakeExpenseString = JSON.stringify(fakeExpense);
        let url = "http://localhost:8080/EmployeeReimbursementSystem/api/adminhome/approve";
        xhttp.open("POST", url);
        xhttp.send(newFakeExpenseString);
        xhttp.onreadystatechange = function () {
            if (xhttp.readyState == 4 && xhttp.status == 200) {
                document.getElementById("expenseTable").getElementsByTagName("tbody")[0].innerHTML = "";
                getAllExpenses();
            }
        }

    }
});
document.addEventListener('click', function (e) {
    if (e.target && e.target.id == 'denyButton') {
        let xhttp = new XMLHttpRequest();
        let fakeExpense = {
            expense_id: e.target.getAttribute('data'),
            employee_id: 0,
            type: "",
            amount: 20,
            submitted: "",
            resolved: "",
            status: "d",
            description: ""
        }
        let newFakeExpenseString = JSON.stringify(fakeExpense);
        let url = "http://localhost:8080/EmployeeReimbursementSystem/api/adminhome/deny";
        xhttp.open("POST", url);
        xhttp.send(newFakeExpenseString);
        xhttp.onreadystatechange = function () {
            if (xhttp.readyState == 4 && xhttp.status == 200) {
                document.getElementById("expenseTable").getElementsByTagName("tbody")[0].innerHTML = "";
                getAllExpenses();
            }
        }
    }
});