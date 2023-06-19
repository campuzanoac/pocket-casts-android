package au.com.shiftyjelly.pocketcasts.featureflag.providers

import android.content.Context
import au.com.shiftyjelly.pocketcasts.featureflag.Feature
import au.com.shiftyjelly.pocketcasts.featureflag.ModifiableFeatureFlagProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Used to override values for feature flags at runtime in debug builds.
 * See StoreFeatureFlagProvider to set feature flag values for release builds.
 */
@Singleton
class PreferencesFeatureFlagProvider @Inject constructor(
    @ApplicationContext context: Context,
) : ModifiableFeatureFlagProvider {
    private val preferences = context.featureFlagsSharedPrefs()

    override fun isEnabled(feature: Feature) =
        preferences.getBoolean(feature.key, feature.defaultValue)

    override fun setEnabled(feature: Feature, enabled: Boolean) =
        preferences.edit().putBoolean(feature.key, enabled).apply()

    private fun Context.featureFlagsSharedPrefs() =
        this.getSharedPreferences("POCKETCASTS_FEATURE_FLAGS", Context.MODE_PRIVATE)
}
