package org.akrck02.skyleriearts.service

object CloudSyncService : ICloudSyncService {

    const val DESTINATION_PATH = "/home/dev/temp"

    override fun sync() {
//        val pwd = Paths.get("").toAbsolutePath().toString()
//
//        val resources = File("$pwd/resources")
//        val destinationResources = File("$DESTINATION_PATH/resources")
//        destinationResources.deleteRecursively()
//        resources.copyRecursively(destinationResources, true)
//
//        val dataJson = File("$pwd/${FileProcessor.GALLERY_FILE_PATH}")
//        dataJson.copyRecursively(File("$DESTINATION_PATH/resources/data/images.json"), true)
    }
}