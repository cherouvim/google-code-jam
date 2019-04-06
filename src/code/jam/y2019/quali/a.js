"use strict";

const readline = require("readline");
const rl = readline.createInterface({
	input: process.stdin,
	output: process.stdout,
	terminal: false
});

let amountOfCases = null,
	caseNumber = 0;

// read lines, solve problem cases
rl.on("line", function(line) {
	if (!amountOfCases) {
		amountOfCases = Number(line);
		return true;
	}
	const tokens = line.split(" ");
	solve(++caseNumber, tokens[0]);
	if (caseNumber === amountOfCases) process.exit();
});

function solve(caseNumber, amountAsText) {
	// Replace all 4 to 3.
	const a = amountAsText.replace(/4/g, 3);
	// Replace all 4 to 1 and everything else to 0.
	// Also cast to number to get rid of leading 0s.
	const b = Number(amountAsText.replace(/[^4]/g, 0).replace(/4/g, 1));
	console.log(`Case #${caseNumber}: ${a} ${b}`);
}
