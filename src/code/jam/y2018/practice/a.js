'use strict';

// deps
const readline = require('readline');

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

// read lines, solve problem cases
rl.on('line', function(line){
	// get number of problems
	if (!numberOfProblems) {
		numberOfProblems = Number(line);
		// if (debug) console.log('got number of problems');
		return true;
	}
	// get problem min/max
	if (!min && !max) {
		[min, max] = line.split(' ').map(Number);
		min++; // make inclusive
		// if (debug) console.log('got minmax');
		return true;
	}
	// get guesses count
	if (!guesses) {
		guesses = Number(line);
		// if (debug) console.log('got guesses, will guess');
		guessNumber();
		return true;
	}
	// process result
	if (line==='CORRECT') {
		// if (debug) console.log('got correct, will reset and decrease number of problems');
		min = max = guesses = guess = null;
		numberOfProblems--;
		if (numberOfProblems===0) rl.close();
		return true;
	}
	if (line==='TOO_SMALL') {
		// if (debug) console.log('got too small, will attempt another one');
		min = guess + 1;
		guessNumber();
		return true;
	}
	if (line==='TOO_BIG') {
		// if (debug) console.log('got too big, will attempt another one');
		max = guess - 1;
		guessNumber();
		return true;
	}
	if (line==='WRONG_ANSWER') {
		// if (debug) console.log('got wrong answer');
		rl.close();
		return true;
	}
	console.log('cannot reach here!');
});

function guessNumber() {
	guesses--;
	guess = Math.round((min + max) / 2);
	console.log(guess);
}
