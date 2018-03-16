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
    moneyToInvest = null;

// read file, solve problem cases
lineReader.eachLine(file, function(line, last) {
	if (!amountOfCases) {
		amountOfCases = Number(line);
		return true;
	}
	if (!moneyToInvest) {
		moneyToInvest = Number(line);
		return true;
	}
	solve(++caseNumber, moneyToInvest, line.split(' ').map(m => Number(m)));
	moneyToInvest = null;
});

function solve(caseNumber, moneyToInvest, itemPricePerMonth) {
	let bestBuyAt = null,
	    bestSellAt = null,
	    bestProfit = 0,
	    bestItemsCountBought = 0;
	for(let a = 0; a < itemPricePerMonth.length - 1; a++) {
		for(let b = a + 1; b < itemPricePerMonth.length; b++) {
			const itemsCountBuyable = Math.floor(moneyToInvest / itemPricePerMonth[a]);
			if (itemsCountBuyable<=0) continue;
			const profit = (itemPricePerMonth[b] * itemsCountBuyable) - (itemPricePerMonth[a] * itemsCountBuyable);
			if (profit > 0 && profit >= bestProfit && itemsCountBuyable >= bestItemsCountBought) {
				bestBuyAt = a;
				bestSellAt = b;
				bestProfit = profit;
				bestItemsCountBought = itemsCountBuyable;
			}
		}
	}
	if (bestProfit) {
		console.log(`Case #${caseNumber}: ${bestBuyAt + 1} ${bestSellAt + 1} ${bestProfit}`);
	} else {
		console.log(`Case #${caseNumber}: IMPOSSIBLE`);
	}
}

