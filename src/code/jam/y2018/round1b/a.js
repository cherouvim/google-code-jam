'use strict';

const readline = require('readline');
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
  terminal: false
});

let amountOfCases = null,
    caseNumber = 0,
    peopleCount = null,
    languageVotes = null;

// read lines, solve problem cases
rl.on('line', function(line) {
	if (!amountOfCases) {
		amountOfCases = Number(line);
		return true;
	}
	if (!peopleCount) {
		const tokens = line.split(' ');
		peopleCount = Number(tokens[0]);
		// tokens[1] not needed
		return true;
	}
	languageVotes = line.split(' ').map(Number);
	solve(++caseNumber, peopleCount, languageVotes);
	peopleCount = null;
	languageVotes = null;
	if (caseNumber===amountOfCases) process.exit();
});

function solve(caseNumber, peopleCount, languageVotes) {
	for (let i = 0, peopleVotesRemaining = peopleCount - getTotalPeopleVotedSoFar(languageVotes); i < peopleVotesRemaining; i++) {
		// this logic doesn't work.
		// I should have somehow attempted all possible permutations of remaining votes and calculate best result
		languageVotes = injectOneMostDefiniteAndSuperiorlyPossibleOptimizedVote(languageVotes, peopleCount);
	}
	console.log('Case #' + caseNumber + ': ' + countRoundedTotal(peopleCount, languageVotes));
}

function countRoundedTotal(peopleCount, languageVotes) {
	let total = 0;
	for (let i = 0; i < languageVotes.length; i++) {
		if (languageVotes[i]===0) continue;
		total += Math.round(languageVotes[i] / peopleCount * 100);
	}
	return total;
}

function getTotalPeopleVotedSoFar(languageVotes) {
	return languageVotes.reduce((a, b) => a + b, 0);
}

function injectOneMostDefiniteAndSuperiorlyPossibleOptimizedVote(languageVotes, peopleCount) {
	const languageVotesClone = languageVotes.slice(); // clone
	languageVotesClone.push(0);
	let mostOptimalVoteIndex = null;
	let mostOptimalVoteResult = -1;
	for (let i = 0; i < languageVotesClone.length; i++) {
		const voteOptimality = getVoteOptimality(languageVotesClone[i] + 1, peopleCount);
		if (voteOptimality > mostOptimalVoteResult) {
			mostOptimalVoteResult = voteOptimality;
			mostOptimalVoteIndex = i;
		}
	}
	languageVotesClone[mostOptimalVoteIndex]++;
	return languageVotesClone.filter(Number);
}

function getVoteOptimality(votes, peopleCount) {
	const percentage = votes / peopleCount * 100;
	const remainder = percentage - Math.floor(percentage);
	return remainder>=0.5?1:0;
}