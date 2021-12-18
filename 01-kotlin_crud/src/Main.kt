import java.io.File
import java.io.InputStream
import java.util.*
import kotlin.collections.ArrayList
import kotlin.system.exitProcess


fun main() {
    principalMenu()
}

fun principalMenu(){
    var optionMenu1: Int = 0
    while (optionMenu1 != 3 ) {
        println("\nPrincipal Menu\n1) All Bands")
        println("2) All Songs")
        println("3) EXIT")
        print("\nChoose -> ")
        optionMenu1 = Integer.parseInt(readLine())

        when (optionMenu1) {
            1 -> {
                BandMenu()
            }
            2 -> {

            }
            3 -> {
                exitProcess(0)
            }
        }
    }
}

fun BandMenu(){
    var optionMenu2: Int = 0

    println("\n\nLIST OF BANDS")
    printArray("BandsList","Band","",null)
    println()

    while (optionMenu2 != 6) {
        println("\nBand Menu\n1) Add a new band")
        println("2) More inf. about one band")
        println("3) Update data from a band")
        println("4) Delete a band")
        println("5) Songs")
        println("6) Back to Principal Menu")
        print("\nChoose -> ")
        optionMenu2 = Integer.parseInt(readLine())

        when (optionMenu2){
            1 -> {
                println("Fill the following inf. about the band")
                println("Name of the band")
                var name: String = ""
                name = readLine().toString()
                println("Country")
                var country = ""
                country = readLine().toString()
                println("Year od fundation")
                var year = ""
                year = readLine().toString()
                println("Genre")
                var genre = ""
                genre = readLine().toString()
                println("Name of the guitarit")
                var guitarit = ""
                guitarit = readLine().toString()
                val bandToAdd = CrudBand(name,country,year,genre,guitarit)

                bandToAdd.addBandInfo()

                println("Band added succsfully!!")
                println("\n\nLIST OF BANDS")
                printArray("BandsList","Band","",null)
                println()
            }
            2 -> {
                println("\nUse the number of the band to show more info.")
                print("Choose -> ")
                var bandPosition: Int = Integer.parseInt(readLine())
                printArray("BandsInfo","Band","",bandPosition)
            }
            3 -> {
                println("\nUse the number of the band to update the info.")
                print("Choose -> ")
                var bandPosition: Int = Integer.parseInt(readLine())
                printArray("BandsInfo","Band","",bandPosition)
                println("\n\nFill the following inf. about the band")
                println("Name of the band")
                var name: String = ""
                name = readLine().toString()
                println("Country")
                var country = ""
                country = readLine().toString()
                println("Year od fundation")
                var year = ""
                year = readLine().toString()
                println("Genre")
                var genre = ""
                genre = readLine().toString()
                println("Name of the guitarit")
                var guitarit = ""
                guitarit = readLine().toString()
                val bandToUpdate = CrudBand(name,country,year,genre,guitarit)

                bandToUpdate.updateInfoBand(bandPosition)

                println("\n\nLIST OF BANDS")
                printArray("BandsList","Band","",null)
                println()

            }
            4 -> {
                println("\nUse the number of the band to delete a band info.")
                print("Choose -> ")
                var bandPosition: Int = Integer.parseInt(readLine())
                print("All the songs of [")
                printArray("BandName","Band","",bandPosition)
                print("] will be deleted too")
                println("\nPress [y] or [n]")
                var confirmInput: String = readLine().toString()
                if (confirmInput.equals("y")){
                    val bandToDelete = CrudBand(bandPosition)
                    bandToDelete.deleteBand(bandPosition)
                    println("\n\nLIST OF BANDS")
                    printArray("BandsList","Band","",null)
                    println()
                }else if (confirmInput.equals("n")){
                    println("\n\nLIST OF BANDS")
                    printArray("BandsList","Band","",null)
                    println()
                }
            }
            5 -> {
                println("\nUse the number of the band to see all the songs of it")
                print("Choose -> ")
                var bandPosition: Int = Integer.parseInt(readLine())
                var bandName: String = printArray("BandName","Band","",bandPosition)
                SongsMenu(bandName)

            }
            6 -> {
                principalMenu()
            }
        }

    }
}

fun SongsMenu(bandName: String){
    var optionMenu3: Int = 0

    println("\n\nLIST OF SONGS")
    printArray("SongList","Songs",bandName,null)
    println()

    while (optionMenu3 != 5){
        println("\n"+bandName+" Song's Menu\n1) Add a new song")
        println("2) More inf. about one song")
        println("3) Update data from a song")
        println("4) Delete a song")
        println("5) Back to band's Menu")
        print("\nChoose -> ")
        optionMenu3 = Integer.parseInt(readLine())

        when(optionMenu3){
            1 -> {
                println("Fill the following inf. about the song")
                println("Name of the song")
                var name: String = ""
                name = readLine().toString()
                println("Band")
                var band: String = ""
                band = readLine().toString()
                println("Album")
                var album: String = ""
                album = readLine().toString()
                println("Release year")
                var year : String =""
                year = readLine().toString()
                println("Genre")
                var genre: String = ""
                genre = readLine().toString()
                println("Duration")
                var duration: String = ""
                duration = readLine().toString()
                val songToAdd = CrudSong(name,band,album,year,genre,duration)

                songToAdd.addSong()

                println("Song added succsfully!!")
                println("\n\nLIST OF SONGS")
                printArray("SongList","Songs",bandName,null)
                println()
            }
            2 -> {
                println("\nUse the number of the song to show more info.")
                print("Choose -> ")
                var songPosition: Int = Integer.parseInt(readLine())
                printArray("SongInfo","Songs",bandName,songPosition)
            }
            3 -> {
                println("\nUse the number of the song to show more info.")
                print("Choose -> ")
                var songPosition: Int = Integer.parseInt(readLine())
                printArray("SongInfo","Songs",bandName,songPosition)
                println("Fill the following inf. about the song")
                println("Name of the song")
                var name: String = ""
                name = readLine().toString()
                println("Band")
                var band: String = ""
                band = readLine().toString()
                println("Album")
                var album: String = ""
                album = readLine().toString()
                println("Release year")
                var year : String =""
                year = readLine().toString()
                println("Genre")
                var genre: String = ""
                genre = readLine().toString()
                println("Duration")
                var duration: String = ""
                duration = readLine().toString()
                val songToUpdate = CrudSong(name,band,album,year,genre,duration)

                songToUpdate.updateSong(songPosition)

                println("\n\nLIST OF SONGS")
                printArray("SongList","Songs",bandName,null)
                println()
            }
            4 -> {
                println("\nUse the number of the song to delete it")
                print("Choose -> ")
                var songPosition: Int = Integer.parseInt(readLine())
                print("All the songs of [")
                printArray("SongName","Songs", bandName,songPosition)
                print("] will be deleted too")
                println("\nPress [y] or [n]")
                var confirmInput: String = readLine().toString()
                if (confirmInput.equals("y")){
                    val songToDelete = CrudSong(songPosition)
                    songToDelete.deleteSong(songPosition)
                    println("\n\nLIST OF SONGS")
                    printArray("SongList","Songs",bandName,null)
                    println()
                }else if (confirmInput.equals("n")){
                    println("\n\nLIST OF SONGS")
                    printArray("SongList","Songs",bandName,null)
                    println()
                }
            }
            5 -> {
                BandMenu()
            }
        }

    }


}

abstract class Band(
    protected var bandName: String,
    protected var bandOrigin: String,
    protected var bandYear: String,
    protected var bandGenre: String,
    protected var bandGuitarist: String
){
    init {
        println("Inicializar")
    }
}

class CrudBand(
    name: String,
    origen: String,
    year: String,
    genre: String,
    guitarist: String
) : Band (
    name,
    origen,
    year,
    genre,
    guitarist
){
    init {
        this.bandName
        this.bandOrigin
        this.bandYear
        this.bandGenre
        this.bandGuitarist
    }

    constructor(
        position: Int
    ) : this (
        "","","","",""
    ){

    }

    fun addBandInfo(){
        var existingBandsInfo = ArrayList<String>()
        existingBandsInfo = existingRegister()
        var auxBand: String = bandName+","+bandOrigin+","+bandYear+","+bandGenre+","+bandGuitarist
        existingBandsInfo.add(auxBand)

        val dataBandFile = "C:/Users/Anthony/Downloads/Documents/7_semestre/aplicaciones_moviles/repoGit/movcom-almachi-chasi-anthony-israel/01-kotlin_crud/bandsInfo.txt"
        val myfile = File(dataBandFile)
        myfile.printWriter().use { out ->
            existingBandsInfo.forEach{
                out.println(it)
            }
        }
    }

    fun updateInfoBand(position: Int){
        var existingBandsInfo = ArrayList<String>()
        existingBandsInfo = existingRegister()
        var auxPos = position-1
        var auxBand: String = bandName+","+bandOrigin+","+bandYear+","+bandGenre+","+bandGuitarist
        existingBandsInfo.forEachIndexed { index: Int, actualValue: String ->
            if (index == auxPos){
                existingBandsInfo[index] = auxBand
            }
        }
        val dataBandFile = "C:/Users/Anthony/Downloads/Documents/7_semestre/aplicaciones_moviles/repoGit/movcom-almachi-chasi-anthony-israel/01-kotlin_crud/bandsInfo.txt"
        val myfile = File(dataBandFile)
        myfile.printWriter().use { out ->
            existingBandsInfo.forEach{
                out.println(it)
            }
        }
    }

    fun deleteBand(position: Int){
        var existingBandsInfo = ArrayList<String>()
        var existingBandsInfoAux = ArrayList<String>()
        existingBandsInfo = existingRegister()
        var auxPos = position-1
        existingBandsInfo.forEachIndexed { index: Int, actualValue: String ->
            if (index != auxPos){
                existingBandsInfoAux.add(existingBandsInfo[index])
            }
        }

        val dataBandFile = "C:/Users/Anthony/Downloads/Documents/7_semestre/aplicaciones_moviles/repoGit/movcom-almachi-chasi-anthony-israel/01-kotlin_crud/bandsInfo.txt"
        val myfile = File(dataBandFile)
        myfile.printWriter().use { out ->
            existingBandsInfoAux.forEach{
                out.println(it)
            }
        }
    }


    companion object{

        var existingSongs = ArrayList<String>()

        fun existingRegister () : ArrayList<String> {
            var existingBandsList = ArrayList<String>()
            val inputStream: InputStream = File("C:/Users/Anthony/Downloads/Documents/7_semestre/aplicaciones_moviles/repoGit/movcom-almachi-chasi-anthony-israel/01-kotlin_crud/bandsInfo.txt").inputStream()
            val lineList = mutableListOf<String>()
            inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it)} }
            lineList.forEach{
                existingBandsList.add(it)
            }
            return existingBandsList
        }
    }
}

abstract class Song(
    protected var songName: String,
    protected var songBand: String,
    protected var songAlbum: String,
    protected var songYear: String,
    protected var songGenre: String,
    protected var songDuration: String
){
    init {
        println("Inicializar")
    }
}
class CrudSong(
    name: String,
    band: String,
    album: String,
    year: String,
    genre: String,
    duration: String
) : Song (
    name,
    band,
    album,
    year,
    genre,
    duration
){
    init {
        this.songName
        this.songBand
        this.songAlbum
        this.songYear
        this.songGenre
        this.songDuration
    }

    constructor(
        position: Int
    ) : this (
        "","","","","",""
    ){

    }

    fun addSong(){
        var existingSongsInfo = ArrayList<String>()
        existingSongsInfo = existingRegisterSongs()
        var auxSong: String = songName+","+songBand+","+songAlbum+","+songYear+","+songGenre+","+songDuration
        existingSongsInfo.add(auxSong)

        val dataSongFile = "C:/Users/Anthony/Downloads/Documents/7_semestre/aplicaciones_moviles/repoGit/movcom-almachi-chasi-anthony-israel/01-kotlin_crud/songs.txt"
        val myfile = File(dataSongFile)
        myfile.printWriter().use { out ->
            existingSongsInfo.forEach{
                out.println(it)
            }
        }
    }

    fun updateSong(position: Int){
        var existingSongsInfo = ArrayList<String>()
        existingSongsInfo = existingRegisterSongs()
        var auxPos = position-1
        var auxSong: String = songName+","+songBand+","+songAlbum+","+songYear+","+songGenre+","+songDuration
        existingSongsInfo.forEachIndexed { index: Int, actualValue: String ->
            if (index == auxPos){
                existingSongsInfo[index] = auxSong
            }
        }
        val dataSongile = "C:/Users/Anthony/Downloads/Documents/7_semestre/aplicaciones_moviles/repoGit/movcom-almachi-chasi-anthony-israel/01-kotlin_crud/songs.txt"
        val myfile = File(dataSongile)
        myfile.printWriter().use { out ->
            existingSongsInfo.forEach{
                out.println(it)
            }
        }
    }

    fun deleteSong(position: Int){
        var existingSongsInfo = ArrayList<String>()
        var existingSongsInfoAux = ArrayList<String>()
        existingSongsInfo = existingRegisterSongs()
        var auxPos = position-1
        existingSongsInfo.forEachIndexed { index: Int, actualValue: String ->
            if (index != auxPos){
                existingSongsInfoAux.add(existingSongsInfo[index])
            }
        }

        val dataSongFile = "C:/Users/Anthony/Downloads/Documents/7_semestre/aplicaciones_moviles/repoGit/movcom-almachi-chasi-anthony-israel/01-kotlin_crud/songs.txt"
        val myfile = File(dataSongFile)
        myfile.printWriter().use { out ->
            existingSongsInfoAux.forEach{
                out.println(it)
            }
        }
    }


    companion object{

        fun existingRegisterSongs () : ArrayList<String> {
            var existingSongsList = ArrayList<String>()
            val inputStream: InputStream = File("C:/Users/Anthony/Downloads/Documents/7_semestre/aplicaciones_moviles/repoGit/movcom-almachi-chasi-anthony-israel/01-kotlin_crud/songs.txt").inputStream()
            val lineList = mutableListOf<String>()
            inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it)} }
            lineList.forEach{
                existingSongsList.add(it)
            }
            return existingSongsList
        }
    }
}


fun printArray(whatToPrint: String, entity: String, songBand : String, position: Int?) : String {
    var returnValue = ""
    var phatFile = "C:/Users/Anthony/Downloads/Documents/7_semestre/aplicaciones_moviles/repoGit/movcom-almachi-chasi-anthony-israel/01-kotlin_crud/"
    if (entity.equals("Band")){
        phatFile = phatFile.plus("bandsInfo.txt")
    }else if (entity.equals("Songs")){
        phatFile = phatFile.plus("songs.txt")
    }
    var inputStream: InputStream = File(phatFile).inputStream()
    val lineList = mutableListOf<String>()
    var auxPos = position?.minus(1)

    inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it)} }
    if (position != null && songBand.equals("")){
        lineList.forEachIndexed { index: Int, actualValue: String ->
            if (auxPos == index){
                returnValue = tokenizer(index,actualValue,whatToPrint,"")
            }
        }
    }else if(!songBand.equals("") && position == null){
        lineList.forEachIndexed { index: Int, actualValue: String ->
            tokenizer(index,actualValue,whatToPrint,songBand)
        }
    }else if(!songBand.equals("") && position != null){
        lineList.forEachIndexed { index: Int, actualValue: String ->
            if (auxPos == index){
                returnValue = tokenizer(index,actualValue,whatToPrint,songBand)
            }
        }
    } else{
        lineList.forEachIndexed { index: Int, actualValue: String ->
            tokenizer(index,actualValue,whatToPrint,"")
        }
    }
    return  returnValue

}

fun tokenizer(index:Int, bandInfo : String, whatToPrint: String, songBand: String) : String{
    var returnValue: String = ""
    var listBand = ArrayList<String>()
    val st = StringTokenizer(bandInfo,",")
    val auxIndex = index+1
    while (st.hasMoreTokens()) {
        listBand.add(st.nextToken())
    }
    if (whatToPrint.equals("BandsInfo")){
        print(auxIndex)
        print("\t")
        listBand.forEach{
            print(it+"\t")
        }
        println("\n")
    }
    else if (whatToPrint.equals("BandsList")) {
        print(auxIndex)
        print("\t")
        print(listBand[0])
        println()
    }else if (whatToPrint.equals("BandName")) {
        returnValue = listBand[0]
        print(listBand[0])
    }else if (whatToPrint.equals("SongInfo")){
        if (listBand[1].equals(songBand)) {
            print(auxIndex)
            print("\t")
            listBand.forEach {
                print(it + "\t")
            }
            println("\n")
        }
    } else if (whatToPrint.equals("SongList")){
        if (listBand[1].equals(songBand)){
            print(auxIndex)
            print("\t")
            print(listBand[0])
            println()
        }
    }else if (whatToPrint.equals("SongName")) {
        if (listBand[1].equals(songBand)){
            returnValue = listBand[0]
            print(listBand[0])
        }
    }
    return returnValue
}


