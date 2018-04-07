'use strict';

const readline = require('readline');
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
  terminal: false
});

let amountOfCases = null,
    caseNumber = 0,
    amountOfNumbers = null;

// read lines, solve problem cases
rl.on('line', function(line) {
	if (!amountOfCases) {
		amountOfCases = Number(line);
		return true;
	}
	if (!amountOfNumbers) {
		amountOfNumbers = Number(line);
		return true;
	}
	solve(++caseNumber, line.split(' ').map(Number));
	amountOfNumbers = null;
	if (caseNumber===amountOfCases) process.exit();
});

function solve(caseNumber, numbers) {
	const odds = [],
	      evens = [];
	// split numbers in 2 arrays based on their index (odd index vs even index)
	for(var i=0; i<numbers.length; i++) {
		if (i%2) {
			evens.push(numbers[i]);
		} else {
			odds.push(numbers[i]);
		}
	}
	// sort both arrays
	odds.sort((a, b) => { return a-b; });
	evens.sort((a, b) => { return a-b; });
	// find sort trouble
	let previousNumber = null;
	for(var i=0; i<numbers.length; i++) {
		const number = i%2?evens.shift():odds.shift();
		if (previousNumber!==null && number<previousNumber) {
			console.log(`Case #${caseNumber}: ${i - 1}`);
			return;
		}
		previousNumber = number;
	}
	console.log(`Case #${caseNumber}: OK`);
}

