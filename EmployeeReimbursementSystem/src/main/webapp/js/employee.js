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
	if(newEmployeeExpense.amount < 0){
		alert("ENTER A NUMBER GREATER THAN ZERO!");
		formReset();
	}else{
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
		submitted.innerHTML = dateStuff(element.submitted);
		if (element.resolved == null) {
			resolved.innerHTML = "";
		} else {
			resolved.innerHTML = dateStuff(element.resolved);
		}
		switch (element.status) {
			case "p":
				status.innerHTML = "<strong>PENDING</strong>"
				status.classList.add("text-info");
				break;
			case "d":
				status.innerHTML = "<strong>DENIED</strong>";
				status.classList.add("text-danger");

				break;
			case "c":
				status.innerHTML = "<strong>COMPLETED</strong>";
				status.classList.add("text-success");

				break;
			default:
				status.innerHTML = "<strong>PENDING</strong>";
				status.classList.add("text-info");
				break;
		}
		description.innerHTML = element.description;
		let deleteButton = document.createElement("button");
		deleteButton.id = "deleteButton";
		deleteButton.innerHTML = "Delete Expense";
		deleteButton.setAttribute("data", element.expense_id);
		deleteButton.setAttribute("type", "button");
		deleteButton.classList.add("btn");
		deleteButton.classList.add("butt");
		deleteButton.classList.add("btn-outline-danger");
		row.appendChild(number);
		row.appendChild(type);
		row.appendChild(amount);
		row.appendChild(submitted);
		row.appendChild(resolved);
		row.appendChild(status);
		row.appendChild(description);
		row.appendChild(deleteButton);
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

function logout() {
	console.log("LOGOUT FUNCTION CALLED");
	let xhttp = new XMLHttpRequest();
	let url = "http://localhost:8080/EmployeeReimbursementSystem/api/logout";
	xhttp.open("GET", url);
	xhttp.send();
	xhttp.onreadystatechange = function () {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			window.location.replace("http://localhost:8080/EmployeeReimbursementSystem/api");
		}
	}
}

document.addEventListener('click', function (e) {
	if (e.target && e.target.id == 'deleteButton') {
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
		let url = "http://localhost:8080/EmployeeReimbursementSystem/api/employeehome/delete";
		xhttp.open("POST", url);
		xhttp.send(newFakeExpenseString);
		xhttp.onreadystatechange = function () {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				document.getElementById("expenseTable").getElementsByTagName("tbody")[0].innerHTML = "";
				getExpenses();
			}
		}

	}
});


function dateStuff(dateInMilli) {
	let months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
	let days = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
	let newDate = new Date(dateInMilli);
	let year = newDate.getFullYear();
	let month = months[newDate.getMonth()];
	let day = days[newDate.getDay()];
	let dayNumber = newDate.getDate();
	let hour = newDate.getHours();
	let minutes = newDate.getMinutes();
	let seconds = newDate.getSeconds();
	let fullDate = day + " / " + month + " / " + dayNumber + " / " + year;
	return fullDate;


}