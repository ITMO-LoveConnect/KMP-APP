package ru.connect.core.cache

import co.touchlab.stately.collections.ConcurrentMutableMap
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull

open class InMemoryLocalDataSource<Request : Any, Response> :
    LocalDataSource<Request, CacheEntry<Request, Response>> {

    private val keys: MutableSharedFlow<Set<Request>> = MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    private val cache = ConcurrentMutableMap<Request, MutableStateFlow<CacheEntry<Request, Response>?>>()

    override fun observe(key: Request): StateFlow<CacheEntry<Request, Response>?> =
        cache.getOrPut(key) {
            MutableStateFlow(null)
        }

    override suspend fun get(key: Request): CacheEntry<Request, Response>? =
        cache[key]?.firstOrNull()

    override suspend fun set(key: Request, value: CacheEntry<Request, Response>) {
        cache.getOrPut(key) {
            MutableStateFlow(null)
        }.value = value
        keys.emit(cache.keys)
    }

    override suspend fun remove(key: Request) {
        cache[key]?.value = null
    }

    override suspend fun clear() {
        cache.clear()
        keys.emit(emptySet())
    }

    override suspend fun clear(key: Request) {
        cache.remove(key)
        keys.emit(keys.first().filter { it != key }.toSet())
    }

    override fun observeKeys(): Flow<Set<Request>> = keys

    override suspend fun getKeys(): Set<Request> = keys.first()
}
