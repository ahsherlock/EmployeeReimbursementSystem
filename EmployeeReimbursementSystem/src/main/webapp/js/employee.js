window.onload = function () {
	console.log("setting event listeners to buttons..");
	document.getElementById("cancelButton").addEventListener("click", formReset);
	//	document.getElementById("submitButton").addEventListener("click", submitExpense);
	document.getElementById("amount").addEventListener("change", formatAmount);
	getExpenses();
}

function getExpenses() {
	formReset();
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function () {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			let expensesList = JSON.parse(xhttp.responseText);
			console.log(xhttp.responseText);
			DOMManipulation(expensesList);
		}
	}
	let url = "http://localhost:8080/EmployeeReimbursementSystem/api/employeehome/expenses";
	xhttp.open("GET", url, true);
	xhttp.send();
}

function submitExpense() {
	let xhttp = new XMLHttpRequest();
	let newEmployeeExpense = {
		type: document.getElementById("type").value,
		amount: document.getElementById("amount").value,
		description: document.getElementById("description").value
	}
	let newEmployeeExpenseString = JSON.stringify(newEmployeeExpense);
	console.log(newEmployeeExpenseString);
	let url = "http://localhost:8080/EmployeeReimbursementSystem/api/employeehome/expenses";
	xhttp.open("POST", url);
	xhttp.send(newEmployeeExpenseString);
	xhttp.onreadystatechange = function () {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			document.getElementById("expenseTable").getElementsByTagName("tbody")[0].innerHTML = "";
			getExpenses();
		}
	}
}



function DOMManipulation(expensesList) {
	expensesList.forEach(element => {
		console.log(element);
		console.log(element.expense_id);
		let table = document.getElementById("expenseTable").getElementsByTagName("tbody")[0];
		let row = document.createElement("tr");
		let number = document.createElement("td");
		let type = document.createElement("td");
		let amount = document.createElement("td");
		let submitted = document.createElement("td");
		let resolved = document.createElement("td");
		let status = document.createElement("td");
		let description = document.createElement("td");
		number.innerHTML = element.expense_id;
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
		row.appendChild(type);
		row.appendChild(amount);
		row.appendChild(submitted);
		row.appendChild(resolved);
		row.appendChild(status);
		row.appendChild(description);
		table.appendChild(row);
	})
}

function formatAmount() {
	let amount = document.getElementById("amount");
	amount.value = parseFloat(amount.value).toFixed(2);
}

function formReset() {
	let form = document.getElementById("expensesForm");
	form.reset();
}