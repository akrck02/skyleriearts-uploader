package org.akrck02.skyleriearts.exception

/**
 * Error codes for the application
 */
enum class ErrorCode(value: Int) {
    Unknown(0),
    FilePathIsDirectory(1),
    FileDoesNotExist(2),
    CannotDeleteFile(3),
    InvalidFileExtension(4)
}
