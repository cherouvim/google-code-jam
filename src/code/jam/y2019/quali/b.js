"use strict";

const readline = require("readline");
const rl = readline.createInterface({
	input: process.stdin,
	output: process.stdout,
	terminal: false
});

let amountOfCases = null,
	sizeOfGrid = null,
	caseNumber = 0;

// read lines, solve problem cases
rl.on("line", function(line) {
	if (!amountOfCases) {
		amountOfCases = Number(line);
		return true;
	}
	if (!sizeOfGrid) {
		sizeOfGrid = Number(line);
		return true;
	}
	solve(++caseNumber, line);
	sizeOfGrid = null;
	if (caseNumber === amountOfCases) process.exit();
});

function solve(caseNumber, path) {
	const solution = path
		.replace(/S/g, "T")
		.replace(/E/g, "S")
		.replace(/T/g, "E");
	console.log(`Case #${caseNumber}: ${solution}`);
}
