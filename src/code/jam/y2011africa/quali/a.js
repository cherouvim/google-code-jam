'use strict';

// deps
const path = require('path');
const lineReader = require('line-reader');
const fs = require('fs');

// file input
const file = process.argv[2];

// usage
if (!file) {
	console.log(`Usage: node ${path.basename(__filename)} <file.in>`);
	process.exit();
}

// check if file exists
if (!fs.existsSync(file)) {
	console.log(`File ${file} not found`);
	process.exit();
}

let amountOfCases = null,
    caseNumber = 0,
    numberOfRopes = null;


// read file, solve problem cases
lineReader.eachLine(file, function(line, last) {
	if (!amountOfCases) {
		amountOfCases = Number(line);
		return true;
	}
	if (!numberOfRopes) {
		numberOfRopes = Number(line);
		return true;
	}
	solve(++caseNumber, line.split(' '));
	numberOfRopes = null;
});

function solve(caseNumber, ropes) {
	const reds = [];
	const blues = [];
	ropes.forEach(function(rope) {
		if (rope.endsWith('R')) reds.push(Number(rope.replace('R', '')));
		if (rope.endsWith('B')) blues.push(Number(rope.replace('B', '')));
	});
	reds.sort(function(a, b) { return a-b; });
	blues.sort(function(a, b) { return a-b; });

	let loopLength = 0;
	while(reds.length && blues.length) {
		loopLength += reds.pop() - 1;
		loopLength += blues.pop() - 1;
	}

	console.log(`Case #${caseNumber}: ${loopLength}`);
}

