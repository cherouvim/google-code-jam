let amountOfCases,
    caseNumber = 0,
    n,
    matrix;

// read lines, solve problem cases
require('readline')
    .createInterface({ input: process.stdin })
    .on('line', (line) => {
        if (!amountOfCases) {
            amountOfCases = Number(line);
            return true;
        }
        if (!n) {
            n = Number(line);
            matrix = [];
            return true;
        }
        matrix.push(line.split(' ').map(Number));
        if (matrix.length === n) {
            solve(++caseNumber, matrix);
            n = undefined;
            matrix = undefined;
        }
        if (caseNumber === amountOfCases) process.exit();
    });

const solve = (caseNumber, matrix) => {
    console.log(
        `Case #${caseNumber}: ` +
            `${calculateTrace(matrix)} ` +
            `${calculateRowsWithRepeatedElements(matrix)} ` +
            `${calculateColumnsWithRepeatedElements(matrix)}`
    );
};

const calculateTrace = (matrix) => {
    let sum = 0;
    for (let i = 0; i < matrix.length; i++) {
        sum += matrix[i][i];
    }
    return sum;
};

const calculateRowsWithRepeatedElements = (matrix) => {
    let result = 0;
    outer: for (let y = 0; y < matrix.length; y++) {
        const counts = Array(n).fill(0);
        for (let x = 0; x < matrix.length; x++) {
            if (counts[matrix[y][x] - 1] !== 0) {
                result++;
                continue outer;
            }
            counts[matrix[y][x] - 1]++;
        }
    }
    return result;
};

const calculateColumnsWithRepeatedElements = (matrix) => {
    let result = 0;
    outer: for (let x = 0; x < matrix.length; x++) {
        const counts = Array(n).fill(0);
        for (let y = 0; y < matrix.length; y++) {
            if (counts[matrix[y][x] - 1] !== 0) {
                result++;
                continue outer;
            }
            counts[matrix[y][x] - 1]++;
        }
    }
    return result;
};
