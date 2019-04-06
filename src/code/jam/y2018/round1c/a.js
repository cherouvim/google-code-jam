'use strict';

const readline = require('readline');
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
  terminal: false
});

let amountOfCases = null,
    caseNumber = 0,
    wordsCount = null,
    wordLength = null,
    wordIndex = 0,
    words = [];

// read lines, solve problem cases
rl.on('line', function(line) {
	if (!amountOfCases) {
		amountOfCases = Number(line);
		return true;
	}
	if (!wordsCount) {
		const tokens = line.split(' ');
		wordsCount = Number(tokens[0]);
		wordLength = Number(tokens[1]);
		return true;
	}
	words.push(line);
	wordIndex++;
	if (wordIndex===wordsCount) {
		solve(++caseNumber, wordsCount, wordLength, words);
		wordsCount = null;
		wordLength = null;
		wordIndex = 0;
		words = [];
	}
	if (caseNumber===amountOfCases) process.exit();
});

function solve(caseNumber, wordsCount, wordLength, words) {
	
	console.log('Case #' + caseNumber + ': -');
}

