let amountOfCases,
    caseNumber = 0,
    n,
    tasks;

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
            tasks = [];
            return true;
        }
        tasks.push(line.split(' ').map(Number));
        if (tasks.length === n) {
            solve(++caseNumber, tasks);
            n = undefined;
            tasks = undefined;
        }
        if (caseNumber === amountOfCases) process.exit();
    });

const solve = (caseNumber, tasks) => {
    console.log(`Case #${caseNumber}: ${getSolutionString(tasks)}`);
};

const getSolutionString = (tasks) => {
    // Mark the task index.
    tasks.map((time, index) => time.push(index));
    // Sort by task start.
    tasks.sort((a, b) => a[0] - b[0]);
    // Track pending tasks.
    let pendingTasks = [];
    for (let i = 0; i < tasks.length; i++) {
        const task = tasks[i];
        // Cleanup finished jobs.
        pendingTasks = pendingTasks.filter((pendingTask) => pendingTask[1] > task[0]);
        // No pending tasks after cleanup.
        if (pendingTasks.length === 0) {
            // Assign it to C.
            task.push('C');
            // Track task.
            pendingTasks.push(task);
            continue;
        }
        // One pending task.
        if (pendingTasks.length === 1) {
            // Assign it to the other possible handler.
            task.push(pendingTasks[0][3] === 'C' ? 'J' : 'C');
            // Track task.
            pendingTasks.push(task);
            continue;
        }
        // Two pending tasks after cleanup.
        if (pendingTasks.length === 2) {
            return 'IMPOSSIBLE';
        }
    }
    // Sort by task index.
    tasks.sort((a, b) => a[2] - b[2]);
    return tasks.map((task) => task[3]).join('');
};
