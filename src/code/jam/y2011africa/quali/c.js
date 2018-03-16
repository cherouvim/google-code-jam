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
		debugger;
		solve(++caseNumber, field, field[0].length, field.length);
	}
});

function solve(caseNumber, field, l, w) {
	let largestAreaFound = 0;
	for (let w1 = 0; w1 < w; w1++) {
		for (let l1 = 0; l1 < l; l1++) {
			// if starting point is obstacle, continue to next starting point
			if (field[w1][l1]) continue;
			let maxL2 = l;
			for (let w2 = w1; w2 < w; w2++) {
				for (let l2 = l1; l2 < maxL2; l2++) {
					// hit obstacle on moving ending point, redefine max l boundary and break to next line
					if (field[w2][l2]) {
						maxL2 = l2;
						break;
					}
					const currentArea = (l2 - l1 + 1) * (w2 - w1 + 1);
					if (currentArea > largestAreaFound) largestAreaFound = currentArea;
				}
			}
		}
	}
	console.log(`Case #${caseNumber}: ${largestAreaFound}`);
}

