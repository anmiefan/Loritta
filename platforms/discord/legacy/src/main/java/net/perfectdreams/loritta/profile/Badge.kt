package net.perfectdreams.loritta.profile

import com.mrpowergamerbr.loritta.dao.Profile
import net.dv8tion.jda.api.entities.User

abstract class Badge(val badgeFileName: String, val priority: Int) {
	abstract fun checkIfUserDeservesBadge(user: User, profile: Profile, mutualGuilds: Set<Long>): Boolean
}