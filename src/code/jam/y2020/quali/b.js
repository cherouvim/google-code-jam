let amountOfCases,
    caseNumber = 0;

// read lines, solve problem cases
require('readline')
    .createInterface({ input: process.stdin })
    .on('line', (line) => {
        if (!amountOfCases) {
            amountOfCases = Number(line);
            return true;
        }
        solve(++caseNumber, line);
        if (caseNumber === amountOfCases) process.exit();
    });

const solve = (caseNumber, line) => {
    let result = line
        .split('')
        .map(Number)
        .map((i) => Array(i).fill('(').join('') + i + Array(i).fill(')').join(''))
        .join('');
    while (result.indexOf(')(') >= 0) result = result.replace(/\)\(/g, '');
    console.log(`Case #${caseNumber}: ${result}`);
};
