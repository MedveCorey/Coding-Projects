let currentPlayer = 1;
let p1 = new Player(1, true);
let p2 = new Player(2, false);

function Player(num, turn) {
    this.name;
    this.locations;
    this.target = [[], [], [], [], [], [], [], [], [], []]; //lame
    this.ships;
    this.turn = turn;
    this.score;
    this.win = false;
    this.health = [0, 0, 0]; //hits on [AC, B, S]

    // initialize target to 0s and ship to space
    for (var i = 0; i < 10; i++) {
        for (var j = 0; j < 10; j++) {
            this.target[i][j] = 0;
        }
    }
}
function submitPlayer1Info() {
    p1.name = document.getElementById('player1Name').value;
    p1.locations = document.getElementById('player1Ships').value;
    p1.ships = parseShipPlacements(p1.locations);

    document.getElementById('player1').style.display = 'none';
    document.getElementById('player2').style.display = 'block';

    // Wait for player 2 to enter information
}

function submitPlayer2Info() {
    p2.name = document.getElementById('player2Name').value;
    p2.locations = document.getElementById('player2Ships').value;
    p2.ships = parseShipPlacements(p2.locations);

    document.getElementById('player2').style.display = 'none';

    // Both players have entered their information, start the game
    startGame();
}

function startGame() {
    // Display whose turn it is
    const playerName = currentPlayer === 1 ? document.getElementById('player1Name').value : document.getElementById('player2Name').value;
    document.getElementById('turnIndicator').textContent = `Current Turn: ${playerName}`;

    // Hide the opponent's setup and show the game grids
    document.getElementById('player2').style.display = 'none';
    document.getElementById('gameGrids').style.display = 'block';

    renderGrids(p1, p2);

    //Blank Screen Between Turns
    document.getElementById('blankScreen').style.display = 'block';
    document.getElementById('gameGrids').style.display = 'none';
    document.getElementById('turnIndicator').style.display = 'none';
    document.getElementById('currentPlayersTurn').textContent = `Press Button to Start ${playerName}'s Turn`;
}

function parseShipPlacements(input) {
    const shipRegex = /([ABS])\(([A-J])(\d)-([A-J])(\d)\);?/g;
    const parsedShips = [];

    let match;
    while ((match = shipRegex.exec(input)) !== null) {
        const shipType = match[1];
        const startLetter = match[2];
        const startNumber = parseInt(match[3]);
        const endLetter = match[4];
        const endNumber = parseInt(match[5]);

        const parsedShip = {
            type: shipType,
            coordinates: []
        };

        // Convert letters to corresponding column index (A=1, B=2, etc.)
        const startX = startLetter.charCodeAt(0) - 'A'.charCodeAt(0) + 1;
        const endX = endLetter.charCodeAt(0) - 'A'.charCodeAt(0) + 1;

        // Generate coordinates between start and end points
        for (let i = startNumber; i <= endNumber; i++) {
            for (let j = startX; j <= endX; j++) {
                parsedShip.coordinates.push({ x: j, y: i });
            }
        }

        parsedShips.push(parsedShip);
    }

    return parsedShips;
}


function renderGrids(p1, p2) {
    ShipPlacement = document.getElementById('ShipPlacements');
    ShipPlacement.innerHTML = '';

    // Create the header row with column labels (A-J)
    const headerRow = document.createElement('tr');
    const headerCell = document.createElement('th');
    headerCell.textContent = '';
    headerRow.appendChild(headerCell);
    for (let i = 1; i <= 10; i++) {
        const labelCell = document.createElement('th');
        labelCell.textContent = String.fromCharCode(64 + i);
        headerRow.appendChild(labelCell);
    }
    ShipPlacement.appendChild(headerRow);

    // Render the grid
    for (let i = 1; i <= 10; i++) {
        const row = document.createElement('tr');

        // Add row label (1-10)
        const labelCell = document.createElement('td');
        labelCell.textContent = i;
        row.appendChild(labelCell);

        for (let j = 1; j <= 10; j++) {
            const cell = document.createElement('td');
            // Check if this cell is occupied by any ship
            cell.style.backgroundColor = 'lightblue';
            cell.id = `ShipPlacement-${j}-${i}`;
            const occupiedShip = getOccupyingShip(p1.ships, j, i);
            cell.textContent = occupiedShip ? occupiedShip.type : '';
            row.appendChild(cell);
        }
        ShipPlacement.appendChild(row);
    }

    TargetGrid = document.getElementById('TargetGrid');
    TargetGrid.innerHTML = '';

    const headerRow2 = document.createElement('tr');
    const headerCell2 = document.createElement('th');
    // Create the header row with column labels (A-J)
    headerCell2.textContent = '';
    headerRow2.appendChild(headerCell2);
    for (let i = 1; i <= 10; i++) {
        const labelCell2 = document.createElement('th');
        labelCell2.textContent = String.fromCharCode(64 + i);
        headerRow2.appendChild(labelCell2);
    }
    TargetGrid.appendChild(headerRow2);

    // Render the grid
    for (let i = 1; i <= 10; i++) {
        const row = document.createElement('tr');

        // Add row label (1-10)
        const labelCell = document.createElement('td');
        labelCell.textContent = i;
        row.appendChild(labelCell);

        for (let j = 1; j <= 10; j++) {
            const cell = document.createElement('td');
            // Check if this cell is occupied by any ship
            cell.style.backgroundColor = 'lightblue';
            cell.id = `TargetGrid-${j}-${i}`;
            cell.addEventListener('click', function () { handleCellClick(p1, p2, this.id) })
            row.appendChild(cell);
        }
        TargetGrid.appendChild(row);
    }
}


function getOccupyingShip(ships, x, y) {
    for (let i = 0; i < ships.length; i++) {
        const ship = ships[i];
        for (let j = 0; j < ship.coordinates.length; j++) {
            const coord = ship.coordinates[j];
            //console.log("Checking ship:", ship.type, "Coordinates:", coord.x, coord.y); // Debugging statement
            if (coord.x === x && coord.y === y) {
                //console.log("Ship found:", ship.type); // Debugging statement
                return ship;
            }
        }
    }
    //console.log("No ship found at coordinates:", x, y); // Debugging statement
    return null;
}

function handleCellClick(p1, p2, id) {
    const cell = document.getElementById(id);

    // Check if the cell has already been clicked
    if (cell.style.backgroundColor == 'red' || cell.style.backgroundColor == 'white') {
        // Cell already clicked, do nothing
        document.getElementById('invalidInput').style.display = 'block';
        return;
    }

    [GridId, j, i] = id.split('-');
    x = parseInt(j);
    y = parseInt(i);


    if (p2.turn) {
        t = p1;
        p1 = p2;
        p2 = t;
    }

    const hitShip = getOccupyingShip(p2.ships, x, y);
    if (hitShip) {
        hitResult = document.getElementById('hitResult').textContent = 'Hit';
        if (hitShip.type === 'A') {
            p2.health[0]++;
            if (p2.health[0] === 5) {
                document.getElementById('shipSunk').style.display = 'block';
                document.getElementById('shipSunk').textContent = 'Aircraft Carrier Sunk';
                p2.a = false;
            }
        } else if (hitShip.type === 'B') {
            p2.health[1]++;
            if (p2.health[1] === 4) {
                document.getElementById('shipSunk').style.display = 'block';
                document.getElementById('shipSunk').textContent = 'Battleship Sunk';
                p2.b = false;
            }
        } else {
            p2.health[2]++;
            if (p2.health[2] === 3) {
                console.log(p2.health[2]);
                document.getElementById('shipSunk').style.display = 'block';
                document.getElementById('shipSunk').textContent ='Submarine Sunk';
                p2.s = false;
            }
        }

        p2.target[x - 1][y - 1] = 1;

        //Blank Screen Between Turns
        document.getElementById('blankScreen').style.display = 'block';
        document.getElementById('gameGrids').style.display = 'none';
        document.getElementById('turnIndicator').style.display = 'none';
        const playerName = currentPlayer.turn ? p1.name : p2.name;
        document.getElementById('currentPlayersTurn').textContent = `Press Button to Start ${playerName}'s Turn`;
        document.getElementById('currentPlayersTurn').style.display = 'block';
    } else {
        p2.target[x - 1][y - 1] = 2;
        //Blank Screen Between Turns
        document.getElementById('blankScreen').style.display = 'block';
        document.getElementById('gameGrids').style.display = 'none';
        document.getElementById('turnIndicator').style.display = 'none';
        document.getElementById('hitResult').textContent = 'Miss';
        const playerName = currentPlayer.turn ? p1.name : p2.name;
        document.getElementById('currentPlayersTurn').textContent = `Press Button to Start ${playerName}'s Turn`;
        document.getElementById('currentPlayersTurn').style.display = 'block';

    }

    if (p2.health[0] == 5 && p2.health[1] == 4 && p2.health[2] == 3) {
        endGame(p1, p2);
    }
    // End current turn
    p1.turn = false;
    p2.turn = true;

    // Update turn indicator with current player's name
    const playerName = currentPlayer.turn ? p1.name : p2.name;
    document.getElementById('turnIndicator').textContent = `Current Turn: ${playerName}`;


    displayGrid(p2.target, p1.target, p2.ships);

}

function displayGrid(target2, target1, ships) {
    table = document.getElementById("TargetGrid");
    for (let i = 1; i <= 10; i++) {
        for (let j = 1; j <= 10; j++) {
            cell = table.rows[i].cells[j];
            if (target1[j - 1][i - 1] === 1) {
                cell.style.backgroundColor = "red";
            } else if (target1[j - 1][i - 1] === 2) {
                cell.style.backgroundColor = "white";
            } else {
                cell.style.backgroundColor = "lightblue";
            }
        }
    }
    table = document.getElementById("ShipPlacements");
    for (let i = 1; i <= 10; i++) {
        for (let j = 1; j <= 10; j++) {
            cell = table.rows[i].cells[j];
            if (target2[j - 1][i - 1] === 1) {
                cell.style.backgroundColor = "red";
                let occupiedShip;
                if (p2.turn) {
                    occupiedShip = getOccupyingShip(p2.ships, j, i);
                } else {
                    occupiedShip = getOccupyingShip(p1.ships, j, i);
                }

                cell.textContent = occupiedShip.type;

            } else if (target2[j - 1][i - 1] === 2) {
                cell.style.backgroundColor = "white";
                cell.textContent = '';

            } else {
                cell.style.backgroundColor = "lightblue";
                if (i >= 1 && j >= 1) {
                    let occupiedShip;
                    if (p2.turn) {
                        occupiedShip = getOccupyingShip(p2.ships, j, i);
                    } else {
                        occupiedShip = getOccupyingShip(p1.ships, j, i);
                    }
                    cell.textContent = occupiedShip ? occupiedShip.type : '';
                }

            }
        }
    }
}

function endGame(p1, p2) {
    P1score = 24 - 2 * (p1.health[0] + p1.health[1] + p1.health[2]);
    document.getElementById('blankScreen').style.display = 'none';
    document.getElementById('winScreen').style.display = 'block';
    document.getElementById('winner').textContent = p1.name+ ' Wins!';
    document.getElementById('playerOneScore').textContent = p1.name+ ': '+ P1score;
    document.getElementById('playerTwoScore').textContent = p2.name+ ': 0';
    exit()
}

function startTurn() {
    document.getElementById('blankScreen').style.display = 'none';
    document.getElementById('gameGrids').style.display = 'block';
    document.getElementById('turnIndicator').style.display = 'block';
    const playerName = currentPlayer.turn ? p1.name : p2.name;
    document.getElementById('currentPlayersTurn').textContent = `Press Button to Start ${playerName} Turn`;
    document.getElementById('currentPlayersTurn').style.display = 'none';
    document.getElementById('shipSunk').style.display = 'none';
}


// Implement game logic functions (e.g., handling clicks, determining hits/misses, etc.)
