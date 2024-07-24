import java.util.Scanner

class Menu {
    private val scanner = Scanner(System.`in`)

    fun start() {
        var exit = false

        while (!exit) {
            println("\nSeleccione una opción:")
            println("1. Calcular Promedio")
            println("2. Filtrar Números Impares")
            println("3. Verificar si es Palíndromo")
            println("4. Agregar Saludo a Nombres")
            println("5. Realizar Operación")
            println("6. Mapear Personas a Estudiantes")
            println("7. Salir")

            when (scanner.nextInt()) {
                1 -> calcularPromedio()
                2 -> filtrarImpares()
                3 -> verificarPalindromo()
                4 -> agregarSaludo()
                5 -> realizarOperacion()
                6 -> mapearPersonas()
                7 -> exit = true
                else -> println("Opción no válida. Intente de nuevo.")
            }
        }
    }

    private fun calcularPromedio() {
        println("Ingrese números separados por espacio:")
        scanner.nextLine() // consumir el salto de línea
        val input = scanner.nextLine()
        val numbers = input.split(" ").mapNotNull {
            it.toIntOrNull()
        }
        if (numbers.isNotEmpty()) {
            val promedio = numbers.sum().toDouble() / numbers.size
            println("El promedio es: $promedio")
        } else {
            println("No se ingresaron números válidos.")
        }
    }

    private fun filtrarImpares() {
        println("Ingrese números separados por espacio:")
        scanner.nextLine() // consumir el salto de línea
        val input = scanner.nextLine()
        val numbers = input.split(" ").mapNotNull {
            it.toIntOrNull()
        }
        val impares = numbers.filter { it % 2 != 0 }
        println("Números impares: $impares")
    }

    private fun verificarPalindromo() {
        println("Ingrese una cadena:")
        scanner.nextLine() // consumir el salto de línea
        val text = scanner.nextLine()
        val esPalindromo = text == text.reversed()
        println("Es palíndromo: $esPalindromo")
    }

    private fun agregarSaludo() {
        println("Ingrese nombres separados por coma:")
        scanner.nextLine() // consumir el salto de línea
        val nombres = scanner.nextLine().split(",").map { it.trim() }
        val saludos = nombres.map { "¡Hola, $it!" }
        println("Saludos: $saludos")
    }

    private fun realizarOperacion() {
        println("Ingrese dos números y la operación (+, -, *, /) separados por espacio:")
        scanner.nextLine() // consumir el salto de línea
        val (a, b, op) = scanner.nextLine().split(" ")
        val resultado = runCatching {
            val num1 = a.toInt()
            val num2 = b.toInt()
            when (op) {
                "+" -> num1 + num2
                "-" -> num1 - num2
                "*" -> num1 * num2
                "/" -> if (num2 != 0) num1 / num2 else throw ArithmeticException("División por cero")
                else -> throw IllegalArgumentException("Operación no válida")
            }
        }.getOrElse {
            println("Operación no válida: ${it.message}")
            return
        }
        println("El resultado de la operación es: $resultado")
    }

    private fun mapearPersonas() {
        println("Ingrese personas (nombre,edad,género) separados por comas :")
        scanner.nextLine() // consumir el salto de línea
        val estudiantes = scanner.nextLine()
            .split(";")
            .mapNotNull {
                val parts = it.split(",").map(String::trim)
                if (parts.size == 3) {
                    val (nombre, edadStr, genero) = parts
                    val edad = edadStr.toIntOrNull()
                    if (edad != null) {
                        Person(nombre, edad, genero)
                    } else {
                        println("Edad no válida: $edadStr")
                        null
                    }
                } else {
                    println("Entrada no válida: $it")
                    null
                }
            }
            .map { Student(it.name, it.age, it.gender, "12345") }
        estudiantes.forEach { println("El Estudiante ${it.name} tiene ${it.age} años") }
    }
}

data class Person(val name: String, val age: Int, val gender: String)
data class Student(val name: String, val age: Int, val gender: String, val studentId: String)

fun main() {
    val menu = Menu()
    menu.start()
}



