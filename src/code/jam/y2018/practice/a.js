'use strict';

// deps
const readline = require('readline');
const fs = require('fs');

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
  terminal: false
});

const debug = process.argv[2]==='debug';

let numberOfProblems,
    min, // inclusive
    max, // inclusive
    guesses,
    guess;

log('*** starting ***');

// read lines, solve problem cases
rl.on('line', function(line){
	// get number of problems
	if (!numberOfProblems) {
		numberOfProblems = Number(line);
		log(`got number of problems: ${numberOfProblems}`);
		return true;
	}
	// get problem min/max
	if (!min && !max) {
		[min, max] = line.split(' ').map(Number);
		min++; // make inclusive
		log(`got minmax: ${min}, ${max}`);
		return true;
	}
	// get guesses count
	if (!guesses) {
		guesses = Number(line);
		log(`got guesses: ${guesses}, will now guess`);
		guessNumber();
		return true;
	}
	// process result
	if (line==='CORRECT') {
		log(`got correct, will reset and decrease number of problems, which currently is ${numberOfProblems}`);
		min = max = guesses = guess = null;
		numberOfProblems--;
		if (numberOfProblems===0) {
			log('number of problems is 0');
			rl.close();
			process.exit(); // don't know why this is needed for the python tester to complete
		}
		return true;
	}
	if (line==='TOO_SMALL') {
		log('got too small, will attempt another one');
		min = guess + 1;
		guessNumber();
		return true;
	}
	if (line==='TOO_BIG') {
		log('got too big, will attempt another one');
		max = guess - 1;
		guessNumber();
		return true;
	}
	if (line==='WRONG_ANSWER') {
		log('got wrong answer');
		rl.close();
		return true;
	}
	console.log('cannot reach here!');
});

function guessNumber() {
	guesses--;
	guess = Math.round((min + max) / 2);
	log(`guessing ${guess}, and guesses remaining are ${guesses}`);
	console.log(guess);
}

function log(text) {
	// fs.appendFileSync("/tmp/google-code-jam-temp.log", text + '\n');
}
