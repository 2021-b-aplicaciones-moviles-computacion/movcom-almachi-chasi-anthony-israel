import java.security.ProtectionDomain
import java.util.*
import javax.swing.SwingUtilities
import kotlin.collections.ArrayList

fun main () {
    println("Hola mundo"); // Los puntos y comas no son necesarios
    // Tipo nombre = valor;
    // int edad = 12;

    // Tipos de variables
    // INMUTABLES (val)

    val inmutable: String = "Anthony"
    // inmutable = "Vicente"; // X

    // MUTABLE

    var mutable: String = "Vicente"
    mutable = "Anthony"

    // val > var

    // Sintaxis y Duck Typing
    // Si algo vuela como un pato, suena como un pato, parece un pato
    // entonces es un pato xD
    val ejemploVariable = "Nombre"
    var sueldo: Int = 12
    val estadoCivil: Char = 'S'
    val fechaNacimiento: Date = Date()


    // Condicionales

    if (true) {
        // Verdadero
    } else {
        // Falso
    }

    // Switch Estado -> S -> C -> ::::

    val estadoCivilWhen: String = "S"

    when (estadoCivilWhen) {
        ("S") -> {
            println("Acercarse")
        }
        "C" -> {
            println("Alejarse")
        }
        "UN" -> println("Hablar")
        else -> println("No reconocido")
    }

    val coqueteo = if (estadoCivilWhen == "S") "SI" else "NO"
    // condicion ? bloque-true : bloque-false

    // imprimirNombre("Adrian")
    // calcularSueldo(100.00)
    // calcularSueldo(100.00, 14.00)

    calcularSueldo(100.00, 14.00, 25.00)

    // Named Parameters
    calcularSueldo(
        bonoEspecial = 15.00,
        // tasa = 12.00
        sueldo = 150.00,
    )

    calcularSueldo(
        tasa = 14.00,
        bonoEspecial = 30.00,
        sueldo = 1000.00
    )

    // Tipos de arreglos

    // Arreglos estaticos
    val arreglosEstaticos: Array<Int> = arrayOf(1, 2, 3)
    println(arreglosEstaticos)

    // Arrelgos Dinamicos
    val arregloDinamico: ArrayList<Int> = arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)


    // OPERADORES -> Sirven para los arreglos estaticos y dinamicos

    // FOR EACH -> Unit
    // Iterar un arreglo

    val respuestaForEach: Unit = arreglosEstaticos.forEach { valorActual: Int ->
        println("valor actual: ${valorActual}")
    }
    arreglosEstaticos
        .forEachIndexed { indice: Int, valorActual: Int ->
            println("Valor ${valorActual} Indice: ${indice}")
        }
    println(respuestaForEach)

    // MAP -> Muta el arreglo (Cambia el arrglo)
    // 1) Enviamos el nuevo valor de la iteración
    // 2) Nos devuelve es un NUEVO ARREGLO con los valores modificados

    val respuestaMap: List<Double> = arreglosEstaticos
        .map { valorActual: Int ->
            return@map valorActual.toDouble() + 100.00
        }
    println(respuestaMap)

    val respuestaMapDos = arreglosEstaticos.map { it + 15 }
//      .map { valorActual: Int ->
//          return@map valorActual + 15
//      }
    println(respuestaMapDos)

    // Filter -> FILTREAR EL ARREGLO
    // 1) Devolver una expresión (TRUE o FALSE)
    // 2) Nuevo arreglo filtrado

    val respuestaFilter: List<Int> = arregloDinamico
        .filter { valorActual: Int ->
            val mayoresCinco: Boolean = valorActual > 5 // Expresión Condición
            return@filter mayoresCinco
        }
    val respuestaFilterDos = arregloDinamico.filter { it <= 5 }

    println(respuestaFilter)
    println(respuestaFilterDos)

    // OR AND
    // OR -> ANY (Alguno cumple)
    // AND -> ALL (Todos cumples)

    val respuestaAny: Boolean = arregloDinamico
        .any { valorActual: Int ->
            return@any (valorActual > 5)
        }

    println(respuestaAny) // true

    val respuestaAll: Boolean = arregloDinamico
        .all { valorActual: Int ->
            return@all (valorActual > 5)
        }

    println(respuestaAll) // false

    // REDUCE -> Valor acumulado
    // Valor acumulado = 0 (Siempre 0 en el lenguaje de Kotlin)
    // [1, 2, 3, 4, 5] -> Sumeme todos los valores de arreglo
    // valorIteracion1 = valorEmpieza + 1 = 0 + 1 = 1 -> Iteracipon 1
    // valorIteracion2 = valorIteracion1 + 2 = 1 + 2 = 3 -> Iteracipon 2
    // valorIteracion3 = valorIteracion2 + 3 = 3 + 3 = 6 -> Iteracipon 3
    // valorIteracion4 = valorIteracion3 + 4 = 6 + 4 = 10 -> Iteracipon 4
    // valorIteracion5 = valorIteracion4 + 5 = 10 + 5 = 15 -> Iteracipon 5

    val respuestaReduce: Int =  arregloDinamico
        .reduce{ // acumulado = 0 -> SIEMPRE EMPIEZA EN 0
            acumulado: Int, valorActual: Int ->
            return@reduce (acumulado + valorActual) // -> lofica negocio
        }

    println("\nRespuesta reduce:\n" + respuestaReduce)

    // 100
    // [12, 15, 8, 10]
    val arregloDanio = arrayListOf<Int>(12, 15, 8, 10)
    val respuestaReduceFold = arregloDanio
        .fold(
            100, // acumulado inicial
            { acumulado, valorActualIteración ->
                return@fold acumulado - valorActualIteración

            }
        )
    println("\nRespuesta Reduce Fold\n" + respuestaReduceFold)

    val vidaActual: Double = arregloDinamico
        .map{ it * 2.3 } // aplicamos el buff del *2.3 de daño extra
        .filter { it > 20 } // aplicamos la condición del escudo que evita daños menores a 20
        .fold( 100.00, {acc, i-> acc - i} ) // aplicamos las operaciones anteriores a la vida base 100
        .also { println(it) } // imprimimos el resultado

    println("\nAplicacipo\nValor vida actual -> "+vidaActual)

    // CLASES
    val ejemploUno = Suma(1,2)
    val ejemploDos = Suma(null,2)
    val ejemploTres = Suma(1,null)
    val ejemploCuatro = Suma(null,null)

    println(ejemploUno.sumar())
    println(Suma.historialSumas)
    println(ejemploDos.sumar())
    println(Suma.historialSumas)
    println(ejemploTres.sumar())
    println(Suma.historialSumas)
    println(ejemploCuatro.sumar())
    println(Suma.historialSumas)

    println(Suma.pi)
    println(Suma.historialSumas)


}

//void  imprimirNombre (String nombre){}
fun imprimirNombre(nombre: String): Unit {
    println("Nombre:  ${nombre}")
}

fun calcularSueldo(
    sueldo: Double,
    tasa: Double = 12.00,
    bonoEspecial: Double? = null,
): Double {
    // String -> String?
    // Int -> Int?
    // Date -> Date?
    if (bonoEspecial == null) {
        return sueldo * (100 / tasa)
    } else {
        return sueldo * (100 / tasa) + bonoEspecial
    }
}

abstract  class NumerosJava{
    protected  val numeroUno: Int // Propiedad clase
    private val numeroDos: Int // Propiedad clase

    constructor(
        uno: Int,   // Parametros requeridos
        dos: Int,   // Parametros requeridos
    ) {
        // this.numeroUno = uno
        // this.numeroDos = dos

        numeroUno = uno
        numeroDos = dos
        println("Inicializar")

    }
}

abstract class Numeros(
    // Constructor Primario
    protected var numeroUno: Int, // Propiedad clase
    protected var numeroDos: Int, // Propiedad clase
){
    init { // Bloque inicio del constructor primario
        println("Inicializar")
    }
}
// instancia.numeroUno
// instancia.numeroDos

class Suma(
    // Constructor primario
    uno: Int, // Parametro requerido
    dos: Int, // Parametros requerido
) : Numeros ( // Constructor "papa" (super) En lugar de poner extend se ponen dos puntos
    uno,
    dos
) {
    init { // Es el bloque de codigo del constructor
        this.numeroUno
        this.numeroDos
        // X -> this.uno -> NO EXISTEN
        // X -> this.uno -> NO EXISTEN
    }

    constructor( // Segundo constructor
        uno: Int?, // parametro
        dos: Int   // parametro
    ) : this(   // llamada constructor primario
        if (uno == null) 0 else uno,
        dos
    ) {
        // bloque de código segundo constructor
    }

    constructor( // Tercer constructor
        uno: Int, // parametro
        dos: Int?   // parametro
    ) : this(
        // llamada constructor primario
        uno,
        if (dos == null) 0 else dos,
    ) {
        // bloque de código tercer constructor
    }

    constructor( // Cuarto constructor
        uno: Int?, // parametro
        dos: Int?   // parametro
    ) : this(
        // llamada constructor primario
        if (uno == null) 0 else uno,
        if (dos == null) 0 else dos,
    ) {
        // bloque de código cuarto constructor
    }

    // public fun sumar(): Int {
    fun sumar(): Int {
        // val total: Int = this.numeroUno + this.numeroDos
        val total: Int = numeroUno + numeroDos
        agregarHistorial(total)
        return total
    }

    // Singleton (Hay una sola instancia de estas cosas)
    companion object{ // METODOS Y PROPIEDADES ESTÁTICAS
       val pi = 3.14
       val historialSumas = arrayListOf<Int>()
        fun agregarHistorial (valorNuevaSuma: Int){
            historialSumas.add(valorNuevaSuma)
        }
    }

}
