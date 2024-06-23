package org.kengine.utility.io

/**
 * Assists in loading resources from the Application's resources directory.
 */
object ResourceReader {
    fun readToByteArray(name: String): ByteArray {
        return javaClass.getResourceAsStream(name)!!.readAllBytes()
    }

    fun readToString(name: String): String {
        return javaClass.getResourceAsStream(name)!!.reader().readText()
    }
}