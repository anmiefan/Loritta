package net.perfectdreams.loritta.cinnamon.platform.commands.`fun`.declarations

import net.perfectdreams.loritta.cinnamon.platform.commands.`fun`.CancelledExecutor
import net.perfectdreams.loritta.cinnamon.platform.commands.CommandCategory
import net.perfectdreams.loritta.cinnamon.platform.commands.declarations.CommandDeclaration
import net.perfectdreams.loritta.cinnamon.i18n.I18nKeysData

object CancelledCommand : CommandDeclaration {
    val I18N_PREFIX = I18nKeysData.Commands.Command.Cancelled

    override fun declaration() = command(listOf("cancelled", "cancelado", "cancel", "cancelar"), CommandCategory.FUN, I18N_PREFIX.Description) {
        executor = CancelledExecutor
    }
}