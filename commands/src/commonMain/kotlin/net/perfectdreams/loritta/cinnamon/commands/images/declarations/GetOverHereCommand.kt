package net.perfectdreams.loritta.cinnamon.commands.images.declarations

import net.perfectdreams.loritta.cinnamon.commands.images.GetOverHereExecutor
import net.perfectdreams.loritta.cinnamon.common.commands.CommandCategory
import net.perfectdreams.loritta.cinnamon.common.commands.declarations.CommandDeclaration
import net.perfectdreams.loritta.cinnamon.i18n.I18nKeysData

object GetOverHereCommand : CommandDeclaration {
    val I18N_PREFIX = I18nKeysData.Commands.Command.Getoverhere

    override fun declaration() = command(listOf("getoverhere"), CommandCategory.IMAGES, I18N_PREFIX.Description) {
        executor = GetOverHereExecutor
    }
}