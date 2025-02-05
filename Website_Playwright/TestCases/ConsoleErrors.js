async function consoleErrors(page) {
    let consoleError = [];
    page.on('console', msg => {
        if (msg.type() === 'error') {
            // if (msg.text().includes('Refused to load the image')) {
                
            //     throw new Error("Console Error: "+consoleError);
                
            // }
            consoleError.push(msg.text());
        }
    })
    return consoleError;
}
async function printConsoleErrors(errors) {
    for (let index = 0; index < errors.length; index++) {
        console.log('Console Error: ' + errors[index]);
    }
}
module.exports = {
    consoleErrors,
    printConsoleErrors
}