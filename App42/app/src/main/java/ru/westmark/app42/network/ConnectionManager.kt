package ru.westmark.app42.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConnectionManager @Inject constructor(
    context: Context
) {
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    private val networkListenerMutableStateFlow =
        MutableStateFlow(isNetworkAvailable(connectivityManager))

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager?.registerDefaultNetworkCallback(
                object : ConnectivityManager.NetworkCallback() {
                    override fun onAvailable(network: Network) {
                        super.onAvailable(network)
                        networkListenerMutableStateFlow.value = true
                    }

                    override fun onLost(network: Network) {
                        super.onLost(network)
                        networkListenerMutableStateFlow.value = false
                    }
                }
            )
        }
    }

    fun observeNetworkState(): Flow<Boolean> = networkListenerMutableStateFlow
    private fun isNetworkAvailable(connectivityManager: ConnectivityManager?): Boolean {
        connectivityManager?.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return checkNetworkConnection(this, this.activeNetwork)
            } else {
                val networks = this.allNetworks
                networks.forEach { network ->
                    if (checkNetworkConnection(this, network)) return true

                }
            }
        }
        return false
    }

    private fun checkNetworkConnection(
        connectivityManager: ConnectivityManager,
        network: Network?
    ): Boolean {
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}