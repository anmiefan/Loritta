package net.perfectdreams.loritta.plugin.profiles.designs

import com.mrpowergamerbr.loritta.Loritta
import com.mrpowergamerbr.loritta.dao.Profile
import com.mrpowergamerbr.loritta.profile.ProfileCreator
import com.mrpowergamerbr.loritta.profile.ProfileUserInfoData
import com.mrpowergamerbr.loritta.utils.Constants
import com.mrpowergamerbr.loritta.utils.DateUtils
import com.mrpowergamerbr.loritta.utils.ImageUtils
import com.mrpowergamerbr.loritta.utils.LorittaUtils
import com.mrpowergamerbr.loritta.utils.drawText
import com.mrpowergamerbr.loritta.utils.enableFontAntiAliasing
import com.mrpowergamerbr.loritta.utils.loritta
import com.mrpowergamerbr.loritta.utils.lorittaShards
import com.mrpowergamerbr.loritta.utils.makeRoundedCorners
import com.mrpowergamerbr.loritta.utils.toBufferedImage
import net.dv8tion.jda.api.entities.Guild
import net.perfectdreams.loritta.common.locale.BaseLocale
import net.perfectdreams.loritta.profile.ProfileUtils
import net.perfectdreams.loritta.utils.extensions.readImage
import java.awt.Color
import java.awt.Font
import java.awt.Graphics
import java.awt.Image
import java.awt.Rectangle
import java.awt.image.BufferedImage
import java.io.File
import java.io.FileInputStream

class Christmas2019ProfileCreator : ProfileCreator("christmas2019") {
	override suspend fun create(sender: ProfileUserInfoData, user: ProfileUserInfoData, userProfile: Profile, guild: Guild?, badges: List<BufferedImage>, locale: BaseLocale, background: BufferedImage, aboutMe: String): BufferedImage {
		val whitneySemiBold = FileInputStream(File(Loritta.ASSETS + "whitney-semibold.ttf")).use {
			Font.createFont(Font.TRUETYPE_FONT, it)
		}
		val whitneyBold = FileInputStream(File(Loritta.ASSETS + "whitney-bold.ttf")).use {
			Font.createFont(Font.TRUETYPE_FONT, it)
		}

		val whitneyMedium22 = whitneySemiBold.deriveFont(22f)
		val whitneyBold16 = whitneyBold.deriveFont(16f)
		val whitneyMedium16 = whitneySemiBold.deriveFont(16f)
		val whitneyBold12 = whitneyBold.deriveFont(12f)
		val oswaldRegular50 = Constants.OSWALD_REGULAR
				.deriveFont(50F)
		val oswaldRegular42 = Constants.OSWALD_REGULAR
				.deriveFont(42F)

		val avatar = LorittaUtils.downloadImage(user.avatarUrl)!!.getScaledInstance(150, 150, BufferedImage.SCALE_SMOOTH)
		val marrySection = readImage(File(Loritta.ASSETS, "profile/christmas_2019/marry.png"))

		val marriage = loritta.newSuspendedTransaction { userProfile.marriage }

		val marriedWithId = if (marriage?.user1 == user.id) {
			marriage.user2
		} else {
			marriage?.user1
		}

		val marriedWith = if (marriedWithId != null) { lorittaShards.retrieveUserInfoById(marriedWithId.toLong()) } else { null }

		val reputations = ProfileUtils.getReputationCount(user)
		val globalPosition = ProfileUtils.getGlobalExperiencePosition(userProfile)

		var xpLocal: Long? = null
		var localPosition: Long? = null

		if (guild != null) {
			val localProfile = ProfileUtils.getLocalProfile(guild, user)

			localPosition = ProfileUtils.getLocalExperiencePosition(localProfile)

			xpLocal = localProfile?.xp
		}

		val globalEconomyPosition = ProfileUtils.getGlobalEconomyPosition(userProfile)

		val resizedBadges = badges.map { it.getScaledInstance(30, 30, BufferedImage.SCALE_SMOOTH).toBufferedImage() }

		val profileWrapper = readImage(File(Loritta.ASSETS, "profile/christmas_2019/perfil_padoru.png"))

		val base = BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB) // Base
		val graphics = base.graphics.enableFontAntiAliasing()

		graphics.drawImage(background.getScaledInstance(800, 600, BufferedImage.SCALE_SMOOTH), 0, 0, null)

		graphics.color = Color.BLACK
		graphics.drawImage(profileWrapper, 0, 0, null)
		drawAvatar(avatar, graphics)

		graphics.font = oswaldRegular50
		graphics.drawText(user.name, 162, 480) // Nome do usuário
		graphics.font = oswaldRegular42

		drawReputations(user, graphics, reputations)

		drawBadges(resizedBadges, graphics)

		graphics.font = whitneyBold16
		val biggestStrWidth = drawUserInfo(user, userProfile, guild, graphics, globalPosition, localPosition, xpLocal, globalEconomyPosition)

		graphics.font = whitneyMedium22

		ImageUtils.drawTextWrapSpaces(aboutMe, 162, 504, 773 - biggestStrWidth - 4, 600, graphics.fontMetrics, graphics)

		if (marriage != null) {
			graphics.drawImage(marrySection, 0, 0, null)

			if (marriedWith != null) {
				graphics.color = Color.WHITE
				graphics.font = whitneyBold12
				ImageUtils.drawCenteredString(graphics, locale["profile.marriedWith"], Rectangle(635, 0, 165, 14), whitneyBold12)
				graphics.font = whitneyMedium16
				ImageUtils.drawCenteredString(graphics, marriedWith.name + "#" + marriedWith.discriminator, Rectangle(635, 16, 165, 18), whitneyMedium16)
				graphics.font = whitneyBold12
				ImageUtils.drawCenteredString(graphics, DateUtils.formatDateDiff(marriage.marriedSince, System.currentTimeMillis(), locale), Rectangle(635, 16 + 18, 165, 14), whitneyBold12)
			}
		}

		return base
	}

	fun drawAvatar(avatar: Image, graphics: Graphics) {
		graphics.drawImage(
				avatar.toBufferedImage()
						.makeRoundedCorners(999),
				7,
				443,
				null
		)
	}

	fun drawBadges(badges: List<BufferedImage>, graphics: Graphics) {
		val treeOrnamentsCoordinates = listOf(
				Pair(13, 80),
				Pair(58, 120),
				Pair(3, 193),
				Pair(67, 210),
				Pair(75, 256),
				Pair(33, 271),
				Pair(131, 290),
				Pair(88, 308),
				Pair(39, 344),
				Pair(127, 344),
				Pair(73, 374),
				Pair(157, 389),
				Pair(116, 402)
		)

		for ((idx, badge) in badges.withIndex()) {
			val coordinate = treeOrnamentsCoordinates.getOrNull(idx) ?: break
			graphics.drawImage(badge, coordinate.first, coordinate.second, null)
		}
	}

	fun drawReputations(user: ProfileUserInfoData, graphics: Graphics, reputations: Long) {
		val font = graphics.font

		ImageUtils.drawCenteredString(graphics, "$reputations reps", Rectangle(634, 454, 166, 52), font)
	}

	fun drawUserInfo(user: ProfileUserInfoData, userProfile: Profile, guild: Guild?, graphics: Graphics, globalPosition: Long?, localPosition: Long?, xpLocal: Long?, globalEconomyPosition: Long?): Int {
		val userInfo = mutableListOf<String>()
		userInfo.add("Global")

		if (globalPosition != null)
			userInfo.add("#$globalPosition / ${userProfile.xp} XP")
		else
			userInfo.add("${userProfile.xp} XP")

		// Iremos remover os emojis do nome da guild, já que ele não calcula direito no stringWidth
		if (guild != null) {
			userInfo.add(guild.name.replace(Constants.EMOJI_PATTERN.toRegex(), ""))
			if (xpLocal != null) {
				if (localPosition != null) {
					userInfo.add("#$localPosition / $xpLocal XP")
				} else {
					userInfo.add("$xpLocal XP")
				}
			} else {
				userInfo.add("???")
			}
		}

		userInfo.add("Sonhos")
		if (globalEconomyPosition != null)
			userInfo.add("#$globalEconomyPosition / ${userProfile.money}")
		else
			userInfo.add("${userProfile.money}")

		val biggestStrWidth = graphics.fontMetrics.stringWidth(userInfo.maxByOrNull { graphics.fontMetrics.stringWidth(it) }!!)

		var y = 515
		for (line in userInfo) {
			graphics.drawText(line, 773 - biggestStrWidth - 2, y)
			y += 16
		}

		return biggestStrWidth
	}
}