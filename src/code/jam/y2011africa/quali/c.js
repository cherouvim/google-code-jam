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
    w = null,
    field = null;


// read file, solve problem cases
lineReader.eachLine(file, function(line, last) {
	// amount of cases
	if (!amountOfCases) {
		amountOfCases = Number(line);
		return true;
	}

	// width of field. following lines contain field data
	if (!w) {
		w = Number(line.split(' ')[1]);
		field = [];
		return true;
	}

	// assemble nested array representing the field
	field.push(
		line.split('').map(
			// 1: rock, water and tree are obstacles
			// 0: everything else (grass and shrubs) are not
			x => ('RWT'.indexOf(x) >= 0) ? 1 : 0
		)
	);

	// end of case, solve it
	if (--w===0) {
		solve(++caseNumber, field, field[0].length, field.length);
	}
});

function solve(caseNumber, field) {
	let largestAreaFound = null;
	console.log(`Case #${caseNumber}: ${largestAreaFound}`);
}

