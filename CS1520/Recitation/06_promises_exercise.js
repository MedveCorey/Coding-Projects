/*
The purpose of this exercise is to get you familiarized with promises.
*/


/*
1) 
Write a promise that resolves after waiting for 2 seconds
You can verify this by printing something to the console
after waiting for 2 seconds. Look into setTimeout function.
*/
const promise1 = new Promise((resolve, reject) => {
    setTimeout(() => {
        console.log("Promise 1 resolve after 2 seconds");
        resolve();
    }, 2000);
}, 1500);



/*
2) 
Imagine you're programming a two player game. For this
purpose we need to write a promise that resolves when
both the players join the game. Your task is to write
a promise that resolves when the number of players is 2
otherwise it rejects. For now, you can declare a variable
that holds the number of players. Again you can print
stuff to the console to verify your implementation works
*/
let numberOfPlayers = 0;
const joinPlayersPromise = new Promise((resolve, reject) => {
    const checkPlayerInterval = setInterval(() => {
        if (numberOfPlayers === 2) {
            clearInterval(checkPlayerInterval);
            console.log("Both Players Joined");
            resolve();
        }
    }, 1000);
});

setTimeout(() => {
    numberOfPlayers = 1;
}, 1500);

setTimeout(() => {
    numberOfPlayers = 2;
}, 2500);


/*
3) 
Now we will try to chain promises together. Notice
that a promise when resolved can return a value or
another promise object. Imagine that you have to
fetch two files from the internet and then merge
the files together. Here we wont be doing any
fetching rather we'll try to emulate this by
setting timeouts. Assume it takes 3 seconds to
fetch the first file and 4 to fecth the second.
Again you can print stuff to the console to verify
your implementation works and use timeouts.
*/
const fetchFile1 = () => {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            console.log("File 1 fetched after 3 seconds");
            resolve("File 1 data");
        }, 3000);
    });
};

const fetchFile2 = () => {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            console.log("File 2 fetched after 4 seconds")
            resolve("File 2 data");
        }, 4000);
    });
}

fetchFile1()
    .then(data1 => {
        return fetchFile2().then(data2 => {
            console.log("Both files fetched and merged:", data1 + data2);
        });
    });


/*
After implementing the above promises. Look at the console output see if it makes sense.
Some promises might have resolved before others even though they were declared afterwards.
*/


