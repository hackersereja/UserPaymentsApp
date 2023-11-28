package ru.sergean.userpaymentsapp.presentation.container

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.sergean.userpaymentsapp.R
import ru.sergean.userpaymentsapp.databinding.ActivityContainerBinding
import kotlin.properties.Delegates

@AndroidEntryPoint
class ContainerActivity : AppCompatActivity() {

    private var binding: ActivityContainerBinding by Delegates.notNull()
    private var navController: NavController by Delegates.notNull()
    private val containerViewModel: ContainerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val navHost = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment

        navController = navHost.navController

        val appBarConfiguration = AppBarConfiguration(setOf(R.id.paymentFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.toolbar.isVisible = destination.id != R.id.loginFragment
        }

        observeUserLogged()
    }

    private fun observeUserLogged() {
        lifecycleScope.launch {
            containerViewModel.userLogged.flowWithLifecycle(lifecycle).collect { logged ->
                when (logged) {
                    false -> {
                        if (navController.currentDestination?.id == R.id.paymentFragment) {
                            navController.navigate(R.id.action_paymentFragment_to_loginFragment)
                        }
                    }

                    true -> {
                        if (navController.currentDestination?.id == R.id.loginFragment) {
                            navController.navigate(R.id.action_loginFragment_to_paymentFragment)
                        }
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_log_out -> {
                containerViewModel.logout()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
