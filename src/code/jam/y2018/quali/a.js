'use strict';

const readline = require('readline');
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
  terminal: false
});

let amountOfCases = null,
    caseNumber = 0;

// read lines, solve problem cases
rl.on('line', function(line) {
	if (!amountOfCases) {
		amountOfCases = Number(line);
		return true;
	}
	const tokens = line.split(' ');
	solve(++caseNumber, Number(tokens[0]), tokens[1]);
	if (caseNumber===amountOfCases) process.exit();
});

function solve(caseNumber, maxDamage, program) {
	let amountOfHacksToTheProgram = 0,
	    damage = countProgramDamage(program);
	while(true) {
		if (damage <= maxDamage) {
			console.log(`Case #${caseNumber}: ${amountOfHacksToTheProgram}`);
			return;
		}
		program = swapFirstChargeAndShootSequence(program);
		amountOfHacksToTheProgram++;
		const newDamage = countProgramDamage(program);
		if (damage === newDamage) {
			console.log(`Case #${caseNumber}: IMPOSSIBLE`);
			return;
		}
		damage = newDamage;
	}
}

function countProgramDamage(program) {
	let damage = 0,
	    beamStrength = 1;
	for (var i = 0; i < program.length; i++) {
		if (program.charAt(i)==='S') damage+=beamStrength;
		if (program.charAt(i)==='C') beamStrength*=2;
	}
	return damage;
}

function swapFirstChargeAndShootSequence(program) {
	return program.replace('CS', 'SC');
}

