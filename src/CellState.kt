enum class CellState {
    EMPTY,
    SHIP,
    HIT,
    MISS;
    fun toChar(): Char {
        return when (this) {
            CellState.EMPTY -> '.'
            CellState.SHIP -> '#'
            CellState.HIT -> 'X'
            CellState.MISS -> 'O'
        }
    }

    fun describe(state: CellState): String {
        return when (state) {
            CellState.EMPTY -> "Пусто"
            CellState.SHIP -> "Корабль"
            CellState.HIT -> "Попадание"
            CellState.MISS ->  "Промах"
        }
    }

    fun applyEffect(state: CellState) {
        when (state) {
            CellState.EMPTY -> println("Ничего не происходит")
            CellState.SHIP -> println("Корабль еще цел")
            CellState.HIT -> println("По кораблю уже попали")
            CellState.MISS -> println("Здесь уже промахнулись")
        }
    }
}