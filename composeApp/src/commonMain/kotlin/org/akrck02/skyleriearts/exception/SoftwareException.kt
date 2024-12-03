package org.akrck02.skyleriearts.exception

import kotlinx.serialization.Serializable

/**
 * This class represents an exception with code and message
 */
@Serializable
class SoftwareException(
    val code: ErrorCode = ErrorCode.Unknown,
    override val message: String = "Unknown error"
) : Exception(message)

