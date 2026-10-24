class Player(val name: String, size: Int = 10) {

    private var _field: Array<Array<CellState>> = Array(size) {Array(size) {CellState.EMPTY}}
    fun field(): Array<Array<CellState>> = _field

    fun placeShip(row: Int, col: Int) {
        if(row in 0.._field.size - 1 && col in 0.._field.size - 1) {
            _field[row][col] = CellState.SHIP
        }
    }

    private var _shots: Int = 0
    val shots: Int
        get() = _shots

    private var _hits: Int = 0
    val hits: Int
        get() = _hits

    var shipsLeft: Int = 20

    fun isAlive(): Boolean {
        return shipsLeft > 0
    }

    fun accuracy(): Double {
        if(_shots == 0) return 0.0
        else return  _hits * 100.0 / _shots
    }

    fun printStats(){
        println("Игрок $name: палуб осталось: $shipsLeft, выстрелов: $_shots, Попаданий: $_hits , точность: ${String.format("%.1f", accuracy())}%")
    }

    fun status(){
        printStats()
    }

    fun takeDamage(){
        shipsLeft -= 1
        isAlive()
    }

    fun registerShot(hit: Boolean){
        _shots++
        if (hit) _hits++
    }

    fun markShot(row: Int, col: Int, hit: Boolean){
        if(row in 0.._field.size - 1 && col in 0.._field.size - 1)
        {
            _field[row][col] = if (hit) CellState.HIT else CellState.MISS
        }
    }

    fun reset(){
        _shots = 0
        _hits = 0
        shipsLeft = 20
        for (r in 0 .. _field.size-1) {
            for (c in 0 .. _field[0].size-1) {
                _field[r][c] = CellState.EMPTY
            }
        }
    }

    fun printField(showShips: Boolean = true, debug: Boolean = true)
    {
        println()
        if(debug)
        {
            val size = _field.size
            println("===Поле игрока $name (debug)===")
            print("    |")
            for (c in 0.._field.size - 1) {
                if( c > 9)
                {
                    print("$c |")
                }
                else print(" $c |")
            }
            println()

            for (r in 0.._field.size - 1) {
                print("    ")
                println("+---".repeat(size) + "+")
                if( r > 9)
                {
                    print(" $r |")
                }
                else print(" $r  |")

                for (c in 0.._field.size - 1) {
                    val value = _field[r][c]
                    val displayChar = if (value == CellState.SHIP && !showShips) '.' else value
                    print(" $displayChar |")
                }
                println()
            }
            print("    ")
            println("+---".repeat(size) + "+")
        }
        else
        {
            println("===Поле игрока $name===")
            print("   ")
            for (c in 0.._field.size - 1) {
                print("$c ")
            }
            println()
            for (r in 0.._field.size - 1) {
                print(" $r ")
                for (c in 0.._field.size - 1) {
                    val value = _field[r][c]
                    val displayChar = if (value == CellState.SHIP && !showShips) '.' else value
                    print("$displayChar ")
                }
                println()
            }
        }

    }




    fun printBothFields(player: Player, enemy: Player, showEnemyShips: Boolean = false)
    {
        if (player._field.size != enemy._field.size) {
            println("Ошибка: невозможно вывести поля рядом, так как их размеры различаются.")
            return
        }
        val size = player._field.size
        println()
        println("===Поле игрока ${player.name} ===        ===Поле игрока ${enemy.name} ===")
        print("  ")
        for (c in 0..size - 1) print("$c ")
        print("     ")
        print("   ")
        for(c in 0..size - 1) print("$c ")
        println()

        for (r in 0..size - 1) {
            print("$r ")
            for (c in 0..size - 1) print("${player._field[r][c].toChar()} ")
            print("      ")
            print("$r ")

            for (c in 0..size - 1)
            {
                val value = enemy._field[r][c]
                val displayChar = if (value == CellState.SHIP && !showEnemyShips) '.' else value
                print("$displayChar ")
            }

            println()
        }
    }
}