package com.dormiwww.echodo.domain.repository

import com.dormiwww.echodo.domain.model.Command
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for voice recognition and command processing.
 * Defines the contract for voice-related data operations.
 */
interface VoiceRepository {
    /**
     * Start listening for voice input.
     * @return Flow emitting recognized text as it becomes available.
     */
    fun startListening(): Flow<String>

    /**
     * Stop listening for voice input.
     */
    suspend fun stopListening()

    /**
     * Process recognized text and match it to a command.
     * @param text The recognized voice input.
     * @return Matched command or null if no match found.
     */
    suspend fun processVoiceInput(text: String): Command?

    /**
     * Get the current listening state.
     * @return Flow emitting true when listening, false when not.
     */
    fun isListening(): Flow<Boolean>
}
