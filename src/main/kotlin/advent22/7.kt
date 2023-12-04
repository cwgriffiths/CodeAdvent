package advent22

import java.nio.file.Files
import java.nio.file.Path
import java.util.*

enum class Command {
    CD{
        override fun execute(dir: Directory, args: String): Directory {
            return when(args){
                ".." -> dir.parent ?: Directory.root
                "/" -> Directory.root
                else -> dir.getSubDir(args)
            }
        }
    },
    LS{
        override fun execute(dir: Directory, args: String): Directory {
            println(dir)
            return dir
        }
    };

    abstract fun execute(dir: Directory, args: String) : Directory
}

data class File(val size: Int, val name: String)

data class Directory(val name: String, val files: MutableList<File>, val subDirs: MutableList<Directory>, val parent: Directory?){
    fun addFile(name: String, size: Int) {
        files.add(File(size, name))
    }
    fun addSubDir(name: String) {
        subDirs.add(Directory(name, mutableListOf(), mutableListOf(), this))
    }
    fun getSubDir(name: String): Directory {
        return subDirs.find { it.name == name } ?: throw Exception("advent22.Directory not found")
    }
    fun getSize(): Int {
        return files.sumOf { it.size } + subDirs.sumOf { it.getSize() }
    }

    companion object {
        val root = Directory("root", mutableListOf(), mutableListOf(), null)
    }

    fun getAllSubDirs(): List<Directory> {
        return subDirs + subDirs.flatMap { it.getAllSubDirs() }
    }

    override fun toString(): String {
        return "$name ${getSize()} bytes${subDirs.joinToString("\n\t","\n\t")}${files.joinToString("\n\t","\n\t")}\n"
    }

}

var currentDir : Directory = Directory.root

fun main(){
    val tree = Files.readString(Path.of("2022/07.txt"))
    val sc = Scanner(tree)

    while (sc.hasNextLine()) {
        val line = sc.nextLine()
        when(line.substring(0,line.indexOf(" "))){
            "dir" -> {
                currentDir.addSubDir(line.split(" ")[1])
            }
            "$" -> {
                val s = (line.substring(line.indexOf(" ")).trim())
                if(s.contains(" ")) {
                    val (cmd, args) = s.split(" ")
                    currentDir = Command.valueOf(cmd.uppercase()).execute(currentDir, args)
                }
            }
            else -> {
                currentDir.addFile(line.split(" ")[1], line.split(" ")[0].toInt())
            }
        }
    }
    //println(advent22.Directory.root)
    val target = 30000000 - 21618835
    val directories = Directory.root.getAllSubDirs().filter { it.getSize() > target }.sortedBy { it.getSize() }
    println(directories.first().getSize())

}