package com.example.oauthjpapostgresql.entities

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.provider.ClientDetails
import java.util.*

data class ClientEntity(

    private val clientId : UUID,

    private val clientSecret : String,

    private val isSecretRequired : Boolean,

    private val scope : Collection<String>,

    private val authorizedGrantTypes : Collection<String>,

    private val resourceIds : Collection<String>,

    private val accessTokenValiditySeconds : Int,

    private val refreshTokenValiditySeconds : Int

) : ClientDetails {

    /**
     * The client id.
     *
     * @return The client id.
     */
    override fun getClientId() : String = this.clientSecret

    /**
     * The client secret. Ignored if the [secret isn&#39;t required][.isSecretRequired].
     *
     * @return The client secret.
     */
    override fun getClientSecret() : String = this.clientSecret

    /**
     * Whether a secret is required to authenticate this client.
     *
     * @return Whether a secret is required to authenticate this client.
     */
    override fun isSecretRequired() : Boolean = this.isSecretRequired

    /**
     * The resources that this client can access. Can be ignored by callers if empty.
     *
     * @return The resources of this client.
     */
    override fun getResourceIds() : MutableSet<String> = this.resourceIds.toMutableSet()

    /**
     * The access token validity period for this client.
     * Null if not set explicitly (implementations might use that fact to provide a default value for instance).
     *
     * @return the access token validity period
     */
    override fun getAccessTokenValiditySeconds() : Int = this.accessTokenValiditySeconds

    /**
     * The refresh token validity period for this client.
     * Null for default value set by token service, and zero or negative for non-expiring tokens.
     *
     * @return the refresh token validity period
     */
    override fun getRefreshTokenValiditySeconds() : Int = this.refreshTokenValiditySeconds

    /**
     * Additional information for this client, not needed by the vanilla OAuth protocol but might be useful,
     * for example, for storing descriptive information.
     *
     * @return a map of additional information
     */
    override fun getAdditionalInformation() : MutableMap<String, Any> {
        TODO("Not yet implemented")
    }

    /**
     * Test whether client needs user approval for a particular scope.
     *
     * @param scope the scope to consider
     * @return true if this client does not need user approval
     */
    override fun isAutoApprove(scope : String?) : Boolean {
        TODO("Not yet implemented")
    }

    /**
     * Returns the authorities that are granted to the OAuth client. Cannot return `null`.
     * Note that these are NOT the authorities that are granted to the user with an authorized access token.
     * Instead, these authorities are inherent to the client itself.
     *
     * @return the authorities (never `null`)
     */
    override fun getAuthorities() : MutableCollection<GrantedAuthority> {
        TODO("Not yet implemented")
    }

    /**
     * The pre-defined redirect URI for this client to use during the "authorization_code" access grant.
     * See OAuth spec section 4.1.1.
     *
     * @return The pre-defined redirect URI for this client.
     */
    override fun getRegisteredRedirectUri() : MutableSet<String> {
        TODO("Not yet implemented")
    }

    /**
     * Whether this client is limited to a specific scope.
     * If false, the scope of the authentication request will be ignored.
     *
     * @return Whether this client is limited to a specific scope.
     */
    override fun isScoped() : Boolean {
        TODO("Not yet implemented")
    }

    /**
     * The scope of this client. Empty if the client isn't scoped.
     *
     * @return The scope of this client.
     */
    override fun getScope() : MutableSet<String> = this.scope.toMutableSet()

    /**
     * The grant types for which this client is authorized.
     *
     * @return The grant types for which this client is authorized.
     */
    override fun getAuthorizedGrantTypes() : MutableSet<String> = this.authorizedGrantTypes.toMutableSet()
}