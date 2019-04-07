let amountOfCases = null,
	count = null,
	caseNumber = 0;

// read lines, solve problem cases
require("readline")
	.createInterface({ input: process.stdin })
	.on("line", function(line) {
		if (!amountOfCases) {
			amountOfCases = Number(line);
			return true;
		}
		if (!count) {
			count = Number(line.split(" ")[1]);
			return true;
		}
		solve(++caseNumber, line.split(" ").map(Number), count);
		count = null;
		if (caseNumber === amountOfCases) process.exit();
	});

function solve(caseNumber, numbers, count) {
	const divisors = getDivisors(numbers, count);
	const solution = getLettersForDivisors(divisors);
	console.log(`Case #${caseNumber}: ${solution}`);
}

function getLettersForDivisors(divisors) {
	const sortedDivisors = [...new Set(divisors)].sort((a, b) => a - b);
	const letters = [];
	divisors.map(divisor => letters.push(String.fromCharCode(sortedDivisors.indexOf(divisor) + 65)));
	return letters.join("");
}

function getDivisors(numbers, count) {
	const divisors = [];
	let previousDivisors = undefined;
	let currentDivisors = undefined;
	let lastPushed = undefined;
	numbers.map((number, index) => {
		currentDivisors = findDivisorsOfNumber(number);
		if (previousDivisors) {
			if (currentDivisors.includes(previousDivisors[0])) {
				divisors.push(previousDivisors[1]);
				lastPushed = previousDivisors[1];
			} else {
				divisors.push(previousDivisors[0]);
				lastPushed = previousDivisors[0];
			}
		}
		// This following block has to be the worst code I've ever written in the past 10 years.
		// Sorry about that. Kids screaming etc.
		if (count - 1 === index) {
			if (previousDivisors[0] === lastPushed) {
				divisors.push(previousDivisors[1]);
				lastPushed = previousDivisors[1];
			} else {
				divisors.push(previousDivisors[0]);
				lastPushed = previousDivisors[0];
			}
			if (currentDivisors[0] === lastPushed) {
				divisors.push(currentDivisors[1]);
			} else {
				divisors.push(currentDivisors[0]);
			}
		} else {
			previousDivisors = currentDivisors;
		}
	});
	return divisors;
}

// This one is probably the reason for the timeout.
function findDivisorsOfNumber(number) {
	const result = [];
	for (let i = 2; i <= number / 2; i++) {
		if (number % i === 0) {
			result.push(i);
			result.push(number / i);
			return result;
		}
	}
	return result;
}
