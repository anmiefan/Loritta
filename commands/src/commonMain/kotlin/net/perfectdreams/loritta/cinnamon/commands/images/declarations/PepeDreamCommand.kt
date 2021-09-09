package net.perfectdreams.loritta.cinnamon.commands.images.declarations

import net.perfectdreams.loritta.cinnamon.commands.images.PepeDreamExecutor
import net.perfectdreams.loritta.cinnamon.common.commands.CommandCategory
import net.perfectdreams.loritta.cinnamon.common.commands.declarations.CommandDeclaration
import net.perfectdreams.loritta.cinnamon.i18n.I18nKeysData

object PepeDreamCommand : CommandDeclaration {
    val I18N_PREFIX = I18nKeysData.Commands.Command.Pepedream

    override fun declaration() = command(listOf("pepedream", "sonhopepe", "pepesonho"), CommandCategory.IMAGES, I18N_PREFIX.Description) {
        executor = PepeDreamExecutor
    }
}