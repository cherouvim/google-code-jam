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

let numberOfProblems;

// read file, solve problem
lineReader.eachLine(file, function(line, last) {
	if (!numberOfProblems) {
		numberOfProblems = Number(line);
		return true;
	}
	const numbers = line.split(' ').map(function(a) { return Number(a); } );
	const sortedNumbers = numbers.sort(function(a, b) { return (a - b); } );
	console.log(`sorted: ${sortedNumbers}`);
});

