fun main()
{
    val player = Player("Вы")
    val enemy = Player("Компьютер")

    player.placeShip(3, 2)
    player.placeShip(3, 3)
    player.placeShip(3, 4)
    player.placeShip(3, 5)

    enemy.placeShip(5, 7)
    enemy.placeShip(6, 7)
    enemy.placeShip(7, 7)

    printBothFields(player, enemy)
    println()

    player.registerShot(true)
    player.registerShot(false)
    player.registerShot(true)

    println(player.status())

    println("Выстрелов: ${player.shots}")
    println("Попаданий: ${player.hits}")
    player.reset()
    println(player.status())
}

fun printBothFields(player: Player, enemy: Player, showEnemyShips: Boolean = false)
{
    if (player.field().size != enemy.field().size) {
        println("Ошибка: невозможно вывести поля рядом, так как их размеры различаются.")
        return
    }
    val size = player.field().size
    println()
    println("=== Поле игрока ${player.name} ===        === Поле игрока ${enemy.name} ===")
    print("  ")
    for (c in 0..size - 1) print("$c ")
    print("     ")
    print("   ")
    for(c in 0..size - 1) print("$c ")
    println()

    for (r in 0..size - 1) {
        print("$r ")
        for (c in 0..size - 1) print("${player.field()[r][c].toChar()} ")
        print("      ")
        print("$r ")

        for (c in 0..size - 1)
        {
            val value = enemy.field()[r][c]
            val displayChar = if (value == CellState.SHIP && !showEnemyShips) '.' else value
            print("$displayChar ")
        }

        println()
    }
}
fun printFields(fields: Array<Array<CharArray>>, titles: Array<String>, showShips: BooleanArray)
{
    println()
    if (fields.size != titles.size || fields.size != showShips.size) {
        println("Ошибка: количество полей (${fields.size}), заголовков (${titles.size}) и флагов (${showShips.size}) не совпадает.")
        return
    }
    if (fields.isEmpty()) {
        println("Ошибка: передан пустой список полей.")
        return
    }
    val size = fields[0].size
    for (field in fields)
    {
        if (field.size != size) {
            println("Ошибка: размеры полей различаются.")
            return
        }
    }

    for (i in 0 .. fields.size-1) {
        print("=== ${titles[i]} ===")
        if (i < fields.size - 1) {
            print("       ")
        }
    }

    println()

    repeat(fields.size) {
        print("   ")
        for (c in 0 .. size - 1) {
            print("$c ")
        }
        print("   ")
    }

    println()

    for (r in 0 .. size - 1)
    {
        for (i in 0..fields.size - 1)
        {
            print (" $r ")
            for (c in 0 .. size - 1) {
                val value = fields[i][r][c]
                val displayChar = if (value == '#' && !showShips[i]) '.' else value
                print("$displayChar ")
            }
            print("   ")
        }
        println()
    }
}

